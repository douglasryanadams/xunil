package io.xunil.web.presentation.model;

import io.xunil.web.persistence.data.BlogPostTable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created on 5/22/16.
 */
@XmlRootElement
public class BlogPost extends BlogPostTable {

    public BlogPost() {
        // JAXb
    }

    public BlogPost(BlogPostTable blogPost) {
        setContent(blogPost.getContent());
        setCreateDate(blogPost.getCreateDate());
        setUpdateDate(blogPost.getUpdateDate());
        setId(blogPost.getId());
    }

}
