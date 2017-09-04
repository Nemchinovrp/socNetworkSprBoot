package com.getjavajob.bezfamilnyydg.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "requests_to_friends_tbl")
public class RequestToFriends implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ACC_ID_FROM", nullable = false)
    private Account from;
    @ManyToOne
    @JoinColumn(name = "ACC_ID_TO", nullable = false)
    private Account to;

    public RequestToFriends() {

    }

    public RequestToFriends(Account from, Account to) {
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account accId) {
        this.from = accId;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account accIdFriend) {
        this.to = accIdFriend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestToFriends)) return false;

        RequestToFriends that = (RequestToFriends) o;

        if (!from.equals(that.from)) return false;
        return to.equals(that.to);
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }
}
