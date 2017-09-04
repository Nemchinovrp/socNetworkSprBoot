package com.getjavajob.bezfamilnyydg.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "requests_to_friends_tbl")
public class RequestToFriendsLight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ACC_ID_FROM")
    private int accIdFrom;
    @Column(name = "ACC_ID_TO")
    private int accIdTo;

    public RequestToFriendsLight() {
    }

    public RequestToFriendsLight(int accIdFrom, int accIdTo) {
        this.accIdFrom = accIdFrom;
        this.accIdTo = accIdTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccIdFrom() {
        return accIdFrom;
    }

    public void setAccIdFrom(int acc_id) {
        this.accIdFrom = acc_id;
    }

    public int getaccIdTo() {
        return accIdTo;
    }

    public void setaccIdTo(int accIdTo) {
        this.accIdTo = accIdTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestToFriendsLight)) return false;

        RequestToFriendsLight that = (RequestToFriendsLight) o;

        if (accIdFrom != that.accIdFrom) return false;
        return accIdTo == that.accIdTo;
    }

    @Override
    public int hashCode() {
        int result = accIdFrom;
        result = 31 * result + accIdTo;
        return result;
    }
}
