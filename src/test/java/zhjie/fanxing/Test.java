package zhjie.fanxing;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author jie.zhang
 * @create_time 2019/12/16 14:25
 * @updater
 * @update_time
 **/
public class Test {
    public <T> void process(IProcessResponse<T> processResponseListener){
        /*
        * 对于实现接口而来的对象，使用getGenericInterfaces()与getActualTypeArguments()

           对于继承父类而来的对象，使用getGenericSuperclass()与getActualTypeArguments()
        * */
        String content = runProcess();

        Gson gson = new Gson();

        Type[] types = processResponseListener.getClass().getGenericInterfaces();
        Type[] params = ((ParameterizedType) types[0]).getActualTypeArguments();
        Class<T> reponseClass = (Class) params[0];

        T responese = gson.fromJson(content, reponseClass);
        processResponseListener.onProcessCompleted(responese);
    }

    private String runProcess() {
        return "result";
    }


}