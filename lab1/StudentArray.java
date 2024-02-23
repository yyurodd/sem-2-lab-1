package lab1;

import java.util.Arrays;
import java.util.Scanner;

class Student {
    String name;
    String gender;
    int groupNumber;
    int listNumber;
    int[] grades = new int[8];
    float averageGrade;
    public Student(String name, String gender, int groupNumber, int listNumber, int[] grades) {
        this.name = name;
        this.gender = gender;
        this.groupNumber = groupNumber;
        this.listNumber = listNumber;
        this.grades = grades;

    }
}

// Класс для работы с массивом студентов
public class StudentArray {
    private static Student[] students = new Student[100];
    private static int size = 0;

    public static void showAllStudents() {
        for (int i = 0; i < size; i++) {
            System.out.println("Student " + (i+1) + ": " + students[i].name + ", Gender: " + students[i].gender + ", Group Number: " + students[i].groupNumber + ", List Number: " + students[i].listNumber
            + ", Grades: " + Arrays.toString(students[i].grades));
        }
    }

    public static void showStudentsByGroupNumber(int groupNumber) {
        for (int i = 0; i < size; i++) {
            if (students[i].groupNumber == groupNumber) {
                System.out.println("Student " + (i+1) + ": " + students[i].name);
            }
        }
    }

    public static void showStudentsByGroupNumberFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter group number to display students: ");
        int groupNumber = scanner.nextInt();
        scanner.nextLine();

        showStudentsByGroupNumber(groupNumber);
    }

    public static Student createNewStudentFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student's name: ");
        String name = scanner.nextLine();

        System.out.println("Enter student's gender: ");
        String gender = scanner.nextLine();

        System.out.println("Enter student's group number: ");
        int groupNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter student's list number: ");
        int listNumber = Integer.parseInt(scanner.nextLine());

        int[] grades = new int[8];
        System.out.println("Enter student's grades for 3 exams and 5 differentiated tests:");
        String gradesInput = scanner.nextLine();
        String[] gradesStrings = gradesInput.split(" ");
        if (gradesStrings.length < 8) {
            System.out.println("Please enter grades for all exams and tests.");
            return createNewStudentFromInput();
        }

        for (int i = 0; i < 8; i++) {
            grades[i] = Integer.parseInt(gradesStrings[i]);
        }

        return new Student(name, gender, groupNumber, listNumber, grades);
    }

    public static void showTopRatedStudents() {
        for (int i = 0; i < size; i++) {
            float sum = 0;
            for (int grade : students[i].grades) {
                sum += grade;
            }
            float averageGrade = sum / 8;
            students[i].averageGrade = averageGrade;
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

        int topNumber = Math.min(5, size);
        System.out.println("Top rated students:");
        for (int i = 0; i < topNumber; i++){
            System.out.println((i+1) + ". " + students[i].name + " - average grade: " + students[i].averageGrade);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of students:");
        int number = scanner.nextInt();
        size = number;

        for (int i = 0; i < size; i++){
            students[i] = createNewStudentFromInput();
        }



        System.out.println("All students:");
        showAllStudents();

        showTopRatedStudents();
    }
}