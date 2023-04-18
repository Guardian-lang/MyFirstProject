package by.Ahmed.entity;

import org.w3c.dom.Text;

import java.io.File;
import java.time.LocalDateTime;

public class Article {

    private Long id;
    private Theme theme;
    private Author author;
    private String title;
    private LocalDateTime date;
    private File text;

    public Article() {
    }

    public Article(Long id, Theme theme, Author author, String title, LocalDateTime date, File text) {
        this.id = id;
        this.theme = theme;
        this.author = author;
        this.title = title;
        this.date = date;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public Theme getTheme() {
        return theme;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public File getText() {
        return text;
    }

    public void setId(Long id) {this.id = id;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setText(File text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "article{" +
                "id=" + id +
                ", theme_id=" + theme.getId() +
                ", author_id=" + author.getId() +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", text=" + text +
                '}';
    }
}
