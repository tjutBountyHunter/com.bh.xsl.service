package util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class XslResultOk extends XslResult {
    private boolean ok;
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();


    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public static XslResultOk format(String json) {
        try {
            return MAPPER.readValue(json, XslResultOk.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
