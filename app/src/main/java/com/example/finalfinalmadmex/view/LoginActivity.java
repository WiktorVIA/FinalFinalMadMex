package com.example.finalfinalmadmex.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalfinalmadmex.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    CallbackManager mCallbackManager;
    Button btnLoginFb;
    private FirebaseAuth mAuth;
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText logEmail, logPassword;
        FirebaseAuth fAuth;
        Button btnLogin;
        ProgressBar loginProgressBar;
        TextView openRegActivity;


        remember = findViewById(R.id.rememberMe);
        logEmail = findViewById(R.id.logEmail);
        logPassword = findViewById(R.id.logPassword);
        fAuth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.btnLogin);
        loginProgressBar = findViewById(R.id.loginProgressBar);
        openRegActivity = findViewById(R.id.openRegActivity);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")) {
            //    startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else if (checkbox.equals("false")) {
        }

        //Initialize Facebook SDK
        FacebookSdk.sdkInitialize(LoginActivity.this);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();


        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        btnLoginFb = findViewById(R.id.btnFacebook);
        btnLoginFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                        Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "Callback is canceled");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "Callback is in error");
                    }
                });
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = logEmail.getText().toString().trim();
                final String password = logPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    logEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    logPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    logPassword.setError("Password must be at least 6 characters long");
                    return;
                }

                //sets the progress bar to visible as the user goes through the process
               loginProgressBar.setVisibility(View.VISIBLE);

                // Authenticate the user

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));


                        } else {
                            Toast.makeText(LoginActivity.this, "An Error as occurred " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            loginProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });

        //Code to set the checklistener for Remember me.
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    SharedPreferences  preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();

                }else if(!buttonView.isChecked()){
                    SharedPreferences  preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                }
            }

        });


openRegActivity.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity (new Intent(getApplicationContext(),RegisterActivity.class));
    }
});

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            updateUI(currentUser);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if(user !=null)
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Please sign in to continue", Toast.LENGTH_SHORT).show();
        }
    }

}





