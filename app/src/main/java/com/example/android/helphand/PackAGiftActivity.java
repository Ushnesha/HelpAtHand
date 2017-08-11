package com.example.android.helphand;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.bitmap;
import static android.R.attr.tag;

public class PackAGiftActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String TAG = "MyActivity";
//    private static final String SERVER_ADDRESS = "https://ush-2168.000webhostapp.com";

    ImageView imgUpld;
    Button bUpld;
    EditText imgName;
    Bitmap image = null;
    String encodeImage;
    String imageName;
    String selectedPhoto;
//    Uri selectedImage;
    GalleryPhoto galleryPhoto;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_agift);


        imgUpld = (ImageView) findViewById(R.id.upload_img);

        bUpld = (Button) findViewById(R.id.upload_btn);
        imgName = (EditText) findViewById(R.id.etUploadImageName);

        imgUpld.setOnClickListener(this);
        bUpld.setOnClickListener(this);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.INVISIBLE);
        galleryPhoto = new GalleryPhoto(getApplicationContext());


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.upload_img) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }

        if (id == R.id.upload_btn) {
            if(imgUpld.getDrawable() == null){
                Toast.makeText(PackAGiftActivity.this, "Image is empty!!", Toast.LENGTH_SHORT).show();

            }
            imageName = imgName.getText().toString();
            if(TextUtils.isEmpty(imageName)){
                Toast.makeText(PackAGiftActivity.this, "Image Name is empty!!", Toast.LENGTH_SHORT).show();
            }
            else {
                mProgressBar.setVisibility(View.VISIBLE);
                new UploadImage().execute();
            }

//            Bitmap image = ((BitmapDrawable) imgUpld.getDrawable()).getBitmap();
//            Bitmap emptyBitmap = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
//            final String imageName = imgName.getText().toString();
//            if (TextUtils.isEmpty(imageName) || image.sameAs(emptyBitmap)) {
//                Toast.makeText(PackAGiftActivity.this, "Image is empty!!", Toast.LENGTH_SHORT).show();
//                return;
        }
    }

//
//            UploadRequest uploadRequest = new UploadRequest(imageName, responseListener);
//            RequestQueue queue = Volley.newRequestQueue(PackAGiftActivity.this);
//            queue.add(uploadRequest);
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
           imgUpld.setImageURI(selectedImage);
            galleryPhoto.setPhotoUri(selectedImage);
            selectedPhoto = galleryPhoto.getPath();

//            galleryPhoto.setPhotoUri(selectedImage);
//            String photoPath = galleryPhoto.getPath();
//            selectedPhoto = photoPath;

        }
    }


    private class UploadImage extends AsyncTask<Void, Void, Void> {
        //        Bitmap image;
//        String name;
//
//        public UploadImage(Bitmap image, String name){
//            this.image = image;
//            this.name = name;
//        }
//
        @Override
        protected void onPostExecute(Void aVoid) {

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.i(TAG, response );
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        mProgressBar.setVisibility(View.INVISIBLE);
                        if (success) {
                            Toast.makeText(PackAGiftActivity.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PackAGiftActivity.this, UserActivity.class);
                            PackAGiftActivity.this.startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(PackAGiftActivity.this);
                            builder.setMessage("Image upload failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            UploadRequest uploadRequest = new UploadRequest(encodeImage, imageName, responseListener);
           RequestQueue queue = Volley.newRequestQueue(PackAGiftActivity.this);
             queue.add(uploadRequest);

            super.onPostExecute(aVoid);
        }

        //
        @Override
        protected Void doInBackground(Void... voids) {
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();

            try {


                    image = com.kosalgeek.android.photoutil.ImageLoader.init().from(selectedPhoto).requestSize(1024, 1024).getBitmap();
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                    encodeImage = ImageBase64.encode(image);
                Log.e("EncodedImage: ", encodeImage);

                
            }catch (FileNotFoundException e){
                Toast.makeText(getApplicationContext(), "Something went wrong!!", Toast.LENGTH_SHORT).show();
            }
//            Map<String, String> params;
//            params = new HashMap<>();
//            params.put("name", name);
//            params.put("image", encodeImage);
//            String encodedStr = getEncodedData(params);
            return null;
        }
    }
};

//
//            try {
//                //Converting address String to URL
//                URL url = new URL(SERVER_ADDRESS + "Register.php");
//                //Opening the connection (Not setting or using CONNECTION_TIMEOUT)
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//                //Post Method
//                con.setRequestMethod("POST");
//                //To enable inputting values using POST method
//                //(Basically, after this we can write the dataToSend to the body of POST method)
//                con.setDoOutput(true);
//                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
//                //Writing dataToSend to outputstreamwriter
//                writer.write(encodedStr);
//                //Sending the data to the server - This much is enough to send data to server
//                //But to read the response of the server, you will have to implement the procedure below
//                writer.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//    }
//    private String getEncodedData(Map<String,String> data) {
//        StringBuilder sb = new StringBuilder();
//        for(String key : data.keySet()) {
//            String value = null;
//            try {
//                value = URLEncoder.encode(data.get(key),"UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//            if(sb.length()>0)
//                sb.append("&");
//
//            sb.append(key + "=" + value);
//        }
//        return sb.toString();
//    }
//
//}
