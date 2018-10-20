package com.example.abl.algeriancitiesapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class image extends Activity {
    private Button button,button1;
    private ImageView imageView;
    static final int Cam_Request=1;
    private TextView type, cat, type1,cat1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        button=(Button) findViewById(R.id.cam);
        imageView=(ImageView)findViewById(R.id.imageView);
        type = (TextView) findViewById(R.id.type);
        cat = (TextView) findViewById(R.id.cat);
        type.setText(getIntent().getStringExtra("evt2"));
        cat.setText(getIntent().getStringExtra("cat"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent,Cam_Request);

            }
        });
        button1=(Button) findViewById(R.id.ok);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Type= type.getText().toString();
                String Cat = cat.getText().toString();
                Intent intent=new Intent(getApplicationContext(),evenement.class);
                intent.putExtra("type",Type);
                intent.putExtra("categorie",Cat);
                startActivity(intent);
                /*SIntent intent2=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent2);*/

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Cam_Request){
            Bitmap bitmap =(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}