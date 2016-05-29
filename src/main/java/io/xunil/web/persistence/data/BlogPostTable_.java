package io.xunil.web.persistence.data;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * Created on 5/21/16.
 */
@StaticMetamodel(value = BlogPostTable.class)
public class BlogPostTable_ {
    public static volatile SingularAttribute<BlogPostTable, Integer> id;
    public static volatile SingularAttribute<BlogPostTable, Date> createDate;
    public static volatile SingularAttribute<BlogPostTable, Date> updateDate;
}
