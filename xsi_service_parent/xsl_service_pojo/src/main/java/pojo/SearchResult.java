package pojo;



import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {

    private long recordCount;
    private int totalPages;
    private List<ItemTransfer> itemList;
    private List<HunterTransfer> hunterList;

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

    public List<ItemTransfer> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemTransfer> itemList) {
        this.itemList = itemList;
    }

    public List<HunterTransfer> getHunterList() {
        return hunterList;
    }

    public void setHunterList(List<HunterTransfer> hunterList) {
        this.hunterList = hunterList;
    }
}
