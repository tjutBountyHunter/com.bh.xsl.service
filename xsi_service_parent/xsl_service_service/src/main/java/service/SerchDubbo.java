package service;

import service.SearchResult;
import service.XslResult;

public interface SerchDubbo {
    XslResult searchDubbo(String keyword, int page, int rows, int sort_type);

    XslResult searchDubboExperience(Integer Lever);
}
