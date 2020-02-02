package com.ongraph.greatsgames.controllers.handler;


import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.enums.Enumeration.*;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthenticationException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.exception.GreatGamesException;
import com.ongraph.greatsgames.message.MessageProperties;
import com.ongraph.greatsgames.services.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageProperties _messages;

    @Autowired
    ICoreService _coreService;

    @ExceptionHandler({Exception.class, GreatGamesException.class})
    public final ResponseEntity<AbstractResponse> handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ErrorCode.INTERNAL_SERVER_ERROR_500.toString(), ex.getMessage() != null ? ex.getMessage() : _messages.getMessage("unexpected.error.message"));
        AbstractResponse errorDetails = new AbstractResponse(
                false, _coreService.isAuthenticated(), _messages.getMessage("unexpected.error"), errorMap);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GreatGamesAuthenticationException.class)
    public final ResponseEntity<AbstractResponse> handleGreatGamesAuthenticationException(GreatGamesAuthenticationException ex) {

        ex.printStackTrace();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ErrorCode.UNAUTHORIZED_401.toString(), ex.getMessage());
        AbstractResponse errorDetails = new AbstractResponse(
                false, _coreService.isAuthenticated(), _messages.getMessage("authentication.failed"), errorMap);
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler({GreatGamesAuthorizationException.class, AccessDeniedException.class})
    public final ResponseEntity<AbstractResponse> handleGreatGamesAuthorizationException(Exception ex) {

        ex.printStackTrace();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ErrorCode.FORBIDDEN_403.toString(), ex.getMessage());
        AbstractResponse errorDetails = new AbstractResponse(
                false, _coreService.isAuthenticated(), _messages.getMessage("authorization.failed"), errorMap);
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({NoSuchElementException.class, BadRequestException.class})
    public final ResponseEntity<AbstractResponse> handleInvalidIndentifierException(Exception ex) {

        ex.printStackTrace();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ErrorCode.BAD_REQEST_400.toString(), ex.getMessage());
        AbstractResponse errorDetails = new AbstractResponse(
                false, _coreService.isAuthenticated(), _messages.getMessage("bad.request"), errorMap);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
