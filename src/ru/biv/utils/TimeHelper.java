package ru.biv.utils;

import java.util.Date;

public class TimeHelper {
	public static void sleep(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static long getCurrentTime() {
		Date date = new Date();
		return date.getTime();
	}
}
