package com.ongraph.greatsgames.exception;

import com.ongraph.greatsgames.message.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class GreatGamesAuthorizationException extends GreatGamesException {

    public GreatGamesAuthorizationException(String message) {
        super(message);
    }

    public GreatGamesAuthorizationException()
    {
        super("You may not have the correct rights to access the requested resource.");
    }
}
