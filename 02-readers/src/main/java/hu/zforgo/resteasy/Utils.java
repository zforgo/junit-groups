package hu.zforgo.resteasy;

public class Utils {

	public static boolean isEmpty(String input) {
		return input == null || input.length() == 0;
	}

	public static boolean isNotEmpty(String input) {
		return input != null && input.length() > 0;
	}
}
