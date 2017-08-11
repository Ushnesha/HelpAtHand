package com.example.android.helphand;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class GoForInternshipActivity extends AppCompatActivity implements View.OnClickListener{


        private static final int RESULT_LOAD_Video = 1;
    VideoView vdoUpld;
        Button bvUpld;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_go_for_internship);

            vdoUpld= (VideoView) findViewById(R.id.videoView);

            bvUpld= (Button) findViewById(R.id.button6);


            vdoUpld.setOnClickListener(this);
            bvUpld.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if(id == R.id.videoView){
                Intent galleryVIntent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryVIntent, RESULT_LOAD_Video);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode== RESULT_LOAD_Video && resultCode == RESULT_OK && data != null){
                Uri selectedImage = data.getData();
                vdoUpld.setVideoURI(selectedImage);
            }
        }
    }

