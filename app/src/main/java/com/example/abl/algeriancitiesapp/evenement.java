package com.example.abl.algeriancitiesapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class evenement extends AppCompatActivity {
   private TextView type ,categorie,place;
   private Button gal;
    static final int Cam_Request=1;
    static final int Pick_Image=1;
    private ImageView imageView;
    private Uri imageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef,mDatabaseRef2;
    private Button Envoyer;
    private ProgressBar mProgressBar;
    private TextView TypeEvent,CatEvent,Etat,Date,Obs;

    private EditText Desc;
    private StorageTask mUploadTask;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    String date;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenement);
        type = (TextView) findViewById(R.id.typeevt);
        categorie = (TextView) findViewById(R.id.cat);

        place = (TextView) findViewById(R.id.adresse);
        Obs = (TextView) findViewById(R.id.obs);
        type.setText(getIntent().getStringExtra("type"));
        categorie.setText(getIntent().getStringExtra("categorie"));

        place.setText(getIntent().getStringExtra("place"));
        String IdUser=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mStorageRef= FirebaseStorage.getInstance().getReference();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Events");
        mDatabaseRef2= FirebaseDatabase.getInstance().getReference("UsersEvents").child(IdUser);

        mProgressBar=(ProgressBar)findViewById(R.id.progressBar) ;

        TypeEvent = (TextView) findViewById(R.id.typeevt);
        CatEvent = (TextView) findViewById(R.id.cat);
        Date = (TextView) findViewById(R.id.date);

        Desc = (EditText) findViewById(R.id.Desc1);
        calendar=Calendar.getInstance();
        simpleDateFormat= new SimpleDateFormat(("dd-MM-yyyy HH:mm:ss"));
        date = simpleDateFormat.format(calendar.getTime());

        Date.setText(date);


        Envoyer=(Button)findViewById(R.id.envoyer);
        Envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadTask!=null && mUploadTask.isInProgress()){
                    Toast.makeText(getApplicationContext(),getString(R.string.envoieEnCours),Toast.LENGTH_LONG).show();

                }else {
                    upload();
                }
            }
        });


        imageView=(ImageView)findViewById(R.id.image);
        gal=(Button)findViewById(R.id.gal);

        gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //Intent gallery_intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,Pick_Image);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Cam_Request){
           // Bitmap bitmap =(Bitmap)data.getExtras().get("data");
           // imageView.setImageBitmap(bitmap);
        }
        if(requestCode==Pick_Image && resultCode==RESULT_OK && data !=null && data.getData()!=null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver CR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(CR.getType(uri));
    }
    public void upload(){
        if (imageUri !=null){
            mProgressBar.setVisibility(View.VISIBLE);
            StorageReference fileRef=mStorageRef.child(System.currentTimeMillis()
            +"."+ getFileExtension(imageUri));
            mUploadTask= fileRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                             mProgressBar.setVisibility(View.INVISIBLE);
                            String uploadId=mDatabaseRef.push().getKey();
                            //String uploadId2=mDatabaseRef2.push().getKey();
                            insertEvent upload=new insertEvent(uploadId,TypeEvent.getText().toString(),CatEvent.getText().toString(),Desc.getText().toString()
                                    ,getString(R.string.nonTraiter), place.getText().toString(),taskSnapshot.getDownloadUrl().toString(),getString(R.string.publier)+Date.getText().toString()
                                    ,getString(R.string.aucune),getString(R.string.modAucune),getApplicationContext());
                            mDatabaseRef.child(uploadId).setValue(upload);
                            mDatabaseRef2.child(uploadId).setValue(upload);
                            Toast.makeText(getApplicationContext(),getString(R.string.BienEnvoyer),Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),accueil.class);
                            startActivity(intent);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                    });

        }else{
            Toast.makeText(getApplicationContext(),getString(R.string.ajoutPhoto),Toast.LENGTH_LONG).show();
        }

    }
}
