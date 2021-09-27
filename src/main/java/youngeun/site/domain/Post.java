package youngeun.site.domain;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String writerName;
    private String content;
    private LocalDateTime createdDatetime;	//작성시간

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getWriterName() {
        return writerName;
    }
    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }
    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
