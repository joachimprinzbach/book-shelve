package com.joachim.bookshelve.messaging;

import com.joachim.bookshelve.model.BookLentEvent;
import com.joachim.bookshelve.repository.BookLentEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaEventReceiver {

    private final BookLentEventRepository repository;

    @KafkaListener(topics = "${spring.kafka.topic.book-lent-event}")
    public void receiveBookLendEvent(BookLentEvent bookLentEvent) {
        log.info("received payload='{}'", bookLentEvent);
        System.out.println("Repository currently has: " + repository.findAll().size());
    }
}
