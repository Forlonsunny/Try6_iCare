package theoaktroop.icare.ProfileActivity;

import java.io.Serializable;

/**
 * Created by Mobile App Develop on 20-6-15.
 */
public class Profile implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private  byte[] finalImages;

    public byte[] getFinalImages() {

        return finalImages;
    }

    public void setFinalImages(byte[] finalImages) {
        this.finalImages = finalImages;
    }

    private String profileName;
    private String relation;
    private Long id;
    private String dateOfBirth;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private String age;


    public Profile(String profileName, String relation, String age,byte[] finalImages ){

        this.profileName = profileName;
        this.relation = relation;
        this.age = age;
        this.finalImages=finalImages;

    }

    public Profile(){

    }


    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelation() {
        return relation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
