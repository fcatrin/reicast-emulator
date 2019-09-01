package com.android.util;

import java.util.Locale;

public class StringUtils {


	public static boolean containsIgnoreCase(String a, String b) {
		if (a== null || b == null) return false;
		
		return a.toLowerCase(Locale.US).contains(b.toLowerCase(Locale.US));
	}

}
