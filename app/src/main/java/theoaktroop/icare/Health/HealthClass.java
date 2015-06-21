package theoaktroop.icare.Health;

import java.io.Serializable;

public class HealthClass implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String height;
    private String wight;
    private String bloodGroup;
    private String bloodPressure;
    private String bloodSugar;



    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWight() {
        return wight;
    }

    public void setWight(String wight) {
        this.wight = wight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }
}
