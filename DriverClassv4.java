//unit4.hw
//Group Members: Jevaughn Morris, Dallas Moody

//imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    private int studentId;
    String studentName;
    private double gpa;
    private ArrayList<Integer> listOfCrns;

    public Student(String studentName, int studentId, double gpa, ArrayList<Integer> listOfCrns) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.gpa = gpa;
        this.listOfCrns = listOfCrns;
    }

    public double calculateTotalPayment() {
        double totalPayment = 35.00; // Base fee for health and id services

        for (int crn : listOfCrns) {
            totalPayment += (120.25 * getCreditHours(crn)); // Cost per credit hour
        }

        // Applying discount if GPA is 3.5 or higher and total payment exceeds $700
        if (gpa >= 3.5 && totalPayment > 700.00) {
            totalPayment *= 0.75; // 25% discount
        }

        return totalPayment;
    }

    public void printFeeInvoice() {
        System.out.println("VALENCE COLLEGE");
        System.out.println("ORLANDO FL 10101");
        System.out.println("Fee Invoice Prepared for Student: " + studentId + " - " + studentName);
        System.out.println("1 Credit Hour = $120.25");
        System.out.println("CRN\tCourse\tCredit Hours");
        for (int crn : listOfCrns) {
            System.out.println(crn + "\t" + getCourseName(crn) + "\t" + getCreditHours(crn));
        }
        System.out.println("Health & id fees $" + 35.00);
        System.out.println("Total Payments $" + calculateTotalPayment());
    }

    String getCourseName(int crn) {
        HashMap<Integer, String> courseMap = new HashMap<>();
        // Populate courseMap with course details (CRN and course name)
        courseMap.put(4587, "MAT 236");
        courseMap.put(4599, "COP 220");
        courseMap.put(8997, "GOL 124");
        courseMap.put(9696, "COP 100");
        courseMap.put(4580, "MAT 136");
        courseMap.put(2599, "COP 260");
        courseMap.put(1997, "COP 424");
        courseMap.put(3696, "KOL 110");

        return courseMap.get(crn);
    }

    int getCreditHours(int crn) {
        HashMap<Integer, Integer> creditHoursMap = new HashMap<>();
        // Populate creditHoursMap with credit hours (CRN and credit hours)
        creditHoursMap.put(4587, 4);
        creditHoursMap.put(4599, 3);
        creditHoursMap.put(8997, 1);
        creditHoursMap.put(9696, 3);
        creditHoursMap.put(4580, 1);
        creditHoursMap.put(2599, 3);
        creditHoursMap.put(1997, 1);
        creditHoursMap.put(3696, 2);

        return creditHoursMap.get(crn);
    }

    // Getters and setters
    public int getStudentId() {
        return studentId;
    }

    public ArrayList<Integer> getListOfCrns() {
        return listOfCrns;
    }
}

class College {
    private ArrayList<Student> list;

    public College() {
        this.list = new ArrayList<>();
    }

    public void enrollStudent(Student student) {
        list.add(student);
    }

    public boolean searchById(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                return true;
            }
        }
        return false;
    }

    public String getStudentName(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                return student.studentName;
            }
        }
        return null;
    }

    public boolean addCourse(int studentId, int crn) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                student.getListOfCrns().add(crn);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(int studentId, int crn) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                return student.getListOfCrns().remove(Integer.valueOf(crn));
            }
        }
        return false;
    }

    public void printInvoice(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                student.printFeeInvoice();
                return;
            }
        }
        System.out.println("No Student found!");
    }

    public void printSortedInvoice(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                ArrayList<Integer> sortedCrns = new ArrayList<>(student.getListOfCrns());
                Collections.sort(sortedCrns);
                System.out.println("VALENCE COLLEGE");
                System.out.println("ORLANDO FL 10101");
                System.out.println("Fee Invoice Prepared for Student: " + studentId + " - " + getStudentName(studentId));
                System.out.println("1 Credit Hour = $120.25");
                System.out.println("CRN\tCourse\tCredit Hours");
                for (int crn : sortedCrns) {
                    System.out.println(crn + "\t" + student.getCourseName(crn) + "\t" + student.getCreditHours(crn));
                }
                System.out.println("Health & id fees $" + 35.00);
                System.out.println("Total Payments $" + student.calculateTotalPayment());
                return;
            }
        }
        System.out.println("No Student found!");
    }
}

