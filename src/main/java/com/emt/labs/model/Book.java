package com.emt.labs.model;

import com.emt.labs.model.baseClass.BaseEntity;
import com.emt.labs.model.enums.ECategory;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Book extends BaseEntity<Long> {

    @Column(name = "book_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_category")
    private ECategory category;

    @Column(name = "book_available_copies")
    private int availableCopies;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return availableCopies == book.availableCopies && Objects.equals(name, book.name) && category == book.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, category, availableCopies);
    }
}