package com.example.computer.loginpagedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private EditText username, password;
    private CheckBox myCheckBox;
    private SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreferences = getSharedPreferences(getPackageName() + ".my_pref", MODE_PRIVATE);

        username = findViewById(R.id.editText_email);
        password = findViewById(R.id.editText_password);
        LinearLayout linearLayout = findViewById(R.id.linearLayout5);
        myCheckBox = findViewById(R.id.checkBox);

        username.setText(mPreferences.getString(USERNAME, ""));
        password.setText(mPreferences.getString(PASSWORD, ""));

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login_username = username.getText().toString().trim();
                String login_password = password.getText().toString();

                if (myCheckBox.isChecked()) {
                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putString(USERNAME, login_username);
                    editor.putString(PASSWORD, login_password);
                    editor.apply();
                }

                if (TextUtils.equals(login_username, mPreferences.getString(login_username + USERNAME, "")) &&
                TextUtils.equals(login_password, mPreferences.getString(login_username + PASSWORD, ""))) {
                    startActivity(new Intent(MainActivity.this, DetailsActivity.class));
                    Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}