package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/7/13 21:00
 */
public class GsonUtil {

    private static Gson gson = GsonSingle.getGson();

    /**
     * 对象转成json
     *
     * @param object
     * @return json
     */
    public static String gsonToString(Object object) {
        return gson.toJson(object);
    }

    /**
     * Json转成对象
     *
     * @param objectString
     * @param cls
     * @return 对象
     */
    public static <T> T gsonToObject(String objectString, Class<T> cls) {
        return  gson.fromJson(objectString,cls);
    }

    /**
     * json转成list<T>
     *
     * @param listString
     * @param cls
     * @return list<T>
     */
    public static <T> List<T> gsonToList(String listString, Class<T> cls) {
        List<T> list =  gson.fromJson(listString, new TypeToken<List<T>>() {}.getType());
        return list;
    }

    /**
     * json转成map的
     *
     * @param gsonString
     * @return Map<String, T>
     */
    public static <T> Map<String, T> gsonToMaps(String gsonString) {
        Map<String, T> map = map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {}.getType());
        return map;
    }

}
