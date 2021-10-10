package youngeun.site.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Guestbook")
@DynamicInsert
@Getter
@Setter
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestbookIdx;

    @ManyToOne
    @JoinColumn(name="writer_idx")
    private User writer;

    private String content;
    private LocalDateTime createdDatetime;	    //작성시간
    private LocalDateTime updatedDatetime;	    //수정시간

    @Override
    public String toString() {
        return "Post{" +
                "guestbookIdx=" + guestbookIdx +
                ", writer=" + writer.toString() +
                ", content='" + content + '\'' +
                ", createdDatetime=" + createdDatetime +
                ", updatedDatetime=" + updatedDatetime +
                '}';
    }
}
