package bigDataOne.entity;

import java.io.Serializable;

/**
 * Ip实体
 *
 * @author sunhuanzhi
 * @date 2022/4/17 10:27
 */
public class Ip implements Serializable {
    private static final long serialVersionUID = -8903000680469719698L;
    private String ip = "";
    private int count;

    public Ip(String ip, Integer integer) {
        this.ip = ip;
        this.count = integer;
    }

    public int getCount() {
        return count;
    }

    public String getIp() {
        return ip;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
