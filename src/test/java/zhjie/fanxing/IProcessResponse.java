package zhjie.fanxing;

/**
 * @Author jie.zhang
 * @create_time 2019/12/16 14:22
 * @updater
 * @update_time
 **/
public interface   IProcessResponse<T> {
    public void onProcessCompleted(T result);
}