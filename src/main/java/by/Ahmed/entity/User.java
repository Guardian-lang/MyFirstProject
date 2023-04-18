package by.Ahmed.entity;

public class User {

    private Long id;
    private String ip;

    public User() {
    }

    public User(Long id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }
}
