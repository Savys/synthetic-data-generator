package org.stg.core;

public class Id {

    String id;
    String idType;

    public String getId() {
        return id;
    }
    public String getIdType() {
        return idType;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String toCSVString() {
        return "\""+ id + "\"";
    }

    @Override
    public String toString() {
        return "Id [id=" + id + ", idType=" + idType + "]";
    }

}
