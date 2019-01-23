package com.joachim.bookshelve.model;

import org.springframework.context.ApplicationEvent;

public class BookHasBeenLentEvent extends ApplicationEvent {

    public BookHasBeenLentEvent(Object source) {
        super(source);
    }
}
