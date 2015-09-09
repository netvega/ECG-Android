package care.dovetail.monitor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;
import android.util.Pair;
import care.dovetail.common.ApiResponseTask;
import care.dovetail.common.model.ApiResponse;
import care.dovetail.common.model.Event;

public class SyncTask extends TimerTask {
	private static final String TAG = "SyncTask";

	private final App app;

	public SyncTask(App app) {
		this.app = app;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		for (Event event : app.events.getLatest(app.getLastSyncTime())) {
			new AddEvent(event).execute();
			Log.i(TAG, String.format("Adding event %s %d", event.tags[0], event.time));
		}
	}

	private class AddEvent extends ApiResponseTask {
		private final Event event;

		AddEvent(Event event) {
			super();
			this.event = event;
		}

		@Override
		protected HttpRequestBase makeRequest(Pair<String, String>... params)
				throws UnsupportedEncodingException {
			List<NameValuePair> queryParams = new ArrayList<NameValuePair>();
			queryParams.add(new BasicNameValuePair("device_id", app.getDeviceId()));
			queryParams.add(new BasicNameValuePair("tags", event.tags[0]));
			queryParams.add(new BasicNameValuePair("time", Long.toString(event.time)));
			for (Pair<String, String> param : params) {
				queryParams.add(new BasicNameValuePair(param.first, param.second));
			}
			HttpPost request = new HttpPost(Config.EVENT_URL);
			request.setEntity(new UrlEncodedFormEntity(queryParams));
			return request;
		}

		@Override
		protected void onPostExecute(ApiResponse result) {
			super.onPostExecute(result);
			if (result != null && "OK".equalsIgnoreCase(result.code)
					&& app.getLastSyncTime() < event.time) {
				app.setLastSyncTime(event.time);
			} else if (result != null && !"OK".equalsIgnoreCase(result.code)) {
				Log.e(TAG, String.format("Failed to add %s %s", event.tags[0], result.message));
			}
		}
	}
}
