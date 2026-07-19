package src;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class GradebookApp {
    public static void main(String[] args) {
        System.out.println ("--Gradebook Student--");
        System.out.println ("(no students yet)");
        Scanner input = new Scanner(System.in);
        GradebookManager gradebook = new GradebookManager();
        int choice = 0;
        while(true){
                try{
                    System.out.println ("Choices: ");
                    System.out.println ("1. Add Student");
                    System.out.println ("2. Add Grade to Student");
                    System.out.println ("3. View All Students");
                    System.out.println ("4. View Student Details");
                    System.out.println ("5. Search Student By ID");
                    System.out.println ("6. Load Data From Fiile");
                    System.out.println ("7. Save Data to File");
                    System.out.println ("8. Exit");
                    System.out.println ("Enter Choice: ");
                    choice = input.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("Invalid input. Please enter a number from 1-8.");
                    input.nextLine();
                    continue;
                }
            if (choice < 0 || choice > 8){
                System.out.println("Invalid input. Please enter a number from 1-8.");
                continue;
            }
            if (choice == 8){
                System.out.println("Goodbye");
                break;
            }
            int id;
            String name;
            String gradetitle;
            double gradescore;
            try{
            switch (choice){
                case 1:
                    System.out.println("Enter Student ID: ");
                    id = input.nextInt();
                    if (id <= 0){
                        System.out.println("Student id must be greater than 0");
                        continue;
                    }
                    System.out.println("Enter Student name: ");
                    name = input.next().trim();
                    if(gradebook.addStudent(id, name)){
                        System.out.println("Student addes successfully");
                    }
                    else{
                        System.out.println("A Student with id " + id + " already exists. Student was not added.");
                    }
                    continue;
                case  2:
                    if (gradebook.getSize() <= 0){
                        System.out.println("No Students yet, cannot add a grade");
                        continue;
                    }
                    System.out.println("Enter Student ID: ");
                    id = input.nextInt();
                    if (gradebook.findById(id) == null){
                        System.out.println("No Student found with id " + id + ". Grade was not added.");
                        continue;
                    }
                    System.out.println("Enter Grade Title: ");
                    gradetitle = input.next().trim();
                    System.out.println("Enter Score: ");
                    gradescore = input.nextDouble();
                    if (gradescore < 0.0 || gradescore > 100.0){
                        System.out.println("Invalid Score. Score must be between 0.0 and 100.0");
                        continue;
                    }
                    else{
                        gradebook.findById(id).addGrade(new GradeItem(gradetitle, gradescore));
                    }
                    continue;
                case 3:
                    if (gradebook.getSize() <= 0){
                        System.out.println("No Students yet, cannot print them");
                        continue;
                    }
                    gradebook.printAll();
                case 4:
                    System.out.println("Enter Student ID: ");
                    id = input.nextInt();
                    if (gradebook.findById(id) == null){
                        System.out.println("No student found with id " + id);
                        continue;
                    }
                    else{
                        System.out.println("Student Details: ");
                        System.out.println("ID: " + gradebook.findById(id).getId());
                        System.out.println("Name: " + gradebook.findById(id).getName());
                        System.out.println();
                        System.out.println("Grades: ");
                        gradebook.findById(id).printGrades();
                    }
                    continue;
                case 5:
                    System.out.println("Enter Student ID: ");
                    id = input.nextInt();
                    if (gradebook.findById(id) == null){
                        System.out.println("No student found with id " + id);
                        continue;
                    }
                    else{
                        System.out.println("Found: ");
                        System.out.println(gradebook.findById(id).getId()  + " - " + gradebook.findById(id).getName());
                        System.out.println ("Average: " + gradebook.findById(id).averageGrade());
                    }
                    continue;
                case 6:
                    gradebook.loadData("data/sampledata.txt");
                    continue;
                case 7:
                    if (gradebook.getSize() <= 0){
                        System.out.println("No Students yet, cannot load them");
                        continue;
                    }
                    try{
                        gradebook.saveData("data/sampledata.txt");
                        System.out.println("Gradebook saved Secessfully");
                        continue;
                    }catch(IOException e){
                        System.out.println("Gradebook could not save;");
                        continue;
                    }
                case 8:
                    System.out.println("Goodbye");
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("Invalid input for the field, retry action");
            continue;
        }
        }
        input.close();
    }
}
