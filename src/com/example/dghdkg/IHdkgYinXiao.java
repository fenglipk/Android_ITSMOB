package com.example.dghdkg;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class IHdkgYinXiao implements Hdkginterface{
	private Context context;
	
	public IHdkgYinXiao(Context context){
		//this.context = context;
	}
	
	@Override
	public void pressBoolean(boolean paramBoolean) {
		//Toast.makeText(context, paramBoolean+"", 3000);
		Log.e("状态:", "" + paramBoolean);  
	}

}
