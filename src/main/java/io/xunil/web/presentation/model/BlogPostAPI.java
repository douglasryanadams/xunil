package io.xunil.web.presentation.model;

import io.xunil.web.persistence.data.BlogPost;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created on 5/22/16.
 */
@XmlRootElement
public class BlogPostAPI extends BlogPost {

    public BlogPostAPI() {
        // JAXb
    }

    public BlogPostAPI(BlogPost blogPost) {
        setContent(blogPost.getContent());
        setCreateDate(blogPost.getCreateDate());
        setUpdateDate(blogPost.getUpdateDate());
        setId(blogPost.getId());
    }

}
