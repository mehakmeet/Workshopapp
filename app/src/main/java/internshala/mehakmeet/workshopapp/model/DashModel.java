package internshala.mehakmeet.workshopapp.model;

/**
 * Created by MEHAKMEET on 07-05-2018.
 */

public class DashModel {
    private String name,desc,date,time;
    private int pos;

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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public DashModel(String name, String desc, String date, String time, int pos) {

        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.pos = pos;
    }
}
