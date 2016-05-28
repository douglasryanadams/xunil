package io.xunil.web.persistence.data;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * Created on 5/21/16.
 */
@StaticMetamodel(value = BlogPost.class)
public class BlogPost_ {
    public static volatile SingularAttribute<BlogPost, Integer> id;
    public static volatile SingularAttribute<BlogPost, Date> createDate;
    public static volatile SingularAttribute<BlogPost, Date> updateDate;
}
