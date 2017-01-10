# ArticleApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**articlesFeedIdArticleIdGet**](ArticleApi.md#articlesFeedIdArticleIdGet) | **GET** /articles/{feedId}/{articleId} | retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article)


<a name="articlesFeedIdArticleIdGet"></a>
# **articlesFeedIdArticleIdGet**
> Article articlesFeedIdArticleIdGet(feedId, articleId)

retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article)

### Example
```java
// Import classes:
//import io.swagger.client.api.ArticleApi;

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
