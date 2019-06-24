
package com.hyssop.framework.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


import com.hyssop.framework.exception.ArgumentsException;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 处理日期和时间的工具类。
 * 实现了不同格式的日期/时间转换，日期/时间之间的计算，以及固定日期格式的判断。
 * 
 * <p>日期时间格式命范参考Microsoft Developer Network中标准日期和时间格式字符串的命名规范。
 * 
 * <p>更多有关日期和时间格式字符串命名规范，请参考:
 * <a href="http://msdn.microsoft.com/zh-cn/library/az4se3k1(v=vs.110).aspx#FullDateLongTime">
 * 
 * 
 *
 * @author fanghui.zhang
 * @version 1.0 2013-11-19
 */

public class DateTimeUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtils.class);
	
	/**
	 * 常规日期时间模式(长时间): yyyy-MM-dd HH:mm:ss
	 */
	public static final String GENERAL_DATETIME_PATTERN_LT = "yyyy-MM-dd HH:mm:ss";

	public static final String GENERAL_DATETIME_PATTERN_LT_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 常规日期时间模式(短时间): yyyy-MM-dd HH:mm
	 */
	public static final String GENERAL_DATETIME_PATTERN_ST = "yyyy-MM-dd HH:mm";


	public static final String TALK_PATTEN = "yyyy年M月d日H时m分";

	/**
	 * 常规日期时间模式(长时间)(中文): yyyy年MM月dd日 HH:mm:ss
	 */
	public static final String GENERAL_DATETIME_PATTERN_LT_CN = "yyyy年MM月dd日 HH:mm:ss";

	public static final String GENERAL_DATETIME_PATTERN_DU_CN = "yyyy年MM月dd日 HH:mm";

	public static final String GENERAL_DATETIME_PATTERN_DU_CN2= "M月dd日 HH:mm";

	/**
	 * 常规日期时间模式(清除格式): yyyyMMddHHmmss
	 */
	public static final String GENERAL_DATETIME_PATTERN_CLEAR = "yyyyMMddHHmmss";
	public static final String PATTEN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	/**
	 * 常规日期时间模式(清除格式并简化): yyMMddHHmmss
	 */
	public static final String GENERAL_DATETIME_PATTERN_SIMPLIFIED = "yyMMddHHmmss";
	public static final String PATTEN_yyMMddHHmmss = "yyMMddHHmmss";
	public static final String PATTEN_yyMMddHHmmssSSS = "yyMMddHHmmssSSS";
	public static final String PATTEN_yyyyMMddHH = "yyyyMMddHH";

    /**
     * 订单发车时间日期时间模式(清除格式并简化): yyMMddHHmm
     */
    public static final String PATTEN_yyyyMMddHHmm = "yyyyMMddHHmm";

	/**
	 * 短日期模式: yyyy-MM-dd
	 */
	public static final String SHORT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String PATTEN_yyyy_MM_dd = "yyyy-MM-dd";
	/**
	 * 短日期模式(中文): yyyy年MM月dd日
	 */
	public static final String SHORT_DATE_PATTERN_CN = "yyyy年MM月dd日";
	
	/**
	 * 短日期模式(清除格式): yyyyMMdd
	 */
	@Deprecated
	public static final String SHORT_DATE_PATTERN_CLEAR = "yyyyMMdd";
	public static final String PATTEN_yyyyMMdd = "yyyyMMdd";
	public static final String PATTEN_yyyy_MM_dd_POINT = "yyyy.MM.dd";
	/**
	 * 长时间模式: HH:mm:ss
	 */
	public static final String LONG_TIME_PATTERN = "HH:mm:ss";
	
	/**
	 * 短时间模式: HH:mm
	 */
	public static final String SHORT_TIME_PATTERN = "HH:mm";
	
	
	/**
     * 下划线日期
     */
    public static final String yyyy_MM_dd = "yyyy_MM_dd";

    /**
     * 年月
     */
    public static final String yyyy_MM = "yyyy-MM";


	/**
	 * x月 x日
	 */
	public static final String MM_dd_PATTERN_CN = "MM月dd日";

	public static final Long ONE_HOUR_MILL_SECOND=60*60*1000L;

	public static final Long ONE_DAY_MILL_SECOND=24*ONE_HOUR_MILL_SECOND;

    public static final Long ONE_HOUR_SECOND=ONE_HOUR_MILL_SECOND/1000;

    public static final Long ONE_DAY_SECOND=ONE_DAY_MILL_SECOND/1000;

	public final static TimeZone GMT_ZONE = TimeZone.getTimeZone("GMT");
	public final static String RFC1123_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";


	/**
	 * 判断日期字符串是否为常规日期模式，这里的常规日期模式指yyyy-MM-dd HH:mm:ss，不考虑长时间和短时间的区别。
	 * 
	 * <p>General Date Pattern: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr 需要进行常规日期模式判断的字符串
	 * @return true 如果字符串是常规日期模式
	 */
	public static boolean isGeneralDatePattern(String dateStr) { 
		SimpleDateFormat dateFormat = new SimpleDateFormat(GENERAL_DATETIME_PATTERN_LT);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 判断日期字符串是否为短日期模式。
	 * 
	 * <p>Short Date Pattern: yyyy-MM-dd
	 * 
	 * @param dateStr 需要进行短日期模式判断的字符串
	 * @return true 如果字符串是短日期模式
	 */

	public static boolean isShortDatePattern(String dateStr) { 
		SimpleDateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_PATTERN);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static boolean isLegalDate(String dateStr, String pattern) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
	}

	/**
	 * 按指定格式解析日期字符串，返回一个日期对象。
	 * 
	 * @param dateStr 需要进行格式解析的字符串
	 * @param pattern 需要解析成的目标格式
	 * @return 按照pattern解析的日期对象
	 */

	public static Date parseDate(String dateStr, String pattern) { 
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 按指定格式格式化日期对象，返回日期字符串。
	 * 
	 * @param date 需要进行格式化的日期对象
	 * @param pattern 需要格式化的目标格式
	 * @return 按照pattern格式化的日期字符串
	 */

	public static String formatDate(Date date, String pattern) { 
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		try {
			return dateFormat.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	public static String formatDate(Timestamp timestamp,String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		try {
			return dateFormat.format(timestamp);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 按指定格式格式化当前日期，返回当前日期字符串。
	 *  
	 * @param pattern 需要格式化的目标格式
	 * @return 按照pattern格式化的当前日期字符串
	 */
	
	public static String formatCurrentDate(String pattern) {
		Date date = new Date();
		return formatDate(date, pattern);
	}
	
	/**
	 * 指定起始格式和目标格式，格式化日期字符串，返回目标格式的日期字符串。
	 * 
	 * @param source 需要进行格式化的日期字符串
	 * @param fromPattern 起始格式
	 * @param toPattern 目标格式
	 * @return 目标格式的日期字符串
	 */

	public static String formatDate(String source, String fromPattern, String toPattern) {
		Date date = parseDate(source, fromPattern);
		return formatDate(date, toPattern);
	}

	/**
	 * 根据给定的格式将字符串转换为日期
	 * @param source 需要进行格式化的日期字符串
	 * @param fromPattern 格式
	 * @return 日期
	 */
	public static Date formatDate(String source, String fromPattern) {
		return parseDate(source, fromPattern);
	}
	/**
	 * 将Integer秒数时间戳解析为指定格式的时间字符串。
	 * 
	 * @param second Integer时间戳-精确到秒
	 * @return 常规日期模式的时间字符串
	 */
	
	public static String parseTimeSeconds(Integer second, String pattern) {
		long millis = second.longValue() * 1000L;
		Date date = new Date(millis);
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		return dateFormat.format(date);
	}
	
	/**
	 * 将long型毫秒解析指定格式的的时间字符串。
	 * 
	 * @param millis long型时间戳-精确到毫秒
	 * @param pattern 目标格式
	 * @return 常规日期模式的时间字符串
	 */
	
	public static String parseTimeMillis(long millis, String pattern) {
		Date date = new Date(millis);
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		return dateFormat.format(date);
	}
	
	/**
	 * 将Integer秒数时间戳转化为long型毫秒。
	 * 
	 * @param second Integer时间戳-精确到秒
	 * @return long型时间戳-精确到毫秒
	 */
	
	public static long secondToMillis(Integer second) {
		return second.longValue() * 1000L;
	}
	
	/**
	 * 将long型毫秒转化成Integer秒数。
	 * 
	 * @param millis long型时间戳-精确到毫秒
	 * @return Integer时间戳-精确到秒
	 */
	
	public static Integer millisToSecond(long millis) {
		return Integer.valueOf((int) (millis/1000));
	}
	public static Integer millisToSecond(Date time) {
		return millisToSecond(time.getTime());
	}
	/**
	 * 将时间字符串按指定格式转化成毫秒。
	 * 
	 * @param dateStr 需要进行转化的时间字符串
	 * @param pattern 目标格式
	 * @return long型时间戳-精确到毫秒
	 */
	
	public static long toTimeMillis(String dateStr, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		try {
			Date date = dateFormat.parse(dateStr);
			return date.getTime();
		} catch (ParseException e) {
			LOGGER.error("toTimeMillis exception",e);
			return 0;
		}
	}

	/**
	 * 获取指定天数的开始秒数时间戳
	 * @param day
	 * @return
     */
	public static long getBeforeDateStartSecond(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis()/1000;
	}

	/**
	 * 获取指定天数的结束秒数时间戳
	 * @param day
	 * @return
	 */
	public static long getBeforeDateEndSecond(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTimeInMillis()/1000;
	}
	
	/**
	 * 将当前时间格式化为毫秒
	 * 
	 * @return long型时间戳-精确到毫秒
	 */
	
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 将当前时间格式化为Integer时间戳-精确到秒
	 * @return Integer时间戳-精确到秒
	 */
	
	public static Integer currentTimeSeconds() {
		return Integer.valueOf((int) (currentTimeMillis() / 1000));
	}

	/**
	 * 得到现在的年份
	 * 
	 * @return int格式的年份
	 */

	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 得到现在的月份
	 * 
	 * @return int格式的月份
	 */

	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 得到现在具体是一个月中的第几天
	 * 
	 * @return int格式的天数
	 */

	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 得到现在的小时数
	 * 
	 * @return int格式的小时数
	 */

	public static int getHour() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	
	public static int getHour(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour;
	}
	
	public static int getHour(String time, String pattern) {
        Date timeDate = parseDate(time, pattern);
        if(timeDate == null) {
            throw new ArgumentsException("invalid arguments format");
        }
        return getHour(timeDate);
    }

	/**
	 * 得到现在的分钟 
	 * 
	 * @return int格式的分钟数
	 */

	public static int getMinute() {
		Calendar cal = Calendar.getInstance();
		int min = cal.get(Calendar.MINUTE);
		return min;
	}
	
	public static int getMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.MINUTE);
        return hour;
    }
    
    public static int getMinute(String time, String pattern) {
        Date timeDate = parseDate(time, pattern);
        if(timeDate == null) {
            throw new ArgumentsException("invalid arguments format");
        }
        return getMinute(timeDate);
    }

	/**
	 * 得到现在的秒数
	 * 
	 * @return int格式的秒数
	 */

	public static int getSecond() {
		Calendar cal = Calendar.getInstance();
		int sec = cal.get(Calendar.SECOND);
		return sec;
	}
	

	/**
	 * 在一个日期基础上加/减年数
	 * 
	 * @param myDate 需要进行运算的日期
	 * @param amount 需要加减的年数，正整数则为加，负整数则为减
	 * @return 进行运算后的日期对象
	 */

	public static Date addYear(Date myDate, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.YEAR, amount);
		return cal.getTime();
	}

	/**
	 * 在一个日期的基础上加/减天数
	 * 
	 * @param myDate 需要进行运算的日期
	 * @param amount 需要加减的天数，正整数则为加，负整数则为减
	 * @return 进行运算后的日期对象
	 */

	public static Date addDay(Date myDate, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}

	/**
	 * 在一个日期的基础上加/减小时
	 * 
	 * @param myDate 需要进行运算的日期
	 * @param amount 需要加减的小时数，正整数则为加，负整数则为减
	 * @return 进行运算后的日期对象
	 */

	public static Date addHour(Date myDate, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.HOUR_OF_DAY, amount);
		return cal.getTime();
	}

	/**
	 * 在一个日期的基础上加/减分钟
	 * 
	 * @param myDate 需要进行运算的日期
	 * @param amount 需要加减的分钟数，正整数则为加，负整数则为减
	 * @return 进行运算后的日期对象
	 */

	public static Date addMin(Date myDate, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.MINUTE, amount);
		return cal.getTime();
	}

	/**
	 * 在一个日期的基础上加/减秒
	 * 
	 * @param myDate 需要进行运算的日期
	 * @param amount 需要加减的秒数，正整数则为加，负整数则为减
	 * @return 进行运算后的日期对象
	 */

	public static Date addSec(Date myDate, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.SECOND, amount);
		return cal.getTime();
	}

	/**
	 * 获取某个日期对象的后一天
	 * 
	 * @param date 需要进行运算的日期
	 * @return 该日期的后一天，日期对象
	 */

	public static Date getNextDate(Date date) { // 原方法为getNextDate 5.工具方法
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.DATE, 1);
			date = cal.getTime();
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取某个日期对象的前一天
	 *
	 * @param date 需要进行运算的日期
	 * @return 该日期的前一天，日期对象
	 */

	public static Date getLastDate(Date date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			date = cal.getTime();
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取两个日期对象之间的间隔天数
	 * 
	 * @param startDate 起始日期对象
	 * @param endDate 结束日期对象
	 * @return 两个日期对象之间的间隔天数，int格式
	 */

	public static int getDaysBetween(Date startDate, Date endDate) { // 原方法为getIntervalDay 5.工具方法
		Calendar scal = Calendar.getInstance();
		scal.setTime(startDate);
		scal.set(Calendar.HOUR_OF_DAY, 0);
		scal.set(Calendar.MINUTE, 0);
		scal.set(Calendar.SECOND, 0);
		scal.set(Calendar.MILLISECOND, 0);
		Calendar ecal = Calendar.getInstance();
		ecal.setTime(endDate);
		ecal.set(Calendar.HOUR_OF_DAY, 0);
		ecal.set(Calendar.MINUTE, 0);
		ecal.set(Calendar.SECOND, 0);
		ecal.set(Calendar.MILLISECOND, 0);
		long duration = ecal.getTimeInMillis() - scal.getTimeInMillis();
		return (int) (duration / (1000 * 60 * 60 * 24));
	}

    public static long getDaysBetweenL(Date startDate, Date endDate) { // 原方法为getIntervalDay 5.工具方法
		Calendar scal = Calendar.getInstance();
		scal.setTime(startDate);
		scal.set(Calendar.HOUR_OF_DAY, 0);
		scal.set(Calendar.MINUTE, 0);
		scal.set(Calendar.SECOND, 0);
		scal.set(Calendar.MILLISECOND, 0);
		Calendar ecal = Calendar.getInstance();
		ecal.setTime(endDate);
		ecal.set(Calendar.HOUR_OF_DAY, 0);
		ecal.set(Calendar.MINUTE, 0);
		ecal.set(Calendar.SECOND, 0);
		ecal.set(Calendar.MILLISECOND, 0);
		long duration = ecal.getTimeInMillis() - scal.getTimeInMillis();
		return  duration / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 获取两个date之间的间隔小时数
	 * 
	 * @param startDate 起始日期对象
	 * @param endDate 结束日期对象
	 * @return 两个日期对象之间的间隔小时数，int格式
	 */
	
	public static int getHoursBetween(Date startDate, Date endDate) {
		Calendar scal = Calendar.getInstance();
		scal.setTime(startDate);
		Calendar ecal = Calendar.getInstance();
		ecal.setTime(endDate);
		long duration = ecal.getTimeInMillis() - scal.getTimeInMillis();
		return (int) ((duration) / (1000 * 60 * 60));
	}

	/**
	 * 获取两个date之间的间隔分钟数
	 *
	 * @param startDate 起始日期对象
	 * @param endDate 结束日期对象
	 * @return 两个日期对象之间的间隔分钟数，long格式
	 */
	public static long getMinutesBetween(Date startDate, Date endDate) {
		Calendar scal = Calendar.getInstance();
		scal.setTime(startDate);
		Calendar ecal = Calendar.getInstance();
		ecal.setTime(endDate);
		long duration = ecal.getTimeInMillis() - scal.getTimeInMillis();
		return (duration) / (1000 * 60);
	}

	/**
	 * 根据日期计算星期几
	 * @param pTime
	 * @param patten
	 * @return
	 * @throws Exception
	 */
    public static int dayForWeek(String pTime,String patten){  
        
        Calendar c = Calendar.getInstance();  
        c.setTime(parseDate(pTime, patten));  
        int dayForWeek = 0;  
        if(c.get(Calendar.DAY_OF_WEEK) == 1){  
         dayForWeek = 7;  
        }else{  
         dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
        }  
        return dayForWeek;  
       }

	/**
	 * 获取本周的凌晨0点时间
	 */
	public static Date getZeroTimeOfThisWeek() {
		Calendar c = Calendar.getInstance();
		if (Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK)) {
			//java 里面默认星期日是第一天
			c.add(Calendar.DAY_OF_WEEK, -1);
		}
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 给定的时间是否在本周内
	 * @param time
	 * @return
	 */
	public static boolean isInThisWeek(Long time) {
		if (time == null) {
			return false;
		}
		Date date = getZeroTimeOfThisWeek();//本周零点时间
		return time >= date.getTime();
	}

	public static int getTodayRemainTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (int)((calendar.getTimeInMillis() - System.currentTimeMillis())/1000);
    }

    public static Date addMonth(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, amount);
        return cal.getTime();
    }

    /**
     * 获取2个日期中的每一天
     * @param startDate 开始日期 yyyy-MM-dd
     * @param endDate 结束日期 yyyy-MM-dd
	 * @param returnPattern 返回日期格式模板
     * @return
     */
    public static List<String> formatDateBy2Dates(String startDate, String endDate,String returnPattern){
        List<String> dateList = Lists.newArrayList();
        Date sDate = parseDate(startDate, SHORT_DATE_PATTERN);
        Date eDate = parseDate(endDate, SHORT_DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sDate);

        while(cal.getTime().compareTo(eDate)<=0){
            String s = formatDate(cal.getTime(), returnPattern);
            cal.add(Calendar.DAY_OF_MONTH,1);
            dateList.add(s);
        }

        return dateList;
    }

	/**
	 * 获取一天时间的头和尾
	 * @param day
	 * @return
	 */
	public static Map<String,Long> getHeadAndTailOfDay(String day){

		Date date = parseDate(day, SHORT_DATE_PATTERN);
		if (date == null){
			return null;
		}

		Map<String,Long> result = Maps.newHashMap();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,1);

		result.put("startTime",date.getTime()/1000);
		result.put("endTime",calendar.getTime().getTime()/1000 -1);
		return  result;
	}

	/**
	 * 获取今天0点的时间
	 * @return
	 */
	public static Date getToday(){
		return getDateHourMinSec(new Date(),0,0,0);
	}

	/**
	 * 获取日期的特定时间
	 * @return
	 */
	public static Date getDateHourMinSec(Date date,int hour,int min,int sec){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, sec);
		return calendar.getTime();
	}

    /**
     * 获取n天后指定时间日期
     * @param day
     * @param hour
     * @param min
     * @param sec
     * @return
     */
    public static Date getDayHourMinSec(int day, int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, sec);
        return cal.getTime();
    }

    /**
     * 把一天划分几个segment, 指定每一个segment的时间长度（s）
     * @return
     */
    public static List<Map<String, Date>> cutSegment(Date date, int step){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long start = calendar.getTimeInMillis();

        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long end = calendar.getTimeInMillis();

        long total = end - start;

        List<Map<String, Date>> result = Lists.newLinkedList();
        long vernier = start;
        long stepMillis = step * 1000;
        double stepCount = Math.ceil(new BigDecimal(total).divide(new BigDecimal(stepMillis), BigDecimal.ROUND_UP).doubleValue());
        for (int i = 0; i < stepCount; i++) {
            long tmpEnd = vernier + stepMillis >= end ? end : vernier + stepMillis;
            Map<String,Date> timeMap = new HashMap<String,Date>();
            timeMap.put("start",new Date(vernier));
            timeMap.put("end",new Date(tmpEnd));
            result.add(timeMap);

            vernier = tmpEnd + 1000;
        }
        return result;
    }

    /**
     * 当前时间至endDate的天数
     * @param endDate
     * @return
     */
    public static int intervalDays(Date endDate) {
        int days = Days.daysBetween(DateTime.now(), new DateTime(endDate.getTime())).getDays() + 1;
        return days;
    }

	/**
	 * 获取时间差值
	 *
	 * @return dd天hh时mm分
	 */
	public static String getIntervalTime(Date begin, Date end) {
		if (begin == null
				|| end == null) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		long day = 0;
		long hour = 0;
		long min = 0;
		long time1 = begin.getTime();
		long time2 = end.getTime();
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		day = diff / (24 * 60 * 60 * 1000);
		hour = (diff / (60 * 60 * 1000) - day * 24);
		min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		if (day > 0) {
			result.append(day).append("天");
		}
		if (hour > 0) {
			result.append(hour).append("时");
		}
		if (min > 0) {
			result.append(min).append("分");
		}
		return result.toString();
	}

	/**
	 * 获取当天结束的unixtime的秒数
	 *
	 * @return
	 */
	public static long getTodayEndUnixTime() {
		// 当天的23:59
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		// 转换为String
		SimpleDateFormat sdf = new SimpleDateFormat(GENERAL_DATETIME_PATTERN_LT);
		// 转换为Timestamp
		Timestamp ts = Timestamp.valueOf(sdf.format(calendar.getTime()));
		return ts.getTime() / 1000;
	}


	/**
	 * format for RFC 1123 date string --> "Sun, 18 Nov 2018 09:44:59 GMT"
	 * @param date
	 * @return
	 */
	public static String rfc1123Format(Date date){
		DateFormat rfc1123Format = new SimpleDateFormat(RFC1123_PATTERN, Locale.US);
		rfc1123Format.setTimeZone(GMT_ZONE);
		return rfc1123Format.format(date);
	}
}