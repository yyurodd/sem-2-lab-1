package lab1;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.*;

class Student {
    String name;
    String gender;
    int groupNumber;
    int listNumber;
    int[] grades;
    float averageGrade;
    String studentID;
    public Student(String name, String gender, int groupNumber, int listNumber, int[] grades, String studentID) {
        this.name = name;
        this.gender = gender;
        this.groupNumber = groupNumber;
        this.listNumber = listNumber;
        this.grades = grades;
        this.studentID = studentID;
    }
}

// Класс для работы с массивом студентов
public class StudentArray {
    private static Student[] students = new Student[100];
    private static int size = 0;

    public static void showAllStudents() {
        for (int i = 0; i < size; i++) {
            System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер группы: " + students[i].groupNumber + ", Номер в списке: " + students[i].listNumber
                    + ", Оценки за сессию: " + Arrays.toString(students[i].grades) + "ID = " + students[i].studentID);
        }
    }

    public static void showStudentsByGroupNumber(int groupNumber) {
        for (int i = 0; i < size; i++) {
            if (students[i].groupNumber == groupNumber) {
                System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер в списке: " + students[i].listNumber
                        + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
            }
        }
    }

    public static void showStudentsByGroupNumberFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер группы : ");
        int groupNumber = scanner.nextInt();
        scanner.nextLine();

        showStudentsByGroupNumber(groupNumber);
    }

    public static Student createNewStudentFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя студента: ");
        String name = scanner.nextLine();

        System.out.println("Введите пол студента(М или Ж): ");
        String gender = scanner.nextLine();
        if (!(Objects.equals(gender, "М") | Objects.equals(gender, "м") | Objects.equals(gender, "Ж") | Objects.equals(gender, "ж"))){
            System.out.println("Неверно введён пол студента. Введите данные студента заново.");
            createNewStudentFromInput();
        }

        System.out.println("Введите номер группы студента: ");
        int groupNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите номер студента в списке: ");
        int listNumber = Integer.parseInt(scanner.nextLine());


        int[] grades = new int[8];
        System.out.println("Введите оценки за прошедшую сессию через пробел, 3 экзамена и 5 дифф. зачётов:");
        String gradesInput = scanner.nextLine();
        String[] gradesStrings = gradesInput.split(" ");
        if (gradesStrings.length < 8) {
            System.out.println("Вы ввели меньше 8 оценок, введите данные студента заново.");
            return createNewStudentFromInput();
        }

        for (int i = 0; i < 8; i++) {
            grades[i] = Integer.parseInt(gradesStrings[i]);
        }

        String studentID = String.valueOf(groupNumber + listNumber);

        return new Student(name, gender, groupNumber, listNumber, grades, studentID);
    }

    public static void showTopRatedStudents() {
        for (int i = 0; i < size; i++) {
            float sum = 0;
            for (int grade : students[i].grades) {
                sum += grade;
            }
            students[i].averageGrade = sum / 8;
        }

        for (int i = 0; i < size - 1; i++){
            for (int j = i + 1; j < size; j++){
                if (students[j].averageGrade > students[i].averageGrade){
                    Student tmp = students[i];
                    students[i] = students[j];
                    students[j] = tmp;
                }
            }
        }

        System.out.println("Топ студентов по рейтингу:");
        for (int i = 0; i < size; i++){
            System.out.println((i+1) + ". " + students[i].name + " - средний балл: " + students[i].averageGrade);
        }
    }

    public static void showMaleFemaleCnt(){
        int cntMale = 0;
        int cntFemale = 0;
        for (int i = 0; i < size; i++){
            if (Objects.equals(students[i].gender, "М") || !Objects.equals(students[i].gender, "м")){
                cntMale+=1;
            }
            else if (Objects.equals(students[i].gender, "Ж") || Objects.equals(students[i].gender, "ж")){
                cntFemale+=1;
            }

        }
        System.out.println("Количество студентов мужского пола: " + cntMale +"\nКоличество студентов женского пола: " + cntFemale);
    }

    public static void showKNumberedStudents(){
        System.out.println("Введите номер студента в списке: ");
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        int falses = 0;
        int success = 0;
        for (int i = 0; i < size; i++){
            if (students[i].listNumber == k){
                System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер группы: " + students[i].groupNumber + ", Номер в списке: " + students[i].listNumber
                        + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
                success+=1;
            }
            else{
                falses+=1;
            }
        }

        if (falses > 0 && success == 0){
            System.out.println("Нет студентов с таким номером.");
        }
    }

    public static boolean checkForDiffInListNums(){
        for (int l = 0; l < size - 1; l++){
            for (int j = l+1; j < size; j++){
                if ((students[l].groupNumber == students[j].groupNumber) && (students[l].listNumber == students[j].listNumber)){
                    System.out.println("Совпадают ID некоторых студентов, введите данные заново:");
                    return true;
                }
            }
        }
        return false;
    }

    public static void showExcellentStudents(){
        System.out.println("\nСтуденты, учащиеся только на 5:");
        for (int i = 0; i < size; i++){
            int sum = 0;
            for (int grade : students[i].grades){
                sum+=grade;
            }
            if (sum/8 == 5){
                System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер группы: " + students[i].groupNumber + ", Номер в списке: " + students[i].listNumber
                        + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
            }
        }
    }

    public static void showC_Students(){
        System.out.println("\nСтуденты, не получающие стипендию:");
        for (int i = 0; i < size; i++){
            int sum = 0;
            for (int grade : students[i].grades){
                if (grade == 3 || grade == 2){
                    System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер группы: " + students[i].groupNumber + ", Номер в списке: " + students[i].listNumber
                            + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
                    break;
                }
            }

        }
    }

    public static void showHonorStudents(){
        System.out.println("\nСтуденты, учащиеся на 4 и 5:");
        for (int i = 0; i < size; i++){
            int sum = 0;
            int cnt = 0;
            for (int grade : students[i].grades){
                if (grade == 4 | grade == 5){
                    sum+=grade;
                    cnt+=1;
                }
            }
            if (sum/8 != 5 && cnt == 8){
                System.out.println("Студент " + (i+1) + ": " + students[i].name + ", Пол: " + students[i].gender + ", Номер группы: " + students[i].groupNumber + ", Номер в списке: " + students[i].listNumber
                        + ", Оценки за сессию: " + Arrays.toString(students[i].grades));
            }

        }
    }





    public static void clearScreen(){
        System.out.println("\n\n");
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true){
            System.out.println( "          Меню\n" +
                    "1 - Создать новую запись о студенте\n" +
                    "2 - Внести изменения в уже имеющуюся запись\n" +
                    "3 - Вывести все данные о студентах\n" +
                    "4 - Вывести информацию обо всех студентах определенной группы\n" +
                    "5 - Вывести топ самых успешных студентов с наивысшим по рейтингу средним баллом за прошедшую сессию\n" +
                    "6 - Вывести количество студентов мужского и женского пола\n" +
                    "7 - Вывести данные о студентах, которые не получают стипендию; учатся только на «хорошо» и «отлично»; учатся только на «отлично»\n" +
                    "8 - Вывести данные о студентах, имеющих определенный номер в списке \n" +
                    "0 - Выйти из программы\n");
            number = scanner.nextInt();
            if (number == 0){
                break;
            }
            switch (number) {
                case 1:
                    System.out.println("Введите количество студентов:");
                    size = scanner.nextInt();

                    for (int i = 0; i < size; i++) {
                        students[i] = createNewStudentFromInput();
                    }
                    if (checkForDiffInListNums()){
                        //КАК-ТО УДАЛИТЬ ПРЕДЫДУЩИЕ ЗАПИСИ КОГДА СДЕЛАЮ ФАЙЛЫ
                        for (int i = 0; i < size; i++) {
                            students[i] = createNewStudentFromInput();
                        }
                    }

                    clearScreen();
                    break;
                case 2:
                    break;
                case 3:
                    showAllStudents();
                    clearScreen();
                    break;
                case 4:
                    showStudentsByGroupNumberFromInput();
                    clearScreen();
                    break;
                case 5:
                    showTopRatedStudents();
                    clearScreen();
                    break;
                case 6:
                    showMaleFemaleCnt();
                    clearScreen();
                    break;
                case 7:
                    showExcellentStudents();
                    showHonorStudents();
                    showC_Students();
                    clearScreen();
                    break;
                case 8:
                    showKNumberedStudents();
                    clearScreen();
                    break;
            }
        }



    }

}
