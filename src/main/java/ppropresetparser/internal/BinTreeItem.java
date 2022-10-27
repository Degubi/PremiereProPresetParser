package ppropresetparser.internal;

import jakarta.xml.bind.annotation.*;
import org.eclipse.persistence.oxm.annotations.*;

public final class BinTreeItem {

    @XmlAttribute(name = "ObjectID")
    public final String id;

    @XmlPath("TreeItemBase/Name/text()")
    public final String name;

    @XmlPath("Items/Item/@ObjectRef")
    public final String[] itemIDs;

    public BinTreeItem() {
        this.id = null;
        this.name = null;
        this.itemIDs = null;
    }
}