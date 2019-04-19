package util;

import static util.IdUtil.getId;

public class Token {
    private String token = getId() + "";

    public String getToken() {
        return this.token;
    }
}
