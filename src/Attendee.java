import java.util.ArrayList;
import java.util.Date;

public class Attendee extends User implements Comparable<Attendee> {
    private Date birthDate;
    private String address;
    private Gender gender;
    private ArrayList<String> interests;
    private Wallet wallet;

    public Attendee(String username, String password, Date birthDate, String address, Gender gender) {
        super(username, password);
        this.birthDate = birthDate;
        this.address = address;
        this.gender = gender;
        wallet = new Wallet(this);
    }

    public Date getDateOfBirth() {
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
                "Gender: " + gender + "\n";


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

    @Override
    public int compareTo(Attendee o) {
        return this.getUsername().compareTo(o.getUsername());
    }

    public Wallet getWallet() {
        return wallet;
    }
}
