package com.acl.pay.utils;

import java.math.BigDecimal;

public class NumberUtil {

	public static double ROUND_DOWN(int newScale, double data) {
		return new BigDecimal(data).setScale(newScale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	public static void main(String[] args) {
		System.out.println(NumberUtil.ROUND_DOWN(1, 1111.999));
	}
}
