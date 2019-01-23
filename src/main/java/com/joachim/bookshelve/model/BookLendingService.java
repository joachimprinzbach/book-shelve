package com.joachim.bookshelve.model;

import com.joachim.bookshelve.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookLendingService {

    private final BookRepository repository;

    public void lendBook(UUID id) {
        Book loadedBook = repository.getOne(id);
        loadedBook.lentOut();
        repository.save(loadedBook);
    }

}
