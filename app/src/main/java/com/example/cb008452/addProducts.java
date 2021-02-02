package com.example.cb008452;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import models.Product;
import models.User;

public class addProducts extends AppCompatActivity {

    private  EditText txtTitle,txtPrice,txtDescription,txtCategory,txtBrandKey,txtBrandValue,txtGenderKey,txtGenderValue;
    private   Button btnUpload,btnInsert;
    private   ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 1;
    private FirebaseAuth mAuth;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    private static  String ImageURl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
        txtCategory = findViewById(R.id.txtCategory);
        txtBrandKey = findViewById(R.id.txtBrandKey);
        txtBrandValue = findViewById(R.id.txtBrandValue);
        txtGenderKey = findViewById(R.id.txtGenderKey);
        txtGenderValue = findViewById(R.id.txtGenderValue);
        btnUpload = findViewById(R.id.btnUpload);
        btnInsert = findViewById(R.id.btnInsert);
        imageView = findViewById(R.id.imageView);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference("products");

btnUpload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openFileChooser();
    }
});
btnInsert.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        UploadFile();
    }
});

    }


    public void InsertProduct(String title,int price, String description,String category,
            String brandk,String brandv, String genderk,String genderv,String picture, Uri imageuri )
        {



        HashMap<String, String> Type = new HashMap<>();
        Type.put(brandk,brandv);
        Type.put(genderk,genderv);
        Product product = new Product(title,description,category,picture,price,Type);
        mDatabase.child("products").child(title).setValue(product);


    }


    private void UploadFile(){

        if(mImageUri != null){
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtenstion(mImageUri));
            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(addProducts.this, "Product Added", Toast.LENGTH_SHORT).show();
fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
    @Override
    public void onSuccess(Uri uri) {
        String url = uri.toString();
        String title;
        int price;
        String description, category, brandk,brandv,  genderk, genderv, picture;
        Uri imageuri;
        title = txtTitle.getText().toString();
        price =Integer.parseInt(txtPrice.getText().toString());
        description = txtDescription.getText().toString();
        category = txtCategory.getText().toString();
        brandk = txtBrandKey.getText().toString();
        brandv = txtBrandValue.getText().toString();
        genderk = txtGenderKey.getText().toString();
        genderv = txtGenderValue.getText().toString();
        picture = url;
        imageuri = null;
        InsertProduct(title,price,description,category,brandk,brandv,genderk,genderv,picture,imageuri);
    }
});
//                            String title;
//                            int price;
//                            String description, category, brandk,brandv,  genderk, genderv, picture;
//                            Uri imageuri;
//                            title = txtTitle.getText().toString();
//                            price =Integer.parseInt(txtPrice.getText().toString());
//                            description = txtDescription.getText().toString();
//                            category = txtCategory.getText().toString();
//                            brandk = txtBrandKey.getText().toString();
//                            brandv = txtBrandValue.getText().toString();
//                            genderk = txtGenderKey.getText().toString();
//                            genderv = txtGenderValue.getText().toString();
//
//                            picture = taskSnapshot.getUploadSessionUri().toString();
//                            imageuri = taskSnapshot.getUploadSessionUri();
//                            InsertProduct(title,price,description,category,brandk,brandv,genderk,genderv,picture,imageuri);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(addProducts.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(addProducts.this, "Uploading", Toast.LENGTH_SHORT).show();
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

        }

    }


    private String getFileExtenstion(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }



}