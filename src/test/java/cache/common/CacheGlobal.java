package cache.common;

/**
 * @Author jie.zhang
 * @create_time 2020/7/9 15:47
 * @updater
 * @update_time
 **/
import  java.util.concurrent.ConcurrentHashMap;
import  java.util.concurrent.ConcurrentMap;

/**
   *  Cache  全局类
   */
public  class  CacheGlobal  {
                //  全局缓存对象
                public  static  ConcurrentMap<String,  MyCache>  concurrentMap  =  new  ConcurrentHashMap<>();
        }