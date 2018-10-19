package com.adinnet.mainlib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Java main test
 */
public class TestMainClass {

    public static void main(String args[]) {
//        testTestUtils();
//        test1();
//        int a =4;
//        int b = 8;
//        int c = 6;
//        int d = 3;
//        System.out.println(a/4 +"==="+a%4);
//        System.out.println(b/4 +"==="+b%4);
//        System.out.println(c/4 +"==="+c%4);
//        System.out.println(getStringToDate("2017-07-12","yyyy-MM-dd"));
//        System.out.println(System.currentTimeMillis());
//        System.out.println( getStringToDate2("2018-07-18 11:28:36.010"));
//        System.out.println(timet("1532280300"));
//        System.out.println(IsLateCheckDate(" 18:30",1532313060));
//        List<Bean> list = new ArrayList<>();
//        list.add(new Bean("哈哈0"));
//        for (int i = 0; i < 5; i++) {
//            list.add(new Bean("item"+i));
//        }
//        list = removeDuplicate(list);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("list.."+list.get(i));
//        }
//        System.out.println(isLetter("B门户"));
//        String mTextName="门户B";
//        System.out.println("...mTextName.."+mTextName.length());
//        if (isLetter(mTextName))mTextName = mTextName.length() >= 2 ? mTextName.substring(0,2) : mTextName;
//        else mTextName = mTextName.length() >= 2 ? mTextName.substring(0,mTextName.length()-2) : mTextName;
//        System.out.println("..mTextName...."+mTextName);
//        String startTime ="2018-01-09 09:09";
//        String begindate = startTime.substring(0,startTime.indexOf(" "));
//        startTime = startTime.substring(startTime.indexOf(" "),startTime.length())+":00";
//        System.out.println("..begindate...."+begindate);
//        System.out.println("..startTime...."+startTime);
//        String time ="用户";
//        String[] split = time.split(",");
//        List<String> contacts =new ArrayList<>();
//        contacts.addAll(Arrays.asList(split));
//        System.out.println("..split...."+IsLateCheckDate("2018-08-22 17:08:00","2018-08-22 17:08:00"));
//        System.out.println("..split...."+(getStringToDate22("2018-06-24") ==getStringToDate22("2018-08-22")));
//        System.out.println("..returnMsg...." + returnMsg());
//        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//        try {
//            System.out.println("..split...."+ dd.parse(dd.format(new Date())).getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        System.out.println(stringTaskFilter(6,2,"1321"));
    }

