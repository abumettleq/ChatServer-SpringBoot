package com.nova.messengerapi.responses;

public class UserIdResponse
{
    int userId;
    boolean success;

    public UserIdResponse() {
    }

    public UserIdResponse(int userId, boolean success) {
        this.userId = userId;
        this.success = success;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
