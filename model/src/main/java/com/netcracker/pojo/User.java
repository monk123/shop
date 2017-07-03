package com.netcracker.pojo;

import lombok.*;
import lombok.extern.java.Log;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean object that represents an User
 *
 * @author Vadim Ayupov
 * @version 1.0
 */

@NoArgsConstructor
@Log
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private static final long serialVersionUID = -9025091900990031369L;

    @Column(name = "USERNAME")
    @Getter @Setter
    private String username;

    @Column(name = "LAST_NAME")
    @Getter @Setter
    private String lastName;

    @Column(name = "EMAIL")
    @Getter @Setter
    private String email;

    @Column(name = "PHONE")
    @Getter @Setter
    private String phone;

    @Column(name = "PASSWORD")
    @Getter @Setter
    private String password;

    @Transient
    @Getter @Setter
    private String confirmPassword;

    @Transient
    @Getter @Setter
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    @Getter @Setter
    private Address address;

    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private Set<Order> orders;

    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(name = "user_has_roles", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @Getter @Setter
    private Set<Role> roles = new HashSet<>();

    public User(String username, String lastName, String email, String phone, String password) {
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
