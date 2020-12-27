package view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Validator {
    public void validateUsernamePassword(String username, String password) {
        if (!(username.matches("^(?=[a-zA-Z0-9._]{4,20}$)(?!.*[_.]{2})[^_.].*[^_.]$") && password.matches("^(?=.*[0-9])(?=.*[a-z]).{8,32}$"))) {
            throw new RuntimeException("Invalid username or password");
        }
    }

    public void validatePassportDetails(String passportDetails) {
        if (!passportDetails.matches("^[0-9]{9}$")){
            throw new RuntimeException("Invalid passport details");
        }
    }

    public void validateDate(String startDate, String endDate) {
        if(!(startDate.matches("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$") &&
                endDate.matches("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$") &&
                (ChronoUnit.DAYS.between(LocalDate.parse(startDate), LocalDate.parse(endDate)) > 0))){
            throw new RuntimeException("Incorrect date");
        }
    }

    public void validatePrice(String price){
        if (!price.matches("^\\d+(?:\\.\\d{0,2})?$")){
            throw new RuntimeException("Price is not valid");
        }
    }

    public void validateName(String name){
        if (!name.matches("^[A-Z](\\w)+$")){
            throw new RuntimeException(name+" is incorrect input");
        }
    }
}
