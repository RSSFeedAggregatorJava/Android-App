# FeedApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**feedsDelete**](FeedApi.md#feedsDelete) | **DELETE** /feeds | Unsuscribe to a feed by url (keep it in database, juste remove reference for user)
[**feedsFeedIdGet**](FeedApi.md#feedsFeedIdGet) | **GET** /feeds/{feedId} | Find a feed and retrieve its articles
[**feedsGet**](FeedApi.md#feedsGet) | **GET** /feeds | Get all feeds subscribed by currend user
[**feedsPost**](FeedApi.md#feedsPost) | **POST** /feeds | Suscribe to a feed by url (add it in database, and reference its id for current user)


<a name="feedsDelete"></a>
# **feedsDelete**
> feedsDelete(feedId)

Unsuscribe to a feed by url (keep it in database, juste remove reference for user)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FeedApi;


FeedApi apiInstance = new FeedApi();
Integer feedId = 56; // Integer | ID of feed that user want to unsubscribe
try {
    apiInstance.feedsDelete(feedId);
} catch (ApiException e) {
    System.err.println("Exception when calling FeedApi#feedsDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **feedId** | **Integer**| ID of feed that user want to unsubscribe |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="feedsFeedIdGet"></a>
# **feedsFeedIdGet**
> List&lt;InlineResponse2002&gt; feedsFeedIdGet(feedId)

Find a feed and retrieve its articles

Returns id and titles of articles of this feed

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FeedApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: api_key
ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
api_key.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//api_key.setApiKeyPrefix("Token");

FeedApi apiInstance = new FeedApi();
Long feedId = 789L; // Long | ID of feed that needs to be fetched
try {
    List<InlineResponse2002> result = apiInstance.feedsFeedIdGet(feedId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FeedApi#feedsFeedIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **feedId** | **Long**| ID of feed that needs to be fetched |

### Return type

[**List&lt;InlineResponse2002&gt;**](InlineResponse2002.md)

### Authorization

[api_key](../README.md#api_key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="feedsGet"></a>
# **feedsGet**
> List&lt;InlineResponse2001&gt; feedsGet()

Get all feeds subscribed by currend user

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FeedApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: api_key
ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
api_key.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//api_key.setApiKeyPrefix("Token");

FeedApi apiInstance = new FeedApi();
try {
    List<InlineResponse2001> result = apiInstance.feedsGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FeedApi#feedsGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;InlineResponse2001&gt;**](InlineResponse2001.md)

### Authorization

[api_key](../README.md#api_key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="feedsPost"></a>
# **feedsPost**
> BigDecimal feedsPost(feedUrl)

Suscribe to a feed by url (add it in database, and reference its id for current user)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FeedApi;


FeedApi apiInstance = new FeedApi();
String feedUrl = "feedUrl_example"; // String | url of feed that user want to subscribe
try {
    BigDecimal result = apiInstance.feedsPost(feedUrl);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FeedApi#feedsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **feedUrl** | **String**| url of feed that user want to subscribe |

### Return type

[**BigDecimal**](BigDecimal.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

