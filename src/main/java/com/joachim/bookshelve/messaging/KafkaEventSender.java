package com.joachim.bookshelve.messaging;

import com.joachim.bookshelve.model.Book;
import com.joachim.bookshelve.model.BookHasBeenLentEvent;
import com.joachim.bookshelve.model.BookLentEvent;
import com.joachim.bookshelve.repository.BookLentEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaEventSender {

    private final KafkaTemplate<String, BookLentEvent> kafkaTemplate;
    private final BookLentEventRepository repository;

    @Value("${spring.kafka.topic.book-bought}")
    private String topic;

    public void send(BookLentEvent payload) {
        log.info("sending payload='{}' to topic='{}'", payload, topic);
        BookLentEvent savedEvent = repository.save(payload);
        kafkaTemplate.send(topic, savedEvent);
    }

    @EventListener
    public void onBookHasBeenLentEvent(BookHasBeenLentEvent event) {
        Book source = (Book) event.getSource();
        BookLentEvent bookLentEvent = BookLentEvent.of(source.getIsbn());
        this.send(bookLentEvent);
    }
}
