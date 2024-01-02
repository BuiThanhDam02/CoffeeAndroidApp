package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request;

import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;

public class CommentRequest {
    private User user;
    private Long coffee_id;
    private String content;
    private float star;

    public CommentRequest(User user, Long coffee_id, String content, float star) {
        this.user = user;
        this.coffee_id = coffee_id;
        this.content = content;
        this.star = star;
    }

    public CommentRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCoffee_id() {
        return coffee_id;
    }

    public void setCoffee_id(Long coffee_id) {
        this.coffee_id = coffee_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "user=" + user +
                ", coffee_id=" + coffee_id +
                ", content='" + content + '\'' +
                ", star=" + star +
                '}';
    }
}
