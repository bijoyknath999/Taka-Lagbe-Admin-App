package com.taka.lagbe.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.*;
import android.view.*;
import android.widget.*;
import android.util.*;

import java.util.*;

import com.taka.lagbe.admin.Activity.LoginActivity;
import com.taka.lagbe.admin.R;
import com.taka.lagbe.admin.Activity.HomeActivity;
import com.taka.lagbe.admin.Controller.RequestNetworkController;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.content.Intent;

import com.facebook.shimmer.*;


public class MainActivity extends AppCompatActivity {

	private ShimmerFrameLayout linear22;

	private FirebaseAuth auth;
	private Intent i = new Intent();
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear22 = (ShimmerFrameLayout) findViewById(R.id.linear22);
		auth = FirebaseAuth.getInstance();
		net = new RequestNetwork(this);
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (auth.getCurrentUser()!=null)
				{
					i.setClass(getApplicationContext(), HomeActivity.class);
					startActivity(i);
					finish();
				}
				else
				{
					i.setClass(getApplicationContext(), LoginActivity.class);
					startActivity(i);
					finish();
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_createSnackBar("No Internet Connection");
			}
		};

	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { 
			Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(0xFF008375); w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); }
		ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById(R.id.linear22); container.startShimmer();
		net.startRequestNetwork(RequestNetworkController.GET, "https://google.com", "a", _net_request_listener);
	}

	
	public void _createSnackBar (final String _message) {
		ViewGroup parentLayout = (ViewGroup) ((ViewGroup) this .findViewById(android.R.id.content)).getChildAt(0);
		   com.google.android.material.snackbar.Snackbar.make(parentLayout, _message, com.google.android.material.snackbar.Snackbar.LENGTH_LONG) 
		        .setAction("OK", new View.OnClickListener() {
			            @Override 
			            public void onClick(View view) {
				
				            } 
			        }).show();
	}

}
