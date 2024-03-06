package com.test.start;

public class AddUserDetails {

    String Type, Amount, Comment;

    public AddUserDetails() {

    }

    public AddUserDetails(String Type, String amount, String Comment) {
        this.Type = Type;
        this.Amount = amount;
        this.Comment = Comment;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }
}
