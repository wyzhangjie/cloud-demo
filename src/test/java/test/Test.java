package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jie.zhang
 * @create_time 2019/8/14 13:58
 * @updater
 * @update_time
 **/
public class Test {
/*    private static final Joiner.MapJoiner MAP_JOINER = Joiner.on("&").withKeyValueSeparator("=");*/
    public static void main(String[] args) {

        Thread shutDownHook = new Thread(new Runnable() {
            private volatile boolean hasShutdown = false;

            @Override
            public void run() {
                synchronized (this) {
                    if (!this.hasShutdown) {
                        System.out.println("大哥，临走之前握个手我再走！");
                    }
                }
            }
        }, "ShutdownHookMQTrace");
        Runtime.getRuntime().addShutdownHook(shutDownHook);
    }

/*        Map<String, String> queryParams = Maps.newHashMap();
        queryParams.put("appId", "opay-payment-overlord");


       String rsult = "/services/config?" + MAP_JOINER.join(queryParams);
        System.out.println("result:"+rsult);*/
     /*   Test t = new Test();
        long start = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            t.testA();
        }

        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            t.testB();
        }
        System.out.println(System.currentTimeMillis() - start);
        t.testC();*/

    public static String StringIndex(){
        String a ="a"+":\\"+"baidu.com";
        return a;
    }


    public static void contactWithString(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("http:").append(1).append("2222");

    }
    public void testA(){
        List<Configuration> list = new ArrayList<>();

        for(int i=0;i<10;i++){
            Configuration configuration = new Configuration();
            configuration.setConfigId("1");
            configuration.setConfigType("type");
            configuration.setName("name");
            configuration.setVersion(Long.parseLong("1"));
            list.add(configuration);
        }
     }
    public void testB(){
        List<Configuration> list = new ArrayList<>();
        Configuration configuration ;
        for(int i=0;i<10;i++){
             configuration = new Configuration();
            configuration.setConfigId("1");
            configuration.setConfigType("type");
            configuration.setName("name");
            configuration.setVersion(Long.parseLong("1"));
            list.add(configuration);
        }
    }

    public void testC(){
        List<Configuration> list = new ArrayList<>();

        for(int i=0;i<10;i++){
            Configuration configuration = new Configuration();
            configuration.setConfigId("1");
            configuration.setConfigType("type");
            configuration.setName("name");
            configuration.setVersion(Long.parseLong("1"));
            list.add(configuration);
        }
        int size = list.size();
        for(int i=0;i<size;i++){
            System.out.println(list.get(i));
        }

        printA(list, size);
        printB(list, size);
    }

    public void printB(List<Configuration> list, int size) {
        for(int i=0;i<size;i++){
            String condi = list.get(i).getConfigId();
            if(condi.equalsIgnoreCase("1")){
                System.out.println("tt");
            }

        }
    }

    public void printA(List<Configuration> list, int size) {
        for(int i=0;i<size;i++){
            if(list.get(i).getConfigId().equalsIgnoreCase("1")){
                System.out.println("tt");
            }

        }
    }

    public void testA(List<Configuration> list, int size) {
        for(int i=0;i<size;i++){
            if(list.get(i).getConfigId().equalsIgnoreCase("1") &&list.get(i).getConfigType().equalsIgnoreCase("1")){
                System.out.println("tt");
            }

        }
    }
    public void testB(List<Configuration> list, int size) {

        for(int i=0;i<size;i++){
            String configId =list.get(i).getConfigId();
            String configType = list.get(i).getConfigType();
            if(configId.equalsIgnoreCase("1") && configType.equalsIgnoreCase("1")){
                System.out.println("tt");
            }

        }
    }
    private void testF(List<Configuration> list, int size) {

        for(int i=0;i<size;i++){
            String configId =list.get(i).getConfigId();
            String configType = list.get(i).getConfigType();
            if(configId.equalsIgnoreCase("1") && configType.equalsIgnoreCase("1")){
                System.out.println("tt");
            }

        }
    }


    public class Configuration  {


        private Long version;

        private String configId;


        private String name;


        private String value;


        private String configType;


        public Long getVersion() {
            return version;
        }

        public void setVersion(Long version) {
            this.version = version;
        }

        public String getConfigId() {
            return configId;
        }

        public void setConfigId(String configId) {
            this.configId = configId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getConfigType() {
            return configType;
        }

        public void setConfigType(String configType) {
            this.configType = configType;
        }
    }

}