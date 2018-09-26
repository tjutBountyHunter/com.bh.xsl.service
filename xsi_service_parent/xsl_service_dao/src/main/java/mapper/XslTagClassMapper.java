package mapper;

import java.util.List;

public interface XslTagClassMapper {
    List<String> selectByXslTag();

    int selectByName(String name);
}
