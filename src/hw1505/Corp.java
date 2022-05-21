package hw1505;

import java.io.Serializable;
import java.util.LinkedList;

public class Corp implements Serializable {
private LinkedList<Employee> empls;
    public Corp() {
        this.empls = new LinkedList<>();
    }

    public LinkedList<Employee> getEmpls() {
        return empls;
    }

    public void setEmpls(LinkedList<Employee> empls) {
        this.empls = empls;
    }

    @Override
    public String toString() {
        return "Corp{" +
                "empls=" + empls +
                '}';
    }
}
