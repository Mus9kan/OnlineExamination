package OasisInfobyte;
import java.util.*;

// User class to store user profile information
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}

// Question class to represent MCQs
class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}

// Exam class to manage the exam process
class Exam {
    private List<Question> questions;
    private int timerInSeconds;

    public Exam(List<Question> questions, int timerInSeconds) {
        this.questions = questions;
        this.timerInSeconds = timerInSeconds;
    }

    public void startExam() {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int seconds = timerInSeconds;

            @Override
            public void run() {
                System.out.println("Time left: " + seconds + " seconds");
                seconds--;
                if (seconds < 0) {
                    timer.cancel();
                    submitExam();
                }
            }
        }, 0, 1000);

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.getQuestionText());
            String[] options = q.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println("   " + (char)('A' + j) + ". " + options[j]);
            }
            System.out.print("Your answer: ");
            char answer = scanner.next().charAt(0);
            int selectedOption = answer - 'A';
            if (selectedOption >= 0 && selectedOption < options.length) {
                if (q.isCorrect(selectedOption)) {
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect.");
                }
            } else {
                System.out.println("Invalid option selected.");
            }
        }

        scanner.close();
    }

    private void submitExam() {
        // Logic to submit exam, calculate score, etc.
        System.out.println("Exam submitted.");
    }
}

// Main class to drive the application
public class OnlineExamination {
    public static void main(String[] args) {
        // Sample usage
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));

        Exam exam = new Exam(questions, 60); // 60 seconds timer for each question
        exam.startExam();
    }
}
