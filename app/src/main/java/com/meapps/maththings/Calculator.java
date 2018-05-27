package com.meapps.maththings;
import java.math.*;
import java.util.*;

public class Calculator {
	public static String calcAverage(List<String> input) {
		BigDecimal result = new BigDecimal(0);
		for (int i=0; i < input.size(); i++) {
			result = result.add(BigDecimal.valueOf(Double.valueOf(input.get(i))));
		}
		result = result.divide(BigDecimal.valueOf((double)input.size()));
		return result.toString();
	}
}
