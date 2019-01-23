package com.joachim.bookshelve.web;

import com.joachim.bookshelve.model.Book;
import com.joachim.bookshelve.model.BookLentEvent;
import com.joachim.bookshelve.repository.BookLentEventRepository;
import com.joachim.bookshelve.repository.BookRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("db")
@RequiredArgsConstructor
public class TemporaryDatabaseController {

    private final BookRepository bookRepository;
    private final BookLentEventRepository bookLentEventRepository;

    @GetMapping
    public ResponseEntity<DBResponse> getAllEntries() {
        List<BookLentEvent> allEvents = bookLentEventRepository.findAll();
        List<Book> allBooks = bookRepository.findAll();
        return new ResponseEntity<>(new DBResponse(allEvents, allBooks), HttpStatus.OK);
    }

    @RequiredArgsConstructor
    @Data
    private static class DBResponse {
        private final List<BookLentEvent> allEvents;
        private final List<Book> allBooks;
    }
}
