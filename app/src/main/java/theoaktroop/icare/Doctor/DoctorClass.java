package theoaktroop.icare.Doctor;

import java.io.Serializable;



/**
 * Created by Mobile App Develop on 20-6-15.
 */
public class DoctorClass
        implements Serializable {
    private static final long serialVersionUID = -7426082437623008161L;

    private String doctorName;
    private String doctorAddress;
    private String doctorPhone;
    private String doctorType;
    private String appointmentDate;
    private String latitude;
    private String longitude;
    private String profileID;
    private long Id;
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
    public String getProfileID() {
        return profileID;
    }

    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
