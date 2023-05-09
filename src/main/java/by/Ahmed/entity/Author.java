package by.Ahmed.entity;

import lombok.Builder;

import java.sql.Date;
import java.time.LocalDateTime;

@Builder
public class Author {

    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthDate;
    private String occupation;
    private String jobTitle;
    private CheckStatus checkStatus;
    private String about;
    private String email;
    private String password;

    public Author() {
    }

    public Author(Long id, String firstName, String lastName, Gender gender, Date date, String occupation, String jobTitle, CheckStatus checkStatus, String about, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = date;
        this.occupation = occupation;
        this.jobTitle = jobTitle;
        this.checkStatus = checkStatus;
        this.about = about;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {return birthDate;}

    public void setBirthDate(Date date) {
        this.birthDate = birthDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", occupation='" + occupation + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", checkStatus=" + checkStatus +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}