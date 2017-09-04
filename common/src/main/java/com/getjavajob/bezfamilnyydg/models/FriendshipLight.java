package com.getjavajob.bezfamilnyydg.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friends_tbl")
public class FriendshipLight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ACC_ID")
    private int accId;
    @Column(name = "ACC_ID_FRIEND")
    private int accIdFriend;

    public FriendshipLight() {
    }

    public FriendshipLight(int accId, int accIdFriend) {
        this.accId = accId;
        this.accIdFriend = accIdFriend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getAccIdFriend() {
        return accIdFriend;
    }

    public void setAccIdFriend(int accIdFriend) {
        this.accIdFriend = accIdFriend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendshipLight)) return false;

        FriendshipLight that = (FriendshipLight) o;

        if (accId != that.accId) return false;
        return accIdFriend == that.accIdFriend;
    }

    @Override
    public int hashCode() {
        int result = accId;
        result = 31 * result + accIdFriend;
        return result;
    }
}
