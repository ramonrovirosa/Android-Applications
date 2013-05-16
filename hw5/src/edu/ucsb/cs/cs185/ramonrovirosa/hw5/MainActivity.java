package edu.ucsb.cs.cs185.ramonrovirosa.hw5;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final int SELECT_PICTURE = 1;

	private String selectedImagePath;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((Button) findViewById(R.id.button))
				.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {

						// in onCreate or any event where your want the user to
						// select a file
						Intent intent = new Intent();
						intent.setType("image/*");
						intent.setAction(Intent.ACTION_GET_CONTENT);
						startActivityForResult(
								Intent.createChooser(intent, "Select Picture"),
								SELECT_PICTURE);
					}
				});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				touchView image = (touchView) findViewById(R.id.touchView);
				Uri selectedImageUri = data.getData();
				selectedImagePath = getPath(selectedImageUri);
				Bitmap myBitmap = BitmapFactory.decodeFile(selectedImagePath);
				image.setImageBitmap(myBitmap);
			}
		}
	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		int action = event.getAction() & MotionEvent.ACTION_MASK;

		switch (action) {
		case MotionEvent.ACTION_DOWN: {
			Log.d("MultitouchExample", "Action Down");
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			Log.d("MultitouchExample", "Action Move");
			break;
		}

		case MotionEvent.ACTION_POINTER_DOWN: {
			Log.d("MultitouchExample", "Pointer Down");
			break;
		}
		case MotionEvent.ACTION_POINTER_UP: {
			Log.d("MultitouchExample", "Pointer up");

			break;
		}
		case MotionEvent.ACTION_UP: {
			Log.d("Multitouch", "Action up");

			break;
		}
		}

		return true;
	}

}
