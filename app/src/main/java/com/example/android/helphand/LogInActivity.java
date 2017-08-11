package com.example.android.helphand;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView registerLink;
    Button bLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Login1Activity.this, "Trying to click", Toast.LENGTH_SHORT).show();
                Intent registerIntent = new Intent(LogInActivity.this, RegisterActivity.class);
                LogInActivity.this.startActivity(registerIntent);
            }
        });


        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LogInActivity.this, UserActivity.class);
//                LogInActivity.this.startActivity(intent);
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                if(TextUtils.isEmpty(password) || TextUtils.isEmpty(email)) {
                    Toast.makeText(LogInActivity.this, "Please enter your credentials completely", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
//                            Log.i("tagconvertstr", "["+response+"]");
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Toast.makeText(LogInActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                                String name = jsonResponse.getString("name");
                                String username = jsonResponse.getString("username");
                                String email = jsonResponse.getString("email");
                                int age = jsonResponse.getInt("age");
                                String phone = jsonResponse.getString("phone");
                                Intent intent = new Intent(LogInActivity.this, UserActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                intent.putExtra("email", email);
                                intent.putExtra("age", age);
                                intent.putExtra("phone", phone);
                                LogInActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                                builder.setMessage("Login failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LogInActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}