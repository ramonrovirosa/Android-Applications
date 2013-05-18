package edu.ucsb.cs.cs185.ramonrovirosa.hw5;
 
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class touchView extends ImageView implements OnGestureListener {
    private static final String TAG = "Touch";
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
 
    static final int NONE = 0;
    static final int MOVE = 1;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;
    int tap = 0;
 
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
 
    touchView image;
    private float pX;
    private float pY;
 
    Paint paint = new Paint();
 
    public touchView(Context context){
        super(context);
        paint.setColor(Color.RED);
    }
 
    public touchView(Context context, AttributeSet attrs){
        super(context, attrs);
        paint.setColor(Color.RED);
    }
 
    public touchView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        paint.setColor(Color.RED);
    }
 
    Canvas can;
    
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        
      //if(tap == 2)
        
        can =canvas;
        image = (touchView) findViewById(R.id.touchView);
        
        image.setImageMatrix(matrix);
        
 
        System.out.println(tap);
        
        
            
 
        canvas.save();
        canvas.drawCircle(start.x, start.y, 15, paint);
        canvas.restore();
    }
 
    float[] lastEvent = null;
    float d = 0f;
    float newRot = 0f;
    
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
 
//        int action = e.getAction() & MotionEvent.ACTION_MASK;
// 
//        // switch statement to go through all actions
//        switch(action){
//            case MotionEvent.ACTION_DOWN:{
//                final float x = e.getX();
//                final float y = e.getY();
//                savedMatrix.set(matrix);
//                start.set(x, y);
//                mode = MOVE;
//                tap = 1;
//                Log.d(TAG, "Action Down");
//                break;
//            }
//            case MotionEvent.ACTION_POINTER_DOWN: {
//                oldDist = spacing(e);
//                if(oldDist > 10f){
//                    savedMatrix.set(matrix);
//                    midPoint(mid, e);
//                    mode = ZOOM;
//                }
//                Log.d(TAG, "Action Multi-Touch Down");
//                break;
//            }
//            case MotionEvent.ACTION_UP: {
//                tap++;
//            }
//            case MotionEvent.ACTION_POINTER_UP: {
//                mode = NONE;
//                Log.d(TAG, "Action Mult-Touch Up");
//                break;
//            }
//            case MotionEvent.ACTION_MOVE: {
//                final float x = e.getX();
//                final float y = e.getY();
//                float dx = x-start.x;
//                float dy = y-start.y;
//                tap = 0;
//                if(mode == MOVE){
//                    matrix.set(savedMatrix);
//                    matrix.postTranslate(dx, dy);
//                    Log.d("Moving", "moving");
//                }
//                else if(mode == ZOOM){
//                    float newDist = spacing(e);
//                    float rotate = rotation(e);
//                    if(newDist > 10f){
//                        matrix.set(savedMatrix);
//                        float scale = newDist / oldDist;
//                        matrix.postScale(scale, scale, mid.x, mid.y);
//                        matrix.postRotate(rotate);
//                        Log.d("Zooming", "zooming");
//                    }
//                }
//                Log.d(TAG, "Action Move");
//                break;
//            }
//        }
//        setImageMatrix(matrix);
//        invalidate();
//        return true;
        
		// Handle touch events here...
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			//Matrix dotMatrix = matrix;
			
			matrix.set(savedMatrix);
			matrix.postTranslate(start.x, start.y );
			
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			// if (Constant.TRACE) Log.d(TAG, "mode=DRAG");
			mode = DRAG;
			lastEvent = null;
			tap = 1;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			savedMatrix.set(matrix);
			midPoint(mid, event);
			mode = ZOOM;
			// if (Constant.TRACE) Log.d(TAG, "mode=ZOOM");
			lastEvent = new float[4];
			lastEvent[0] = event.getX(0);
			lastEvent[1] = event.getX(1);
			lastEvent[2] = event.getY(0);
			lastEvent[3] = event.getY(1);
			d = rotation(event);
			break;
		case MotionEvent.ACTION_UP: 
			tap++;
			System.out.println("Action up tap value: "+ tap);
			break;
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			lastEvent = null;
			// if (Constant.TRACE) Log.d(TAG, "mode=NONE");
			break;
		case MotionEvent.ACTION_MOVE:
			tap = 0;
			if (mode == DRAG) {
				// ...
				matrix.set(savedMatrix);
				matrix.postTranslate(event.getX() - start.x, event.getY()
						- start.y);
			} else if (mode == ZOOM && event.getPointerCount() == 2) {
				float newDist = spacing(event);
				// if (Constant.TRACE) Log.d(TAG, "Count=" +
				// event.getPointerCount());
				// if (Constant.TRACE) Log.d(TAG, "newDist=" + newDist);
				matrix.set(savedMatrix);
				if (newDist > 10f) {
					float scale = newDist / oldDist;
					matrix.postScale(scale, scale, mid.x, mid.y);
				}
				if (lastEvent != null) {
					newRot = rotation(event);
					// if (Constant.TRACE) Log.d("Degreeeeeeeeeee",
					// "newRot="+(newRot));
					float r = newRot - d;
					matrix.postRotate(r, this.getMeasuredWidth() / 2,
							this.getMeasuredHeight() / 2);
				}
			}
			break;
		}
        
        image.setImageMatrix(matrix);
        return true;
    }
 
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
//    View v = this.findViewById(Window.ID_ANDROID_CONTENT);
//    Point thisPoint = new Point();
//    thisPoint.x = (int) e.getX()-(image.getLeft()*2);
//    thisPoint.y = (int) e.getY()-(image.getTop()*2);
//    can.drawCircle(e.getX(), e.getY(), 15, paint);
//    //v.addPoint(thisPoint);
//    this.invalidate();
//    
//    System.out.println("in the onSingleTapUp class...");
//    //can.drawCircle(start.x, start.y, 15, paint);
    return false;
    }
    
    private float spacing(MotionEvent e){
        float x = e.getX(0) - e.getX(1);
        float y = e.getY(0) - e.getY(1);
        return FloatMath.sqrt(x*x + y*y);
    }
 
    private void midPoint(PointF point, MotionEvent e){
        float x = e.getX(0) + e.getX(1);
        float y = e.getY(0) + e.getY(1);
        point.set(x/2, y/2);
    }
 
//    private float rotation(MotionEvent event) {
//        double dx = (event.getX(0) - event.getX(1));
//        double dy = (event.getY(0) - event.getY(1));
//        double radians = Math.atan2(dy, dx);
//        return (float) Math.toDegrees(radians);
//    }
    
    /** Determine the degree between the first two fingers */
    private float rotation(MotionEvent event) {  
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);       
        //if (Constant.TRACE) Log.d("Rotation ~~~~~~~~~~~~~~~~~", delta_x+" ## "+delta_y+" ## "+radians+" ## "
       //                  +Math.toDegrees(radians));
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