import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    // =========================
    // SAVE DATA TO FILE
    // =========================
    public static void saveData(String data) {

        try {

            FileWriter writer =
                    new FileWriter("library.txt", true);

            writer.write(data);
            writer.write("\n");

            writer.close();

        } catch (IOException e) {

            System.out.println("Error while saving data.");
        }
    }


    // =========================
    // LOAD BOOKS FROM FILE
    // =========================
    public static ArrayList<Book> loadBooks() {

        ArrayList<Book> books = new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("library.txt")
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Book ID:")) {

                    String[] parts = line.split(", ");

                    int id = Integer.parseInt(
                            parts[0].replace("Book ID: ", "")
                    );

                    String title =
                            parts[1].replace("Title: ", "");

                    String author =
                            parts[2].replace("Author: ", "");

                    Book book =
                            new Book(id, title, author);

                    // Check saved status
                    if (parts.length >= 4) {

                        String status =
                                parts[3].replace("Status: ", "");

                        if (status.equals("Issued")) {
                            book.issueBook();
                        }
                    }

                    books.add(book);
                }
            }

            reader.close();

        } catch (IOException e) {

            // File may not exist on first run

        } catch (Exception e) {

            System.out.println(
                    "Error while loading books."
            );
        }

        return books;
    }


    // =========================
    // LOAD STUDENTS FROM FILE
    // =========================
    public static ArrayList<Student> loadStudents() {

        ArrayList<Student> students =
                new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("library.txt")
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Student ID:")) {

                    String[] parts = line.split(", ");

                    int id = Integer.parseInt(
                            parts[0].replace(
                                    "Student ID: ", ""
                            )
                    );

                    String name =
                            parts[1].replace(
                                    "Name: ", ""
                            );

                    String course =
                            parts[2].replace(
                                    "Course: ", ""
                            );

                    Student student =
                            new Student(
                                    id,
                                    name,
                                    course
                            );

                    students.add(student);
                }
            }

            reader.close();

        } catch (IOException e) {

            // File may not exist on first run

        } catch (Exception e) {

            System.out.println(
                    "Error while loading students."
            );
        }

        return students;
    }


    // =========================
    // SAVE ALL BOOKS
    // =========================
    public static void saveBooks(
            ArrayList<Book> books) {

        try {

            FileWriter writer =
                    new FileWriter("library.txt");

            // Save books
            for (Book book : books) {

                writer.write(
                        "Book ID: "
                        + book.getBookId()
                        + ", Title: "
                        + book.getTitle()
                        + ", Author: "
                        + book.getAuthor()
                        + ", Status: "
                        + (book.isAvailable()
                                ? "Available"
                                : "Issued")
                        + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {
S
            System.out.println(
                    "Error while saving books."
            );
        }
    }
}