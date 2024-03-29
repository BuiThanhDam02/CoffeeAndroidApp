package com.example.smartcoffeecourt.Model;

public class Rating {

    private User user;
    private Coffee coffee;
    private Long coffee_id;
    private String content;
    private float star;

    public Rating() {
    }

    public Rating(Float star, String content) {
        this.star = star;
        this.content = content;
    }

    public Rating(User user, Long coffee_id, String content, float star) {
        this.user = user;
        this.coffee_id = coffee_id;
        this.content = content;
        this.star = star;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
//

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
        return "Rating{" +
                "user=" + user +
                ", coffee=" + coffee +
                ", rateValue='" + star + '\'' +
                ", comment='" + content + '\'' +
                '}';
    }
}