public class DriverClassv4 {
    private static College valenceCollege;

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Choose from the following options:");
            System.out.println("1 - Add a new student");
            System.out.println("2 - Add/Delete a course");
            System.out.println("3 - Search for a student");
            System.out.println("4 - Print fee invoice");
            System.out.println("5 - Print fee invoice sorted by CRN");
            System.out.println("0 - Exit program");
            System.out.print("Enter your selection: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    addDeleteCourse();
                    break;
                case 3:
                    searchForStudent();
                    break;
                case 4:
                    printFeeInvoice();
                    break;
                case 5:
                    printFeeInvoiceSortedByCrn();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);
            scanner.close();
    }

    public static void addNewStudent() {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print("Enter the student's id: ");
            int studentId = scanner.nextInt();
            // Check if student already exists
            if (valenceCollege.searchById(studentId)) {
                System.out.println("Sorry, " + studentId + " is already assigned to another student.");
                return;
            }
            scanner.nextLine(); // Consume newline
            System.out.print("Enter the student's name: ");
            String studentName = scanner.nextLine();
            System.out.print("Enter the student's GPA: ");
            double gpa = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            ArrayList<Integer> listOfCrns = new ArrayList<>();
            System.out.print("Enter how many courses " + studentName + " is taking: ");
            int numCourses = scanner.nextInt();
            System.out.println("Enter the course numbers:");
            for (int i = 0; i < numCourses; i++) {
                int crn = scanner.nextInt();
                listOfCrns.add(crn);
            }
            Student student = new Student(studentName, studentId, gpa, listOfCrns);
            valenceCollege.enrollStudent(student);
            System.out.println("Student added successfully!");
        } finally {
            scanner.close();
        }
    }

    public static void addDeleteCourse() {
        // Implementation to add/delete a course
        Scanner scanner = new Scanner(System.in);
        try { 
            System.out.print("Enter the student's id: ");
            int studentId = scanner.nextInt();
            // Check if student exists
            if (!valenceCollege.searchById(studentId)) {
                System.out.println("No student found!");
                return;
            }
            System.out.println("Here are the courses " + valenceCollege.getStudentName(studentId) + " is taking:");
            valenceCollege.printInvoice(studentId);
            System.out.println("Choose from:");
            System.out.println("A - Add a new course");
            System.out.println("D - Delete a course");
            System.out.println("C - Cancel operation");
            System.out.print("Enter your selection: ");
            char selection = scanner.next().charAt(0);
            switch (selection) {
                case 'A':
                    System.out.print("Enter the course number to add: ");
                    int crnToAdd = scanner.nextInt();
                    valenceCollege.addCourse(studentId, crnToAdd);
                    System.out.println("Course added successfully!");
                    break;
                case 'D':
                    System.out.print("Enter the course number to delete: ");
                    int crnToDelete = scanner.nextInt();
                    valenceCollege.deleteCourse(studentId, crnToDelete);
                    System.out.println("Course deleted successfully!");
                    break;
                case 'C':
                    System.out.println("Operation cancelled.");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }finally {
            scanner.close();
        }
                
    }

    public static void searchForStudent() {
        // Implementation to search for a student
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the student's id: ");
        int studentId = scanner.nextInt();
        valenceCollege.printInvoice(studentId);

        scanner.close();
    }

    public static void printFeeInvoice() {
        // Implementation to print fee invoice
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the student's id: ");
        int studentId = scanner.nextInt();
        valenceCollege.printInvoice(studentId);

        scanner.close();
    }

    public static void printFeeInvoiceSortedByCrn() {
        // Implementation to print fee invoice sorted by CRN
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the student's id: ");
        int studentId = scanner.nextInt();
        valenceCollege.printSortedInvoice(studentId);

        scanner.close();
    }

    public static void main(String[] args) {
        valenceCollege = new College();
        mainMenu();
    }
}
