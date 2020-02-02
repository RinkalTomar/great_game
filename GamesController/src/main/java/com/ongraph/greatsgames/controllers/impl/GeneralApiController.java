package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.PasswordResetBean;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.controllers.GeneralApi;
import com.ongraph.greatsgames.services.ITransactionCategoryService;
import com.ongraph.greatsgames.services.IUserService;
import com.ongraph.greatsgames.services.IUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class GeneralApiController extends AbstractController implements GeneralApi {


    @Autowired
    IUserService _userService;

    @Autowired
    IUserTokenService _userTokenService;

    @Autowired
    ITransactionCategoryService _transactionCategoryService;



    @Override
    public ResponseEntity<AbstractResponse> passwordResetRequest(@RequestBody @Valid String email) throws Exception {

        return null;
    }
    @Override
    public ResponseEntity<AbstractResponse> verifyPasswordResetRequest(@Valid @RequestParam String token)throws Exception{

        _userTokenService.verifyRequest(token);
        return buildResponse(HttpStatus.OK);

    }
    @Override
    public ResponseEntity<AbstractResponse> passwordReset(@RequestBody @Valid PasswordResetBean body)throws Exception{
        _userService.doPasswordReset(body);
        return buildResponse(_message.getMessage("password.changed"),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbstractResponse>verifyEmail(@Valid @RequestParam String token) throws Exception{
        _userService.verifyEmail(token);
        return buildResponse(_message.getMessage("email.verified.successfully"),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbstractResponse> getAllTransactionCategories() throws Exception {

        return buildResponse(_transactionCategoryService.getAllCategories(), HttpStatus.OK);

    }

}
