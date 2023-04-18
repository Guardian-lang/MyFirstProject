package by.Ahmed.entity;

public class AuthorThemes {

    private Author author;
    private Theme theme;

    public AuthorThemes() {
    }

    public AuthorThemes(Author author, Theme theme) {
        this.author = author;
        this.theme = theme;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "author_themes{" +
                "author_id=" + author.getId() +
                ", theme_id=" + theme.getId() +
                '}';
    }
}
