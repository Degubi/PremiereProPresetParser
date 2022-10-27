package ppropresetparser.internal;

import jakarta.xml.bind.annotation.*;
import org.eclipse.persistence.oxm.annotations.*;

public final class FilterPreset {

    @XmlAttribute(name = "ObjectID")
    public final String id;

    @XmlPath("Component/@ObjectRef")
    public final String componentObjectID;

    public FilterPreset() {
        this.id = null;
        this.componentObjectID = null;
    }
}