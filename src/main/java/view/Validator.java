package view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Validator {
    public boolean validateUsername(String username){
        return username.matches("^(?=[a-zA-Z0-9._]{4,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean validatePassword(String password){
        return password.matches("^(?=.*[0-9])(?=.*[a-z]).{8,32}$");
    }

    public boolean validatePassportDetails(String passportDetails){
        return passportDetails.matches("^[0-9]{9}$");
    }

    public boolean validateDate(String startDate, String endDate){
        return startDate.matches("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$") &&
                endDate.matches("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$") &&
                (ChronoUnit.DAYS.between(LocalDate.parse(startDate), LocalDate.parse(endDate))>0);
    }
}
