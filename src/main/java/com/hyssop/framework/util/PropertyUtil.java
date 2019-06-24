//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hyssop.framework.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PropertyUtil {
    private static Log log = LogFactory.getLog(PropertyUtil.class);
    public static final String DEFAULT_PROPERTY_FILE = "ApplicationResources.properties";
    private static final String ADD_PROPERTY_PREFIX = "add.property.file.";
    private static final String PROPERTY_EXTENSION = ".properties";
    private static TreeMap<String, String> props = new TreeMap();
    private static Set<String> files = new HashSet();

    public PropertyUtil() {
    }

    private static void load(String name) {
        StringBuilder key = new StringBuilder();
        Properties p = readPropertyFile(name);
        Iterator i$ = p.entrySet().iterator();

        while(i$.hasNext()) {
            Entry<Object, Object> e = (Entry)i$.next();
            props.put((String)e.getKey(), (String)e.getValue());
        }

        if (p != null) {
            int i = 1;

            while(true) {
                key.setLength(0);
                key.append("add.property.file.");
                key.append(i);
                String addfile = p.getProperty(key.toString());
                if (addfile == null) {
                    break;
                }

                String path = getPropertiesPath(name, addfile);
                addPropertyFile(path);
                ++i;
            }
        }

    }

    private static Properties readPropertyFile(String name) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        if (is == null) {
            is = PropertyUtil.class.getResourceAsStream("/" + name);
        }

        InputStreamReader reader = null;
        if (is != null) {
            reader = new InputStreamReader(is);
        }

        Properties p = new Properties();

        try {
            p.load(reader);
            files.add(name);
        } catch (NullPointerException var13) {
            System.err.println("!!! PANIC: Cannot load " + name + " !!!");
          //  System.err.println(ExceptionUtil.getStackTrace(var13));
        } catch (IOException var14) {
            System.err.println("!!! PANIC: Cannot load " + name + " !!!");
            //System.err.println(ExceptionUtil.getStackTrace(var14));
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException var12) {
                log.error("", var12);
            }

        }

        return p;
    }

    private static void overrideProperties() {
        Enumeration enumeration = Collections.enumeration(props.keySet());

        while(enumeration.hasMoreElements()) {
            String name = (String)enumeration.nextElement();
            String value = System.getProperty(name);
            if (value != null) {
                props.put(name, value);
            }
        }

    }

    public static void addPropertyFile(String name) {
        if (!name.endsWith(".properties")) {
            StringBuilder nameBuf = new StringBuilder();
            nameBuf.append(name);
            nameBuf.append(".properties");
            name = nameBuf.toString();
        }

        if (!files.contains(name)) {
            load(name);
        }

    }

    public static String getProperty(String key) {
        String result = (String)props.get(key);
        if (result != null && result.equals("@" + key)) {
            return result;
        } else if (result != null && result.startsWith("@@")) {
            return result.substring(1);
        } else {
            if (result != null && result.startsWith("@")) {
                result = getProperty(result.substring(1));
            }

            return result;
        }
    }

    public static String getProperty(String key, String defaultValue) {
        String result = (String)props.get(key);
        return result == null ? defaultValue : result;
    }

    public static Enumeration<String> getPropertyNames() {
        return Collections.enumeration(props.keySet());
    }

    public static Enumeration<String> getPropertyNames(String keyPrefix) {
        Map<String, String> map = props.tailMap(keyPrefix);
        Iterator iter = map.keySet().iterator();

        String name;
        do {
            if (!iter.hasNext()) {
                return Collections.enumeration(map.keySet());
            }

            name = (String)iter.next();
        } while(name.startsWith(keyPrefix));

        return Collections.enumeration(props.subMap(keyPrefix, name).keySet());
    }

    public static Set<String> getPropertiesValues(String propertyName, String keyPrefix) {
        Properties localProps = loadProperties(propertyName);
        if (localProps == null) {
            return null;
        } else {
            Enumeration<String> keyEnum = getPropertyNames(localProps, keyPrefix);
            return keyEnum == null ? null : getPropertiesValues(localProps, keyEnum);
        }
    }

    public static Enumeration<String> getPropertyNames(Properties localProps, String keyPrefix) {
        if (localProps != null && keyPrefix != null) {
            Collection<String> matchedNames = new ArrayList();
            Enumeration propNames = localProps.propertyNames();

            while(propNames.hasMoreElements()) {
                String name = (String)propNames.nextElement();
                if (name.startsWith(keyPrefix)) {
                    matchedNames.add(name);
                }
            }

            return Collections.enumeration(matchedNames);
        } else {
            return null;
        }
    }

    public static Set<String> getPropertiesValues(Properties localProps, Enumeration<String> propertyNames) {
        if (localProps != null && propertyNames != null) {
            HashSet retSet = new HashSet();

            while(propertyNames.hasMoreElements()) {
                retSet.add(localProps.getProperty((String)propertyNames.nextElement()));
            }

            return retSet;
        } else {
            return null;
        }
    }

    public static Properties loadProperties(String propertyName) {
        if (propertyName != null && !"".equals(propertyName)) {
            Properties retProps = new Properties();
            StringBuilder resourceName = new StringBuilder();
            resourceName.append(propertyName);
            resourceName.append(".properties");
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName.toString());
            if (is == null) {
                is = PropertyUtil.class.getResourceAsStream("/" + propertyName + ".properties");
            }

            InputStreamReader reader = null;
            if (is != null) {
                reader = new InputStreamReader(is);
            }

            try {
                retProps.load(reader);
            } catch (NullPointerException var16) {
                log.warn("*** Can not find property-file [" + propertyName + ".properties] ***", var16);
                retProps = null;
            } catch (IOException var17) {
                log.error("", var17);
                retProps = null;
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException var15) {
                    log.error("", var15);
                    retProps = null;
                }

            }

            return retProps;
        } else {
            return null;
        }
    }

    private static String getPropertiesPath(String resource, String addFile) {
        File file = new File(resource);
        String dir = file.getParent();
        StringBuilder retBuf;
        if (dir != null) {
            retBuf = new StringBuilder();
            retBuf.setLength(0);
            retBuf.append(dir);
            retBuf.append(File.separator);
            dir = retBuf.toString();
        } else {
            dir = "";
        }

        retBuf = new StringBuilder();
        retBuf.setLength(0);
        retBuf.append(dir);
        retBuf.append(addFile);
        return retBuf.toString();
    }

    static {
        StringBuilder key = new StringBuilder();
        load("ApplicationResources.properties");
        if (props != null) {
            int i = 1;

            while(true) {
                key.setLength(0);
                key.append("add.property.file.");
                key.append(i);
                String path = getProperty(key.toString());
                if (path == null) {
                    break;
                }

                addPropertyFile(path);
                ++i;
            }
        }

        overrideProperties();
    }
}
