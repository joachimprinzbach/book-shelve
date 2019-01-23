package com.joachim.bookshelve.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class AlreadyLendOutException extends RuntimeException {
}
