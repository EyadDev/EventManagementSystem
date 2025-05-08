package Core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Attendee extends User {
    private LocalDate birthDate;
    private String address;
    private Gender gender;
    private ArrayList<String> interests;

    public Attendee(String username, String password, LocalDate birthDate, String address, Gender gender) {
        super(username, password, UserType.Attendee);
        this.birthDate = birthDate;
        this.address = address;
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public String getInterest(int index) {
        return interests.get(index);
    }

    // input interests
    public void addInterest(String interest) {
        interests.add(interest);
    }

    //  display attendee info
    public String toString() {
        String stringToPrint = "Username: " + super.getUsername() + "\n" +
                "Date of Birth: " + getDateOfBirth() + "\n" +
                "Address: " + address + "\n" +
                "Core.Room.Gender: " + gender + "\n";


        if (this.interests != null) {
            StringBuilder interestList = new StringBuilder();
            for (int i = 0; i < interests.size(); i++) {
                interestList.append(interests.get(i));
                if (i < interests.size() - 1) {
                    interestList.append(", ");
                }
            }
            stringToPrint += interestList;
        }

        return stringToPrint;
    }
}
