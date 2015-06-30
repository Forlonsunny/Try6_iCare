package theoaktroop.icare.DietChart;


import java.io.Serializable;


public class DietChartClass implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;
    private long Id;

    private String mealType;
    private String day;
    private String foodMenu;
    private String dietTime;
    private String profileID;
    private String checkRe;

    public String getCheckRe() {
        return checkRe;
    }

    public void setCheckRe(String checkRe) {
        this.checkRe = checkRe;
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

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(String foodMenu) {
        this.foodMenu = foodMenu;
    }

    public String getDietTime() {
        return dietTime;
    }

    public void setDietTime(String dietTime) {
        this.dietTime = dietTime;
    }

    /**
     * Created by Sunny_PC on 6/23/2015.
     */

}
