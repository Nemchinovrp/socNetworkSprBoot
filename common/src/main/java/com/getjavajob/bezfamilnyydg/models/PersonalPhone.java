package com.getjavajob.bezfamilnyydg.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personal_phone_tbl")
public class PersonalPhone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHONE_ID", unique = true, nullable = false)
    private int id;
    @Column(name = "PHONE")
    private String phone;
    @ManyToOne
    @JoinColumn(name = "ACC_ID", nullable = false)
    private Account account;

    public PersonalPhone() {

    }

    public PersonalPhone(String phone) {
        this.phone = phone;
    }

    public PersonalPhone(String phone, Account account) {
        this.phone = phone;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "PersonalPhone{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", account.Email=" + account.getEmail() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalPhone)) return false;

        PersonalPhone that = (PersonalPhone) o;

        return phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        int result = phone.hashCode();
        result = 31 * result;
        return result;
    }
}
