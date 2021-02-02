package viewmodels;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cb008452.OnboardingItem;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import models.User;
import repositories.AuthRepository;

public class UserViewmodel extends AndroidViewModel {

    private AuthRepository authRepository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;



    public UserViewmodel(@NonNull Application application){
        super(application);
        authRepository = new AuthRepository(application);
        userMutableLiveData = authRepository.getUserMutableLiveData();
    }

    public void Register(String email,String password){
        authRepository.Register(email,password);
    }

    public void Login(String email,String password){
        authRepository.Login(email,password);

    }



    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
