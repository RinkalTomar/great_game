package com.ongraph.greatsgames.controllers;


import com.ongraph.greatsgames.beans.dto.SlotGroupBean;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Api(value = "SlotGroup",description = "GreatGames SlotGroup API",tags = {"SlotGroup"})
public interface SlotGroupApi {
    @ApiOperation(value = "Add a new SlotGroup in the GreatGames Applicaiton", nickname = "AddNewSlotGroup", notes = "Returns HTTP 201 if slotGroup is successfully created",
            response = AbstractResponse.class,
            tags = "SlotGroup")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new SlotGroup is created .",
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
    @PostMapping(value = "/slotgroups",
            produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<AbstractResponseBean> addNewSlotGroup(@ApiParam(value = "", required = true) @Valid @RequestBody SlotGroupBean body)
            throws Exception;


    @ApiOperation(value = "Return List of all SlotGroups", nickname = "getslotGroups",
            notes = "Returns HTTP 404 if the " + "slotGroups are not found.", response = AbstractResponse.class,
            responseContainer = "List", tags = {"SlotGroup",})
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
    @GetMapping(value = "/slotgroups", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getAllSlotGroups(@RequestParam(required = false) Map<String, String> searchParams) throws Exception;



    @ApiOperation(value = "Return slotGroups by given id", nickname = "getslotGroupsById",
            notes = "Returns HTTP 404 if the " + "slotGroups are not found.", response = AbstractResponse.class,
            tags = {"SlotGroup",})
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
    @GetMapping(value = "/slotgroups/{id}", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getslotGroupsById(@ApiParam(value = "Unique id of the resource type.", required = true) @PathVariable("id") Long id)
            throws Exception;
}
