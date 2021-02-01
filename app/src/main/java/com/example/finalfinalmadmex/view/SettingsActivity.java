package com.example.finalfinalmadmex.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalfinalmadmex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity";
    private Button btnResPass;
    private Button btnResEmail;
    EditText newPass;
    EditText newEmail;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnResPass = findViewById(R.id.btn_change_pass);
        btnResEmail = findViewById(R.id.btn_change_email);
        newPass = findViewById(R.id.new_pass_et);
        newEmail = findViewById(R.id.new_email);






        btnResEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = newEmail.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    newEmail.setError("Email is required.");
                    return;
                }
                user.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SettingsActivity.this, "Your Email has been succsessfully changed to" + email, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "User email address updated.");
                    }
                });
            }
        });

        btnResPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = newPass.getText().toString().trim();
                if(TextUtils.isEmpty(password))
                {
                    newEmail.setError("Email is required.");
                    return;
                }
                user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SettingsActivity.this, "Your Password has been succsessfully changed to" + password, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "User password address updated.");
                    }
                });
            }
        });
    }


}