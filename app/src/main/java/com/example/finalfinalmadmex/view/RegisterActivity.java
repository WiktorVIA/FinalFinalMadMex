package com.example.finalfinalmadmex.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalfinalmadmex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText tvFullName, tvEmail, tvPassword, tvPhone;
    TextView openLogActivity;
    Button btnRegister;
    FirebaseAuth fAuth;
    ProgressBar registerProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);
        tvPhone = findViewById(R.id.tvPhone);
        btnRegister = findViewById(R.id.btnRegister);
        openLogActivity = findViewById(R.id.openLogActivity);


        registerProgressBar = findViewById(R.id.registerProgressBar);
        fAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tvEmail.getText().toString().trim();
                String password = tvPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    tvEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    tvPassword.setError("Password is required.");
                    return;
                }
                if(password.length()<6)
                {
                    tvPassword.setError("Password must have at least 6 characters");
                }

                registerProgressBar.setVisibility(View.VISIBLE);
                
                //Registering the user in Firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegisterActivity.this, "New User Created", Toast.LENGTH_SHORT).show();
                            startActivity (new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        openLogActivity.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
        startActivity (new Intent(getApplicationContext(),LoginActivity.class));
    }
});

    }
}
