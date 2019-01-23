package com.joachim.bookshelve.web;

import com.joachim.bookshelve.messaging.KafkaEventSender;
import com.joachim.bookshelve.model.Book;
import com.joachim.bookshelve.model.BookLendingService;
import com.joachim.bookshelve.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/rest/v1/booklendings")
@RequiredArgsConstructor
public class BookLendingRestController {

    private final BookLendingService service;
    private final KafkaEventSender sender;
    private final BookRepository repository;

    @GetMapping
    public void getAllLendingBooks() {
        Book book = repository.save(Book.of("isbn1", false));
        System.out.println(book);
        //sender.send(new BookLentEvent("isbn", "title"));
    }

    // should be post, but atm easier with get
    @GetMapping(path = "/{id}")
    public void lendBook(@PathVariable String id) {
        service.lendBook(UUID.fromString(id));
    }
}
