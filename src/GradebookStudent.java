package src;
import java.util.ArrayList;
public class GradebookStudent {
    private int id;
    private String name;
    private ArrayList<GradeItem> grades;

    public GradebookStudent(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student id must be > 0.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name must not be null or blank.");
        }
        this.id = id;
        this.name = name.trim();
        this.grades = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    //for this getter i made a copy so user cant directly access the arrayList
    public ArrayList<GradeItem> getGrades() {
        return new ArrayList<>(grades);
    }

    public void addGrade(GradeItem item) {
        if (item == null) {
            throw new IllegalArgumentException("GradeItem cannot be null.");
        }
        grades.add(item);
    }

    public double averageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (GradeItem g : grades) {
            sum += g.getScore();
        }
        return sum / grades.size();
    }

    //have not overriden toString yet(need to do)
    @Override
    public String toString(){
        if (averageGrade() == 0.0){
            return getId() + " - " + getName() + " - " + averageGrade() + " (no grades yet)";
        }
        return getId() + " - " + getName() + " - " + averageGrade(); 
    }
    public void printGrades(){
        for (GradeItem g : grades) {
            System.out.println(g.toString());
        }
    }

    
}
