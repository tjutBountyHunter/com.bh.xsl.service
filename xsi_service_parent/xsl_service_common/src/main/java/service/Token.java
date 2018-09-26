package service;

import static service.IdUtil.getId;

public class Token {
    private String token = getId() + "";

    public String getToken() {
        return this.token;
    }
}
