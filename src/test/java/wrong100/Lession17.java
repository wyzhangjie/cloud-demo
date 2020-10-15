package wrong100;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@Slf4j
public class Lession17 {
    //别以为“自动挡”就不可能出现OOM
    //一个对象从数据库到到处文件，期间到底复制了多少对象承接要搞清楚。一个简单的现象，100M的数据库数据
    //到文件里面就需要400M，直接说明了整个流程一个对象呗复制了很多份来承接。
    //分析内存dump MAT

    //一个对象要尽量做到不总是new 重复还占用空间。 一个对象要做到复用，尤其是缓存场景，不会修改的情况下，一个对象做到高度复用。

    //100M 的数据加载到程序内存中，变为 Java 的数据结构就已经占用了 200M 堆内存；这些数据经过 JDBC、MyBatis 等框架其实是加载了 2 份，然后领域模型、DTO 再进行转换可能又加载了 2 次；最终，占用的内存达到了 200M*4=800M。

    //垃圾回收器不会回收有强引用的对象；
    //在内存充足时，垃圾回收器不会回收具有软引用的对象；
    //垃圾回收器只要扫描到了具有弱引用的对象就会回收，WeakHashMap 就是利用了这个特点。

    //Tomcat 参数配置不合理导致 OOM
    //一定要根据实际需求来修改参数配置，可以考虑预留 2 到 5 倍的量。容量类的参数背后往往代表了资源，设置超大的参数就有可能占用不必要的资源，在并发量大的时候因为资源大量分配导致 OOM。
    /**
     * 一是，我们的程序确实需要超出 JVM 配置的内存上限的内存。
     * 不管是程序实现的不合理，还是因为各种框架对数据的重复处理、加工和转换，相同的数据在内存中不一定只占用一份空间。
     * 针对内存量使用超大的业务逻辑，比如缓存逻辑、文件上传下载和导出逻辑，我们在做容量评估时，可能还需要实际做一下 Dump，而不是进行简单的假设。
     * 二是，出现内存泄露，其实就是我们认为没有用的对象最终会被 GC，但却没有。
     * GC 并不会回收强引用对象，我们可能经常在程序中定义一些容器作为缓存，但如果容器中的数据无限增长，要特别小心最终会导致 OOM。
     * 使用 WeakHashMap 是解决这个问题的好办法，但值得注意的是，如果强引用的 Value 有引用 Key，也无法回收 Entry。
     * 三是，不合理的资源需求配置，在业务量小的时候可能不会出现问题，但业务量一大可能很快就会撑爆内存。
     * 比如，随意配置 Tomcat 的 max-http-header-size 参数，会导致一个请求使用过多的内存，请求量大的时候出现 OOM。
     * 在进行参数配置的时候，我们要认识到，很多限制类参数限制的是背后资源的使用，资源始终是有限的，需要根据实际需求来合理设置参数。
     * 我建议你为生产系统的程序配置 JVM 参数启用详细的 GC 日志，方便观察垃圾收集器的行为，并开启 HeapDumpOnOutOfMemoryError，以便在出现 OOM 时能自动 Dump 留下第一问题现场。
     *
     XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=. -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M

     当我们需要动态执行一些表达式时，可以使用 Groovy 动态语言实现：new 出一个 GroovyShell 类，然后调用 evaluate 方法动态执行脚本。这种方式的问题是，会重复产生大量的类，增加 Metaspace 区的 GC 负担，有可能会引起 OOM。你知道如何避免这个问题吗？
     可以先compile，然后在内存中保存，脚本内容的hash作为key，compile结果作为value，用ConcurrentReferenceHashMap保存
     * */

    private Map<User, UserProfile> cache = new WeakHashMap<>();

    public void wrong() {
        String userName = "zhuye";
        //间隔1秒定时输出缓存中的条目数
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                () -> log.info("cache size:{}", cache.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 2000000).forEach(i -> {
            User user = new User(userName + i);
            //这里有一个强引用导致cache数据得不到回收
            cache.put(user, new UserProfile(user, "location" + i));
        });
    }


    private Map<User, WeakReference<UserProfile>> cache2 = new WeakHashMap<>();

    public void right() {
        String userName = "zhuye";
        //间隔1秒定时输出缓存中的条目数
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                () -> log.info("cache size:{}", cache2.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 2000000).forEach(i -> {
            User user = new User(userName + i);
            //这次，我们使用弱引用来包装UserProfile
            cache2.put(user, new WeakReference(new UserProfile(user, "location" + i)));
            //避免强引用 在弱引用的场景里面出现
            cache.put(user, new UserProfile(new User(user.username), "location" + i));
        });
    }
/**
 * spring 提供的ConcurrentReferenceHashMap类可以使用弱引用、
 * 软引用做缓存，Key 和 Value 同时被软引用或弱引用包装，也能解决相互引用导致的数据不能释放问题。
 * 与 WeakHashMap 相比，ConcurrentReferenceHashMap 不但性能更好，还可以确保线程安全
 * */

    public static void main(String[] args) {
        Lession17 lession17 = new Lession17();
        lession17.wrong();
    }

    public class User{
        public String username;

        public User(String userName){
            this.username = userName;
        }

    }

    public class UserProfile{
     public User user;
     public String username;
     public UserProfile(User user,String username){
         this.user = user;
         this.username = username;
     }
    }
}
