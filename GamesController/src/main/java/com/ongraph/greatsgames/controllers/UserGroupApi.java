package com.ongraph.greatsgames.controllers;

import com.ongraph.greatsgames.beans.dto.UserBean;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Api(value = "UserGroup", description = "GreatGames Users API", tags = {"UserGroup"})
public interface UserGroupApi {

    @ApiOperation(value = "Return List of all users", nickname = "getUsers",
            notes = "Returns HTTP 404 if the " + "UserGroups not found.", response = UserBean.class,
            responseContainer = "List", tags = {"UserGroup",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK.", response = AbstractResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The Users might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @GetMapping(value = "/usergroups", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getAllUserGroups (@RequestParam(required = false) Map<String, String> searchParams) throws Exception;

}
