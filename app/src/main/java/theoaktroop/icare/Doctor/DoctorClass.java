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
    private String appointmentTime;
    private String latitude;
    private String longitude;
    private String profileID;
    private long Id;
    private String doctoRemaindercheck;

    public String getDoctoRemaindercheck() {
        return doctoRemaindercheck;
    }

    public void setDoctoRemaindercheck(String doctoRemaindercheck) {
        this.doctoRemaindercheck = doctoRemaindercheck;
    }

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

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

}
