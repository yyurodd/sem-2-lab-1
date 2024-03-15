package lab1;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
//import java.io.*;

class Applicant {
    String name;
    String gender;
    int age;
    String city;
    static int[] examScores;
    static float averageExamGrade;
    boolean original;
    int[] certGrades;
    float averageCertGrade;
    public Applicant(String name, String gender, int age, String city, int[] examScores, boolean original, int[] certGrades){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.city = city;
        Applicant.examScores = examScores;
        this.original = original;
        this.certGrades = certGrades;
    }
}
class Student {
    String name;
    String gender;
    int groupNumber;
    int listNumber;
    int[] grades;
    float averageGrade;

    public Student(String name, String gender, int groupNumber, int listNumber, int[] grades) {
        this.name = name;
        this.gender = gender;
        this.groupNumber = groupNumber;
        this.listNumber = listNumber;
        this.grades = grades;

    }
}
class ApplicantArray{
    private static final int MAX_APPLICANTS = 100;
    private static final Applicant[] applicants = new Applicant[MAX_APPLICANTS];
    private static int applicantCount = 0;

    public static Applicant createNewApplicantFromInput() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Введите имя абитуриента: ");
        String name = scanner.nextLine();

        String gender;
        boolean validGender = false;
        do {
            System.out.println("Введите пол абитуриента(М или Ж): ");
            gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("М") || gender.equalsIgnoreCase("Ж")) {
                validGender = true;
            } else {
                System.out.println("Неверно введён пол абитуриента. Повторите ввод.");
            }
        } while (!validGender);

        System.out.println("Введите возраст абитуриента: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите город абитуриента: ");
        String city = scanner.nextLine();



        System.out.println("Введите количество сданных предметов ЕГЭ: ");
        int num = scanner.nextInt();
        int[] examScores = new int[num];
        System.out.println("Введите баллы ЕГЭ через пробел: ");
        String scoresInput = scanner.nextLine();
        String[] scoresStrings = scoresInput.split(" ");
        if (scoresStrings.length < num) {
            System.out.println("Ошибка при вводе, повторите ввод снова.");
            return createNewApplicantFromInput();
        }
        for (int i = 0; i < num; i++) {
            examScores[i] = Integer.parseInt(scoresStrings[i]);
        }

        System.out.println("Наличие оригинала аттестата?(1-да/0-нет): ");
        boolean original = (scanner.nextInt() == 1);

        System.out.println("Введите количество предметов в аттестате: ");
        int certNum = scanner.nextInt();
        int[] certGrades = new int[num];
        System.out.println("Введите оценки в аттестате через пробел: ");
        String gradesInput = scanner.nextLine();
        String[] gradesStrings = gradesInput.split(" ");
        if (gradesStrings.length < num) {
            System.out.println("Ошибка при вводе, повторите ввод снова.");
            return createNewApplicantFromInput();
        }
        for (int i = 0; i < num; i++) {
            certGrades[i] = Integer.parseInt(gradesStrings[i]);
        }
        return new Applicant(name, gender, age, city, examScores, original, certGrades);
    }

    public static void averageGrade(){
        float sum = 0;

        for (int grade : Applicant.examScores) {
            sum += grade;
        }
        Applicant.averageExamGrade = sum / !!!!!!!num!!!!!!!!!!!;
    }
    }
}

public class StudentArray {

    private static final int MAX_STUDENTS = 100;
    private static final Student[] students = new Student[MAX_STUDENTS];
    private static int studentCount = 0;


    public static void showAllStudents() {
        for (int i = 0; i < studentCount; i++) {
            if (students[i] != null) {
                System.out.println("Имя: " + students[i].name);
                System.out.println("Пол: " + students[i].gender);
                System.out.println("Номер группы: " + students[i].groupNumber);
                System.out.println("Номер в списке: " + students[i].listNumber);

                System.out.print("Оценки: ");
                for (int grade : students[i].grades) {
                    System.out.print(grade + " ");
                }
                System.out.println();

                System.out.println("-------------------------------");
            }
        }
    }

