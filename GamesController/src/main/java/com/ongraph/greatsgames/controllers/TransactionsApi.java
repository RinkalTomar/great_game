package com.ongraph.greatsgames.controllers;


import com.ongraph.greatsgames.beans.dto.TransactionsBean;
import com.ongraph.greatsgames.beans.request.UITransaction;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.enums.Enumeration.TransactionType;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Api(value = "Transaction",description = "GreatGames Transaction Api",tags = {"Transactions"})
public interface TransactionsApi {

    @ApiOperation(value = "Add a new Transaction in the GreatGames Applicaiton", nickname = "AddTransaction", notes = "Returns HTTP 201 if Transaction is Created",
            response = AbstractResponse.class,
            tags = "Transactions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Brand is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response =
                                                                                                              AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @PostMapping(value = "/transactions",
            produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<AbstractResponse> addTransaction(
            @ApiParam(value = "", required = true) @Valid @RequestBody UITransaction body,
            @RequestParam(required = true) TransactionType type)
            throws Exception;


    @ApiOperation(value = "Add a new Transaction in the GreatGames Applicaiton", nickname = "AddTransaction", notes = "Returns HTTP 201 if Transaction is Created",
            response = AbstractResponse.class,
            tags = "Transactions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Brand is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response =
                    AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @GetMapping(value = "/transactions",
            produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<AbstractResponse> getAll()throws Exception;


    @ApiOperation(value = "Add a new Transaction in the GreatGames Applicaiton", nickname = "AddTransaction", notes = "Returns HTTP 201 if Transaction is Created",
            response = AbstractResponse.class,
            tags = "Transactions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Brand is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response =
                    AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @GetMapping(value = "/cashbox/{id}/transactions",
            produces = {"application/json"},consumes = {"application/json"})
    ResponseEntity<AbstractResponse> getTransactionsByCashBox(@ApiParam(value = "Unique id of the Cashbox", required = true) @PathVariable Long id)throws Exception;




    @ApiOperation(value = "Get new Transaction in the GreatGames Applicaiton", nickname = "GetTransaction", notes = "Returns HTTP 201 if Transaction is Created",
            response = AbstractResponse.class,
            tags = "Transactions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Transaction is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response =
                    AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @GetMapping(value = "/transactionsById", produces = {"application/json"})
    ResponseEntity<AbstractResponse> getTransactionsByTransactionId(@RequestParam Map<String, String> searchParams) throws Exception;


}
