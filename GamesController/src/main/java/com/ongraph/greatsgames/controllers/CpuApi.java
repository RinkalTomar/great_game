package com.ongraph.greatsgames.controllers;

import com.ongraph.greatsgames.beans.dto.CpuBean;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@Api(value = "Cpu",description = "GreatGames Cpu API",tags = {"Cpu"})
public interface CpuApi {

    @ApiOperation(value = "Add a new Cpu in the GreatGames Applicaiton", nickname = "AddNewCpu", notes = "Returns HTTP 201 if Cpu is successfully created",
            response = AbstractResponse.class,
            tags = "Cpu")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new Cpu is created .",
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
    @PostMapping(value = "/cpu",
            produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<AbstractResponseBean> addOrUpdateCpu(@ApiParam(value = "", required = true) @Valid @RequestBody CpuBean body)
            throws Exception;

    @ApiOperation(value = "Return List of all Cpu", nickname = "getAllCpu",
            notes = "Returns HTTP 404 if the " + "Cpu are not found.", response = AbstractResponse.class,
            responseContainer = "List", tags = {"Cpu",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK.", response = AbstractResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided",
                    response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format",
                    response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponseBean.class)})
    @GetMapping(value = "/cpu", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getAllCpu(@RequestParam(required = false) Map<String, String> searchParams) throws Exception;



    @ApiOperation(value = "Return Cpu by given id", nickname = "getCpuById",
            notes = "Returns HTTP 404 if the " + "Cpu are not found.", response = AbstractResponse.class,
            tags = {"Cpu",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK.", response = AbstractResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided",
                    response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format",
                    response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @GetMapping(value = "/cpu/{id}", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getCpuById(@ApiParam(value = "Unique id of the resource type.", required = true) @PathVariable("id") Long id)
            throws Exception;

}
