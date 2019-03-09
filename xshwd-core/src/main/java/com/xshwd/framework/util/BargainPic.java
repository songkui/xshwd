package com.xshwd.framework.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.OptionalDouble;

/**
 * 根据砍价规则,计算砍价金额
 */
public class BargainPic {

    private static Double[] bigDecimals = new Double[10];


    /**
     * 前六分之1人砍掉4分之1的价格
     * <p>
     * 获取金额
     *
     * @param total     总价格
     * @param number    当前砍价次数
     * @param totalNum  总次数
     * @param currMoney 当前砍掉总价格
     * @return 当前砍的金额
     */
    public synchronized static BigDecimal getMoney(BigDecimal total, BigDecimal currMoney, Integer totalNum, Integer number) {
        //规则以后的金额
        BigDecimal subtract = total.divide(new BigDecimal(4)).setScale(0, BigDecimal.ROUND_HALF_UP);
        //规则以后的次数(强制去掉小数,保留整数)
        Integer beforeNum = totalNum / 6;
        //规则内可用的金额
        BigDecimal avlRuleMoney = subtract.subtract(total.subtract(currMoney)).setScale(0, BigDecimal.ROUND_HALF_UP);;
        if (beforeNum > number) {
            // 规则内剩余金额
           //规则内剩余的人数
            beforeNum = beforeNum - number+1;
            //规则内。人均金额
            BigDecimal customerMoney = avlRuleMoney.divide(new BigDecimal(beforeNum),0);
            BigDecimal maxMoney = getMaxMoney(customerMoney);

            return maxMoney.setScale(0, BigDecimal.ROUND_HALF_UP);
        } else if (beforeNum.intValue() == number.intValue()) {
            return avlRuleMoney;
        } else if (number.intValue() == totalNum.intValue()) {
            return currMoney.setScale(0, BigDecimal.ROUND_HALF_UP);
        } else if(totalNum.intValue()>number){
//            BigDecimal v = total.subtract(currMoney);
//            v =v.setScale(0, BigDecimal.ROUND_HALF_UP);
            int i = totalNum - number;
            BigDecimal customer = currMoney.divide(new BigDecimal(i),0);
            BigDecimal maxMoney = getMaxMoney(customer);
            return maxMoney.setScale(0, BigDecimal.ROUND_HALF_UP);
        }else{
            return new BigDecimal(0);
        }
    }

    //在规定范围内去除最大金额
    private static BigDecimal getMaxMoney(BigDecimal money) {
        for (int i = 0; i < 10; i++) {
            double v = Math.random() * (money.floatValue());
            bigDecimals[i] = v;
        }
        OptionalDouble asDouble = Arrays.stream(bigDecimals).mapToDouble(Double::doubleValue).max();
        BigDecimal bigDecimal = new BigDecimal(0);
        if (asDouble.isPresent()) {
            bigDecimal = bigDecimal.add(new BigDecimal(asDouble.getAsDouble()));
        }
        return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP);
    }


    public static void main(String[] args) {
        BigDecimal total = new BigDecimal(19000);
        Integer totalNum = 2;
        Integer number;
        BigDecimal currNoney = new BigDecimal(19000);
        for (number = 1; number <= totalNum; number++) {
            BigDecimal money = getMoney(total, currNoney, totalNum, number);
            currNoney = currNoney.subtract(money);
            System.out.println("第" + number + "人砍价:" + money);
        }
        System.out.println("总共砍掉的金额: "+ currNoney);
        BigDecimal money = getMoney(total, currNoney, totalNum, 7);
        System.out.println(": "+ currNoney);
    }
}
