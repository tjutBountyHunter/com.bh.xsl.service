package mapper;

import java.util.Map;

public interface XslTokenFindMapper {
    String selectByTokenphoneTot(String phone);

    int updataByTokenphone(Map<String, Object> map);
}
