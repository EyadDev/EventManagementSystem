package Core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Attendee extends User {
    private LocalDate birthDate;
    private String address;
    private Gender gender;
    private String interests;

    public Attendee(String username, String password, LocalDate birthDate, String address, Gender gender, String interests) {
        super(username, password, UserType.Attendee);
        this.birthDate = birthDate;
        this.address = address;
        this.gender = gender;
        this.interests = interests;
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

    public String getInterests() {
        return interests;
    }

    //  display attendee info
    public String toString() {
        String stringToPrint = "Username: " + super.getUsername() + "\n" +
                "Date of Birth: " + getDateOfBirth() + "\n" +
                "Address: " + address + "\n" +
                "Core.Room.Gender: " + gender + "\n" +
                "interests: " + interests;

        return stringToPrint;
    }
}
