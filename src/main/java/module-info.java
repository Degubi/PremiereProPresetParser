module degubi.ppropresetparser {
    exports ppropresetparser;

    requires transitive jakarta.xml.bind;
    requires java.sql;

    requires org.eclipse.persistence.core;
    requires org.eclipse.persistence.moxy;

    opens ppropresetparser.internal to org.eclipse.persistence.core, org.eclipse.persistence.moxy;
}