package com.example.smartcoffeecourt.Model;

public class Like {
    private int user_id;
    private int coffee_id;


    public Like(int user_id, int coffee_id) {
        this.user_id = user_id;
        this.coffee_id = coffee_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCoffee_id() {
        return coffee_id;
    }

    public void setCoffee_id(int coffee_id) {
        this.coffee_id = coffee_id;
    }

    @Override
    public String toString() {
        return "Like{" +
                "user_id=" + user_id +
                ", coffee_id=" + coffee_id +
                '}';
    }
}
