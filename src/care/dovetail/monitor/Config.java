package care.dovetail.monitor;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Config {
	public static final long PEAK_UUID = 0x404846A1;
	public static final long X_DATA_UUID = 0x404846A2;
	public static final long Y_DATA_UUID = 0x404846A3;
	public static final long Z_DATA_UUID = 0x404846A4;
	public static final String BT_DEVICE_NAME = "Pregnansi";

	public static final int DATA_LENGTH = 500;

	public static final int SAMPLE_RATE = 200;
	public static final int SAMPLE_INTERVAL_MS = 10;

	public static final int NUM_SAMPLES_LONG_TERM_GRAPH = 5000;
	public static final int NUM_SAMPLES_AVERAGE = 5;

	public static final int UI_UPDATE_INTERVAL_MILLIS = 10000;

	public static final SimpleDateFormat EVENT_TIME_FORMAT =
			new SimpleDateFormat("hh:mm:ssaa, MMM dd yyyy", Locale.US);
}
