package ppropresetparser.internal;

import jakarta.xml.bind.annotation.*;

public final class ComponentParam {

    @XmlAttribute(name = "ObjectID")
    public final String id;

    @XmlElement(name = "Name")
    public final String name;

    @XmlElement(name = "StartKeyframe")
    public final String startKeyframe;

    @XmlElement(name = "Keyframes")
    public final String keyframes;

    public ComponentParam() {
        this.id = null;
        this.name = null;
        this.startKeyframe = null;
        this.keyframes = null;
    }
}