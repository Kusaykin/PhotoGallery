package photogallery.android.bignerdranch.com.photogallery;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

/**
*
 */
public class PhotoGalleryFragment extends Fragment {
	private static final String TAG = "PhotoGalleryFragment";
	GridView               mGridView;
	ArrayList<GalleryItem> mItems;
	ThumbnailDownloader<ImageView> mThumbnailThread;

	void setupAdapter() {
		if (getActivity() == null || mGridView == null) return;
		if (mItems != null) {
			mGridView.setAdapter(new GalleryItemAdapter(mItems));
		} else {
			mGridView.setAdapter(null);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		new FetchItemsTask().execute();
		mThumbnailThread=new ThumbnailDownloader<ImageView>();
		mThumbnailThread.start();
		mThumbnailThread.getLooper();
		Log.i(TAG, "Background thread started");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_photo_gallery, container,
				false);
		mGridView = (GridView) v.findViewById(R.id.gridView);

		setupAdapter();

		return v;
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		mThumbnailThread.quit();
		Log.i(TAG, "Background thread destroyed");
	}
	private class FetchItemsTask extends AsyncTask<Void,Void,ArrayList<GalleryItem>> {
		@Override
		protected ArrayList<GalleryItem> doInBackground(Void... params)  {
			return new FlickrFetchr().fetchItems();
		}
		@Override
		protected void onPostExecute(ArrayList<GalleryItem> items) {
			mItems = items;
			setupAdapter();
		}
	}
	private class GalleryItemAdapter extends ArrayAdapter<GalleryItem>{

		public GalleryItemAdapter(ArrayList<GalleryItem> item) {
			super(getActivity(),0,item);
		}
		@Override
		public  View getView(int position, View convertView, ViewGroup paren) {
			if (convertView==null){
				convertView=getActivity().getLayoutInflater().inflate(R.layout.gallery_item, paren, false);
			}
			ImageView imageView = (ImageView) convertView.findViewById((R.id.gallery_item_imageView));
			imageView.setImageResource(R.drawable.cat_low);
			GalleryItem item=getItem(position);
			mThumbnailThread.queueThombnail(imageView, item.getUrl());

			return convertView;
		}
	}


}
