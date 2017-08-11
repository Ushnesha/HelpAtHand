package com.example.android.helphand;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by darip on 24-04-2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String Register_Request_URL = "https://ush-2168.000webhostapp.com/HAHRegister.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String email, int age, String password, String phone, Response.Listener<String> listener){
        super(Method.POST, Register_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("email", email);
        params.put("age", age + "");
        params.put("password", password);
        params.put("phone", phone);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

