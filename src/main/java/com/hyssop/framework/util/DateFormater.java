package com.hyssop.framework.util;

import lombok.Synchronized;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @Author jie.zhang
 * @create_time 2019/8/30 14:19
 * @updater
 * @update_time
 **/
public class DateFormater {
    public static volatile DateFormater instent = new DateFormater();
    private void DateFormater(){};
    public static DateFormater getInstance(){
        return instent;
    }



    public static LocalDateTime dateTimeStrToLocalDateTimeForWEMA(String transTime) {
        //2019/7/26 6:48:00
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/M/d H:mm:ss", Locale.ENGLISH);
        LocalDateTime ldt = LocalDateTime.parse(transTime, df);
        //System.out.println("String类型的时间转成LocalDateTime："+ldt);

        //LocalDateTime time = LocalDateTime.now();
        //String localTime = df.format(time);
        //System.out.println("LocalDateTime转成String类型的时间："+localTime);
        return ldt;
    }

    public static LocalDateTime dateTimeStrToLocalDateTimeForZENITH(String transTime) {
        //2019/7/26 6:48:00
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss", Locale.ENGLISH);
        LocalDateTime ldt = LocalDateTime.parse(transTime, df);
        //System.out.println("String类型的时间转成LocalDateTime："+ldt);

        //LocalDateTime time = LocalDateTime.now();
        //String localTime = df.format(time);
        //System.out.println("LocalDateTime转成String类型的时间："+localTime);
        return ldt;
    }

    public static LocalDateTime dateTimeStrToLocalDateTimeForGTB_BANK(String transTime) {
        //2019/7/26 6:48:00
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/M/d H:mm:ss", Locale.ENGLISH);
        LocalDateTime ldt = LocalDateTime.parse(transTime, df);
        //System.out.println("String类型的时间转成LocalDateTime："+ldt);

        //LocalDateTime time = LocalDateTime.now();
        //String localTime = df.format(time);
        //System.out.println("LocalDateTime转成String类型的时间："+localTime);
        return ldt;
    }

    public static LocalDateTime dateTimeStrToLocalDateTimeForUbaBank(String transTime) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss", Locale.ENGLISH);
        LocalDateTime ldt = LocalDateTime.parse(transTime, df);
        //System.out.println("String类型的时间转成LocalDateTime："+ldt);

        //LocalDateTime time = LocalDateTime.now();
        //String localTime = df.format(time);
        //System.out.println("LocalDateTime转成String类型的时间："+localTime);
        return ldt;
    }

    public static LocalDateTime dateTimeStrToLocalDateTimeForFCMB(String transTime) {
        //7/22/2019
        //	DateTimeFormatter df = DateTimeFormatter.ofPattern("M/dd/yyyy",Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(transTime, DateTimeFormatter.ofPattern("M/dd/yyyy"));
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime;
    }

    public static LocalDateTime dateTimeStrToLocalDateTimeForInterSwitch(String transTime) {
        //2019/7/22 1:16
        LocalDate localDate = LocalDate.parse(transTime, DateTimeFormatter.ofPattern("yyyy/M/d H:mm:ss"));
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime;
    }

    public static LocalDateTime dateTimeStrToLocalDateTimeForSTERLING(String transTime) {
        LocalDate localDate = LocalDate.parse(transTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime;
    }
    public static LocalDateTime dateTimeStrToLocalDateTimeForacessBank(String transTime) {
        //Monday 22nd July, 2019 01:13 PM
        String[] subStr = transTime.split(",");
        String year = subStr[1].split(" ")[1];
        String ourAndseconf = subStr[1].split(" ")[2]+" "+subStr[1].split(" ")[3];
        String day = subStr[0].split(" ")[1].replace("nd","");
        String month =  subStr[0].split(" ")[2];
        int months=1 ;
        if("january".equalsIgnoreCase(month)){
            months=1;
        }
        if("February".equalsIgnoreCase(month)){
            months=2;
        }
        if("March".equalsIgnoreCase(month)){
            months=3;
        }
        if("April".equalsIgnoreCase(month)){
            months=4;
        }
        if("May".equalsIgnoreCase(month)){
            months=5;
        }
        if("June".equalsIgnoreCase(month)){
            months=6;
        }
        if("July".equalsIgnoreCase(month)){
            months=7;
        }
        if("August".equalsIgnoreCase(month)){
            months=8;
        }
        if("Septemper".equalsIgnoreCase(month)){
            months=9;
        }
        if("October".equalsIgnoreCase(month)){
            months=10;
        }
        if("November".equalsIgnoreCase(month)){
            months=11;
        }
        if("December".equalsIgnoreCase(month)){
            months=12;
        }

        transTime = year+"/"+String.valueOf(months)+"/"+day+" "+ourAndseconf;
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy/M/d hh:mm a", Locale.ENGLISH);
        LocalDateTime ldt = LocalDateTime.parse(transTime, sdf);
        return ldt;
    }

    public static void main(String[] args) {
      /*  dateTimeStrToLocalDateTimeForWEMA("2019/7/27 17:16:15");
        dateTimeStrToLocalDateTimeForZENITH("2019-07-26 19:37:55");
        dateTimeStrToLocalDateTimeForGTB_BANK("2019/7/22 7:40:18");
        dateTimeStrToLocalDateTimeForUbaBank("2019-07-26 13:02:38");
        dateTimeStrToLocalDateTimeForFCMB("7/22/2019");
        dateTimeStrToLocalDateTimeForInterSwitch("2019/7/22 1:11:06");
        dateTimeStrToLocalDateTimeForSTERLING("2019-07-24 17:46:20");*/

       // dateTimeStrToLocalDateTimeForacessBank("Monday 22nd July, 2019 01:13 PM");
      //  dateTimeStrToLocalDateTimeForEnglish("Aug 28, 2015 6:8:30 PM");
        System.out.println(dateTimeStrToLocalDateTimeForacessBank("Monday 22nd July, 2019 01:13 PM").toString()); ;
        String src="21/Feb/2012:11:20:33 +0800";
        SimpleDateFormat in=new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss ZZZZZ]",Locale.US);
        SimpleDateFormat out=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date d=in.parse(src);
            System.out.println(out.format(d));
        }catch (ParseException e){
            e.printStackTrace();
        }

    }
    public static LocalDateTime dateTimeStrToLocalDateTimeForEnglish(String transTime){
        LocalDate localDate = LocalDate.parse(transTime, DateTimeFormatter.ofPattern("MMM d, yyyy h:m:s a"));
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime;
    }

}