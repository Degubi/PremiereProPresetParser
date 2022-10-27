package ppropresetparser.internal;

import org.eclipse.persistence.oxm.annotations.*;

import jakarta.xml.bind.annotation.*;

public final class FilterPresetItem {

    @XmlAttribute(name = "ObjectID")
    public final String id;

    @XmlPath("FilterPresets/FilterPreset/@ObjectRef")
    public final String[] filterPresetIDs;

    public FilterPresetItem() {
        this.id = null;
        this.filterPresetIDs = null;
    }
}