package mapper;

import pojo.XslWaitTask;
import pojo.Xsltaskmainpage;

import java.util.List;

public interface XslWaitTaskMapper {
    List<XslWaitTask> selectByuserId(Integer userId);

    List<Xsltaskmainpage> selectBysendId(Integer userId);

    List<Xsltaskmainpage> selectByaccectId(Integer userId);
}
