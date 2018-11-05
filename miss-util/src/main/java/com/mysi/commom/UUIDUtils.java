package com.mysi.commom;

import java.util.UUID;

public final class UUIDUtils {
	public static String uuid() {
		return UUID.randomUUID().toString();
	}
}
