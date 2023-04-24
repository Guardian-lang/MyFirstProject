package by.Ahmed.entity;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Author {

    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDateTime birthDate;
    private String occupation;
    private String jobTitle;
    private CheckStatus checkStatus;
    private String about;
    private Long authorizationId;

    public Author() {
    }

    public Author(Long id, String firstName, String lastName, Gender gender, LocalDateTime date, String occupation, String jobTitle, CheckStatus checkStatus, String about, Long authorizationId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = date;
        this.occupation = occupation;
        this.jobTitle = jobTitle;
        this.checkStatus = checkStatus;
        this.about = about;
        this.authorizationId = authorizationId;
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

    public LocalDateTime getBirthDate() {return birthDate;}

    public void setBirthDate(LocalDateTime date) {
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

    public Long getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(Long authorizationId) {
        this.authorizationId = authorizationId;
    }

    @Override
    public String toString() {
        return "author{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birth_date=" + birthDate +
                ", occupation='" + occupation + '\'' +
                ", job_title='" + jobTitle + '\'' +
                ", check_status=" + checkStatus +
                ", about=" + about +
                ", authorization_id=" + authorizationId +
                '}';
    }
}