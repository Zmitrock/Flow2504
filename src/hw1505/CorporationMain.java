package hw1505;

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CorporationMain {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
//        Задание 5
//        Напишите информационную систему "Корпорация".
//                Программа должна обеспечивать:
//■■ ввод данных;
//■■ редактирование данных сотрудника корпорации;
//■■ удаление сотрудника корпорации;
//■■ поиск сотрудника корпорации по фамилии;
//■■ вывод информации обо всех сотрудниках, указанного возраста, или фамилия которых начинается на
//        указанную букву.
//        Организуйте возможность сохранения найденной информации в файл.
//        Также весь список сотрудников корпорации сохраняется в файл (при выходе из программы
//        автоматически, в проwессе исполнения программы по команде пользователя).
//        При старте программы происходит загрузка списка сотрудников корпорации из указанного пользователем
//        файла.
        File file1 = new File("Corp.txt");
        if (!file1.exists()) {
            try {
                boolean result = file1.createNewFile();
                System.out.println(result ? "Файл создан" : "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Corp corp = new Corp();
        try (FileInputStream fis1 = new FileInputStream("Corp.txt"); ObjectInputStream objIntStr = new ObjectInputStream(fis1)) {
            corp = (Corp) objIntStr.readObject();
            System.out.print("Считываем из файла Corp.txt записанный объект: ");
            System.out.println(corp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.println("EOFException - что с этим делать??????????");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        boolean flag = true;
        while (flag) {
            System.out.println("1. Ввод данных\n" +
                    "2. редактирование данных сотрудника корпорации \n" +
                    "3. удаление сотрудника корпорации\n" +
                    "4. поиск сотрудника корпорации по фамилии\n" +
                    "5. вывод информации обо всех сотрудниках, указанного возраста\n" +
                    "6. вывод информации обо всех сотрудниках фамилия которых " +
                    "начинается на уазанную букву\n" +
                    "0. Завершить выполнение программы\n");
            switch (inputMenu()) {
                case 1:
                    addEmployee(corp.getEmpls());//добавляем новых сотрудников
                    break;
                case 2:
                    editEmployee(corp.getEmpls());
                    break;
                case 3:
                    removeEmployee(corp.getEmpls());
                    break;
                case 4:
                    searchLastName(corp.getEmpls());
                    break;
                case 5:
                    searchAge(corp.getEmpls());
                    break;
                case 6:
                    searchLetter(corp.getEmpls());
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }

        try (FileOutputStream fos1 = new FileOutputStream("corp.txt"); ObjectOutputStream objOutStr = new ObjectOutputStream(fos1)) {
            objOutStr.writeObject(corp);
            System.out.println("записали в файл corp.txt объект corp класса \"Corp\"");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int inputMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        int answer = -1;
        try {
            answer = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("incorrect input!!! Try again");
        }
        return answer;
    }

    public static void addEmployee(LinkedList linkedList) throws IOException {
        System.out.println("Введите фамилию нового сотрудника");
        String lastname = reader.readLine();
        System.out.println("Введите возраст нового сотрудника (от 18 до 70)");
        String numberString = reader.readLine();
        Integer age = null;
        try {
            age = Integer.parseInt(numberString);
            linkedList.add(new Employee(lastname, age));
        } catch (NumberFormatException ex) {
            try {
                double userNumberDouble = Double.parseDouble(numberString);
                System.out.println("Введено нецелое число. Необходимо ввести целое число");
            } catch (NumberFormatException exception) {
                System.out.println("Введено некорректное значение. Необходимо ввести целое число ");
            }
        }
    }

    public static void editEmployee(LinkedList<Employee> linkedList) throws IOException {
        System.out.println("Введите фамилию сотрудника, данные которого нужно изменить");
        String lastName = reader.readLine();
        AtomicInteger count = new AtomicInteger(0);
        linkedList.stream().forEach(x -> {
            if (x.getLastName().equals(lastName)) {
                count.incrementAndGet();
            }
        });
        System.out.println("count " + count.get());
        if (count.get() == 0) {
            System.out.println("нет такого сотрудника");
        } else {
            boolean flag = true;
            while (flag) {
                System.out.println("1.Изменить фамилию\n" +
                        "2. Изменить возраст \n" +
                        "0. Завершить выполнение программы\n");
                switch (inputMenu()) {
                    case 1:
                        System.out.println("Введите новую фамилию сотрудника ");
                        String newLastName = reader.readLine();
                        linkedList.stream().filter(x -> x.getLastName().equals(lastName)).forEach(x -> x.setLastName(newLastName));
                        break;
                    case 2:
                        System.out.println("Введите новый возраст нового сотрудника (от 18 до 70)");
                        String numberString = reader.readLine();
                        Integer age = null;
                        try {
                            age = Integer.parseInt(numberString);
                            Integer finalAge = age;
                            linkedList.stream().filter(x -> x.getLastName().equals(lastName)).forEach(x -> x.setAge(finalAge));
                        } catch (NumberFormatException ex) {
                            try {
                                double userNumberDouble = Double.parseDouble(numberString);
                                System.out.println("Введено нецелое число. Необходимо ввести целое число");
                            } catch (NumberFormatException exception) {
                                System.out.println("Введено некорректное значение. Необходимо ввести целое число ");
                            }
                        }
                        break;
                    case 0:
                        flag = false;
                        break;
                    default:
                        System.out.println("Некорректный ввод");
                }
            }
        }
    }

    public static void removeEmployee(LinkedList<Employee> linkedList) throws IOException {
        System.out.println("Введите фамилию сотрудника, данные которого нужно удалить");
        String lastName = reader.readLine();
        AtomicInteger count = new AtomicInteger(0);
        linkedList.stream().forEach(x -> {
            if (x.getLastName().equals(lastName)) {
                count.incrementAndGet();
            }
        });
        System.out.println("count " + count.get());
        if (count.get() == 0) {
            System.out.println("нет такого сотрудника");
        } else {
            linkedList.removeIf(x -> x.getLastName().equals(lastName));
        }
    }

    public static void searchLastName(LinkedList<Employee> linkedList) throws IOException {
        System.out.println("Введите фамилию для поиска");
        String lastName = reader.readLine();
        AtomicInteger count = new AtomicInteger(0);
        linkedList.stream().forEach(x -> {
            if (x.getLastName().equalsIgnoreCase(lastName)) {
                count.incrementAndGet();
                System.out.println(x);
            }
        });
        System.out.println("count " + count.get());
        if (count.get() == 0) {
            System.out.println("нет такого сотрудника");
        }
    }

    public static void searchAge(LinkedList<Employee> linkedList) throws IOException {
        System.out.println("Введите искомый возраст сотрудников");
        String numberString = reader.readLine();
        Integer age = null;
        try {
            age = Integer.parseInt(numberString);
            Integer finalAge1 = age;
            linkedList.stream().filter(x -> x.getAge() == finalAge1).forEach(x -> System.out.println(x));
        } catch (NumberFormatException ex) {
            try {
                double userNumberDouble = Double.parseDouble(numberString);
                System.out.println("Введено нецелое число. Необходимо ввести целое число");
            } catch (NumberFormatException exception) {
                System.out.println("Введено некорректное значение. Необходимо ввести целое число ");
            }
        }
    }

    public static void searchLetter(LinkedList<Employee> linkedList) throws IOException {
        System.out.println("Введите первую букву фамилии");
        String letter = reader.readLine();
        char ch = letter.toLowerCase().charAt(0);
        linkedList.stream().filter(x -> x.getLastName().toLowerCase().charAt(0) == ch).forEach(x -> System.out.println(x));
    }

}


