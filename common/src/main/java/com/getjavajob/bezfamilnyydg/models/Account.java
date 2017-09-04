package com.getjavajob.bezfamilnyydg.models;

import javax.persistence.*;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "account_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = "EMAIL")})
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACC_ID", unique = true, nullable = false)
    private int id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "SURNAME", nullable = false)
    private String surname;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account", fetch = FetchType.LAZY)
    private Set<PersonalPhone> personalPhoneNumbers;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account", fetch = FetchType.LAZY)
    private Set<WorkPhone> workPhoneNumbers;
    @OneToMany(mappedBy = "to", fetch = FetchType.LAZY)
    private Set<RequestToFriends> takenRequestsToFriends;
    @OneToMany(mappedBy = "from", fetch = FetchType.LAZY)
    private Set<RequestToFriends> sentRequestsToFriends;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Friendship> friendships;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles_tbl", joinColumns = {@JoinColumn(name = "ACC_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles;
    @Column(name = "HOME_ADDRESS")
    private String homeAddress;
    @Column(name = "WORK_ADDRESS")
    private String workAddress;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "ICQ")
    private String icq;
    @Column(name = "SKYPE")
    private String skype;
    @Column(name = "ADD_INFO")
    private String additionInfo;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "IMAGE")
    private byte[] avatar;
    @Transient
    private String confirmPassword;

    public Account() {
    }

    public Account(String name, String surname, String middleName, LocalDate dateOfBirth, Set<PersonalPhone> personalPhoneNumbers,
                   Set<WorkPhone> workPhoneNumbers, String homeAddress, String workAddress, String email, String icq,
                   String skype, String additionInfo, String password, byte[] avatar) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.personalPhoneNumbers = personalPhoneNumbers;
        this.workPhoneNumbers = workPhoneNumbers;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.email = email;
        this.icq = icq;
        this.skype = skype;
        this.additionInfo = additionInfo;
        this.password = password;
        this.avatar = avatar;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(Set<Friendship> friendships) {
        this.friendships = friendships;
    }

    public Set<RequestToFriends> getTakenRequestsToFriends() {
        return takenRequestsToFriends;
    }

    public void setTakenRequestsToFriends(Set<RequestToFriends> takenRequestsToFriends) {
        this.takenRequestsToFriends = takenRequestsToFriends;
    }

    public Set<RequestToFriends> getSentRequestsToFriends() {
        return sentRequestsToFriends;
    }

    public void setSentRequestsToFriends(Set<RequestToFriends> sentRequestsToFriends) {
        this.sentRequestsToFriends = sentRequestsToFriends;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<PersonalPhone> getPersonalPhoneNumbers() {
        return personalPhoneNumbers;
    }

    public void setPersonalPhoneNumbers(Set<PersonalPhone> personalPhoneNumbers) {
        this.personalPhoneNumbers = personalPhoneNumbers;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<WorkPhone> getWorkPhoneNumbers() {
        return workPhoneNumbers;
    }

    public void setWorkPhoneNumbers(Set<WorkPhone> workPhoneNumbers) {
        this.workPhoneNumbers = workPhoneNumbers;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getAdditionInfo() {
        return additionInfo;
    }

    public void setAdditionInfo(String additionInfo) {
        this.additionInfo = additionInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", personalPhoneNumber=" + personalPhoneNumbers +
                ", workPhoneNumber=" + workPhoneNumbers +
                ", homeAddress='" + homeAddress + '\'' +
                ", workAddress='" + workAddress + '\'' +
                ", email='" + email + '\'' +
                ", icq='" + icq + '\'' +
                ", skype='" + skype + '\'' +
                ", additionInfo='" + additionInfo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!name.equals(account.name)) return false;
        if (surname != null ? !surname.equals(account.surname) : account.surname != null) return false;
        if (middleName != null ? !middleName.equals(account.middleName) : account.middleName != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(account.dateOfBirth) : account.dateOfBirth != null) return false;
        if (personalPhoneNumbers != null ? !personalPhoneNumbers.equals(account.personalPhoneNumbers) : account.personalPhoneNumbers != null)
            return false;
        if (workPhoneNumbers != null ? !workPhoneNumbers.equals(account.workPhoneNumbers) : account.workPhoneNumbers != null)
            return false;
        if (homeAddress != null ? !homeAddress.equals(account.homeAddress) : account.homeAddress != null) return false;
        if (workAddress != null ? !workAddress.equals(account.workAddress) : account.workAddress != null) return false;
        if (!email.equals(account.email)) return false;
        if (icq != null ? !icq.equals(account.icq) : account.icq != null) return false;
        if (skype != null ? !skype.equals(account.skype) : account.skype != null) return false;
        if (additionInfo != null ? !additionInfo.equals(account.additionInfo) : account.additionInfo != null)
            return false;
        return password != null ? password.equals(account.password) : account.password == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (personalPhoneNumbers != null ? personalPhoneNumbers.hashCode() : 0);
        result = 31 * result + (workPhoneNumbers != null ? workPhoneNumbers.hashCode() : 0);
        result = 31 * result + (homeAddress != null ? homeAddress.hashCode() : 0);
        result = 31 * result + (workAddress != null ? workAddress.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + (icq != null ? icq.hashCode() : 0);
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        result = 31 * result + (additionInfo != null ? additionInfo.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
