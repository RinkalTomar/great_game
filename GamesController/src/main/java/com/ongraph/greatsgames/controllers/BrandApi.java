package com.ongraph.greatsgames.controllers;

import com.ongraph.greatsgames.beans.dto.BrandBean;
import com.ongraph.greatsgames.beans.dto.search.BrandSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Api(value = "Brand",description = "GreatGames Brand Api",tags = {"Brand"})
public interface BrandApi {


    @ApiOperation(value = "Add a new Brand in the GreatGames Applicaiton", nickname = "AddBrand", notes = "Returns HTTP 201 if brand is successfully created",
            response = AbstractResponse.class,
            tags = "Brand")
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
    @PostMapping(value = "/brands",
        produces = {"application/json"}, consumes = {"application/json"})
        ResponseEntity<AbstractResponseBean> addOrUpdateBrand(@ApiParam(value = "", required = true) @Valid @RequestBody BrandBean body)
        throws Exception;



        @ApiOperation(value = "Return List of all Brands", nickname = "getBrands",
                notes = "Returns HTTP 404 if the " + "Brands are not found.", response = AbstractResponseBean.class,
                responseContainer = "List", tags = {"Brand",})
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "OK. Request is OK.", response = AbstractResponseBean.class),
                @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided",
                        response = AbstractResponseBean.class),
                @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
                @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
                @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format",
                        response = AbstractResponseBean.class),
                @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                        response = AbstractResponseBean.class)})
        @GetMapping(value = "/brands", produces = {"application/json"})
        ResponseEntity<AbstractResponseBean> getAllBrands(@RequestParam(required = false) Map<String, String> searchParams) throws Exception;



    @ApiOperation(value = "Return Brands by given id", nickname = "getBrandsById",
            notes = "Returns HTTP 404 if the " + "Brands are not found.", response = AbstractResponse.class,
             tags = {"Brand",})
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
    @GetMapping(value = "/brands/{id}", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getBrandsById(@ApiParam(value = "Unique id of the resource type.", required = true) @PathVariable("id") Long id)
            throws Exception;
}

