package com.example.dghdkg;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SeekBar;

public class MainActivity extends Activity {
	
	private SlipButton kaiguan1; //是否震动
	private SlipButton kaiguan2; //是否震动
	private SeekBar seekBar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamesetting);
		// 实例化SlipButton
		kaiguan1 = (SlipButton) findViewById(R.id.zd_checkbox);
		kaiguan2 = (SlipButton) findViewById(R.id.sdmsButton);
		seekBar = (SeekBar) findViewById(R.id.seekbar_yx);
		inithdkg();
		//seekBar.setOnSeekBarChangeListener(new );
	}
	
	/**
	 * 初始化滑动开关，给接口赋实现类，在实现类里面做具体实现
	 */
	private void inithdkg(){
		kaiguan1.initIPressBoolean(new IHdkgYinXiao(this));
		//预设值开关
		kaiguan1.setChecked(false);
		
		kaiguan2.initIPressBoolean(new IHdkgYinYue(this));
		kaiguan2.setChecked(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
