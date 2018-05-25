package service;

import DomenObjects.Info;
import DomenObjects.Student;

import java.io.*;
import java.util.*;

public class ScriptReaderService {
    private Student student = null;


    private static final String INSERT_SCRIPT_FILE_STUDENT = "D:\\Java\\Project\\adress\\src\\main\\resources\\insertStudent.sql";
    public static final String INSERT_SCRIPT_FILE_INFO = "D:\\Java\\Project\\adress\\src\\main\\resources\\insertInfo.sql";
    public static final String INSERT_SCRIPT_INFO = "INSERT INTO info_student(city, street, house) VALUES (";
    public static final String INSERT_SCRIPT_STUDENT = "INSERT INTO student (name, ser_name, phone, email) VALUES (";


    public void printTheSameSernameStudent() {
        final ArrayList<String> listSername = new ArrayList<>();
        for (Student st : readStudentTableAndGetStudentList()) {
            listSername.add(st.getSerName());
        }
        final Set<String> setSername = new HashSet<>();
        for (String st : listSername) {
            if (setSername.add(st) == false) {
                for (Student student : readStudentTableAndGetStudentList()) {
                    if (student.getSerName().equals(st)) {
                        System.out.println(student.getName() + " " + student.getSerName() + " " + student.getPhone() + " " + student.getEmail());
                    }
                }
            }
        }
    }

    public void insertToTableInfoScript() {
        studentCount();
        ArrayList<Info> infoList = new ArrayList<>();
        for (int i = 0; i < studentCount(); i++) {
            infoList.add(new Info("city" + i + 1, "street" + i + 1, (i +"/"+ 1)));
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INSERT_SCRIPT_FILE_INFO, true))) {
            for (Info info : infoList) {
                bw.write(INSERT_SCRIPT_INFO + "'" + info.getCity() + "', '" + info.getStreet() + "', '" + info.getHouse() + "');");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int studentCount() {
        int studentCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(INSERT_SCRIPT_FILE_STUDENT))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                studentCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentCount;
    }

    public void insertToTableStudentScript30students() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INSERT_SCRIPT_FILE_STUDENT, true))) {
            student = new Student("Vasya", "Pupkin", "+380789820990", "level.com");
            for (int i = 0; i < 30; i++) {
                bw.write(INSERT_SCRIPT_STUDENT + "'" + student.getName() + i + "', '" + student.getSerName() + i + "', '" + student.getPhone() + i + "', '" + student.getEmail() + i + "');");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Student> readStudentTableAndGetStudentList() {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        String[] studFieldMass = null;
        try (BufferedReader br = new BufferedReader(new FileReader(INSERT_SCRIPT_FILE_STUDENT))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String studentDataStr = sCurrentLine.substring(sCurrentLine.indexOf("VALUES") + 8, sCurrentLine.length() - 2).replace("'", "").trim();
                studFieldMass = studentDataStr.split(",");
                student = new Student(studFieldMass[0], studFieldMass[1], studFieldMass[2], studFieldMass[3]);
                studentArrayList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentArrayList;
    }

    public void printStudentWithEmail(String email) {
        for (Student student : readStudentTableAndGetStudentList()) {
            if (student.getEmail().contains(email)) {
                System.out.println(student.getName() + " " + student.getSerName() + " " + student.getEmail());
            }
        }
    }

    public void printStudents() {
        readStudentTableAndGetStudentList().forEach(student -> System.out.println(student.getName() + " " + student.getSerName() + " " + student.getPhone() + " " + student.getEmail()));
    }


}

