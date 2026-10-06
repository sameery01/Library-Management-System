import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Student> students;


    // =========================
    // CONSTRUCTOR
    // =========================

    public Library() {

        books = FileManager.loadBooks();

        students = FileManager.loadStudents();
    }


    // =========================
    // ADD BOOK
    // =========================

    public void addBook(Book book) {

        books.add(book);

        FileManager.saveBooks(books);

        System.out.println(
                "Book added successfully!"
        );
    }


    // =========================
    // ADD STUDENT
    // =========================

    public void addStudent(Student student) {

        students.add(student);

        FileManager.saveData(
                "Student ID: "
                + student.getStudentId()
                + ", Name: "
                + student.getName()
                + ", Course: "
                + student.getCourse()
        );

        System.out.println(
                "Student added successfully!"
        );
    }


    // =========================
    // DISPLAY BOOKS
    // =========================

    public void displayBooks() {

        if (books.isEmpty()) {

            System.out.println(
                    "No books available."
            );

            return;
        }

        for (Book book : books) {

            book.displayBook();
        }
    }


    // =========================
    // DISPLAY STUDENTS
    // =========================

    public void displayStudents() {

        if (students.isEmpty()) {

            System.out.println(
                    "No students registered."
            );

            return;
        }

        for (Student student : students) {

            student.displayStudent();
        }
    }


    // =========================
    // SEARCH BOOK
    // =========================

    public void searchBook(int bookId) {

        for (Book book : books) {

            if (book.getBookId() == bookId) {

                book.displayBook();

                return;
            }
        }

        System.out.println(
                "Book not found."
        );
    }


    // =========================
    // ISSUE BOOK
    // =========================

    public void issueBook(int bookId) {

        for (Book book : books) {

            if (book.getBookId() == bookId) {

                if (book.isAvailable()) {

                    book.issueBook();

                    // Save updated status
                    FileManager.saveBooks(books);

                    System.out.println(
                            "Book issued successfully!"
                    );

                } else {

                    System.out.println(
                            "Book is already issued."
                    );
                }

                return;
            }
        }

        System.out.println(
                "Book not found."
        );
    }


    // =========================
    // RETURN BOOK
    // =========================

    public void returnBook(
            int bookId,
            int lateDays) {

        for (Book book : books) {

            if (book.getBookId() == bookId) {

                if (!book.isAvailable()) {

                    book.returnBook();

                    int fine =
                            lateDays * 5;

                    // Save updated status
                    FileManager.saveBooks(books);

                    System.out.println(
                            "Book returned successfully!"
                    );

                    System.out.println(
                            "Late Days: "
                            + lateDays
                    );

                    System.out.println(
                            "Fine: ₹"
                            + fine
                    );

                } else {

                    System.out.println(
                            "Book is already available."
                    );
                }

                return;
            }
        }

        System.out.println(
                "Book not found."
        );
    }


    // =========================
    // GET BOOKS
    // =========================

    public ArrayList<Book> getBooks() {

        return books;
    }


    // =========================
    // GET STUDENTS
    // =========================

    public ArrayList<Student> getStudents() {

        return students;
    }
}