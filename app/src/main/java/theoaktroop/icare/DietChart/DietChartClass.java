package theoaktroop.icare.DietChart;


import theoaktroop.icare.ProfileActivity.Profile;


public class DietChartClass extends Profile {

    private String profileID;
    private String mealType;
    private String day;
    private String foodMenu;

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
