package hw1505;

import java.io.Serializable;

public class Employee implements Serializable {
    private String lastName;
    private int age;

    public Employee(String lastName,  int age) {
        this.lastName = lastName;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
