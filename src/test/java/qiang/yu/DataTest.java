package qiang.yu;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.ListUtils;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.springframework.util.StringUtils;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author jie.zhang
 * @create_time 2020/5/25 12:52
 * @updater
 * @update_time
 **/
public class DataTest {
    public static String joinin="&&";
   public static String inward = "\\(\\)";
   public  List<String> result = new ArrayList<>();

    public static  String[] getResult(String a){
        //按照括号分组，不在括号里面的要首先放到结果集里面去。
     //   a = a.replaceAll("\\)&&\\(","\\)\\(");
        // 创建 Pattern 对象
        List<String> subStr = getSubStr(a);
        int t = a.lastIndexOf(")");
        //每一个都要带上尾巴
        StringBuffer tail = new StringBuffer();
        tail.append(a.substring(t+3));
        List<List<String>> listStr= getListInfo(subStr);
        List<List<String>> result = new ArrayList<>();
        List<String> curList = new ArrayList<>();
        descartes(listStr,result,0,curList);
        result.stream().forEach((tt)->{
            tt.add(String.valueOf(tail));
            //
            String preResult = CollectionUtils.join(tt,joinin);

            System.out.println( preResult);

        });

        return null;

    }
    public  static void descartes(List<List<String>> dimvalue, List<List<String>> result, int layer, List<String> curList) {
        if (layer < dimvalue.size() - 1) {
            if (dimvalue.get(layer).size() == 0) {
                descartes(dimvalue, result, layer + 1, curList);
            } else {
                for (int i = 0; i < dimvalue.get(layer).size(); i++) {
                    List<String> list = new ArrayList<String>(curList);

                    list.add(dimvalue.get(layer).get(i));
                    descartes(dimvalue, result, layer + 1, list);
                }
            }
        } else if (layer == dimvalue.size() - 1) {
            if (dimvalue.get(layer).size() == 0) {
                result.add(curList);
            } else {
                for (int i = 0; i < dimvalue.get(layer).size(); i++) {
                    List<String> list = new ArrayList<String>(curList);
                    list.add(dimvalue.get(layer).get(i));
                    result.add(list);
                }
            }
        }
    }

    private static  List<List<String>> getListInfo(List<String> subStr) {
        ArrayList<String> list = new ArrayList<String>();
        //把数组转成集合，也就是把数组里面的数据存进集合；
        List<List<String>> map = new ArrayList<>();
        for(String tmp:subStr){
            list = new ArrayList<>();
            String[] group = tmp.split("\\|\\|");
            Collections.addAll(list, group);
            map.add(list);
        }
        System.out.println(JSON.toJSONString(map));
        return map;
    }

    private static List<String> getSubStr(String a) {
        int begin = a.indexOf("(");
        int end = a.indexOf(")");
        List<String> subStr = new ArrayList<>();
        int i=0;
        while(begin!=-1 && end!=-1){
            String sub = a.substring(begin+1,end);
            subStr.add(sub);
             begin = a.indexOf("(",end);
             end = a.indexOf(")",begin);
        }
        return subStr;
    }

    public static void print(List<String> stringBuffers){
        for(String sb:stringBuffers){
            System.out.println(sb);
        }

    }

    public static void  print(String[] subSt){
        for(int i=0;i<subSt.length;i++){
            System.out.println(subSt[i]);
        }
    }

    public static void main(String[] args) {
        String a ="(1&&2||1&&4||2&&4)&&(5||6)&&(7||8)&&9";
        String b="(1&&2||1&&4||2&&4)&&(5||6)&&(7||8||9)&&10";
        String c="(1&&2||1&&4||2&&4)&&(5||6)&&3";
     //   getResult(a);
        getResult(b);
           /* String s = " movie1（1968）/ Male1/Male2/Famale1，MOVIE123（1998）要道";
            String regex = "（\\d{4}）";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                String temp = s.substring(start+1, end-1);
                System.out.println("数字为：" + temp);
            }*/
        }

}