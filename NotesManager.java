import java.io.*;
import java.util.*;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";
    private static List<String> notes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadNotesFromFile();

        while (true) {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Save Notes");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addNote();
                    break;
                case "2":
                    viewNotes();
                    break;
                case "3":
                    saveNotesToFile();
                    break;
                case "4":
                    saveNotesToFile();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addNote() {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();
        notes.add(note);
        System.out.println("Note added.");
    }

    private static void viewNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes to display.");
        } else {
            System.out.println("\n--- Your Notes ---");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }

    private static void saveNotesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String note : notes) {
                writer.write(note);
                writer.newLine();
            }
            System.out.println("Notes saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving notes: " + e.getMessage());
        }
    }

    private static void loadNotesFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
            System.out.println("Notes loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading notes: " + e.getMessage());
        }
    }
}