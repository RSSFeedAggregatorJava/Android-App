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


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * InlineResponse2002
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-01-26T20:46:26.709Z")
public class InlineResponse2002 {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("title")
  private String title = null;

  public InlineResponse2002 id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public InlineResponse2002 title(String title) {
    this.title = title;
    return this;
  }

   /**
   * title of article
   * @return title
  **/
  @ApiModelProperty(example = "null", value = "title of article")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2002 inlineResponse2002 = (InlineResponse2002) o;
    return Objects.equals(this.id, inlineResponse2002.id) &&
        Objects.equals(this.title, inlineResponse2002.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2002 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

