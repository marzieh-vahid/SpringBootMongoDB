package com.marziehvahid.springbootmongodb.exception;

import lombok.NoArgsConstructor;

import java.net.PortUnreachableException;

/*
 *  @author Marzieh Vahid (marzieh.vahid@gmail.com)
 */
@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }
}
