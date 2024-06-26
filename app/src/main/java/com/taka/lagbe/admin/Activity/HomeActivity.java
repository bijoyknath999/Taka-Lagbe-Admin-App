package com.taka.lagbe.admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.graphics.*;
import android.util.*;

import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.taka.lagbe.admin.R;

import android.view.View;


public class HomeActivity extends AppCompatActivity {
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double total = 0;
	private HashMap<String, Object> m = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap2 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap3 = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear16;
	private LinearLayout linear7;
	private LinearLayout linear9;
	private LinearLayout linear11;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private TextView textview1;
	private LinearLayout linear4;
	private TextView textview4;
	private TextView textview2;
	private TextView textview16;
	private TextView textview7;
	private TextView textview9;
	private TextView textview11;
	private TextView textview14;
	private TextView textview15;
	private TextView textview17;
	
	private Intent i = new Intent();
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private Intent p = new Intent();
	private Intent r = new Intent();
	private DatabaseReference subscription = _firebase.getReference("subscription");
	private ChildEventListener _subscription_child_listener;
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private Intent q = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview16 = (TextView) findViewById(R.id.textview16);
		textview7 = (TextView) findViewById(R.id.textview7);
		textview9 = (TextView) findViewById(R.id.textview9);
		textview11 = (TextView) findViewById(R.id.textview11);
		textview14 = (TextView) findViewById(R.id.textview14);
		textview15 = (TextView) findViewById(R.id.textview15);
		textview17 = (TextView) findViewById(R.id.textview17);
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), UserActivity.class);
				startActivity(i);
			}
		});
		linear16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				r.setClass(getApplicationContext(), ReqActivity.class);
				startActivity(r);
			}
		});
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p.setClass(getApplicationContext(), PaymentActivity.class);
				startActivity(p);
			}
		});
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), NotificationActivity.class);
				startActivity(i);
			}
		});
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SubscriptionActivity.class);
				startActivity(i);
			}
		});
		
		linear14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SponsorActivity.class);
				startActivity(i);
			}
		});
		
		linear15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), UpdateActivity.class);
				startActivity(i);
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		users.addChildEventListener(_users_child_listener);
		
		_subscription_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		subscription.addChildEventListener(_subscription_child_listener);
		
		_history_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("key")) {
					if (_childValue.get("subject").toString().equals("withdraw")) {
						if (_childValue.get("seen").toString().equals("false")) {
							_rippleRoundStroke(linear7, "#B71C1C", "#FFFFFF", 15, 0, "#000000");
						}
						else {
							_rippleRoundStroke(linear7, "#F6910F", "#FFFFFF", 15, 0, "#000000");
						}
					}
					else {
						if (_childValue.get("subject").toString().equals("subscription")) {
							if (_childValue.get("seen").toString().equals("false")) {
								_rippleRoundStroke(linear16, "#B71C1C", "#FFFFFF", 15, 0, "#000000");
							}
							else {
								_rippleRoundStroke(linear16, "#F6910F", "#FFFFFF", 15, 0, "#000000");
							}
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("key")) {
					if (_childValue.get("subject").toString().equals("withdraw")) {
						if (_childValue.get("seen").toString().equals("false")) {
							_rippleRoundStroke(linear7, "#B71C1C", "#FFFFFF", 15, 0, "#000000");
						}
						else {
							_rippleRoundStroke(linear7, "#F6910F", "#FFFFFF", 15, 0, "#000000");
						}
					}
					else {
						if (_childValue.get("subject").toString().equals("subscription")) {
							if (_childValue.get("seen").toString().equals("false")) {
								_rippleRoundStroke(linear16, "#B71C1C", "#FFFFFF", 15, 0, "#000000");
							}
							else {
								_rippleRoundStroke(linear16, "#F6910F", "#FFFFFF", 15, 0, "#000000");
							}
						}
					}
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		history.addChildEventListener(_history_child_listener);
	}
	
	private void initializeLogic() {
		_loadingdialog(true, "Please wait...");
		_ui();
		_totalusers();
	}

	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
		_view.setElevation(5);
	}
	
	
	public void _ui () {
		_rippleRoundStroke(linear3, "#FF5722", "#FFFFFF", 15, 0, "#000000");
		_rippleRoundStroke(linear4, "#B71C1C", "#FFFFFF", 15, 0, "#000000");
		_rippleRoundStroke(linear7, "#708090", "#FFFFFF", 15, 0, "#000000");
		_rippleRoundStroke(linear9, "#708090", "#FFFFFF", 15, 0, "#000000");
		_rippleRoundStroke(linear11, "#483D8B", "#FFFFFF", 15, 0, "#000000");
		_rippleRoundStroke(linear14, "#483D8B", "#FFFFFF", 15, 0, "#000000");
		_rippleRoundStroke(linear15, "#483D8B", "#FFFFFF", 15, 0, "#000000");
		_rippleRoundStroke(linear16, "#483D8B", "#FFFFFF", 15, 0, "#000000");
	}
	
	
	public void _totalusers () {
		users.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listmap = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listmap.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				_loadingdialog(false, "");
				total = 0;
				for(int _repeat15 = 0; _repeat15 < (int)(listmap.size()); _repeat15++) {
					if (listmap.get((int)total).containsKey("uid")) {
						total++;
					}
				}
				textview2.setText(String.valueOf((long)(total)));
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	
	public void _loadingdialog (final boolean _ifShow, final String _title) {
		if (_ifShow) {
			if (prog == null){
				prog = new ProgressDialog(this);
				prog.setMax(100);
				prog.setIndeterminate(true);
				prog.setCancelable(false);
				prog.setCanceledOnTouchOutside(false);
			}
			prog.setMessage(_title);
			prog.show();
			
		}
		else {
			if (prog != null){
				prog.dismiss();
			}
			
			
		}
	} private ProgressDialog prog; {
		
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (FirebaseAuth.getInstance().getCurrentUser()==null)
		{
			startActivity(new Intent(HomeActivity.this,LoginActivity.class));
			finish();
			Toast.makeText(this, "Please login first.", Toast.LENGTH_SHORT).show();
		}
	}
}
