package ppropresetparser;

import jakarta.xml.bind.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import ppropresetparser.internal.*;

public final class Preset {
    private static final jakarta.xml.bind.Unmarshaller xmlParser = createXMLParser();

    public final String path;
    public final Effect[] effects;

    private Preset(String name, String binPath, Effect[] effects) {
        this.path = binPath + '/' + name;
        this.effects = effects;
    }


    public static Preset[] parseFromFile(String filePath) throws JAXBException {
        var presetData = (PremiereData) xmlParser.unmarshal(new File(filePath));

        return Arrays.stream(presetData.presetDeclarations)
                     .map(k -> new Preset(k.presetName, findBinPath(k.id, presetData.binDeclarations).substring(6), findEffectsForPreset(presetData, k.treeItemBaseObjectID)))
                     .toArray(Preset[]::new);
    }

    private static String findBinPath(String presetID, BinTreeItem[] binDeclarations) {
        return Arrays.stream(binDeclarations)
                     .filter(k -> k.itemIDs != null && Arrays.stream(k.itemIDs).anyMatch(presetID::equals))
                     .reduce("", (pathSoFar, element) -> findBinPath(element.id, binDeclarations) + '/' + element.name + pathSoFar, (l, r) -> r);
    }

    private static Effect[] findEffectsForPreset(PremiereData presetData, String id) {
        // TODO: This is because of <Flavor>2</Flavor> thing, idk what that represents.
        var presetDeclaration = findOptionalObjectByID(presetData.presetItems, k -> k.id, id).orElse(null);

        return presetDeclaration == null ? new Effect[0] :
               Arrays.stream(findObjectByID(presetData.presetItems, k -> k.id, id).filterPresetIDs)
                     .map(k -> findObjectByID(presetData.filterPresets, n -> n.id, k).componentObjectID)
                     .map(k -> findFilterComponent(presetData.videoFilterComponents, presetData.audioFilterComponent, k))
                     .map(k -> createEffect(k, presetData.componentParams))
                     .toArray(Effect[]::new);
    }

    private static Effect createEffect(Object filterComponent, ComponentParam[] componentParams) {
        return filterComponent instanceof VideoFilterComponent vfc ? new Effect(vfc.displayName, Effect.TYPE_VIDEO, collectEffectParams(vfc.componentObjectIDs, componentParams)) :
               filterComponent instanceof AudioFilterComponent afc ? new Effect(afc.displayName, Effect.TYPE_AUDIO, collectEffectParams(afc.componentObjectIDs, componentParams)) : null;
    }

    private static Object findFilterComponent(VideoFilterComponent[] videoFilterComponents, AudioFilterComponent[] audioFilterComponents, String id) {
        var optionalVFC = findOptionalObjectByID(videoFilterComponents, k -> k.id, id).orElse(null);

        return optionalVFC != null ? optionalVFC : findObjectByID(audioFilterComponents, k -> k.id, id);
    }

    private static EffectParam[] collectEffectParams(String[] componentObjectRefs, ComponentParam[] componentParams) {
        return Arrays.stream(componentObjectRefs)
                     .map(k -> findObjectByID(componentParams, n -> n.id, k))
                     .map(k -> new EffectParam(k.name, k.startKeyframe, k.keyframes))
                     .toArray(EffectParam[]::new);
    }

    private static<T> Optional<T> findOptionalObjectByID(T[] objects, Function<T, String> idSelector, String id) {
        return Arrays.stream(objects)
                     .filter(k -> idSelector.apply(k).equals(id))
                     .findFirst();
    }

    private static<T> T findObjectByID(T[] objects, Function<T, String> idSelector, String id) {
        return Arrays.stream(objects)
                     .filter(k -> idSelector.apply(k).equals(id))
                     .findFirst()
                     .orElseThrow();
    }

    private static jakarta.xml.bind.Unmarshaller createXMLParser() {
        try {
            return JAXBContextFactory.createContext(new Class[] { PremiereData.class }, null).createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}