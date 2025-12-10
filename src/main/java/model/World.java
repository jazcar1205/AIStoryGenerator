package model;

public class World {
    private String setting;
    private String timePeriod;

    public World() {
        this.setting = "Any";
        this.timePeriod = "Any";
    }

    public World(String setting, String timePeriod) {
        this.setting = setting;
        this.timePeriod = timePeriod;
    }

    public String getSetting() { return setting; }
    public void setSetting(String setting) { this.setting = setting; }

    public String getTimePeriod() { return timePeriod; }
    public void setTimePeriod(String timePeriod) { this.timePeriod = timePeriod; }

}
