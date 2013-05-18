package edu.ucsb.cs.cs185.ramonrovirosa.hw5;
 
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class touchView extends ImageView {
    private static final String TAG = "Touch";
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
 
    static final int NONE = 0;
    static final int MOVE = 1;
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
 
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        image = (touchView) findViewById(R.id.touchView);
        
        //matrix = image.getImageMatrix();
        image.setImageMatrix(matrix);
        
 
        if(tap == 2)
            canvas.drawCircle(start.x, start.y, 15, paint);
 
//        canvas.save();
//        canvas.translate(pX, pY);
//        canvas.restore();
    }
 
    public boolean onTouchEvent(MotionEvent e){
        super.onTouchEvent(e);
 
        int action = e.getAction() & MotionEvent.ACTION_MASK;
 
        // switch statement to go through all actions
        switch(action){
            case MotionEvent.ACTION_DOWN:{
                final float x = e.getX();
                final float y = e.getY();
                savedMatrix.set(matrix);
                start.set(x, y);
                mode = MOVE;
                tap = 1;
                Log.d(TAG, "Action Down");
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                oldDist = spacing(e);
                if(oldDist > 10f){
                    savedMatrix.set(matrix);
                    midPoint(mid, e);
                    mode = ZOOM;
                }
                Log.d(TAG, "Action Multi-Touch Down");
                break;
            }
            case MotionEvent.ACTION_UP: {
                tap++;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                mode = NONE;
                Log.d(TAG, "Action Mult-Touch Up");
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final float x = e.getX();
                final float y = e.getY();
                float dx = x-start.x;
                float dy = y-start.y;
                tap = 0;
                if(mode == MOVE){
                    matrix.set(savedMatrix);
                    matrix.postTranslate(dx, dy);
                    Log.d("Moving", "moving");
                }
                else if(mode == ZOOM){
                    float newDist = spacing(e);
                    float rotate = rotation(e);
                    if(newDist > 10f){
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                        matrix.postRotate(rotate);
                        Log.d("Zooming", "zooming");
                    }
                }
                Log.d(TAG, "Action Move");
                break;
            }
        }
        setImageMatrix(matrix);
        invalidate();
        return true;
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
 
    private float rotation(MotionEvent event) {
        double dx = (event.getX(0) - event.getX(1));
        double dy = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(dy, dx);
        return (float) Math.toDegrees(radians);
    }
 
}