package ppropresetparser.internal;

import jakarta.xml.bind.annotation.*;
import org.eclipse.persistence.oxm.annotations.*;

public final class TreeItem {

    @XmlAttribute(name = "ObjectID")
    public final String id;

    @XmlPath("TreeItemBase/Name/text()")
    public final String presetName;

    @XmlPath("TreeItemBase/Data/@ObjectRef")
    public final String treeItemBaseObjectID;

    private TreeItem() {
        this.id = null;
        this.presetName = null;
        this.treeItemBaseObjectID = null;
    }
}