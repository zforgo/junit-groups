package hu.zforgo.resteasy.model;

public enum Operation {
	BASE("00_01", "/samlple01"),
	EXTRA("00_02", "/samlple01/extra"),
	QUERY("00_03", "/samlple01/query");

	private final String pid; //input parameter, ez alapján irányítjuk át
	private final String uri; //erre a rest url-re irányítjuk át
	private final String opName; //max 25. karakter, logba ez megy action valuekent

	private Operation(String pid, String uri) {
		this.pid = pid;
		this.uri = uri;
		this.opName = null;
	}

	private Operation(String pid, String uri, String opName) {
		this.pid = pid;
		this.uri = uri;
		this.opName = opName;
	}

	public static Operation byPid(final String pid) {
		Operation[] all = values();
		for (Operation t : all) {
			if (t.pid.equals(pid)) {
				return t;
			}
		}
		throw new IllegalArgumentException("Unknown PID parameter: " + pid);
	}

	public static Operation byUri(final String uri) {
		Operation[] all = values();
		for (Operation t : all) {
			if (t.uri.equals(uri)) {
				return t;
			}
		}
		throw new IllegalArgumentException("Unknown URI : " + uri);

	}

	public String getPid() {
		return pid;
	}

	public String getUri() {
		return uri;
	}

	public String getOpName() {
		return opName;
	}

}
