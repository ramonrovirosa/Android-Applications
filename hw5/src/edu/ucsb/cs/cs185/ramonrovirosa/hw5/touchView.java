package edu.ucsb.cs.cs185.ramonrovirosa.hw5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;

import android.widget.ImageView;

public class touchView extends ImageView {

//	private TextView textview;
	
	Matrix imgMatrix;
	
	public touchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public touchView(Context context, AttributeSet set) {
		super(context, set); 
		}
	

	public touchView(Context context) {
		super(context);
		setScaleType(ScaleType.CENTER_INSIDE);
//		this.textview = textview;
	}

	@Override
	public void onDraw(Canvas c){
		super.onDraw(c);
		setImageMatrix(imgMatrix);
		
	}
	
}
