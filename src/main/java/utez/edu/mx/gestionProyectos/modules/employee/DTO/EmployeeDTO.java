package utez.edu.mx.gestionProyectos.modules.employee.DTO;

//DTO con nombre completo, correo y estatus

public class EmployeeDTO {
    private long id_Employee;
    private String name;
    private String surname;
    private String lastname;
    private String email;
    private boolean status;
    private String oldPassword;
    private String username;
    private String newPassword;



    public EmployeeDTO() {
    }

    public EmployeeDTO(long id_Employee, String name, String surname, String lastname, String email, boolean status) {
        this.id_Employee = id_Employee;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.status = status;
    }

    public EmployeeDTO(String name, String surname, String lastname, String email, boolean status) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.status = status;
    }

    public EmployeeDTO(String name, String surname, String lastname) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
    }

    public EmployeeDTO(long id_Employee, String name, String surname, String lastname, String email, String username) {
        this.id_Employee = id_Employee;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
    }

    public EmployeeDTO(long id_Employee, String oldPassword, String newPassword) {
        this.id_Employee = id_Employee;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public long getId_Employee() {
        return id_Employee;
    }

    public void setId_Employee(long id_Employee) {
        this.id_Employee = id_Employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id_Employee=" + id_Employee +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
