package com.example.computer.loginpagedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private EditText mEmail;
    private EditText mPassword, mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPreferences = getSharedPreferences(getPackageName() + ".my_pref", MODE_PRIVATE);

        mEmail = findViewById(R.id.editText_email);
        mPassword = findViewById(R.id.editText_password);
        mConfirmPassword = findViewById(R.id.editText_confirm_password);
    }

    public void register(View view) {
        String userEmail = mEmail.getText().toString().trim();
        String userPassword = mConfirmPassword.getText().toString();

        if (TextUtils.equals(userPassword, mPassword.getText().toString())) {

            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(userEmail + MainActivity.USERNAME, userEmail);
            editor.putString(userEmail + MainActivity.PASSWORD, userPassword);
            editor.apply();

            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }

    public void redirectLogin(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
