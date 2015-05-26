package hu.zforgo.resteasy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class RequestLogger {

	public String logResponse(Object obj, String reqId, long arrived) {
		ObjectOutputStream oos = null;
		try {
			File tmp = File.createTempFile("demo_", ".log");
			FileOutputStream fos = new FileOutputStream(tmp);
			oos = new ObjectOutputStream(fos);
			oos.writeObject("-- REQUEST ARRIVED --");
			oos.writeObject(new Date(arrived));
			oos.writeObject("-- REQUEST ID --");
			oos.writeObject(reqId);
			if (obj != null) {
				oos.writeObject("-- RESPONSE OBJECT --");
				oos.writeObject(obj);
			}
			return tmp.getAbsolutePath();
		} catch (IOException ignored) {

		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception ignored) {
				}
			}
		}
		return null;
	}
}