    public static void showStudentsByGroupNumber(int groupNumber) {
        for (int i = 0; i < MAX_STUDENTS; i++) {
            if (students[i] == null) {
                continue;
            }
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

        String gender;
        boolean validGender = false;
        do {
            System.out.println("Введите пол студента(М или Ж): ");
            gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("М") || gender.equalsIgnoreCase("Ж")) {
                validGender = true;
            } else {
                System.out.println("Неверно введён пол студента. Повторите ввод.");
            }
        } while (!validGender);

        System.out.println("Введите номер группы студента: ");
        int groupNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите номер студента в списке: ");
        int listNumber = Integer.parseInt(scanner.nextLine());



        int[] grades = new int[8];
        System.out.println("""
                Введите оценки за прошедшую сессию через пробел
                1.АиГ, 2.МатАн, 3.Программирование, 4.Физика, 5.Философия, 6.Ин.Яз., 7.Информатика, 8.ОРГ""");
        String gradesInput = scanner.nextLine();
        String[] gradesStrings = gradesInput.split(" ");
        if (gradesStrings.length < 8) {
            System.out.println("Вы ввели меньше 8 оценок, введите данные студента заново.");
            return createNewStudentFromInput();
        }

        for (int i = 0; i < 8; i++) {
            grades[i] = Integer.parseInt(gradesStrings[i]);
        }


        return new Student(name, gender, groupNumber, listNumber, grades);
    }


