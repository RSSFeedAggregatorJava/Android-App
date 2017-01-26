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

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.swagger.client.model.Article;
import io.swagger.client.model.InlineResponse2002;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleApi {
    private ApiClient apiClient;

    public ArticleApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ArticleApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /* Build call for articlesFeedIdArticleIdGet */
    private com.squareup.okhttp.Call articlesFeedIdArticleIdGetCall(String feedId, String articleId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/articles/{feedId}/{articleId}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "feedId" + "\\}", apiClient.escapeString(feedId.toString()))
        .replaceAll("\\{" + "articleId" + "\\}", apiClient.escapeString(articleId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "api_key" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call articlesFeedIdArticleIdGetValidateBeforeCall(String feedId, String articleId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'feedId' is set
        if (feedId == null) {
            throw new ApiException("Missing the required parameter 'feedId' when calling articlesFeedIdArticleIdGet(Async)");
        }
        
        // verify the required parameter 'articleId' is set
        if (articleId == null) {
            throw new ApiException("Missing the required parameter 'articleId' when calling articlesFeedIdArticleIdGet(Async)");
        }
        
        
        com.squareup.okhttp.Call call = articlesFeedIdArticleIdGetCall(feedId, articleId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article)
     * 
     * @param feedId ID of feed containing article (required)
     * @param articleId ID of article to retrieve (required)
     * @return Article
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Article articlesFeedIdArticleIdGet(String feedId, String articleId) throws ApiException {
        ApiResponse<Article> resp = articlesFeedIdArticleIdGetWithHttpInfo(feedId, articleId);
        return resp.getData();
    }

    /**
     * retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article)
     * 
     * @param feedId ID of feed containing article (required)
     * @param articleId ID of article to retrieve (required)
     * @return ApiResponse&lt;Article&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Article> articlesFeedIdArticleIdGetWithHttpInfo(String feedId, String articleId) throws ApiException {
        com.squareup.okhttp.Call call = articlesFeedIdArticleIdGetValidateBeforeCall(feedId, articleId, null, null);
        Type localVarReturnType = new TypeToken<Article>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article) (asynchronously)
     * 
     * @param feedId ID of feed containing article (required)
     * @param articleId ID of article to retrieve (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call articlesFeedIdArticleIdGetAsync(String feedId, String articleId, final ApiCallback<Article> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = articlesFeedIdArticleIdGetValidateBeforeCall(feedId, articleId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Article>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for articlesFeedIdGet */
    private com.squareup.okhttp.Call articlesFeedIdGetCall(String feedId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/articles/{feedId}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "feedId" + "\\}", apiClient.escapeString(feedId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "api_key" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call articlesFeedIdGetValidateBeforeCall(String feedId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'feedId' is set
        if (feedId == null) {
            throw new ApiException("Missing the required parameter 'feedId' when calling articlesFeedIdGet(Async)");
        }
        
        
        com.squareup.okhttp.Call call = articlesFeedIdGetCall(feedId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * retrieve the lisT of articles in the feed
     * 
     * @param feedId ID of feed containing article (required)
     * @return List&lt;InlineResponse2002&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<InlineResponse2002> articlesFeedIdGet(String feedId) throws ApiException {
        ApiResponse<List<InlineResponse2002>> resp = articlesFeedIdGetWithHttpInfo(feedId);
        return resp.getData();
    }

    /**
     * retrieve the lisT of articles in the feed
     * 
     * @param feedId ID of feed containing article (required)
     * @return ApiResponse&lt;List&lt;InlineResponse2002&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<List<InlineResponse2002>> articlesFeedIdGetWithHttpInfo(String feedId) throws ApiException {
        com.squareup.okhttp.Call call = articlesFeedIdGetValidateBeforeCall(feedId, null, null);
        Type localVarReturnType = new TypeToken<List<InlineResponse2002>>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * retrieve the lisT of articles in the feed (asynchronously)
     * 
     * @param feedId ID of feed containing article (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call articlesFeedIdGetAsync(String feedId, final ApiCallback<List<InlineResponse2002>> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = articlesFeedIdGetValidateBeforeCall(feedId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<List<InlineResponse2002>>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
