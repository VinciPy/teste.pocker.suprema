package org.suprema.infra.controllers.response;

public class PlayerWinnerReponse {
    private String userName;
    private Long userId;

    public PlayerWinnerReponse(String userName, Long userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
