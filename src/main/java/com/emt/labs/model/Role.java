package com.emt.labs.model;

import com.emt.labs.model.baseClass.BaseEntity;
import com.emt.labs.model.enums.ERole;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class Role extends BaseEntity<Long> implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private ERole role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role1 = (Role) o;
        return role == role1.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role);
    }
}
