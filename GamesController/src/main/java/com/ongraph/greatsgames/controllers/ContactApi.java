package com.ongraph.greatsgames.controllers;

import com.ongraph.greatsgames.beans.dto.CasinoContactBean;
import com.ongraph.greatsgames.beans.dto.ContactBean;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "Contact", description = "GreatGames Contacts API", tags = {"Contacts"})
public interface ContactApi {


    @ApiOperation(value = "Add/Update a new Contact in the GreatGames Applicaiton", nickname = "AddOrEditContact", notes = "Returns HTTP 201 if Contact is successfully created",
            response = AbstractResponse.class,
            tags = "Contacts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Contact is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response =
                                                                                                              AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The Contact might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @PostMapping(value = "/contacts",
            produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<AbstractResponseBean> saveContact(@RequestBody ContactBean body) throws Exception;

    @ApiOperation(value = "Return List of all contacts", nickname = "getContacts",
            notes = "Returns HTTP 404 if the " + "Contact are not found.", response = ContactBean.class,
            responseContainer = "List", tags = {"Contacts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK.", response = AbstractResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The Clients might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponseBean.class)})
    @GetMapping(value = "/contacts", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getAllContacts (@RequestParam(required = false) Map<String, String> searchParams) throws Exception;


    @ApiOperation(value = "Get the Contact with the given id", nickname = "getContact",
            notes = "Returns HTTP 200 if the contact is successfully activated. Returns HTTP 409 if already Activated",
            response = Void.class,
            tags = {"Contacts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Contact is De-Activated Successfully", response = AbstractResponseBean
                    .class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The contact might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.", response = AbstractResponse.class),
            @ApiResponse(code = 501, message = "Method not implemented. The server either does not recognize the request method, or it lacks the ability to fulfill the request."),
            @ApiResponse(code = 502, message = "Bad Gateway. The server was acting as a gateway or proxy and received an invalid response from the upstream server"),
            @ApiResponse(code = 503, message = "Service Unavailable. The server is currently unavailable (overloaded or down)."),
            @ApiResponse(code = 504, message = "Gateway Timeout. The server is acting as a gateway or proxy and did not receive a timely response from the upstream server")})
    @GetMapping(value = "/contacts/{id}",
            produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getContactDetails (
            @ApiParam(value = "Unique id of the resource type.", required = true) @PathVariable("id") Long id)
            throws Exception;


    @ApiOperation(value = "Delete the casino from contact with the given id", nickname = "deleteCasino",
            notes = "Returns HTTP 200 if the User is successfully deleted.",
            response = Void.class,
            tags = {"Contacts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Casino is Suspended Successfully", response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered."),
            @ApiResponse(code = 501, message = "Method not implemented. The server either does not recognize the request method, or it lacks the ability to fulfill the request."),
            @ApiResponse(code = 502, message = "Bad Gateway. The server was acting as a gateway or proxy and received an invalid response from the upstream server"),
            @ApiResponse(code = 503, message = "Service Unavailable. The server is currently unavailable (overloaded or down)."),
            @ApiResponse(code = 504, message = "Gateway Timeout. The server is acting as a gateway or proxy and did not receive a timely response from the upstream server")})
    @PutMapping(value = "/contacts/delete",
            produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> deleteCasino (@RequestBody List<Long> id)
            throws Exception;


    @ApiOperation(value = "Update a new Contact in the GreatGames Applicaiton", nickname = "AddOrEditContact", notes = "Returns HTTP 201 if Contact is successfully created",
            response = AbstractResponse.class,
            tags = "Contacts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Contact is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response =
                    AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The Contact might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @PutMapping(value = "/contacts",
            produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<AbstractResponseBean> updateContact(@RequestBody ContactBean body) throws Exception;


    @ApiOperation(value = "Create the casinoContact from contact with the given id", nickname = "createCasinoContact",
            notes = "Returns HTTP 200 if the User is successfully deleted.",
            response = Void.class,
            tags = {"Contacts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. CasinoContact is Suspended Successfully", response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered."),
            @ApiResponse(code = 501, message = "Method not implemented. The server either does not recognize the request method, or it lacks the ability to fulfill the request."),
            @ApiResponse(code = 502, message = "Bad Gateway. The server was acting as a gateway or proxy and received an invalid response from the upstream server"),
            @ApiResponse(code = 503, message = "Service Unavailable. The server is currently unavailable (overloaded or down)."),
            @ApiResponse(code = 504, message = "Gateway Timeout. The server is acting as a gateway or proxy and did not receive a timely response from the upstream server")})
    @PostMapping(value = "/contacts/casinoContact",
            produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> createCasinoContact (@RequestBody CasinoContactBean bean)throws Exception;

}
