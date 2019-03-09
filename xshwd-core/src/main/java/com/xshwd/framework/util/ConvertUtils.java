package com.xshwd.framework.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: SK ON  2018/10/25 10:43
 * @Description: 转换工具
 */
public class ConvertUtils {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * 将字符串转换成数字(Integer)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static Integer toInteger(String value, Integer defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    /**
     * 将字符串转换成数字(Integer)
     *
     * @param value 需要转换成数字的字符串
     * @return 转换后的数字
     */
    public static Integer toInteger(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 将字符串转换成数字(Long)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static Long toLong(String value, Long defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成数字(Long)
     *
     * @param value 需要转换成数字的字符串
     * @return 转换后的数字
     */
    public static Long toLong(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 将字符串转换成数字(Float)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static Float toFloat(String value, Float defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }

        try {
            return Float.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    /**
     * 将字符串转换成数字(double)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static Double toDouble(String value, Double defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成数字(BigDecimal)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static BigDecimal toBigDecimal(String value, BigDecimal defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成布尔值(boolean)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成布尔值的字符串
     * @param defaultValue 默认值
     * @return 转换后的布尔值
     */
    public static boolean toBoolean(String value, boolean defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        if ("on".equals(value)) {
            return true;
        }
        if ("off".equals(value)) {
            return false;
        }
        if ("1".equals(value)) {
            return true;
        }
        if ("0".equals(value)) {
            return false;
        }
        try {
            return Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成布尔值(Boolean)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成布尔值的字符串
     * @param defaultValue 默认值
     * @return 转换后的布尔值
     */
    public static Boolean toBoolean(String value, Boolean defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        if ("on".equalsIgnoreCase(value)) {
            return Boolean.TRUE;
        }
        if ("off".equalsIgnoreCase(value)) {
            return Boolean.FALSE;
        }
        if ("1".equals(value)) {
            return Boolean.TRUE;
        }
        if ("0".equals(value)) {
            return Boolean.FALSE;
        }
        try {
            return Boolean.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    private final static long PRICE_RATIO = 100L;
    private final static int BIGDECIMAL_SCALE = 10;
    private final static int BIGDECIMAL_YUAN_SCALE = 2;

    /**
     * 将外部元的金额转换成 Long的数据
     *
     * @param strPrice 字符串的价格
     * @return 返回以 Long 型的金额
     */
    public static Integer priceToLong(String strPrice) {

        if (StringUtils.isEmpty(strPrice)) {
            return null;
        }
        try {
            BigDecimal price = toBigDecimal(strPrice, null);
            if (null == price) {
                return 0;
            }
            return price.multiply(BigDecimal.valueOf(PRICE_RATIO)).intValue();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将数据库里面的价格转换到以元为单位
     *
     * @param price 需要转换的价格
     * @return 返回以元为单位的字符串
     */
    public static String convertToYuan(Integer price) {
        if (null == price) {
            return StringUtils.EMPTY;
        }
        if (price.intValue() == 0) {
            return "0";
        }
        return removeZero( convertToBigDecimal(price).toString() );
    }

    /**
     * 去掉小数点后面的0
     * @param s
     * @return
     */
    public static String removeZero(String s){
        if (null == s || s.isEmpty()) {
            return "";
        }
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }


    /**
     * 将数据库中long类型的金额 转化为分 元 等单位
     *
     * @param price
     * @return
     */
    public static BigDecimal convertToBigDecimal(Integer price) {
        BigDecimal result = BigDecimal.ZERO;
        if (null == price || 0 == price.intValue()) {
            return result;
        }
        try {
            result = (new BigDecimal(price).divide(BigDecimal.valueOf( PRICE_RATIO ),  BIGDECIMAL_SCALE, BigDecimal.ROUND_HALF_UP))
                    .setScale( BIGDECIMAL_YUAN_SCALE, BigDecimal.ROUND_HALF_UP);
            return result;
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }



    public static Date getDayStartTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获取当天的结束时间
    public static Date getDayEndTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    // 获取上周的开始时间
    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal);
    }

    // 获取上周的结束时间
    public static Date getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return getDayEndTime(cal);
    }


    // 获取上月的开始时间
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDayStartTime(calendar);
    }

    // 获取上月的结束时间
    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDayEndTime(calendar);
    }


    /**
     * 生成二維碼CODE
     * @param orderNo
     * @return
     */
    public static String getOrderQRCode(String orderNo){
       String currentDate = dateFormat.format(Calendar.getInstance().getTime()).substring(3, 8);
       String increment = orderNo.substring(16, orderNo.length()-1);
       return  StringUtils.leftPad(String.valueOf(Long.parseLong(currentDate + increment)>>4), 8, "0");
    }



//    public static void main(String[] args) {
//
//        Date strat = ConvertUtils.getBeginDayOfLastMonth();
//        Date end = ConvertUtils.getEndDayOfLastMonth();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      System.out.println(dateFormat.format(Calendar.getInstance().getTime()).substring(3, 8));
//      String orderNo = "HWJY2018060500000027296";
//        String increment = orderNo.substring(16, orderNo.length()-1);
//        System.out.println(increment);
//        System.out.println(Long.parseLong(dateFormat.format(Calendar.getInstance().getTime()).substring(3, 8) + increment));
//        System.out.println(Long.parseLong(dateFormat.format(Calendar.getInstance().getTime()).substring(3, 8) + increment)>>4);
//        System.out.println(getOrderQRCode("HWJY2018060500000028164"));
//        System.out.println(getOrderQRCode("HWJY2018060500000029807"));
//
//        System.out.println(getOrderQRCode("HWJY2018060600000004245"));
//        System.out.println(getOrderQRCode("HWJY2018060400000012204"));
//
//        System.out.println(getOrderQRCode("HWJY2018060600000016364"));
//         System.out.println(getOrderQRCode("HWJY2018071100000018187"));
//    }


}
