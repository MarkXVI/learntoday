package Functionality;

public class Logic {

    public boolean check_ValidRegister(String FirstName, String LastName, String Username, String Password, String AccountType){
        String[] user = {FirstName, LastName, Username, Password};

        if (AccountType.equals("Choose Account Type")){ // Checks if the user choose an Account Type.
            return false;
        }
        for(String validCheck: user){
            if (validCheck.length() < 3){
                return false;
            }
        }
        return true;
    }
}
