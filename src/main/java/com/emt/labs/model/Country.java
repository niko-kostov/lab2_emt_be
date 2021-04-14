package com.emt.labs.model;

import com.emt.labs.model.baseClass.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Country extends BaseEntity<Long> implements Serializable{

    @Column(name = "country_name")
    private String name;

    @Column(name = "country_continent")
    private String continent;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private Set<Author> author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) && Objects.equals(continent, country.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, continent);
    }
}
