package com.lyd.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 工具类
 * @author oracle
 */
public class UtilTools
{
	// 日期格式
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 生成uuid
	 * @return
	 */
	public static String createUuid()
	{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 字符串转日期
	 * @param dateString 字符串
	 * @return 日期
	 */
	public static Date parseDate(String dateString)
	{
		try
		{
			return sdf.parse(dateString);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 日期转字符串
	 * @param date 日期
	 * @return 字符串
	 */
	public static String formatDate(Date date)
	{
		return sdf.format(date);
	}
	
	/**
	 * 保留两位小数
	 * @param num
	 * @return
	 */
	public static double parseDouble2(double num)
	{
		try
		{
//			BigDecimal bg = new BigDecimal(num);  
//            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            DecimalFormat df = new DecimalFormat("#.00");
            return Double.parseDouble(df.format(num));
		}
		catch (Exception e)
		{
			// 如果转换失败，返回0.00
			return 0.00;
		}
	}
	
	/**
	 * 计算评分
	 * 先判断是否为整数，如果是整数，没有半红，红取分数，灰取 5-分数
                                 有半红：
                                 灰：5-总分上取整
                                半红：1
                                红：5-1-灰
	 * @return
	 */
	public static int[] calScoreNum(float score)
	{
		// 0:红，1：半红，2：灰
		int[] scores = new int[3];
		// 判断是否为小数，不是小数
		if(score==(int)score)
		{
			scores[0] = (int)score;
			scores[1] = 0;
			scores[2] = 5 - (int)score;
		}
		else
		{
			scores[0] = (int)score;
			scores[1] = 1;
			scores[2] = 5 - 1 - (int)score;
		}
		
		return scores;
	}
	
	public static void main(String[] args)
	{
		System.out.println(parseDouble2(12.00));
	}
}
