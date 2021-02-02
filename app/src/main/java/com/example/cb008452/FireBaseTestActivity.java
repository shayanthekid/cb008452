package com.example.cb008452;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.User;

public class FireBaseTestActivity extends AppCompatActivity {
    private static final String TAG = "FireBaseTestActivity";
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText txtName,txtEmail,txtPassword;

    private Button btnDelete,btnLogin,btnRegister,btnUpdate,btnImagePicker;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    private static  String ImageURl;
    private ImageView imageView;
//    public User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_test);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnDelete = findViewById(R.id.btnDelete);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnImagePicker = findViewById(R.id.btnImagePicker);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        imageView = findViewById(R.id.imageView);


    btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            UploadFile();
        }
    });

btnLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AuthenticateUser();
    }
});



        btnImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openFileChooser();

            }
        });


    }



    private void RegisterUser(String email, String name, String password, String picture) {

        User user = new User(email,name, password,picture);
        String userID = mDatabase.push().getKey();
        HashMap<String,String> UserAuth = new HashMap<>();
        UserAuth.put(name,userID);
        mDatabase.child("users").child(userID).setValue(user);
        mDatabase.child("userAuth").setValue(UserAuth);
    }


    private void AuthenticateUser(){

      Query query=  mDatabase.child("userAuth");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
          String name = txtName.getText().toString();
           String ID = dataSnapshot.child(name).getValue().toString();
//                System.out.println(ID);
                Query query = mDatabase.child("users").child(ID);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                        User user = dataSnapshot2.getValue(User.class);

                        if(txtPassword.getText().toString().equals( user.getPassword())){
                            Toast.makeText(FireBaseTestActivity.this, "You won bitch", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(FireBaseTestActivity.this, "Wrong passwrod", Toast.LENGTH_SHORT).show();
                            System.out.println(user.getPassword());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError2) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }






    private void UploadFile(){

        if(mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
            + "." + getFileExtenstion(mImageUri));
            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(FireBaseTestActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                            user.setImageUrl(taskSnapshot.getUploadSessionUri().toString());
                            String name,email,password;
                            name = txtName.getText().toString();
                            email = txtEmail.getText().toString();
                            password = txtPassword.getText().toString();
                            ImageURl = taskSnapshot.getUploadSessionUri().toString();
                            RegisterUser(email,name,password,ImageURl);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(FireBaseTestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(FireBaseTestActivity.this, "Uploading", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        else
        {
            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
        }


    }





    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
    && data != null && data.getData() != null){
    mImageUri = data.getData();
    imageView.setImageURI(mImageUri);
//    user.setProfilePic(mImageUri);
    }

    }


    private String getFileExtenstion(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}







