import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LibraryGUI {

    // Main colors
    private static final Color SIDEBAR_COLOR = new Color(35, 45, 65);
    private static final Color HEADER_COLOR = new Color(245, 247, 250);
    private static final Color BACKGROUND_COLOR = new Color(238, 242, 247);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(40, 45, 55);
    private static final Color LIGHT_TEXT = new Color(110, 118, 130);
    private static final Color ACCENT_COLOR = new Color(52, 152, 219);
    private static final Color GREEN_COLOR = new Color(46, 180, 126);
    private static final Color ORANGE_COLOR = new Color(243, 156, 18);
    private static final Color RED_COLOR = new Color(231, 76, 60);

    private static JFrame frame;
    private static JPanel contentPanel;
    private static Library library;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            library = new Library();

            frame = new JFrame("Library Management System");

            frame.setSize(1100, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            createMainWindow();

            frame.setVisible(true);
        });
    }

    // =========================================================
    // MAIN WINDOW
    // =========================================================

    private static void createMainWindow() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        // Sidebar
        JPanel sidebar = createSidebar();

        // Right side
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(BACKGROUND_COLOR);

        JPanel header = createHeader();

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(
                new EmptyBorder(25, 30, 25, 30)
        );

        showDashboard();

        rightPanel.add(header, BorderLayout.NORTH);
        rightPanel.add(contentPanel, BorderLayout.CENTER);

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private static JPanel createSidebar() {

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setLayout(new BorderLayout());

        // Logo / Title
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(SIDEBAR_COLOR);
        logoPanel.setLayout(new BoxLayout(
                logoPanel,
                BoxLayout.Y_AXIS
        ));

        JLabel logo = new JLabel("📚");
        logo.setFont(new Font(
                "Segoe UI Emoji",
                Font.PLAIN,
                38
        ));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel(
                "<html><center>LIBRARY<br>MANAGEMENT</center></html>"
        );

        title.setForeground(Color.WHITE);
        title.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                18
        ));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoPanel.add(Box.createVerticalStrut(25));
        logoPanel.add(logo);
        logoPanel.add(Box.createVerticalStrut(8));
        logoPanel.add(title);
        logoPanel.add(Box.createVerticalStrut(30));

        // Navigation
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(SIDEBAR_COLOR);

        menuPanel.setLayout(new BoxLayout(
                menuPanel,
                BoxLayout.Y_AXIS
        ));

        addMenuButton(
                menuPanel,
                "⌂   Dashboard",
                e -> showDashboard()
        );

        addMenuButton(
                menuPanel,
                "📖   Books",
                e -> showBooks()
        );

        addMenuButton(
                menuPanel,
                "👨‍🎓   Students",
                e -> showStudents()
        );

        addMenuButton(
                menuPanel,
                "➕   Add Book",
                e -> addBook()
        );

        addMenuButton(
                menuPanel,
                "🔍   Search Book",
                e -> searchBook()
        );

        addMenuButton(
                menuPanel,
                "📤   Issue Book",
                e -> issueBook()
        );

        addMenuButton(
                menuPanel,
                "📥   Return Book",
                e -> returnBook()
        );

        sidebar.add(logoPanel, BorderLayout.NORTH);
        sidebar.add(menuPanel, BorderLayout.CENTER);

        // Exit
        JButton exitButton = createMenuButton("✕   Exit");

        exitButton.addActionListener(e -> {

            int answer = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        JPanel exitPanel = new JPanel(new BorderLayout());
        exitPanel.setBackground(SIDEBAR_COLOR);
        exitPanel.setBorder(
                new EmptyBorder(10, 10, 20, 10)
        );

        exitPanel.add(exitButton);

        sidebar.add(exitPanel, BorderLayout.SOUTH);

        return sidebar;
    }

    private static void addMenuButton(
            JPanel panel,
            String text,
            java.awt.event.ActionListener action
    ) {

        JButton button = createMenuButton(text);

        button.addActionListener(action);

        panel.add(button);
        panel.add(Box.createVerticalStrut(5));
    }

    private static JButton createMenuButton(String text) {

        JButton button = new JButton(text);

        button.setFont(new Font(
                "Segoe UI",
                Font.PLAIN,
                14
        ));

        button.setForeground(Color.WHITE);
        button.setBackground(SIDEBAR_COLOR);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(12, 18, 12, 10)
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        // Hover effect
        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        button.setBackground(
                                new Color(55, 70, 95)
                        );
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        button.setBackground(
                                SIDEBAR_COLOR
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private static JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(HEADER_COLOR);

        header.setBorder(
                new EmptyBorder(
                        18,
                        30,
                        18,
                        30
                )
        );

        JLabel heading = new JLabel(
                "Library Management System"
        );

        heading.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                24
        ));

        heading.setForeground(TEXT_COLOR);

        JLabel admin = new JLabel(
                "Administrator"
        );

        admin.setFont(new Font(
                "Segoe UI",
                Font.PLAIN,
                14
        ));

        admin.setForeground(LIGHT_TEXT);

        header.add(
                heading,
                BorderLayout.WEST
        );

        header.add(
                admin,
                BorderLayout.EAST
        );

        return header;
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private static void showDashboard() {

        contentPanel.removeAll();

        JPanel dashboard = new JPanel();
        dashboard.setBackground(BACKGROUND_COLOR);
        dashboard.setLayout(
                new BoxLayout(
                        dashboard,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel welcome = new JLabel(
                "Welcome to the Library Dashboard"
        );

        welcome.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                25
        ));

        welcome.setForeground(TEXT_COLOR);

        JLabel subtitle = new JLabel(
                "Manage books, students and library activities easily."
        );

        subtitle.setFont(new Font(
                "Segoe UI",
                Font.PLAIN,
                14
        ));

        subtitle.setForeground(LIGHT_TEXT);

        dashboard.add(welcome);
        dashboard.add(Box.createVerticalStrut(5));
        dashboard.add(subtitle);
        dashboard.add(Box.createVerticalStrut(25));

        // Statistics
        JPanel cards = new JPanel(
                new GridLayout(
                        1,
                        4,
                        15,
                        15
                )
        );

        cards.setBackground(BACKGROUND_COLOR);

        int totalBooks =
                library.getBooks().size();

        int availableBooks = 0;

        for (Book book : library.getBooks()) {

            if (book.isAvailable()) {
                availableBooks++;
            }
        }

        int issuedBooks =
                totalBooks - availableBooks;

        int totalStudents =
                library.getStudents().size();

        cards.add(
                createStatCard(
                        "TOTAL BOOKS",
                        String.valueOf(totalBooks),
                        ACCENT_COLOR
                )
        );

        cards.add(
                createStatCard(
                        "AVAILABLE",
                        String.valueOf(availableBooks),
                        GREEN_COLOR
                )
        );

        cards.add(
                createStatCard(
                        "ISSUED",
                        String.valueOf(issuedBooks),
                        ORANGE_COLOR
                )
        );

        cards.add(
                createStatCard(
                        "STUDENTS",
                        String.valueOf(totalStudents),
                        RED_COLOR
                )
        );

        dashboard.add(cards);

        dashboard.add(
                Box.createVerticalStrut(30)
        );

        // Quick Actions
        JLabel quickTitle = new JLabel(
                "Quick Actions"
        );

        quickTitle.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                20
        ));

        quickTitle.setForeground(TEXT_COLOR);

        dashboard.add(quickTitle);

        dashboard.add(
                Box.createVerticalStrut(15)
        );

        JPanel actions = new JPanel(
                new GridLayout(
                        2,
                        3,
                        15,
                        15
                )
        );

        actions.setBackground(
                BACKGROUND_COLOR
        );

        actions.add(
                createActionButton(
                        "➕  Add Book",
                        ACCENT_COLOR,
                        e -> addBook()
                )
        );

        actions.add(
                createActionButton(
                        "📖  View Books",
                        GREEN_COLOR,
                        e -> showBooks()
                )
        );

        actions.add(
                createActionButton(
                        "👨‍🎓  View Students",
                        ORANGE_COLOR,
                        e -> showStudents()
                )
        );

        actions.add(
                createActionButton(
                        "🔍  Search Book",
                        ACCENT_COLOR,
                        e -> searchBook()
                )
        );

        actions.add(
                createActionButton(
                        "📤  Issue Book",
                        ORANGE_COLOR,
                        e -> issueBook()
                )
        );

        actions.add(
                createActionButton(
                        "📥  Return Book",
                        GREEN_COLOR,
                        e -> returnBook()
                )
        );

        dashboard.add(actions);

        contentPanel.add(
                dashboard,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private static JPanel createStatCard(
            String title,
            String value,
            Color accent
    ) {

        JPanel card = new JPanel(
                new BorderLayout()
        );

        card.setBackground(CARD_COLOR);

        card.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        titleLabel.setForeground(
                LIGHT_TEXT
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        valueLabel.setForeground(
                accent
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // QUICK ACTION BUTTON
    // =========================================================

    private static JButton createActionButton(
            String text,
            Color color,
            java.awt.event.ActionListener action
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        button.setForeground(Color.WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);
        button.setBorder(
                new EmptyBorder(
                        15,
                        10,
                        15,
                        10
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addActionListener(action);

        return button;
    }

    // =========================================================
    // VIEW BOOKS
    // =========================================================

    private static void showBooks() {

        contentPanel.removeAll();

        JPanel panel =
                createPagePanel(
                        "📖  Book Collection",
                        "View all books in the library"
                );

        String[] columns = {
                "Book ID",
                "Title",
                "Author",
                "Status"
        };

        String[][] data =
                new String[
                        library.getBooks().size()
                ][4];

        int i = 0;

        for (Book book :
                library.getBooks()) {

            data[i][0] =
                    String.valueOf(
                            book.getBookId()
                    );

            data[i][1] =
                    book.getTitle();

            data[i][2] =
                    book.getAuthor();

            data[i][3] =
                    book.isAvailable()
                    ? "Available"
                    : "Issued";

            i++;
        }

        JTable table =
                new JTable(data, columns);

        styleTable(table);

        JScrollPane scrollPane =
                new JScrollPane(table);

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // VIEW STUDENTS
    // =========================================================

    private static void showStudents() {

        contentPanel.removeAll();

        JPanel panel =
                createPagePanel(
                        "👨‍🎓  Registered Students",
                        "View students registered in the library"
                );

        String[] columns = {
                "Student ID",
                "Name",
                "Course"
        };

        String[][] data =
                new String[
                        library.getStudents().size()
                ][3];

        int i = 0;

        for (Student student :
                library.getStudents()) {

            data[i][0] =
                    String.valueOf(
                            student.getStudentId()
                    );

            data[i][1] =
                    student.getName();

            data[i][2] =
                    student.getCourse();

            i++;
        }

        JTable table =
                new JTable(data, columns);

        styleTable(table);

        JScrollPane scrollPane =
                new JScrollPane(table);

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // PAGE PANEL
    // =========================================================

    private static JPanel createPagePanel(
            String title,
            String subtitle
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(0, 15)
                );

        panel.setBackground(
                BACKGROUND_COLOR
        );

        JLabel heading =
                new JLabel(title);

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        heading.setForeground(
                TEXT_COLOR
        );

        JLabel sub =
                new JLabel(subtitle);

        sub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        sub.setForeground(
                LIGHT_TEXT
        );

        JPanel top =
                new JPanel();

        top.setBackground(
                BACKGROUND_COLOR
        );

        top.setLayout(
                new BoxLayout(
                        top,
                        BoxLayout.Y_AXIS
                )
        );

        top.add(heading);
        top.add(Box.createVerticalStrut(5));
        top.add(sub);

        panel.add(
                top,
                BorderLayout.NORTH
        );

        return panel;
    }

    // =========================================================
    // TABLE STYLE
    // =========================================================

    private static void styleTable(
            JTable table
    ) {

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        table.setRowHeight(35);

        table.setSelectionBackground(
                new Color(220, 235, 250)
        );

        table.setSelectionForeground(
                TEXT_COLOR
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );

        table.getTableHeader()
                .setBackground(
                        SIDEBAR_COLOR
                );

        table.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        table.setGridColor(
                new Color(
                        220,
                        225,
                        230
                )
        );
    }

    // =========================================================
    // ADD BOOK
    // =========================================================

    private static void addBook() {

        JTextField idField =
                new JTextField();

        JTextField titleField =
                new JTextField();

        JTextField authorField =
                new JTextField();

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        panel.setBorder(
                new EmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        panel.add(
                new JLabel("Book ID:")
        );

        panel.add(idField);

        panel.add(
                new JLabel("Book Title:")
        );

        panel.add(titleField);

        panel.add(
                new JLabel("Author:")
        );

        panel.add(authorField);

        int result =
                JOptionPane.showConfirmDialog(
                        frame,
                        panel,
                        "Add New Book",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result ==
                JOptionPane.OK_OPTION) {

            try {

                int id =
                        Integer.parseInt(
                                idField.getText()
                        );

                String title =
                        titleField.getText()
                                .trim();

                String author =
                        authorField.getText()
                                .trim();

                if (title.isEmpty()
                        || author.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Please enter all details."
                    );

                    return;
                }

                Book book =
                        new Book(
                                id,
                                title,
                                author
                        );

                library.addBook(book);

                JOptionPane.showMessageDialog(
                        frame,
                        "Book added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                showDashboard();

            } catch (
                    NumberFormatException ex
            ) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Book ID must be a number!",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // =========================================================
    // SEARCH BOOK
    // =========================================================

    private static void searchBook() {

        String input =
                JOptionPane.showInputDialog(
                        frame,
                        "Enter Book ID:"
                );

        if (input == null) {
            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            input
                    );

            for (Book book :
                    library.getBooks()) {

                if (book.getBookId() == id) {

                    String details =
                            "Book ID: "
                            + book.getBookId()
                            + "\n\nTitle: "
                            + book.getTitle()
                            + "\n\nAuthor: "
                            + book.getAuthor()
                            + "\n\nStatus: "
                            + (
                                book.isAvailable()
                                ? "Available"
                                : "Issued"
                            );

                    JOptionPane.showMessageDialog(
                            frame,
                            details,
                            "Book Found",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    return;
                }
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Book not found.",
                    "Search Result",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter a valid Book ID.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // ISSUE BOOK
    // =========================================================

    private static void issueBook() {

        String input =
                JOptionPane.showInputDialog(
                        frame,
                        "Enter Book ID to issue:"
                );

        if (input == null) {
            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            input
                    );

            boolean found = false;

            for (Book book :
                    library.getBooks()) {

                if (book.getBookId() == id) {

                    found = true;

                    if (book.isAvailable()) {

                        library.issueBook(id);

                        JOptionPane.showMessageDialog(
                                frame,
                                "Book issued successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                frame,
                                "This book is already issued.",
                                "Book Unavailable",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }

                    break;
                }
            }

            if (!found) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Book not found."
                );
            }

            showDashboard();

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter a valid Book ID."
            );
        }
    }

    // =========================================================
    // RETURN BOOK
    // =========================================================

    private static void returnBook() {

        String input =
                JOptionPane.showInputDialog(
                        frame,
                        "Enter Book ID to return:"
                );

        if (input == null) {
            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            input
                    );

            boolean found = false;

            for (Book book :
                    library.getBooks()) {

                if (book.getBookId() == id) {

                    found = true;

                    if (!book.isAvailable()) {

                        String lateInput =
                                JOptionPane.showInputDialog(
                                        frame,
                                        "Enter number of late days:"
                                );

                        if (lateInput == null) {
                            return;
                        }

                        int lateDays =
                                Integer.parseInt(
                                        lateInput
                                );

                        if (lateDays < 0) {

                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Late days cannot be negative."
                            );

                            return;
                        }

                        library.returnBook(
                                id,
                                lateDays
                        );

                        int fine =
                                lateDays * 5;

                        JOptionPane.showMessageDialog(
                                frame,
                                "Book returned successfully!\n\n"
                                + "Late Days: "
                                + lateDays
                                + "\nFine: ₹"
                                + fine,
                                "Return Successful",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                frame,
                                "This book is already available.",
                                "Return Book",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }

                    break;
                }
            }

            if (!found) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Book not found."
                );
            }

            showDashboard();

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter valid numbers.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // REFRESH GUI
    // =========================================================

    private static void refresh() {

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}