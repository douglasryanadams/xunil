package io.xunil.web.persistence.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * Created on 5/21/16.
 */
@Entity
@Table(name = "blog_post")
public class BlogPost {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return Objects.equals(
                id,
                blogPost.id
        ) &&
                Objects.equals(
                        content,
                        blogPost.content
                ) &&
                Objects.equals(
                        createDate,
                        blogPost.createDate
                ) &&
                Objects.equals(
                        updateDate,
                        blogPost.updateDate
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                content,
                createDate,
                updateDate
        );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlogPost{");
        sb.append("id=")
          .append(id);
        sb.append(", content='")
          .append(content)
          .append('\'');
        sb.append(", createDate=")
          .append(createDate);
        sb.append(", updateDate=")
          .append(updateDate);
        sb.append('}');
        return sb.toString();
    }

}
