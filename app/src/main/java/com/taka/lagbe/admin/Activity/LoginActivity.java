package com.taka.lagbe.admin.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.taka.lagbe.admin.R;
import com.taka.lagbe.admin.Utils.SketchwareUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
	private final FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Button loginBtn;
	private EditText editEmail, editPassword;
	private final Intent i = new Intent();
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private final Calendar c = Calendar.getInstance();
	private SharedPreferences tme;
	private Calendar clnd = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

		auth = FirebaseAuth.getInstance();
		tme = getSharedPreferences("tme", Activity.MODE_PRIVATE);

		loginBtn = findViewById(R.id.login_btn);
		editEmail = findViewById(R.id.login_email);
		editPassword = findViewById(R.id.login_password);

		loginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (editEmail.getText().toString().trim().equals("")) {
					editEmail.setError("Enter Email Address");
				}
				else {
					if (editPassword.getText().toString().trim().equals("")) {
						editPassword.setError("Enter Password");
					}
					else {
						if (editEmail.getText().toString().trim().equals("bijoykumarnath999@gmail.com"))
						{
							auth.signInWithEmailAndPassword(editEmail.getText().toString().trim(), editPassword.getText().toString().trim()).addOnCompleteListener(LoginActivity.this, _auth_sign_in_listener);
							SketchwareUtil.hideKeyboard(getApplicationContext());
							_loadingdialog(true, "Please wait...");
						}
						else
						{
							SketchwareUtil.showMessage(getApplicationContext(), "Please login with admin mail.");
						}
					}
				}
			}
		});
		

		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					_loadingdialog(false, "");
					SketchwareUtil.showMessage(getApplicationContext(), "Successfully Login");
					_getTime();
					i.setClass(getApplicationContext(), HomeActivity.class);
					startActivity(i);
					finish();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
					_loadingdialog(false, "");
				}
			}
		};
		
	}
	
	private void initializeLogic() {
		_SX_CornerRadius_card(loginBtn, "#F47F3C", 12);
		_SX_CornerRadius_card(editEmail, "#FFFFFF", 12);
		_SX_CornerRadius_card(editPassword, "#FFFFFF", 12);
	}

	
	@Override
	public void onBackPressed() {
		finishAffinity();
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
	
	
	public void _SX_CornerRadius_card (final View _view, final String _color, final double _value) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_value);
		_view.setBackground(gd);
		
		if (Build.VERSION.SDK_INT >= 21){
			_view.setElevation(5);
		}
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

	
	
	public void _getTime () {
		clnd = Calendar.getInstance();
		tme.edit().putString("time_", String.valueOf(clnd.getTimeInMillis())).commit();
	}

}
