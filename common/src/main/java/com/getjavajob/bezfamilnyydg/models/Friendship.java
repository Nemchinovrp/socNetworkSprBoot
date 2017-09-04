package com.getjavajob.bezfamilnyydg.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "friends_tbl")
public class Friendship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ACC_ID", nullable = false)
    private Account account;
    @ManyToOne
    @JoinColumn(name = "ACC_ID_FRIEND", nullable = false)
    private Account friend;

    public Friendship() {
    }

    public Friendship(Account account, Account friend) {
        this.account = account;
        this.friend = friend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getFriend() {
        return friend;
    }

    public void setFriend(Account friend) {
        this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friendship)) return false;

        Friendship that = (Friendship) o;

        if (!account.equals(that.account)) return false;
        return friend.equals(that.friend);
    }

    @Override
    public int hashCode() {
        int result = account.hashCode();
        result = 31 * result + friend.hashCode();
        return result;
    }
}