    public static void updateStudentInformation(Student student, String ID) {
        Scanner scanner = new Scanner(System.in);

        String studentID = String.valueOf(student.groupNumber) + student.listNumber;
        if(!ID.equals(studentID)){
            System.out.println("Студент с указанным ID не найден.");
            return;
        }

        int[] grades;
        System.out.println("""
        Какую информацию вы хотите изменить?"
        "1. Имя студента"
        "2. Номер группы"
        "3. Номер студента в списке"
        "4. Оценки за сессию""");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Введите новое имя студента: ");
                scanner.nextLine();
                student.name = scanner.nextLine();
                break;
            case 2:
                System.out.println("Введите новый номер группы студента: ");
                student.groupNumber = scanner.nextInt();
                break;
            case 3:
                System.out.println("Введите новый номер студента в списке: ");
                student.listNumber = scanner.nextInt();

                break;
            case 4:
                System.out.println("Введите новые оценки за прошедшую сессию через пробел:");
                scanner.nextLine();
                grades = new int[8];
                String gradesInput = scanner.nextLine();
                String[] gradesStrings = gradesInput.split(" ");
                if (gradesStrings.length < 8) {
                    System.out.println("Вы ввели меньше 8 оценок.");
                    updateStudentInformation(student,studentID);
                }

                for (int i = 0; i < 8; i++) {
                    grades[i] = Integer.parseInt(gradesStrings[i]);
                }
                student.grades = grades;
                break;
            default:
                System.out.println("Некорректный выбор. Изменение отменено.");
                break;
        }

    }

    public static void showTopRatedStudents() {
        for (int i = 0; i < MAX_STUDENTS; i++) {
            float sum = 0;
            if (students[i] == null) {
                continue;
            }
            for (int grade : students[i].grades) {
                sum += grade;
            }
            students[i].averageGrade = sum / 8;
        }

        for (int i = 0; i < MAX_STUDENTS - 1; i++){
            if (students[i] == null) {
                continue;
            }
            for (int j = i + 1; j < MAX_STUDENTS; j++){
                if (students[j] == null) {
                    continue;
                }
                if (students[j].averageGrade > students[i].averageGrade){
                    Student tmp = students[i];
                    students[i] = students[j];
                    students[j] = tmp;
                }
            }
        }

        System.out.println("Топ студентов по рейтингу:");
        for (int i = 0; i < MAX_STUDENTS; i++){
            if (students[i] == null) {
                continue;
            }
            System.out.println((i+1) + ". " + students[i].name + " - средний балл: " + students[i].averageGrade);
        }
    }

    public static void showMaleFemaleCnt(){
        int cntMale = 0;
        int cntFemale = 0;
        for (int i = 0; i < MAX_STUDENTS; i++){
            if (students[i] == null) {
                continue;
            }
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
        for (int i = 0; i < MAX_STUDENTS; i++){
            if (students[i] == null) {
                continue;
            }
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

    public static boolean checkForDiffInListNums(Student[] students, Student newStudent, int studentCount) {
        for (int l = 0; l < studentCount; l++) {
            if (students[l] == null) {
                continue;
            }

            if ((students[l].groupNumber == newStudent.groupNumber) && (students[l].listNumber == newStudent.listNumber)) {
                return true;
            }
        }
        return false;
    }




    public static void showExcellentStudents(){
        System.out.println("\nСтуденты, учащиеся только на 5:");
        for (int i = 0; i < MAX_STUDENTS; i++){
            if (students[i] == null) {
                continue;
            }
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
        for (int i = 0; i < MAX_STUDENTS; i++){
            if (students[i] == null) {
                continue;
            }
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
        for (int i = 0; i < MAX_STUDENTS; i++){
            int sum = 0;
            int cnt = 0;
            if (students[i] == null) {
                continue;
            }
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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int number;
        while (true){
            System.out.println("""
                              Меню
                    1 - Создать новую запись о студенте
                    2 - Внести изменения в уже имеющуюся запись
                    3 - Вывести все данные о студентах
                    4 - Вывести информацию обо всех студентах определенной группы
                    5 - Вывести топ самых успешных студентов с наивысшим по рейтингу средним баллом за прошедшую сессию
                    6 - Вывести количество студентов мужского и женского пола
                    7 - Вывести данные о студентах, которые не получают стипендию; учатся только на «хорошо» и «отлично»; учатся только на «отлично»
                    8 - Вывести данные о студентах, имеющих определенный номер в списке\s
                    0 - Выйти из программы
                    """);
            number = scanner.nextInt();
            if (number == 0){
                break;
            }
            switch (number) {
                case 1:
                    if (studentCount >= MAX_STUDENTS) {
                        System.out.println("Достигнуто максимальное количество студентов.");
                        break;
                    }

                    System.out.print("Введите количество студентов: ");
                    int size = scanner.nextInt();

                    // Добавьте проверку, чтобы убедиться, что не превышено максимальное количество студентов
                    if (studentCount + size > MAX_STUDENTS) {
                        System.out.println("Нельзя добавить столько студентов, так как превышено максимальное количество.");
                        break;
                    }



                    for (int i = 0; i < size; i++) {
                        Student newStudent;
                        boolean hasSameNumbers;

                        do {
                            newStudent = createNewStudentFromInput();

                            // Проверяем на совпадение номеров с уже существующими студентами
                            hasSameNumbers = checkForDiffInListNums(students, newStudent, studentCount);

                            if (hasSameNumbers) {
                                System.out.println("Найдено совпадение номеров. Введите данные заново.");
                            }
                        } while (hasSameNumbers);

                        students[studentCount] = newStudent;
                        studentCount++;
                    }


                    System.out.println("Новые студенты успешно добавлены.");
                    break;

                case 2:
                    System.out.print("Введите ID студента, информацию которого нужно изменить(ID - номер группы + номер в списке без пробела, например 337222: ");
                    scanner.nextLine();
                    String studentId = scanner.nextLine();

                    boolean found = false;
                    for (Student student : students) {
                        if (student == null) {
                            continue;
                        }
                        String ID = String.valueOf(student.groupNumber) + student.listNumber;
                        if (studentId.equals(ID)) {
                            updateStudentInformation(student, ID);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Студент с указанным ID не найден.");
                    }

                    break;
                case 3:
                    showAllStudents();
                    System.out.println("\n");
                    break;
                case 4:
                    showStudentsByGroupNumberFromInput();
                    System.out.println("\n");
                    break;
                case 5:
                    showTopRatedStudents();
                    System.out.println("\n");
                    break;
                case 6:
                    showMaleFemaleCnt();
                    System.out.println("\n");
                    break;
                case 7:
                    showExcellentStudents();
                    showHonorStudents();
                    showC_Students();
                    System.out.println("\n");
                    break;
                case 8:
                    showKNumberedStudents();
                    System.out.println("\n");
                    break;
            }
        }
    }
}
