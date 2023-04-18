package by.Ahmed.entity;

public class AuthorIps {

    private Author author;
    private String authorIp;

    public AuthorIps() {
    }

    public AuthorIps(Author author, String authorIp) {
        this.author = author;
        this.authorIp = authorIp;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getAuthorIp() {
        return authorIp;
    }

    public void setAuthorIp(String authorIp) {
        this.authorIp = authorIp;
    }

    @Override
    public String toString() {
        return "author_ips{" +
                "author_id=" + author.getId() +
                ", author_ip='" + authorIp + '\'' +
                '}';
    }

    public void setId(long author_id) {
        var author = new Author();
        author.setId(author_id);
        this.author = author;
    }
}
