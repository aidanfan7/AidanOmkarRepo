package src;
import java.util.ArrayList;
public class GradebookStudent {
    private int id;
    private String name;
    ArrayList<GradeItem> grades;

    public GradebookStudent(int id, String name){
        this.id = id;
        this.name = name;
        grades = new ArrayList<>();
    }
}
