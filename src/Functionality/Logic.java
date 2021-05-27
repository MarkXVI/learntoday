package Functionality;

import Model.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class Logic {

    public boolean checkValidRegister(String firstName, String lastName, String username, String password, String accountType) {
        String[] user = {firstName, lastName, username, password};

        if (accountType.equals("Choose Account Type")) { // Checks if the user choose an Account Type.
            return false;
        }
        for (String validCheck : user) {
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

        ArrayList<String> checkValid = new ArrayList<>();

        for(String alt: alternatives){
            if(alt.isEmpty()){
                return false;
            }
            if(checkValid.contains(alt)){
                return false;
            }
            checkValid.add(alt);
        }
        return true;
    }
}