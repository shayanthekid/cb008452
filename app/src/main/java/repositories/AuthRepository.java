package repositories;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import models.User;

public class AuthRepository {

    private Application application;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private MutableLiveData<User> UserMutableLiveData;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> LoggedOutMutableLiveData;

    public AuthRepository(Application application){
    this.application = application;
    mAuth = FirebaseAuth.getInstance();
    userMutableLiveData = new MutableLiveData<>();
    LoggedOutMutableLiveData = new MutableLiveData<>();

    if(mAuth.getCurrentUser() != null){
        userMutableLiveData.postValue(mAuth.getCurrentUser());
        LoggedOutMutableLiveData.postValue(false);
    }
    }





    public void Register(String email, String password){

        mAuth.createUserWithEmailAndPassword(email,password) //this was the method i used to create the user, idk if there r other methods to put the photo and shit
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userMutableLiveData.postValue(mAuth.getCurrentUser());
                        } else{
                            Toast.makeText(application, "Registration failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    public void Register2(String email, String password, String name, String picture){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        User user = new User(email,name, password,picture);
        String userID = mDatabase.push().getKey();
        HashMap<String,String> UserAuth = new HashMap<>();
        UserAuth.put(name,userID);
        mDatabase.child("users").child(userID).setValue(user);
        mDatabase.child("userAuth").setValue(UserAuth);
//        UserMutableLiveData.setValue(user);

    }


    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }


    public void Login(String email, String password){
mAuth.signInWithEmailAndPassword(email,password)
        .addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    userMutableLiveData.postValue(mAuth.getCurrentUser());
                }
                else{
                    Toast.makeText(application, "Login failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();


                }

            }
        });
    }


    public void Logout(){
        mAuth.signOut();
        LoggedOutMutableLiveData.postValue(true);
    }



    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return LoggedOutMutableLiveData;
    }
}



