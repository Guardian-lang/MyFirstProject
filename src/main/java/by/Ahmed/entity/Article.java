package by.Ahmed.entity;

import java.sql.Date;
import java.time.LocalDateTime;

public class Article {

    private Long id;
    private Long themeId;
    private Long authorId;
    private String title;
    private Date date;
    private String text;

    public Article() {
    }

    public Article(Long id, Long themeId, Long authorId, String title, Date date, String text) {
        this.id = id;
        this.themeId = themeId;
        this.authorId = authorId;
        this.title = title;
        this.date = date;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public Long getThemeId() {
        return themeId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {this.id = id;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "article{" +
                "id=" + id +
                ", theme_id=" + themeId +
                ", author_id=" + authorId +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", text=" + text +
                '}';
    }
}
