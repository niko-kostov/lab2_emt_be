package com.emt.labs.model;

import com.emt.labs.model.baseClass.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Author extends BaseEntity<Long> implements Serializable {

    @Column(name = "author_name")
    private String name;

    @Column(name = "author_surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) && Objects.equals(surname, author.surname) && Objects.equals(country, author.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, country);
    }
}
