package service;

import util.XslResult;

public interface SupplementDataService {

    XslResult SupplementTaskData(String json);

    XslResult SupplementCategoryData(String json);

    XslResult SupplementTaskTagData(String json, Integer taskId);

    XslResult SupplementTagWrite(String json);

}
