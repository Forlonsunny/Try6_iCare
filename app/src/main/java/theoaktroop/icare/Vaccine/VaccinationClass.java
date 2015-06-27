package theoaktroop.icare.Vaccine;

import java.io.Serializable;


public class VaccinationClass implements Serializable {
    private static final long serialVersionUID = -7406082437623008112L;
    private String vaccineName;
    private String reason;
    private String vaccineDate;
    private String profileID;
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    private long Id;
    public String getProfileID() {
        return profileID;
    }

    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }
    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(String vaccineDate) {
        this.vaccineDate = vaccineDate;
    }
}
