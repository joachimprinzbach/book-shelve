package com.joachim.bookshelve.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Book extends AbstractAggregateRoot<Book> implements Identifiable<UUID> {

    @Id
    private UUID id;
    private String isbn;
    private boolean isLentOut;
    private LocalDateTime created;

    public static Book of(String isbn, boolean isLentOut) {
        return new Book(UUID.randomUUID(), isbn, isLentOut, LocalDateTime.now());
    }

    public Book lentOut() {
        if (isLentOut) {
            throw new AlreadyLendOutException();
        }
        isLentOut = true;
        registerEvent(new BookHasBeenLentEvent(this));
        return this;
    }
}
