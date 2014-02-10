package com.example.dghdkg;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class IHdkgYinYue implements Hdkginterface{
	private Context context;
	
	public IHdkgYinYue(Context context){
		//this.context = context;
	}
	
	@Override
	public void pressBoolean(boolean paramBoolean) {
		Log.e("状态2:", "" + paramBoolean);  
	}

}
