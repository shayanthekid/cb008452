package repositories;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.cb008452.FireBaseTestActivity;
import com.example.cb008452.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.security.auth.callback.Callback;

import models.User;

public class AuthenticationRepository {
    private StorageReference mStorageRef;
    private Application application;
    private DatabaseReference mDatabase;
    private MutableLiveData<Boolean> UserMutableLiveData;
    private MutableLiveData<User> userMutableLiveData;
    public User user2 = new User();
    private boolean authen;
    private static AuthenticationRepository instance;





    public AuthenticationRepository(Application application){
        this.application = application;
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        UserMutableLiveData = new MutableLiveData<>();
        userMutableLiveData  =new MutableLiveData<>();
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        userMutableLiveData.postValue(user2);

    }



//    public AuthenticationRepository getInstance(){
//        if(instance == null){
//            instance = new AuthenticationRepository();
//            userMutableLiveData.postValue(user2);
//
//            mDatabase = FirebaseDatabase.getInstance().getReference();
//            UserMutableLiveData = new MutableLiveData<>();
//            mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
//
//        }
//        return instance;
//    }



    public void Login(final String Name, final String password){


        Query query=  mDatabase.child("userAuth");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                String name = Name;
                String ID = dataSnapshot.child(Name).getValue().toString();
//                System.out.println(ID);
                Query query = mDatabase.child("users").child(ID);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                        User user = dataSnapshot2.getValue(User.class);

                        if(password.equals( user.getPassword())){
//                         Toast.makeText(context, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            userMutableLiveData.postValue(user);
                            user2 = user;
                         setAuthen(true);

                        }
                        else {
//                            Toast.makeText(context, "Incorrect Details", Toast.LENGTH_SHORT).show();
                            user2 = null;
                          setAuthen(false);
//                            System.out.println(user.getPassword());


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


    public void setUserMutableLiveData(MutableLiveData<User> userMutableLiveData) {
        this.userMutableLiveData = userMutableLiveData;
    }

    public void setAuthen(boolean authen) {
        this.authen = authen;
    }

    public boolean isAuthen() {
        return authen;
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
