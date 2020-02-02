package com.ongraph.greatsgames.controllers;

import com.ongraph.greatsgames.beans.dto.CasinoBean;
import com.ongraph.greatsgames.beans.dto.ClientBean;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Api(value = "Casino Endpoints", description = "GreatGames Casinos API", tags = {"Casino"})
public interface CasinoApi {

    @ApiOperation(value = "Add/Update a new Casino in the GreatGames Applicaiton", nickname = "AddOrEditCasino", notes = "Returns HTTP 201 if Casino is successfully created",
            response = AbstractResponse.class,
            tags = "Casino")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Casino is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response =
                                                                                                              AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The Casino might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @PostMapping(value = "/casinos",
            produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<AbstractResponseBean> saveOrUpdateCasino(@RequestBody @Valid CasinoBean body) throws Exception;

    @ApiOperation(value = "Return List of all casino", nickname = "getCasinos",
            notes = "Returns HTTP 404 if the " + "Casino are not found.", response = CasinoBean.class,
            responseContainer = "List", tags = {"Casino",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK.", response = AbstractResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The Casinos might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @GetMapping(value = "/casinos", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getAllCasinos (@RequestParam(required = false) Map<String, String> searchParams) throws Exception;


    @ApiOperation(value = "Get the Casino with the given id", nickname = "getCasino",
            notes = "Returns HTTP 200 if the casino is successfully activated. Returns HTTP 409 if already Activated",
            response = Void.class,
            tags = {"Casino",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Casino is De-Activated Successfully", response = AbstractResponse
                    .class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The contract might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.", response = AbstractResponse.class),
            @ApiResponse(code = 501, message = "Method not implemented. The server either does not recognize the request method, or it lacks the ability to fulfill the request."),
            @ApiResponse(code = 502, message = "Bad Gateway. The server was acting as a gateway or proxy and received an invalid response from the upstream server"),
            @ApiResponse(code = 503, message = "Service Unavailable. The server is currently unavailable (overloaded or down)."),
            @ApiResponse(code = 504, message = "Gateway Timeout. The server is acting as a gateway or proxy and did not receive a timely response from the upstream server")})
    @GetMapping(value = "/casinos/{id}",
            produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getCasinoDetails (
            @ApiParam(value = "Unique id of the resource type.", required = true) @PathVariable("id") Long id)
            throws Exception;
}
