package by.Ahmed.entity;

public class RecommendedThemes {

    private User user;
    private Theme theme;

    public RecommendedThemes() {
    }

    public RecommendedThemes(User user, Theme theme) {
        this.user = user;
        this.theme = theme;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "recommended_themes{" +
                "user_id=" + user.getId() +
                ", theme_id=" + theme.getId() +
                '}';
    }
}
