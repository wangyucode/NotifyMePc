package cn.wycode.notifyme.pc.bean;

import java.util.List;

/**
 *
 * Created by wWX383516 on 2016/11/23.
 */
public class Page {
    public List<Notification> content;
    public int totalElements;
    public int totalPages;
    public boolean first;
    public boolean last;
    public int numberOfElements;
    public int size;
    public int number;
}
