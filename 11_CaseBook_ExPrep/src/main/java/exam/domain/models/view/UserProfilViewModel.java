package exam.domain.models.view;

import exam.domain.entities.Gender;

public class UserProfilViewModel {

    private String username;
    private String gender;

    public UserProfilViewModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
