import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        do {

            System.out.println("\n==============================");
            System.out.println("   LIBRARY MANAGEMENT SYSTEM");
            System.out.println("==============================");

            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Add Student");
            System.out.println("5. View Students");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");

            System.out.println("==============================");
            System.out.print("Enter your choice: ");

            try {

                choice = scanner.nextInt();

                switch (choice) {

                    case 1:

                        System.out.print("Enter Book ID: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter Author Name: ");
                        String author = scanner.nextLine();

                        Book book = new Book(bookId, title, author);

                        library.addBook(book);

                        break;

                    case 2:

                        System.out.println("\n--- BOOK LIST ---");

                        library.displayBooks();

                        break;

                    case 3:

                        System.out.print("Enter Book ID to search: ");
                        int searchId = scanner.nextInt();

                        library.searchBook(searchId);

                        break;

                    case 4:

                        System.out.print("Enter Student ID: ");
                        int studentId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Course: ");
                        String course = scanner.nextLine();

                        Student student =
                                new Student(studentId, name, course);

                        library.addStudent(student);

                        break;

                    case 5:

                        System.out.println("\n--- STUDENT LIST ---");

                        library.displayStudents();

                        break;

                    case 6:

                        System.out.print("Enter Book ID to issue: ");
                        int issueId = scanner.nextInt();

                        library.issueBook(issueId);

                        break;

                    case 7:

                        System.out.print("Enter Book ID to return: ");
                        int returnId = scanner.nextInt();

                        System.out.print("Enter number of late days: ");
                        int lateDays = scanner.nextInt();

                        library.returnBook(returnId, lateDays);

                        break;

                    case 8:

                        System.out.println(
                                "Thank you for using Library Management System!"
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid choice. Please try again."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Please enter a number."
                );

                scanner.nextLine();
            }

        } while (choice != 8);

        scanner.close();
    }
}