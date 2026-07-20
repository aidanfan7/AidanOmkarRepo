package src;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class GradebookManager {
    private ArrayList<GradebookStudent> students;
    
    public GradebookManager(){
        students = new ArrayList<>();
    }

    public int getSize(){
        return students.size();
    }

    public GradebookStudent findById(int id) {
        for (GradebookStudent s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean addStudent(int id, String name) {
        if (findById(id) != null) {
            return false; // reject duplicate id
        }
        students.add(new GradebookStudent(id, name));
        return true;
    }

    //this method returns an ArrayList with all results that have that fragment in their name

    public ArrayList<GradebookStudent> findByNameContains(String fragment) {
        ArrayList<GradebookStudent> results = new ArrayList<>();
        if (fragment == null) {
            return results;
        }
        String frag = fragment.toLowerCase();
        for (GradebookStudent s : students) {
            if (s.getName().toLowerCase().contains(frag)) {
                results.add(s);
            }
        }
        return results;
    }

    public void printAll(){
        System.out.println("All Students: ");
        for (GradebookStudent student : students) {
            System.out.println(student.toString());
        }
    }
    // option 2 adds a grade to a student in the arraylist
    public void addGradeToStudent(int studentid, String gradetitle, double grade){
        if(findById(studentid) == null){
            System.out.println("No student found with ID " + studentid + ". Grade was not added.");
            return;
        }
        GradeItem newGrade = new GradeItem(gradetitle, grade);
        findById(studentid).addGrade(newGrade);
        System.out.println("Grade added succesfully");
    }
    public void loadData(String path){
        int studentsloaded = 0;
        int gradesloaded = 0;
        int wronginputs = 0;
        Scanner input;
        try{
            input = new Scanner (new File(path));
        }catch(FileNotFoundException e){
            System.out.println("Could not find File: " + path);
            System.out.println("Gradebook was not changed");
            return;
        }
        while(input.hasNextLine()){
            String line = input.nextLine().trim();
            if(line.isEmpty() ){
                continue;
            }
            if(line.toUpperCase().startsWith("STUDENT")){
                try{
                    String[] parts = line.split(",");
                    String type = parts[0].trim();
                    int id = Integer.parseInt(parts[1].trim());
                    String name = parts[2].trim();
                    addStudent(id, name);
                    studentsloaded++;
                }catch(Exception e){
                    System.out.println("not a valid input line: " + line);
                    wronginputs++;
                }
            }
            else if(line.toUpperCase().startsWith("GRADE")){
                try{
                    String[] parts = line.split(",");
                    String type = parts[0].trim();
                    int id = Integer.parseInt(parts[1].trim());
                    String name = parts[2].trim();
                    double score = Double.parseDouble(parts[3].trim());
                    GradebookStudent student = findById(id);
                    if(student == null){
                        System.out.println("not a valid input line: " + line);
                        wronginputs++;
                        continue;
                    }
                    student.addGrade(new GradeItem(name, score));
                    gradesloaded++;
                }catch(Exception e){
                    System.out.println("not a valid input line: " + line);
                    wronginputs++;
                }
            }
            else{
                System.out.println("not a valid input line: " + line);
                wronginputs++;
            }
        }
        input.close();
        System.out.println("Data Loaded Succesfully");
        System.out.println("Students loaded: " + studentsloaded);
        System.out.println("Grades loaded: " + gradesloaded);
        System.out.println("Wrong inputs: " + wronginputs);
    }
    public void saveData(String path) throws IOException{
        PrintWriter out = new PrintWriter(path);
        for (GradebookStudent student : students) {
            out.println("STUDENT," + student.getId() + "," + student.getName());
            for (GradeItem g : student.getGrades()) {
                out.println("GRADE," + student.getId() + "," + g.getTitle() + "," + g.getScore());
            }
        }
        out.close();
    }
}
