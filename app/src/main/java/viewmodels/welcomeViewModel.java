package viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cb008452.OnboardingItem;

import java.util.List;

import repositories.OnboardingItemRepository;

public class welcomeViewModel extends ViewModel {
    private MutableLiveData<List<OnboardingItem>> mOnboardingItem;
    private OnboardingItemRepository mRepo;

    public void init(){
if(mOnboardingItem != null){
    return;
}
mRepo = OnboardingItemRepository.getInstance();
mOnboardingItem = mRepo.getOnboardingItems();
    }

    public LiveData<List<OnboardingItem>> getOnboardingItem(){
        return mOnboardingItem;
    }

}
