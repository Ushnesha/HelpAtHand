package com.example.android.helphand;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    Button button, button3, button4, button2;
    GetLocation gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        button = (Button) findViewById(R.id.button);
       button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, PackAGiftActivity.class);
                UserActivity.this.startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                gps = new GetLocation(UserActivity.this);
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Your Location is- \nLat: "+ latitude +"\nLong: "+ longitude, Toast.LENGTH_LONG).show();
                }else {
                    gps.showSettingAlert();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, OneTimeVolunteerActivity.class);
                UserActivity.this.startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, InternshipActivity.class);
                UserActivity.this.startActivity(intent);
            }
        });
    }

//    public void onClickOpenAddressButton(View v) {
//        String addressString = "Atal Bihari Vajpayee Indian Institute of Technology";
//        Uri addressUri = Uri.parse("geo:0,0?q=" + addressString);
//        showMap(addressUri);
//    }
//    private void showMap(Uri geoLocation) {
//
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(geoLocation);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

}
