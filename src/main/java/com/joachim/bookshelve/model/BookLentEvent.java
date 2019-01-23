package com.joachim.bookshelve.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookLentEvent {

    @Id
    private UUID id;
    private String isbn;
    private LocalDateTime created;

    public static BookLentEvent of(String isbn) {
        return new BookLentEvent(UUID.randomUUID(), isbn, LocalDateTime.now());
    }

}
