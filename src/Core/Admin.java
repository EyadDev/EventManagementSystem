package Core;

import java.time.LocalDate;
import java.util.Date;

public class Admin extends User {
    private LocalDate birthDate;
    private String role;
    private String workHours;

    Admin(String username, String password, LocalDate birthDate, String role, String workHours){
        super(username, password, UserType.Admin);
        this.birthDate = birthDate;
        this.role = role;
        this.workHours = workHours;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getRole() {
        return role;
    }

    public String getWorkHours() {
        return workHours;
    }
}
