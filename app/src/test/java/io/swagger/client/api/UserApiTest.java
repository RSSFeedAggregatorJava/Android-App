/*
 * RSS Feed Aggregator
 * This is an api for \"RSS Feed Aggregator\".  [View Subject](https://intra.epitech.eu/module/2016/M-EAP-650/PAR-9-1/acti-235029/project/file/RSS-feed-aggregator.pdf) or [Messenger group](https://www.messenger.com/t/552069568251252)  A successfull login or signup generate a key usable to authenticate request  This key is owned by one account, a request providing an apiKey should get result for the user owning this key. 
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.Credentials;
import io.swagger.client.model.Credentials1;
import io.swagger.client.model.InlineResponse200;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UserApi
 */
@Ignore
public class UserApiTest {

    private final UserApi api = new UserApi();

    
    /**
     * Login with email and password
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void usersLoginPostTest() throws ApiException {
        Credentials1 credentials = null;
        InlineResponse200 response = api.usersLoginPost(credentials);

        // TODO: test validations
    }
    
    /**
     * Logs out current logged in user session
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void usersLogoutGetTest() throws ApiException {
        api.usersLogoutGet();

        // TODO: test validations
    }
    
    /**
     * Signup with email and password - application/json
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void usersSignupPostTest() throws ApiException {
        Credentials credentials = null;
        InlineResponse200 response = api.usersSignupPost(credentials);

        // TODO: test validations
    }
    
}