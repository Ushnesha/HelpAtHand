package com.example.android.helphand;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by darip on 15-05-2017.
 */

public class UploadRequest extends StringRequest {
    private static final String Upload_Request_URL = "https://ush-2168.000webhostapp.com/uploadedPic.php";
    private Map<String, String> params;

    public UploadRequest(String encodedImage, String imageName, Response.Listener<String> listener){
        super(Method.POST, Upload_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("image", encodedImage );
        params.put("name", imageName);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
