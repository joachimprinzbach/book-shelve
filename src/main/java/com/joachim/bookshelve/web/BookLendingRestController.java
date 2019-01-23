package com.joachim.bookshelve.web;

import com.joachim.bookshelve.model.Book;
import com.joachim.bookshelve.model.BookLendingService;
import com.joachim.bookshelve.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("api/rest/v1/booklendings")
@RequiredArgsConstructor
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class BookLendingRestController {

    private final BookLendingService service;
    private final BookRepository repository;

    @GetMapping(produces = {"application/hal+json"})
    public ResponseEntity<?> getAllLendingBooks() {
        Book book = repository.save(Book.of("isbn1", false));
        Resource<Book> resource = new Resource<>(book);
        Link link = linkTo(BookLendingRestController.class).slash(book.getId()).withSelfRel();
        resource.add(link);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    // should be post, but atm easier with get
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> lendBook(@PathVariable String id) {
        service.lendBook(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

}
