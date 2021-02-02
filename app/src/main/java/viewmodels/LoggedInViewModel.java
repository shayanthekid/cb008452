package viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import repositories.AuthRepository;

public class LoggedInViewModel extends AndroidViewModel {



    /*
    *
    * This class is called loggedin refering to the activity
    * the user is sent to once logged in/registered
    * */


    private AuthRepository authRepository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> LoggedOutMutableLiveData;

    public LoggedInViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
        userMutableLiveData = authRepository.getUserMutableLiveData();
        LoggedOutMutableLiveData = authRepository.getLoggedOutMutableLiveData();


    }


    public void LogOut(){
        authRepository.Logout();

    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return LoggedOutMutableLiveData;
    }
}
