package ppropresetparser.internal;

import jakarta.xml.bind.annotation.*;
import org.eclipse.persistence.oxm.annotations.*;

public final class AudioFilterComponent {

    @XmlAttribute(name = "ObjectID")
    public final String id;

    @XmlPath("AudioComponent/Component/DisplayName/text()")
    public final String displayName;

    @XmlPath("AudioComponent/Component/Params/Param/@ObjectRef")
    public final String[] componentObjectIDs;

    public AudioFilterComponent() {
        this.id = null;
        this.displayName = null;
        this.componentObjectIDs = null;
    }
}