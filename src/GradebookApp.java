package src;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class GradebookApp {
    public static void main(String[] args) {
        System.out.println ("--Gradebook Student--");
        System.out.println ("(no students yet)");
        Scanner input = new Scanner(System.in);
        GradebookManager gradebook = new GradebookManager();
        while(true){
            int choice;
            System.out.println ("1. Add Student");
            System.out.println ("2. Add Grade to Student");
            System.out.println ("3. View All Students");
            System.out.println ("4. View Student Details");
            System.out.println ("5. Search Student By ID");
            System.out.println ("6. Load Data From Fiile");
            System.out.println ("7. Save Data to File");
            System.out.println ("8. Exit");
            try{
                choice = input.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter a number from 1-8.");
                continue;
            }
            int id;
            String name;
            String gradetitle;
            double gradescore;
            switch (choice){
                case 1:
                    System.out.println("Enter Student ID: ");
                    id = input.nextInt();
                    System.out.println("Enter Student name: ");
                    name = input.next().trim();
                    gradebook.addStudent(id, name);
                case  2:
                    System.out.println("Enter Student ID: ");
                    id = input.nextInt();
                    System.out.println("Enter Grade Title: ");
                    gradetitle = input.next().trim();
                    System.out.println("Enter Score: ");
                    id = input.nextInt();
                    GradebookStudent g = gradebook.findById(id);

                case 8:
                    break;

            }
        }
        input.close();
    }
}
