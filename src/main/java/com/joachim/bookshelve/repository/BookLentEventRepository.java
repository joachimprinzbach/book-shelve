package com.joachim.bookshelve.repository;

import com.joachim.bookshelve.model.BookLentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookLentEventRepository extends JpaRepository<BookLentEvent, UUID> {
}
