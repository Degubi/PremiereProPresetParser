package ppropresetparser;

import java.util.*;
import java.util.stream.*;

public final class EffectParam {

    public final String name;
    public final String initialValue;
    public final LinkedHashMap<String, String> valuesPerKeyframeTicks;

    EffectParam(String name, String initialKeyframeText, String keyframesText) {
        this.name = name;
        this.initialValue = initialKeyframeText == null ? null : initialKeyframeText.split(",")[1];
        this.valuesPerKeyframeTicks = keyframesText == null || keyframesText.isBlank()
                                      ? new LinkedHashMap<>()
                                      : Arrays.stream(keyframesText.split(";"))
                                              .map(k -> k.split(","))
                                              .collect(Collectors.toMap(k -> k[0], k -> k[1], (k, l) -> l, LinkedHashMap::new));
    }
}