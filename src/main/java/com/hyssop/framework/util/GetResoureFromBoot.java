package com.hyssop.framework.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author jie.zhang
 * @create_time 2019/8/19 18:26
 * @updater
 * @update_time
 **/
public class GetResoureFromBoot {
    /**
     * 读取boot文件有点儿特别
     * 读取文件数据，按照占位符表替换参数。
     */
    public String parseText(String fileName, Map<String, String> placeholders) {
        BufferedReader br = null;
        File file = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("files" + File.separator + fileName);
            InputStream inputStream = classPathResource.getInputStream();

            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String resultContent;
            StringBuilder toReplaceContent = new StringBuilder();

            String s;
            while ((s = br.readLine()) != null) {
                toReplaceContent.append(System.lineSeparator() + s);
            }
            Iterator iter = placeholders.entrySet().iterator();
            resultContent = toReplaceContent.toString();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) iter.next();
                String key = entry.getKey();
                String val = entry.getValue();
                //jdk里面使用了正则，上线造成多次故障。改成非正则方式
                resultContent = StringUtils.replace(resultContent, key, val);
            }
            return toReplaceContent.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (file != null) {
                file.delete();
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
    }
}