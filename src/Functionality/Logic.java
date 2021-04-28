package Functionality;

public class Logic {

    public boolean check_ValidRegister(String FirstName, String LastName, String Username, String Password){
        String[] user = {FirstName, LastName, Username, Password};

        for(String validCheck: user){
            if (validCheck.length() < 1){
                return false;
            }
        }
        return true;
    }
}
