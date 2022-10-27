package ppropresetparser;

public final class Effect {
    public static final String TYPE_AUDIO = "audio";
    public static final String TYPE_VIDEO = "video";

    public final String name;
    public final String type;
    public final EffectParam[] params;

    Effect(String name, String type, EffectParam[] params) {
        this.name = name;
        this.type = type;
        this.params = params;
    }
}