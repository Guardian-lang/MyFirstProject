package by.Ahmed.entity;

public class UserHistory {

    private User user;
    private Article article;

    public UserHistory() {
    }

    public UserHistory(User user, Article article) {
        this.user = user;
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "user_history{" +
                "user_id=" + user.getId() +
                ", article_id=" + article.getId() +
                '}';
    }
}
