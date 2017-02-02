# UserApi

All URIs are relative to *http://91.121.9.68:8080/swagger-jaxrs-server-1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**usersLoginPost**](UserApi.md#usersLoginPost) | **POST** /users/login | Login with email and password
[**usersLogoutGet**](UserApi.md#usersLogoutGet) | **GET** /users/logout | Logs out current logged in user session
[**usersSignupPost**](UserApi.md#usersSignupPost) | **POST** /users/signup | Signup with email and password - application/json


<a name="usersLoginPost"></a>
# **usersLoginPost**
> InlineResponse200 usersLoginPost(credentials)

Login with email and password



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserApi;


UserApi apiInstance = new UserApi();
Credentials1 credentials = new Credentials1(); // Credentials1 | credentials
try {
    InlineResponse200 result = apiInstance.usersLoginPost(credentials);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#usersLoginPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **credentials** | [**Credentials1**](Credentials1.md)| credentials |

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="usersLogoutGet"></a>
# **usersLogoutGet**
> usersLogoutGet()

Logs out current logged in user session



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.UserApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: api_key
ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
api_key.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//api_key.setApiKeyPrefix("Token");

UserApi apiInstance = new UserApi();
try {
    apiInstance.usersLogoutGet();
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#usersLogoutGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

[api_key](../README.md#api_key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/xml

<a name="usersSignupPost"></a>
# **usersSignupPost**
> InlineResponse200 usersSignupPost(credentials)

Signup with email and password - application/json

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserApi;


UserApi apiInstance = new UserApi();
Credentials credentials = new Credentials(); // Credentials | credentials
try {
    InlineResponse200 result = apiInstance.usersSignupPost(credentials);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#usersSignupPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **credentials** | [**Credentials**](Credentials.md)| credentials |

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

