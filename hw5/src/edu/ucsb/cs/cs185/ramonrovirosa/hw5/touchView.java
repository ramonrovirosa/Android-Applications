package edu.ucsb.cs.cs185.ramonrovirosa.hw5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;

public class touchView extends ImageView implements OnGestureListener {
	private static final String TAG = "Touch";
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();

	static final int NONE = 0;
	static final int MOVE = 1;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
	int movingFlag = 0;

	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;

	touchView image;
	private float pX;
	private float pY;

	Paint paint = new Paint();

	public touchView(Context context) {
		super(context);
		paint.setColor(Color.RED);
		matrix.reset();
		matrix.postScale(.3f, .3f);
	}

	public touchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint.setColor(Color.RED);
		matrix.reset();
		matrix.postScale(.3f, .3f);
	}

	public touchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint.setColor(Color.RED);
		matrix.reset();
		matrix.postScale(.3f, .3f);
	}

	Canvas can;

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		can = canvas;

		image = (touchView) findViewById(R.id.touchView);
		image.setImageMatrix(matrix);

		System.out.println(movingFlag);

		canvas.save();
		// matrix.postTranslate(start.x,start.y);
		if (movingFlag == 2)
			canvas.drawCircle(start.x, start.y, 10, paint);
		canvas.translate(pX, pY);
		// matrix.postTranslate(-start.x,-start.y);
		canvas.restore();
	}

	float[] lastEvent = null;
	float d = 0f;
	float newRot = 0f;
	float rotate = 0f;
	float x = 0;
	float y = 0;

	public boolean onTouchEvent(MotionEvent e) {
		super.onTouchEvent(e);

		int action = e.getAction() & MotionEvent.ACTION_MASK;

		// switch statement to go through all actions
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			movingFlag = 1;
			x = e.getX();
			y = e.getY();
			savedMatrix.set(matrix);
			start.set(x, y);
			mode = MOVE;
			break;

		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(e);
			if (oldDist > 10f) {
				savedMatrix.set(matrix);
				midPoint(mid, e);
				mode = ZOOM;
			}
			lastEvent = new float[4];
			lastEvent[0] = e.getX(0);
			lastEvent[1] = e.getX(1);
			lastEvent[2] = e.getY(0);
			lastEvent[3] = e.getY(1);
			d = rotation(e);
			break;

		case MotionEvent.ACTION_UP:
			movingFlag++;
			break;

		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			break;

		case MotionEvent.ACTION_MOVE:
			x = e.getX();
			y = e.getY();
			float dx = x - start.x;
			float dy = y - start.y;
			movingFlag = 0;
			if (mode == MOVE) {
				matrix.set(savedMatrix);
				matrix.postTranslate(dx, dy);
			} else if (mode == ZOOM) {
				float newDist = spacing(e);
				matrix.set(savedMatrix);
				// float rotate = rotation(e);
				if (newDist > 10f) {
					// matrix.set(savedMatrix);
					float scale = newDist / oldDist;
					matrix.postScale(scale, scale, mid.x, mid.y);
					// matrix.postRotate(20);
				}
				if (lastEvent != null) {
					rotate = rotation(e);
					float r = rotate - d;
					matrix.postRotate(r, this.getMeasuredWidth() / 2,
							this.getMeasuredHeight() / 2);
				}
			}
			Log.d(TAG, "Action Move");
			break;

		}
		image.setImageMatrix(matrix);
		invalidate();
		return true;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// View v = this.findViewById(Window.ID_ANDROID_CONTENT);
		// Point thisPoint = new Point();
		// thisPoint.x = (int) e.getX()-(image.getLeft()*2);
		// thisPoint.y = (int) e.getY()-(image.getTop()*2);
		// can.drawCircle(e.getX(), e.getY(), 15, paint);
		// //v.addPoint(thisPoint);
		// this.invalidate();
		//
		// System.out.println("in the onSinglemovingFlagUp class...");
		// //can.drawCircle(start.x, start.y, 15, paint);
		return false;
	}

	private float spacing(MotionEvent e) {
		float x = e.getX(0) - e.getX(1);
		float y = e.getY(0) - e.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	private void midPoint(PointF point, MotionEvent e) {
		float x = e.getX(0) + e.getX(1);
		float y = e.getY(0) + e.getY(1);
		point.set(x / 2, y / 2);
	}

	private float rotation(MotionEvent event) {
		double dx = (event.getX(0) - event.getX(1));
		double dy = (event.getY(0) - event.getY(1));
		double radians = Math.atan2(dy, dx);
		return (float) Math.toDegrees(radians);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

}