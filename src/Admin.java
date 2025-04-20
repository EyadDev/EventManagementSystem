import java.util.Date;

public class Admin extends Person{
    private Date birthDate;
    private String role;
    private String workHours;

    Admin(String username, String password, Date birthDate, String role, String workHours){
        super(username, password);
        this.birthDate = birthDate;
        this.role = role;
        this.workHours = workHours;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getRole() {
        return role;
    }

    public String getWorkHours() {
        return workHours;
    }
}
