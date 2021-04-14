package com.emt.labs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "lab_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "user_password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_role")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
