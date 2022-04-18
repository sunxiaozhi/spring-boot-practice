package bigDataOne.entity;

import java.io.Serializable;

/**
 * DnsLog实体
 *
 * @author sunhuanzhi
 * @date 2022/4/17 10:27
 */
public class DnsLog implements Serializable {
    private static final long serialVersionUID = -8903000680469719698L;
    private String url = "";
    private int count;

    public DnsLog(String url, Integer integer) {
        this.url = url;
        this.count = integer;
    }

    public int getCount() {
        return count;
    }

    public String getUrl() {
        return url;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDnsLog(String url) {
        this.url = url;
    }
}
