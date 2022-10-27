package ppropresetparser.internal;

import jakarta.xml.bind.annotation.*;
import org.eclipse.persistence.oxm.annotations.*;

public final class VideoFilterComponent {

    @XmlAttribute(name = "ObjectID")
    public final String id;

    @XmlPath("Component/DisplayName/text()")
    public final String displayName;

    @XmlPath("Component/Params/Param/@ObjectRef")
    public final String[] componentObjectIDs;

    public VideoFilterComponent() {
        this.id = null;
        this.displayName = null;
        this.componentObjectIDs = null;
    }
}