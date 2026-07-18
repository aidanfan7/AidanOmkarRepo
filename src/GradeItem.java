package src;
public class GradeItem {

    private String title;
    private double score;

    public GradeItem(String title, double score) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Assignment title must not be null or blank.");
        }
        if (score < 0.0 || score > 100.0) {
            throw new IllegalArgumentException("Score must be between 0.0 and 100.0 (inclusive).");
        }
        this.title = title.trim();
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return title + ": " + score;
    }
}

