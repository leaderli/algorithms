package li;

import org.joda.time.DateTime;

import java.util.Date;

public class Log {
	public static void print(String s) {
		System.out.print(s);
	}

	public static void println(String s) {
		System.out.println(s);
	}

	public static void println() {
		System.out.println();
	}

	public static void print(Object o) {
		print(String.valueOf(o));
	}

	public static void println(Object o) {
		println(String.valueOf(o));
	}

	public static void printLine() {
		println("----------------------------------------");
	}

	public static void printNow() {
		println(new DateTime(new Date()).toString("yyyyMMdd HH:mm:ss SSS"));
	}
}
