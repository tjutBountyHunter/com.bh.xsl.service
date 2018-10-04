package service;

public interface SerchDubbo {
    XslResult searchDubbo_item(String keyword, int page, int rows, int sort_type);

    XslResult searchDubbo_hunter(String keyword, int page, int rows, int sort_type);
}
