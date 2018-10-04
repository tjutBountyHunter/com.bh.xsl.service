package pojo;


import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {

    private long recordCount;
    private int totalPages;
    private List<SearchItem> itemList;
    private List<SearchHunter> hunterList;

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public List<SearchHunter> getHunterList() {
        return hunterList;
    }

    public void setHunterList(List<SearchHunter> hunterList) {
        this.hunterList = hunterList;
    }
}
