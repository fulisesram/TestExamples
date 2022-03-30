import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public void validateFirstName(){
        if(this.firstName.isBlank())
            throw new RuntimeException("First Name Cannot be null or empty");
    }

    public void validateLastName(){
        if(this.lastName.isBlank())
            throw new RuntimeException("Last Name Cannot be null or empty");
    }

    public void validatePhoneNumber(){
        if(this.phoneNumber.isBlank()){
            throw new RuntimeException("Phone Number Cannot be null or empty");
        }
        if (this.phoneNumber.length() != 10){
                throw new RuntimeException("Phone Number Should be 10 Digits Long");
            }
        /*if (this.phoneNumber.matches("\\d+")){
            throw new RuntimeException("Phone Number Contain Only Digits");
        }
        if (this.phoneNumber.startsWith("0")){
            throw new RuntimeException("Phone Number Should Start with '0'");
        }*/
    }


}
