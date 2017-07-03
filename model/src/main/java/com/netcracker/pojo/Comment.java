package com.netcracker.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Simple JavaBean object that represents an Comment
 *
 * @author Vadim Ayupov
 * @version 1.0
 */
@Entity
@Table(name = "comments")
@NoArgsConstructor
@Log
public class Comment extends BaseEntity {

    @Column(name = "COMMENT")
    @Getter @Setter
    private String comment;

    @Column(name = "COMMENT_DATE")
    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "M/dd/yyyy hh:mm:ss a")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @Getter @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    @Getter @Setter
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        if (!super.equals(o)) return false;

        Comment comment1 = (Comment) o;

        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        return date != null ? date.equals(comment1.date) : comment1.date == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
