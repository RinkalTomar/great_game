package com.ongraph.greatsgames.exception;

import com.ongraph.greatsgames.exception.BadRequestException;

public class IncompatibleCategoryException extends BadRequestException {

    public IncompatibleCategoryException(String message) {
        super(message);
    }
}
