package com.getjavajob.bezfamilnyydg.models.Utils;

import com.getjavajob.bezfamilnyydg.models.RequestToFriends;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "account_tbl")
public class AccToRequestCame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACC_ID", unique = true, nullable = false)
    private int id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "SURNAME", nullable = false)
    private String surname;
    @Column(name = "IMAGE")
    private byte[] avatar;
    @OneToOne(mappedBy = "to", cascade = CascadeType.ALL, orphanRemoval = true)
    private RequestToFriends requestToFriends;

    public RequestToFriends getRequestToFriends() {
        return requestToFriends;
    }

    public void setRequestToFriends(RequestToFriends requestToFriends) {
        this.requestToFriends = requestToFriends;
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccToRequestCame)) return false;

        AccToRequestCame that = (AccToRequestCame) o;

        if (!name.equals(that.name)) return false;
        if (!surname.equals(that.surname)) return false;
        return Arrays.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }
}
