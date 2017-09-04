package com.getjavajob.bezfamilnyydg.models;

import java.io.Serializable;

public class DTOAccount implements Serializable {
    private int id;
    private String name;
    private String surname;
    // it is or string base 64 representation of avatar or path to default avatar if account has not avatar in DB
    private String avatarStringRepresentation;

    public String getAvatarStringRepresentation() {
        return avatarStringRepresentation;
    }

    public void setAvatarStringRepresentation(String avatarStringRepresentation) {
        this.avatarStringRepresentation = avatarStringRepresentation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DTOAccount)) return false;

        DTOAccount that = (DTOAccount) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        return surname.equals(that.surname);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }
}
