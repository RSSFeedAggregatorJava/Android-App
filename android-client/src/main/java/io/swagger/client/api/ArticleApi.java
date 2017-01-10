/**
 * RSS Feed Aggregator
 * This is an api for \"RSS Feed Aggregator\".  [View Subject](https://intra.epitech.eu/module/2016/M-EAP-650/PAR-9-1/acti-235029/project/file/RSS-feed-aggregator.pdf) or [Messenger group](https://www.messenger.com/t/552069568251252)  A successfull login or signup generate a key usable to authenticate request  This key is owned by one account, a request providing an apiKey should get result for the user owning this key. 
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.client.api;

import io.swagger.client.ApiInvoker;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;

import io.swagger.client.model.*;

import java.util.*;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import io.swagger.client.model.Article;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ArticleApi {
  String basePath = "http://localhost";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public void addHeader(String key, String value) {
    getInvoker().addDefaultHeader(key, value);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  /**
  * retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article)
  * 
   * @param feedId ID of feed containing article
   * @param articleId ID of article to retrieve
   * @return Article
  */
  public Article articlesFeedIdArticleIdGet (String feedId, String articleId) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
     Object postBody = null;
  
      // verify the required parameter 'feedId' is set
      if (feedId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'feedId' when calling articlesFeedIdArticleIdGet",
      new ApiException(400, "Missing the required parameter 'feedId' when calling articlesFeedIdArticleIdGet"));
      }
  
      // verify the required parameter 'articleId' is set
      if (articleId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'articleId' when calling articlesFeedIdArticleIdGet",
      new ApiException(400, "Missing the required parameter 'articleId' when calling articlesFeedIdArticleIdGet"));
      }
  

  // create path and map variables
  String path = "/articles/{feedId}/{articleId}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "feedId" + "\\}", apiInvoker.escapeString(feedId.toString())).replaceAll("\\{" + "articleId" + "\\}", apiInvoker.escapeString(articleId.toString()));

  // query params
  List<Pair> queryParams = new ArrayList<Pair>();
      // header params
      Map<String, String> headerParams = new HashMap<String, String>();
      // form params
      Map<String, String> formParams = new HashMap<String, String>();



      String[] contentTypes = {
  
      };
      String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

      if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
  

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
      } else {
      // normal form params
        }

      String[] authNames = new String[] { "api_key" };

      try {
        String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
        if(localVarResponse != null){
           return (Article) ApiInvoker.deserialize(localVarResponse, "", Article.class);
        } else {
           return null;
        }
      } catch (ApiException ex) {
         throw ex;
      } catch (InterruptedException ex) {
         throw ex;
      } catch (ExecutionException ex) {
         if(ex.getCause() instanceof VolleyError) {
	    VolleyError volleyError = (VolleyError)ex.getCause();
	    if (volleyError.networkResponse != null) {
	       throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
	    }
         }
         throw ex;
      } catch (TimeoutException ex) {
         throw ex;
      }
  }

      /**
   * retrieve article feed id and article id (id correspond to 1st, 2nd, 3rd, 4th... article)
   * 
   * @param feedId ID of feed containing article   * @param articleId ID of article to retrieve
  */
  public void articlesFeedIdArticleIdGet (String feedId, String articleId, final Response.Listener<Article> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

  
    // verify the required parameter 'feedId' is set
    if (feedId == null) {
       VolleyError error = new VolleyError("Missing the required parameter 'feedId' when calling articlesFeedIdArticleIdGet",
         new ApiException(400, "Missing the required parameter 'feedId' when calling articlesFeedIdArticleIdGet"));
    }
    
    // verify the required parameter 'articleId' is set
    if (articleId == null) {
       VolleyError error = new VolleyError("Missing the required parameter 'articleId' when calling articlesFeedIdArticleIdGet",
         new ApiException(400, "Missing the required parameter 'articleId' when calling articlesFeedIdArticleIdGet"));
    }
    

    // create path and map variables
    String path = "/articles/{feedId}/{articleId}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "feedId" + "\\}", apiInvoker.escapeString(feedId.toString())).replaceAll("\\{" + "articleId" + "\\}", apiInvoker.escapeString(articleId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

      String[] authNames = new String[] { "api_key" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Article) ApiInvoker.deserialize(localVarResponse,  "", Article.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
}
