package org.davideviscogliosi.devchecklist.exception;

import org.springframework.http.HttpStatus;

public class ChecklistException extends RuntimeException {
    private final HttpStatus status;

    public ChecklistException(String message,HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return this.status;
    }
}
