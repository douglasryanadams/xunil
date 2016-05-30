package io.xunil.web.exception;

import javax.xml.bind.JAXBException;

/**
 * Created on 5/29/16.
 */
public class UserInputException extends RuntimeException {
    public UserInputException(String s, JAXBException e) {
        super(s, e);
    }
}
