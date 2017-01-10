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

package io.swagger.client.model;

import java.util.Date;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class Article {
  
  @SerializedName("id")
  private Integer id = null;
  @SerializedName("title")
  private String title = null;
  @SerializedName("link")
  private String link = null;
  @SerializedName("description")
  private String description = null;
  @SerializedName("pubdate")
  private Date pubdate = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getLink() {
    return link;
  }
  public void setLink(String link) {
    this.link = link;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getPubdate() {
    return pubdate;
  }
  public void setPubdate(Date pubdate) {
    this.pubdate = pubdate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Article article = (Article) o;
    return (this.id == null ? article.id == null : this.id.equals(article.id)) &&
        (this.title == null ? article.title == null : this.title.equals(article.title)) &&
        (this.link == null ? article.link == null : this.link.equals(article.link)) &&
        (this.description == null ? article.description == null : this.description.equals(article.description)) &&
        (this.pubdate == null ? article.pubdate == null : this.pubdate.equals(article.pubdate));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.id == null ? 0: this.id.hashCode());
    result = 31 * result + (this.title == null ? 0: this.title.hashCode());
    result = 31 * result + (this.link == null ? 0: this.link.hashCode());
    result = 31 * result + (this.description == null ? 0: this.description.hashCode());
    result = 31 * result + (this.pubdate == null ? 0: this.pubdate.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Article {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  title: ").append(title).append("\n");
    sb.append("  link: ").append(link).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  pubdate: ").append(pubdate).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
