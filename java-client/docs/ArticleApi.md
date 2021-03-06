# ArticleApi

All URIs are relative to *http://91.121.9.68:8080/swagger-jaxrs-server-1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**articlesFeedIdArticleIdGet**](ArticleApi.md#articlesFeedIdArticleIdGet) | **GET** /articles/{feedId}/{articleId} | retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article)
[**articlesFeedIdGet**](ArticleApi.md#articlesFeedIdGet) | **GET** /articles/{feedId} | retrieve the lisT of articles in the feed


<a name="articlesFeedIdArticleIdGet"></a>
# **articlesFeedIdArticleIdGet**
> Article articlesFeedIdArticleIdGet(feedId, articleId)

retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article)

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ArticleApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: api_key
ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
api_key.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//api_key.setApiKeyPrefix("Token");

ArticleApi apiInstance = new ArticleApi();
String feedId = "feedId_example"; // String | ID of feed containing article
String articleId = "articleId_example"; // String | ID of article to retrieve
try {
    Article result = apiInstance.articlesFeedIdArticleIdGet(feedId, articleId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArticleApi#articlesFeedIdArticleIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **feedId** | **String**| ID of feed containing article |
 **articleId** | **String**| ID of article to retrieve |

### Return type

[**Article**](Article.md)

### Authorization

[api_key](../README.md#api_key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="articlesFeedIdGet"></a>
# **articlesFeedIdGet**
> List&lt;InlineResponse2002&gt; articlesFeedIdGet(feedId)

retrieve the lisT of articles in the feed

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ArticleApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: api_key
ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
api_key.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//api_key.setApiKeyPrefix("Token");

ArticleApi apiInstance = new ArticleApi();
String feedId = "feedId_example"; // String | ID of feed containing article
try {
    List<InlineResponse2002> result = apiInstance.articlesFeedIdGet(feedId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ArticleApi#articlesFeedIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **feedId** | **String**| ID of feed containing article |

### Return type

[**List&lt;InlineResponse2002&gt;**](InlineResponse2002.md)

### Authorization

[api_key](../README.md#api_key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

