//package com.xshwd.framework.util;
//
//import org.apache.commons.lang3.StringUtils;
//import org.elasticsearch.common.geo.GeoHashUtils;
//import org.elasticsearch.common.geo.GeoUtils;
//
//import java.util.List;
//
///**
// * 距离工具类
// * Create By zhouchang
// */
//public class DistanceUtil {
///**/
//    private static final int LENGTH=8;
//
//    private static final int DISTANCE=3;
//
//    private static final  double EARTH_RADIUS = 6371000;//赤道半径(单位m)
//
//
///*    public static void main(String[] args){
//
//*//*
//        double distance = getDistance(new Double("104.024960"), new Double("30.691910"), new Double("104.02184"), new Double("30.69131"));
//*//*
//        String areaCode1 = getAreaCode("104.024960", "30.691910", "3");
//        String areaCode2 = getAreaCode("104.024960", "30.691910", "0.002");
//        String areaCode3 = getAreaCode("104.024960", "30.691910", "45");
//        String areaCode4 = getAreaCode("104.024960", "30.691910", "10");
//        String areaCode5 = getAreaCode("104.024960", "30.691910", "5546");
//        String areaCode6 = getAreaCode("104.024960", "30.691910", "123");
//        String areaCode7 = getAreaCode("104.024960", "30.691910", "500");
//        System.out.println("--测试areaCode--1--" +areaCode1);
//        System.out.println("--测试areaCode--2--" +areaCode2);
//        System.out.println("--测试areaCode--3--" +areaCode3);
//        System.out.println("--测试areaCode--4--" +areaCode4);
//        System.out.println("--测试areaCode--5--" +areaCode5);
//        System.out.println("--测试areaCode--6--" +areaCode6);
//        System.out.println("--测试areaCode--7--" +areaCode7);
//
//
//    }*/
//
//
//    /**
//     * 根据用户当前经纬度、目标经纬度计算距离
//     */
//
//    /**
//     * 根据经纬度获取geoHashCode 默认长度8 精度19m+-
//     * @param longitude 经度
//     * @param latitude  纬度
//     * @return geoHashCode
//     */
//    public static String tran2GeoHashCode(String longitude,String latitude){
//        return tran2GeoHashCode(longitude,latitude,LENGTH);
//    }
//
//    /**
//     * 根据经纬度计算距其他地址经纬度的距离
//     * @param lon1S 经度1
//     * @param lat1S  纬度1
//     * @param lon2S 经度2
//     * @param lat2S  纬度2
//     * @return distance 距离(m)
//     */
//    public static double getDistance(String lon1S,String lat1S
//            ,String lon2S,String lat2S){
//        if(StringUtils.isBlank(lon1S)||StringUtils.isBlank(lat1S)||StringUtils.isBlank(lon2S)||StringUtils.isBlank(lat2S)){
//            return 0D;
//        }
//        Double lon1 = Double.valueOf(lon1S);
//        Double lon2 = Double.valueOf(lon2S);
//        Double lat1 = Double.valueOf(lat1S);
//        Double lat2 = Double.valueOf(lat2S);
//
//        return GeoUtils.arcDistance(lat1,lon1,lat2,lon2);
//    }
//    /**
//     * 根据经纬度计算距其他地址经纬度的距离
//     * @param lon1 经度1
//     * @param lat1  纬度1
//     * @param lon2 经度2
//     * @param lat2  纬度2
//     * @return distance 距离(m)
//     */
//    public static double getDistance(Double lon1,Double lat1
//        ,Double lon2,Double lat2){
//
//        double x1 = Math.cos(lat1) * Math.cos(lon1);
//        double y1 = Math.cos(lat1) * Math.sin(lon2);
//        double z1 = Math.sin(lat1);
//        double x2 = Math.cos(lat2) * Math.cos(lon2);
//        double y2 = Math.cos(lat2) * Math.sin(lon2);
//        double z2 = Math.sin(lat2);
//        double lineDistance =
//                Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
//        double s = EARTH_RADIUS * Math.PI * 2 * Math.asin(0.5 * lineDistance) / 180;
//        return Math.round(s * 10000) / 10000;
//    }
//
///*    public static LinkedList<T> sortListByDistance(LinkedList<T> orginList){
//
//        //TODO 进行排序
//
//        return null;
//    }*/
//
//
//    /**
//     * 根据经纬度获取指定长度geoHashCode
//     * @param longitude 经度
//     * @param latitude  纬度
//     * @param length 生成的code长度 与精度有关
//     * @return geoHashCode
//     */
//    public static String tran2GeoHashCode(String longitude,String latitude,Integer length){
//        //TODO 校验返回
//        if(length==null||length==0){
//            length=LENGTH;
//        }
//        if(!GeoUtils.isValidLongitude(Double.parseDouble(longitude))
//            ||!GeoUtils.isValidLatitude(Double.parseDouble(latitude))){
//            throw new RuntimeException("经纬度非法");
//        }
//        String geoHashCode = GeoHashUtils.stringEncode(new Double(longitude), new Double(latitude),length);
//        return geoHashCode;
//    }
//
//    /**
//     * 根据用户经纬度和范围获取区块
//     * @param longitude
//     * @param latitude
//     * @param distance 范围（Km）
//     * @return
//     */
//    public static String getAreaCode(String longitude,String latitude,String distance){
//        //将distance转换为精度
//        int length = 12;
//        Double dis = new Double(distance);
//        if(dis>630D){
//            length = 1;
//        }else if(dis>78D){
//            length = 2;
//        }else if(dis>19.5D){
//            length = 3;
//        }else if(dis>78D){
//            length = 4;
//        }else if(dis>2.4D){
//            length = 5;
//        }else if(dis>0.61){
//            length = 6;
//        }else if(dis>0.076){
//            length = 7;
//        }else if(dis>0.019D){
//            length = 8;
//        }else if(dis>0.0024D){
//            length = 9;
//        }else if(dis>0.0006D) {
//            length = 10;
//        }else if(dis < 0){
//            return null;
//        }
//        return tran2GeoHashCode(longitude,latitude,length);
//    }
//
//    /**
//     * 根据经纬度获取对应九个geoHashCode 默认距离3KM
//     * @param longitude 经度
//     * @param latitude 纬度
//     * @return AreaCodes 所属geoHash区域和周围的八个区域
//     */
//    public static List<String> getAreaCodes(String longitude, String latitude){
//
//        //TODO
//
//        return getAreaCodes(longitude,latitude,DISTANCE);
//    }
//
//    /**
//     * 根据经纬度、距离(Km)获取对应九个geoHashCode
//     * @param longitude 经度
//     * @param latitude 纬度
//     * @param distance 距离
//     * @return AreaCodes 所属geoHash区域和周围的八个区域
//     */
//    public static List<String> getAreaCodes(String longitude, String latitude,int distance){
//
//        //TODO
//        int level = tranDistanceToLevel(distance);
//        return null;
//    }
//
//    private static int tranDistanceToLevel(int distance){
//
//
//        return 0;
//    }
//
//    public static String validItude(String longitude,String latitude){
//        if(StringUtils.isBlank(longitude) ||StringUtils.isBlank(latitude)
//            ||!GeoUtils.isValidLongitude(Double.parseDouble(longitude))
//            ||!GeoUtils.isValidLatitude(Double.parseDouble(latitude))){
//            return "经纬度异常";
//        }
//        return null;
//    }
//
//    public static String validItude(Double longitude,Double latitude){
//        if(!GeoUtils.isValidLongitude(longitude)
//            ||!GeoUtils.isValidLatitude(latitude)){
//            return "经纬度异常";
//        }
//        return null;
//    }
//
//
//
//
//}
