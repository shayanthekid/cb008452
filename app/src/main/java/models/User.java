package models;

import android.net.Uri;
import android.text.TextUtils;

public class User {
    String Email, Name, Password, ImageUrl;
//    Uri ProfilePic;
    public User(){

    }

    public User(String email, String name, String password, String ImageUrl) {
        this.Email = email;
        this.Name = name;
        this.Password = password;
        this.ImageUrl = ImageUrl;
 ;

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
//    public Uri getProfilePic() {
//        return ProfilePic;
//    }
//
//    public void setProfilePic(Uri profilePic) {
//        ProfilePic = profilePic;
//    }
}
