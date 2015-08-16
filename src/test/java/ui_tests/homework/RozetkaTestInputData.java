package ui_tests.homework;

import org.openqa.selenium.security.UserAndPassword;

public class RozetkaTestInputData {
    private static UserAndPassword credentials = new UserAndPassword("dolv2000@mail.ru","P@ssw0rd");

    public String getFacebookLogin() {
        return this.credentials.getUsername();
    }

    public String getFacebookPassword() {
        return this.credentials.getPassword();
    }
}
