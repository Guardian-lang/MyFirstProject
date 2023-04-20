package by.Ahmed.entity;

public class Author {

    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private int age;
    private String occupation;
    private String jobTitle;
    private CheckStatus checkStatus;
    private String about;

    public Author() {
    }

    public Author(Long id, String firstName, String lastName, Gender gender, int age, String occupation, String jobTitle, CheckStatus checkStatus, String about) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.jobTitle = jobTitle;
        this.checkStatus = checkStatus;
        this.about = about;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "author{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", job_title='" + jobTitle + '\'' +
                ", check_status=" + checkStatus +
                ", about=" + about +
                '}';
    }
}