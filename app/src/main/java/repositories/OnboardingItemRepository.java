package repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.cb008452.OnboardingItem;
import com.example.cb008452.R;

import java.util.ArrayList;
import java.util.List;

public class OnboardingItemRepository {

    private static OnboardingItemRepository instance;
    private List<OnboardingItem> dataSet = new ArrayList<>();

    public static OnboardingItemRepository getInstance(){
    if(instance == null){
        instance = new OnboardingItemRepository();
    }
    return instance;
}

    public MutableLiveData<List<OnboardingItem>> getOnboardingItems(){

        setOnboardingItems();
        MutableLiveData<List<OnboardingItem>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }


    private void setOnboardingItems(){

        OnboardingItem ladyCart = new OnboardingItem();
        ladyCart.setTitle("Welcome to LookGOOD!");
        ladyCart.setDescription("Clothes shopping has never been easier! Hassle free buying for all your needs!");
        ladyCart.setImage(R.drawable.ic_ladywithcartv2);

        OnboardingItem ladyBags = new OnboardingItem();
        ladyBags.setTitle("LookGOOD to feel good");
        ladyBags.setDescription("Shop with us to leave you feeling satisfied.Always!");
        ladyBags.setImage(R.drawable.ladywithbagv2);


        OnboardingItem ladyDress = new OnboardingItem();
        ladyDress.setTitle("Curated Selections");
        ladyDress.setDescription("Our vast catalogue of options will leave you wanting for more!");
        ladyDress.setImage(R.drawable.ladywithclothesv2);

        dataSet.add(ladyCart);
        dataSet.add(ladyBags);
        dataSet.add(ladyDress);
    }
}