    /**正则匹配decimal*/
    public static String stringTaskFilter(int first,int secend,String str)throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String regEx  =  "[^[1-9]{0,"+(first-secend)+"}(\\.|0\\.)?[0-9]{0,"+secend+"}$]";
        Pattern p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher(str);
        System.out.println("xlee...m.matches()..."+m.matches());
        return   m.replaceAll("").trim();
    }


    public static boolean IsLateCheckDate(String firstTime, String compareTime) {
        try {
            return date2TimeStamp(firstTime) > date2TimeStamp(compareTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static long date2TimeStamp(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//mm:ss
        try {
            Date parse = simpleDateFormat.parse(dateString);
            return parse.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getStringToDate22(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }


    public static String getYearMonth(String date) {
        String tempDate = date;
        try {
            tempDate = tempDate.replace("年", "");
            tempDate = tempDate.replace("月", "");
            StringBuffer stringBuffer = new StringBuffer(tempDate);
            return tempDate.length() >= 6 ?
                    tempDate : stringBuffer.insert(tempDate.length() - 1, "0").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前系统时间_小时分钟
     */
    public static String getCurHourAndM(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");//mm:ss
        Date dateBean = new Date(date);
        return simpleDateFormat.format(dateBean);
    }

    /**
     * “scale-zoom”映射关系：https://www.cnblogs.com/yesyes/p/6785705.html
     *
     * @param scale 比例尺
     * @return zoom值
     */
    public static int scale2Zoom(int scale) {
        if (scale <= 10) return 19;
        else if (scale <= 25) return 18;
        else if (scale <= 50) return 17;
        else if (scale <= 100) return 16;
        else if (scale <= 200) return 15;
        else if (scale <= 500) return 14;
        else if (scale <= 1000) return 13;
        else if (scale <= 2000) return 12;
        else if (scale <= 5000) return 11;
        else if (scale <= 10000) return 10;
        else if (scale <= 20000) return 9;
        else if (scale <= 30000) return 8;
        else if (scale <= 50000) return 7;
        else if (scale <= 100000) return 6;
        else if (scale <= 200000) return 5;
        else if (scale <= 500000) return 4;
        else if (scale <= 1000000) return 3;
        else if (scale > 1000000) return 2;
        return 20;
    }

    public static boolean isLetter(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String regEx = "[^a-zA-Z]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean check(String fstrData) {
        char c = fstrData.charAt(0);
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return true;
        } else {
            return false;
        }
    }

    public static List<Bean> removeDuplicate(List<Bean> list) {
        Set set = new LinkedHashSet<Bean>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    static class Bean {
        String name;

        public Bean(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            return this.name == ((Bean) o).name;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


    public static SimpleDateFormat formatSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 获取当前系统时间_小时分钟
     */
    public static String getCurHourAndMM2() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");//mm:ss
        Date date = new Date(System.currentTimeMillis());
        return formatSSS.format(date);
    }

    public static String getDateAfter() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date(System.currentTimeMillis()));
        now.set(Calendar.DATE, now.get(Calendar.DATE) + 2);
        Date time = now.getTime();
        return time.toGMTString();
    }


    /***IsLateCheckDate 是否迟到
     * @param firstTime
     * @param compareTime
     * @return 0 相等 1 后面时间大（迟到） -1 前面时间大（未迟到）
     */
    public static boolean IsLateCheckDate(String firstTime, long compareTime) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            return compareTime < getStringToDate(firstTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static long date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String format1 = sdf.format(new Date(date));
            return sdf.parse(format1).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }


    public static long getStringToDate2(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        int i = Integer.parseInt(dateString);
//        String times = dateFormat.format(new Date(i * 1000L));
        try {
            return dateFormat.parse(dateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static String timet(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
//        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(lcc * 1000L));
        return times;
    }

    public static long toTimet(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm:ss");
//        long lcc = Long.valueOf(time);
//        int i = Integer.parseInt(time);
//        String times = sdr.format(new Date(time));
        try {
            return sdr.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 获取时间_小时分钟
     */
    public static String getHHAndMM(long time) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sssDate = null;
        try {
            sssDate = df.parse(String.valueOf(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getWeekAndHHAndMM(String time) {
        time = time.substring(0, time.lastIndexOf("."));
        System.out.println("xlee....time...." + time);
        DateFormat df = new SimpleDateFormat("EEEE");
        Date date = null;
        try {
            date = new Date(Long.parseLong(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date == null ? "" : df.format(date) + " " + getHHAndMM(date);
    }

    /**
     * 获取时间_小时分钟
     */
    public static String getHHAndMM(Date time) {
        DateFormat df = new SimpleDateFormat("HH:mm");
        return time == null ? "" : df.format(time);
    }


    public static long getStringToDate(String dateString) {
        StringBuilder sb = new StringBuilder();
        sb.append(getCurHourAndMM());
        sb.append(dateString);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//mm:ss
        try {
            Date parse = simpleDateFormat.parse(sb.toString());
            long time = parse.getTime() / 1000;
            System.out.println(time);
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当前系统时间_小时分钟
     */
    public static String getCurHourAndMM() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /***
     * nums = [2, 7, 11, 15], target = 9
     */
    public static void test1() {
        int nums[] = new int[]{2, 7, 11, 15};
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i; j < nums.length; j++) {
                if (temp + nums[j] == 9) {
                    System.out.println(temp + "====" + nums[j]);
                }
            }
        }
    }


}
