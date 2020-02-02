package com.ongraph.greatsgames.controllers;

import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Api(value = "CashBox", description = "GreatGames CashBox/Accounting API", tags = {"CashBox"})
public interface CashBoxApi {

    @ApiOperation(value = "Add/Update a new CashBox in the GreatGames Applicaiton", nickname = "AddOrEditUser", notes = "Returns HTTP 201 if user is successfully created",
            response = AbstractResponse.class,
            tags = "CashBox")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Cashbox is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @PostMapping(value = "/cashboxes",
            produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<AbstractResponse> addCashBox(@ApiParam(value = "", required = true) @Valid @RequestBody CashBoxBean body)
            throws Exception;

    @ApiOperation(value = "Add/Update a new CashBox in the GreatGames Applicaiton", nickname = "AddOrEditUser", notes = "Returns HTTP 201 if user is successfully created",
            response = AbstractResponse.class,
            tags = "CashBox")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Cashbox is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @GetMapping(value = "/cashboxes", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getAllCashBoxes(@RequestParam Map<String, String> searchParams) throws Exception;


    @ApiOperation(value = "Get the details of a Cashbox" , nickname = "get_cashbox_by_id", notes = "Returns 200"
        , response = AbstractResponseBean.class, tags = "CashBox")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Cashbox is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @GetMapping(value = "/cashboxes/{id}", produces = {"application/json"})
    ResponseEntity<AbstractResponse> getCashBox(@PathVariable Long id) throws Exception;



    @ApiOperation(value = "Delete  a Cashbox" , nickname = "delete_cashbox", notes = "Returns 200"
            , response = AbstractResponseBean.class, tags = "CashBox")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @DeleteMapping(value = "/cashboxes/{id}", produces = {"application/json"})
    public ResponseEntity<AbstractResponseBean<Long, CashBoxBean>> deleteCashbox(@PathVariable Long id) throws Exception;


    @ApiOperation(value = "Update  a Cashbox" , nickname = "update_cashbox", notes = "Returns 200"
                        , response = AbstractResponseBean.class, tags = "Update")
    @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK. Request is OK."),
                        @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
                        @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
                        @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
                        @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
                        @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
                        @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                                response = AbstractResponse.class)})
    @PutMapping(value = "/cashboxes", produces = {"application/json"})
    public ResponseEntity<AbstractResponseBean<Long, CashBoxBean>> Update(@Valid @RequestBody CashBoxBean body) throws Exception;





}
