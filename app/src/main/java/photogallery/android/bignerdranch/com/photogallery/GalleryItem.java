package photogallery.android.bignerdranch.com.photogallery;

/**
 *
 */
public class GalleryItem {
	public String getCaption() {
		return mCaption;
	}

	public void setCaption(String mCaption) {
		this.mCaption = mCaption;
	}

	public String getId() {
		return mId;
	}

	public void setId(String mId) {
		this.mId = mId;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	private String mCaption;
	private String mId;
	private String mUrl;

	public String toString() {
		return mCaption;
	}
}
