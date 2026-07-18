package src;
import java.util.ArrayList;
public class GradebookManager {
    private ArrayList<GradebookStudent> students;
    
    public GradebookManager(){
        students = new ArrayList<>();
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
}
