package com.netcracker.pojo;

import lombok.*;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean object that represents role of {@link User}
 *
 * @author Vadim Ayupov
 * @version 1.0
 */

@NoArgsConstructor
@Log
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private static final long serialVersionUID = -6262324057754548782L;

    @Column(name = "ROLE_NAME")
    @Getter @Setter
    private String roleName;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST)
    @Getter @Setter
    private Set<User> users = new HashSet<>();

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        return roleName != null ? roleName.equals(role.roleName) : role.roleName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
