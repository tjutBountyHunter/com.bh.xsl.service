package service;

import pojo.XslDateTask;

public interface SupplementDataService {

    XslResult SupplementTaskData(String json);

    XslResult SupplementTagData(String json);

    XslResult SupplementCategoryData(String json);

    XslResult SupplementTaskTagData(String json);

    XslResult SupplementTagWrite(String json);

}
