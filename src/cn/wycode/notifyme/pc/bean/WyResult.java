package cn.wycode.notifyme.pc.bean;

/**
 *
 * Created by wWX383516 on 2016/11/23.
 */
public class WyResult<T> {
    public int code;
    public String message;
    public T data;
    
    public WyResult(){
        //default for json
    }
    
    public WyResult(String message){
        this.code = 0;
        this.message = message;
    }
}
