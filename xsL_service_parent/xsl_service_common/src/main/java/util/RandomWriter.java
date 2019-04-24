package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWriter {
    public List<String> writerEvalune() {
        List<String> list = new ArrayList<>();
        list.add("信誉良好 质量保障");
        list.add("人在江湖飘 哪有不挨刀");
        list.add("信任我 你便成功一大办");
        list.add("马不停蹄 高效准确");
        list.add("你的世界 有我而精彩");
        list.add("我们的口号 为人民服务");
        return list;
    }

    public String randowEvalune() {
        List<String> list = writerEvalune();
        Random rd = new Random();
        int random = rd.nextInt(list.size());
        String writere = list.get(random);
        return writere;
    }
}
