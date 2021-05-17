package Functionality;

import Model.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class Logic {

    public boolean checkValidRegister(String FirstName, String LastName, String Username, String Password, String AccountType) {
        String[] user = {FirstName, LastName, Username, Password};

        if (AccountType.equals("Choose Account Type")) { // Checks if the user choose an Account Type.
            return false;
        }
        for (String validCheck: user) {
            if (validCheck.length() < 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSufficientQuestions(String quiz_name) throws SQLException {
        return new DBConnection().checkQuestionAmount(quiz_name) > 0;
    }

    public static boolean checkValidQuiz(String question, String alt1, String alt2, String alt3, String alt4){
        ArrayList<String> alternatives = new ArrayList<>();
        alternatives.add(question);
        alternatives.add(alt1);
        alternatives.add(alt2);
        alternatives.add(alt3);
        alternatives.add(alt4);

        for(String alt: alternatives){
            if(alt.isEmpty()){
                return false;
            }
        }
        return true;
    }
}