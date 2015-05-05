package photogallery.android.bignerdranch.com.photogallery;

import android.os.HandlerThread;
import android.util.Log;

/**
 * Created by  on 04.05.2015.
 */
public class ThumbnailDownloader<Token> extends HandlerThread {
	private static final  String TAG = "ThumbnailDownloader";

	public ThumbnailDownloader() {
		super(TAG);
	}
	public  void queueThombnail(Token token, String url){
		Log.i(TAG,"Got an URL:"+url );
	}
}
