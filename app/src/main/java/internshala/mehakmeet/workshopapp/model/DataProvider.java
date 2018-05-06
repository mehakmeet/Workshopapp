package internshala.mehakmeet.workshopapp.model;

/**
 * Created by MEHAKMEET on 07-05-2018.
 */

public class DataProvider {

    private String name,desc,date,time;

    public DataProvider(String name, String desc, String date, String time) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
