package care.dovetail.monitor;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Config {
	public static final long DATA_UUID = 0x1902;
	public static final long PEAK_UUID = 0x1903;
	public static final String BT_DEVICE_NAME = "Pregnansi";

	public static final int SAMPLE_RATE = 200;
	public static final int SAMPLE_INTERVAL_MS = 10;

	public static final int NUM_SAMPLES_LONG_TERM_GRAPH = 30000;
	public static final int NUM_SAMPLES_AVERAGE = 5;

	public static final int UI_UPDATE_INTERVAL_MILLIS = 10000;

	public static final SimpleDateFormat EVENT_TIME_FORMAT =
			new SimpleDateFormat("hh:mm:ssaa, MMM dd yyyy", Locale.US);
}
