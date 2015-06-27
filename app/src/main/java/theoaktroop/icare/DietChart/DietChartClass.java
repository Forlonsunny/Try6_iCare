package theoaktroop.icare.DietChart;


import java.io.Serializable;


public class DietChartClass implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    private long Id;

    private String mealType;
    private String day;
    private String foodMenu;
    private String profileID;
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



    /**
     * Created by Sunny_PC on 6/23/2015.
     */

}
