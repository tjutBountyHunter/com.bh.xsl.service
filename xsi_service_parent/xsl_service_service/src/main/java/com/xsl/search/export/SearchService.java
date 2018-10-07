package com.xsl.search.export;

import pojo.SearchResult;

public interface SearchService {
    SearchResult search_item(String keyword, int page, int rows, int sort_type) throws Exception;

    SearchResult search_hunter(String keyword, int page, int rows, int sort_type) throws Exception;
}
