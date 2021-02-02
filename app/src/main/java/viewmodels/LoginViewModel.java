package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import models.User;
import repositories.AuthRepository;
import repositories.AuthenticationRepository;

public class LoginViewModel extends AndroidViewModel {

    private AuthenticationRepository authenticationRepository;
    private MutableLiveData<User> userMutableLiveData;
private boolean authen;



    public LoginViewModel(@NonNull Application application) {
        super(application);
        authenticationRepository = new AuthenticationRepository(application);
//        authenticationRepository.getInstance();
        userMutableLiveData = authenticationRepository.getUserMutableLiveData();
        authen = authenticationRepository.isAuthen();
    }



    public void Login(final String name, final String password) {
        authenticationRepository.Login(name, password);
    }

    public void setUserMutableLiveData(MutableLiveData<User> userMutableLiveData) {
        this.userMutableLiveData = userMutableLiveData;
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }
    public boolean authen(){
        return authen;
    }

//    public void init() {
//        if (userMutableLiveData != null) {
//            return;
//        }
//    }
}
