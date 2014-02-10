package com.example.dghdkg;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SlipButton extends View implements View.OnTouchListener {
	//图片资源
	private Bitmap slip_btn,setting_off,setting_on;
	// 按下时的x和当前的x
	private float downX, nowX;
	// 记录用户是否在滑动
	private boolean onSlip = false;
	// 当前的状状态
	private boolean nowStatus = false;
	
	private Hdkginterface iPressBoolean;

	public SlipButton(Context context) {
		super(context);
		init();
	}

	public SlipButton(Context context, AttributeSet paramAttributeSet) {
		super(context, paramAttributeSet);
		init();
	}

	private void init() {
		this.slip_btn = BitmapFactory.decodeResource(getResources(),
				R.drawable.slip_btn);
		this.setting_off = BitmapFactory.decodeResource(getResources(),
				R.drawable.setting_off);
		this.setting_on = BitmapFactory.decodeResource(getResources(),
				R.drawable.setting_on);
		setOnTouchListener(this);
	}

	/**
	 * 给外部调用初始化接口
	 * @param ipress
	 */
	public void initIPressBoolean(Hdkginterface ipress){
		this.iPressBoolean = ipress;
		setChecked(false);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Matrix matrix = new Matrix();
		Paint paint = new Paint();
		float x = 0;
		// 根据nowX设置背景，开或�?关状�?
		if (nowX < (setting_on.getWidth() / 2)) {
			canvas.drawBitmap(setting_off, matrix, paint);// 画出关闭时的背景
		} else {
			canvas.drawBitmap(setting_on, matrix, paint);// 画出打开时的背景
		}
		if (onSlip) {// 是否是在滑动状�?,
			if (nowX >= setting_on.getWidth())// 是否划出指定范围,不能让滑块跑到外�?必须做这个判�?
				x = setting_on.getWidth() - slip_btn.getWidth() / 2;// 减去滑块1/2的长�?
			else
				x = nowX - slip_btn.getWidth() / 2;
		} else {
			if (nowStatus) {// 根据当前的状态设置滑块的x�?
				x = setting_on.getWidth() - slip_btn.getWidth();
			} else {
				x = 0;
			}
		}

		// 对滑块滑动进行异常处理，不能让滑块出�?
		if (x < 0) {
			x = 0;
		} else if (x > setting_on.getWidth() - slip_btn.getWidth()) {
			x = setting_on.getWidth() - slip_btn.getWidth();
		}
		// 画出滑块
		canvas.drawBitmap(slip_btn, x, 0, paint);
	}

	public boolean onTouch(View paramView, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			if (event.getX() > setting_off.getWidth()
					|| event.getY() > setting_off.getHeight()) {
				return false;
			} else {
				onSlip = true;
				downX = event.getX();
				nowX = downX;
			}
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			nowX = event.getX();
			break;
		}
		case MotionEvent.ACTION_UP: {
			onSlip = false;
			if (event.getX() >= (setting_on.getWidth() / 2)) {
				nowStatus = true;
				nowX = setting_on.getWidth() - slip_btn.getWidth();
			} else {
				nowStatus = false;
				nowX = 0;
			}

//			if (listener != null) {
//				listener.OnChanged(SlipButton.this, nowStatus);
//			}
			if(iPressBoolean!=null){
				iPressBoolean.pressBoolean(nowStatus);
			}
			break;
		}
		}
		// 刷新界面
		invalidate();
		return true;

	}

	/**
	 * 设置滑动�?��的初始状态，供外部调�?
	 * 
	 * @param checked
	 */
	public void setChecked(boolean checked) {
		if (checked) {
			nowX = setting_off.getWidth();
		} else {
			nowX = 0;
		}
		nowStatus = checked;
		iPressBoolean.pressBoolean(nowStatus);
	}

//	/**
//	 * 回调接口
//	 * 
//	 * @author samsung
//	 */
//	public interface OnChangedListener {
//		public void OnChanged(SlipButton wiperSwitch, boolean checkState);
//	}

}
