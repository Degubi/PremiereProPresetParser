package ppropresetparser.internal;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "PremiereData")
public final class PremiereData {

    @XmlElement(name = "BinTreeItem")
    public final BinTreeItem[] binDeclarations;

    @XmlElement(name = "TreeItem")
    public final TreeItem[] presetDeclarations;

    @XmlElement(name = "FilterPresetItem")
    public final FilterPresetItem[] presetItems;

    @XmlElement(name = "FilterPreset")
    public final FilterPreset[] filterPresets;

    @XmlElement(name = "VideoFilterComponent")
    public final VideoFilterComponent[] videoFilterComponents;

    @XmlElement(name = "AudioFilterComponent")
    public final AudioFilterComponent[] audioFilterComponent;

    @XmlElements({
        @XmlElement(name = "PointComponentParam"),
        @XmlElement(name = "VideoComponentParam"),
        @XmlElement(name = "AudioComponentParam"),
        @XmlElement(name = "ArbVideoComponentParam")
    })
    public final ComponentParam[] componentParams;

    private PremiereData() {
        this.binDeclarations = null;
        this.presetDeclarations = null;
        this.presetItems = null;
        this.filterPresets = null;
        this.videoFilterComponents = null;
        this.componentParams = null;
        this.audioFilterComponent = null;
    }
}