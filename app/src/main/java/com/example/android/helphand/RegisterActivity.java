package com.example.android.helphand;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
//    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        progressdialog = new ProgressDialog(this);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etRePassword = (EditText) findViewById(R.id.etRepassword);
        final EditText etPhone = (EditText) findViewById(R.id.etPhone);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String repassword = etRePassword.getText().toString();
                final String email = etEmail.getText().toString();
                final String phone = etPhone.getText().toString();
                String agee = etAge.getText().toString();
                final int age = !(agee.trim()).equals("") ? Integer.parseInt(agee) : 0;


                if(!password.equals(repassword)){
                    Toast.makeText(RegisterActivity.this, "Your passwords do not match, please confirm your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || age == 0){
                    Toast.makeText(RegisterActivity.this, "Please enter your credentials completely", Toast.LENGTH_SHORT).show();
                    return;
                }
//                progressdialog.setMessage("Registering User...");
//                progressdialog.show();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                RegisterRequest registerRequest = new RegisterRequest(name, username, email, age, password, phone, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}