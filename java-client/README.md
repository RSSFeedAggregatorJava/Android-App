# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.ArticleApi;

import java.io.File;
import java.util.*;

public class ArticleApiExample {

    public static void main(String[] args) {
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ArticleApi* | [**articlesFeedIdArticleIdGet**](docs/ArticleApi.md#articlesFeedIdArticleIdGet) | **GET** /articles/{feedId}/{articleId} | retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article)
*FeedApi* | [**feedsDelete**](docs/FeedApi.md#feedsDelete) | **DELETE** /feeds | Unsuscribe to a feed by url (keep it in database, juste remove reference for user)
*FeedApi* | [**feedsFeedIdGet**](docs/FeedApi.md#feedsFeedIdGet) | **GET** /feeds/{feedId} | Find a feed and retrieve its articles
*FeedApi* | [**feedsGet**](docs/FeedApi.md#feedsGet) | **GET** /feeds | Get all feeds subscribed by currend user
*FeedApi* | [**feedsPost**](docs/FeedApi.md#feedsPost) | **POST** /feeds | Suscribe to a feed by url (add it in database, and reference its id for current user)
*UserApi* | [**usersLoginPost**](docs/UserApi.md#usersLoginPost) | **POST** /users/login | Login with email and password
*UserApi* | [**usersLogoutGet**](docs/UserApi.md#usersLogoutGet) | **GET** /users/logout | Logs out current logged in user session
*UserApi* | [**usersSignupPost**](docs/UserApi.md#usersSignupPost) | **POST** /users/signup | Signup with email and password - application/json


## Documentation for Models

 - [Article](docs/Article.md)
 - [Credentials](docs/Credentials.md)
 - [Credentials1](docs/Credentials1.md)
 - [Feed](docs/Feed.md)
 - [InlineResponse200](docs/InlineResponse200.md)
 - [User](docs/User.md)


## Documentation for Authorization

Authentication schemes defined for the API:
### api_key

- **Type**: API key
- **API key parameter name**: api_key
- **Location**: HTTP header


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



