
import java.io.Serializable;

public class Student implements Serializable {
    private String fio;
    private String group;
    private double avg;

    public Student(String fio, String group, double avg) {
        this.fio = fio;
        this.group = group;
        this.avg = avg;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "fio: " + fio + ", group: " + group + ", avg: " + avg;
    }
}


