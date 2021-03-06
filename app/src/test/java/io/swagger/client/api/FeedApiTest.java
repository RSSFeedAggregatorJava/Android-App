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
import java.math.BigDecimal;
import io.swagger.client.model.Feed;
import io.swagger.client.model.InlineResponse2001;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for FeedApi
 */
@Ignore
public class FeedApiTest {

    private final FeedApi api = new FeedApi();

    
    /**
     * Unsuscribe to a feed by url (keep it in database, juste remove reference for user)
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void feedsDeleteTest() throws ApiException {
        Integer feedId = null;
        api.feedsDelete(feedId);

        // TODO: test validations
    }
    
    /**
     * Find a feed and retrieve its articles
     *
     * Returns id and titles of articles of this feed
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void feedsFeedIdGetTest() throws ApiException {
        Long feedId = null;
        Feed response = api.feedsFeedIdGet(feedId);

        // TODO: test validations
    }
    
    /**
     * Get all feeds subscribed by currend user
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void feedsGetTest() throws ApiException {
        List<InlineResponse2001> response = api.feedsGet();

        // TODO: test validations
    }
    
    /**
     * Suscribe to a feed by url (add it in database, and reference its id for current user)
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void feedsPostTest() throws ApiException {
        String feedUrl = null;
        BigDecimal response = api.feedsPost(feedUrl);

        // TODO: test validations
    }
    
}
