package com.ongraph.greatsgames.controllers;


import com.ongraph.greatsgames.beans.dto.PasswordResetBean;
import com.ongraph.greatsgames.beans.dto.UserBean;
import com.ongraph.greatsgames.beans.dto.UserTokenBean;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(value = "General", description = "GreatGames Password Reset API", tags = {"General"})
public interface GeneralApi {

    @ApiOperation(tags = {"General"}, nickname = "forgotPassword", value = "To request a new Password reset Link",
            response = AbstractResponse.class, produces = MediaType.APPLICATION_JSON_VALUE,
            notes = "Returns HTTP 200 if Success")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Password reset request is generated and link send to your registered email"),
            @ApiResponse(code = 400, message = "Bad Request. Email is not registered with GreatGames"),
            @ApiResponse(code = 500, message = "Some error Occurred while generating password reset link"),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
    })
    @PostMapping(value = "/password/forgot", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AbstractResponse> passwordResetRequest(@RequestBody @Valid String email) throws Exception;


    @ApiOperation(value = " To verify password Reset Request", nickname = "verifyPasswordResetRequest",
            notes = "Returns HTTP 404 if the " + "Token not found.", response = AbstractResponse.class,
            tags = "General")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK", response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.", response = AbstractResponse.class),})

    @GetMapping(value = "/password/reset", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AbstractResponse> verifyPasswordResetRequest(@RequestParam @Valid String token) throws Exception;


    @ApiOperation(value = "  To Reset Password", nickname = "PasswordResetRequest",
            notes = "Returns HTTP 200 if success", response = AbstractResponse.class,
            tags = "General")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK", response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            })

    @PostMapping(value="/password/reset",consumes =MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AbstractResponse> passwordReset(@RequestBody @Valid PasswordResetBean body)throws Exception;



    @ApiOperation(value = " To verify Email", nickname = "verifyEmail",
            notes = "Returns HTTP 404 if the " + "Token not found.", response = AbstractResponse.class,
            tags = "General")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK", response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.", response = AbstractResponse.class),})

    @GetMapping(value = "/verify/email", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AbstractResponse> verifyEmail(@RequestParam @Valid String token) throws Exception;

    @ApiOperation(value = " To verify Email", nickname = "verifyEmail",
            notes = "Returns HTTP 404 if the " + "Token not found.", response = AbstractResponse.class,
            tags = "General")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK", response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.", response = AbstractResponse.class),})

    @GetMapping(value = "/transactions/cagtegories", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AbstractResponse> getAllTransactionCategories() throws Exception;

}
