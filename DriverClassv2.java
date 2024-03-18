//unit2.hw
//Group Members: Jevaughn Morris, Dallas Moody

//imports
import java.util.Scanner;

public class DriverClassv2 {

	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);

		{
	    System.out.print("Enter the student's Id:");
	    String studentId = scanner.nextLine();

	    System.out.print("Enter the student's full name:");
	    String studentName = scanner.nextLine();

	    System.out.print("\nEnter CRN and Credit Hours for the first class:");
	    String class1Input = scanner.nextLine();
	    String[] class1Data = class1Input.split("/");
	    int crn1 = Integer.parseInt(class1Data[0]);
	    int creditHours1 = Integer.parseInt(class1Data[1]);

	    System.out.print("Enter CRN and Credit Hours for the second class:");
	    String class2Input = scanner.nextLine();
	    String[] class2Data = class2Input.split("/");
	    int crn2 = Integer.parseInt(class2Data[0]);
	    int creditHours2 = Integer.parseInt(class2Data[1]);

	    // Instantiation of FeeInvoice
	    FeeInvoice feeInvoice = new FeeInvoice(studentName, studentId, crn1, creditHours1, crn2, creditHours2);

	    // Printing the fee invoice
	    feeInvoice.printFeeInvoice();
	  } 
	  	scanner.close();
	}
}
	class FeeInvoice {
	  private String studentName;
	  private String studentId;
	  private int crn1;
	  private int creditHours1;
	  private int crn2;
	  private int creditHours2;

	  //construtor
	  public FeeInvoice(String studentName, String studentId, int crn1, int creditHours1, int crn2, int creditHours2) {
	    this.studentName = studentName;
	    this.studentId = studentId;
	    this.crn1 = crn1;
	    this.creditHours1 = creditHours1;
	    this.crn2 = crn2;
	    this.creditHours2 = creditHours2;
	  }

	   // Setters and getters
	   public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getCrn1() {
        return crn1;
    }

    public void setCrn1(int crn1) {
        this.crn1 = crn1;
    }

    public int getCreditHours1() {
        return creditHours1;
    }

    public void setCreditHours1(int creditHours1) {
        this.creditHours1 = creditHours1;
    }

    public int getCrn2() {
        return crn2;
    }

    public void setCrn2(int crn2) {
        this.crn2 = crn2;
    }

    public int getCreditHours2() {
        return creditHours2;
    }

    public void setCreditHours2(int creditHours2) {
        this.creditHours2 = creditHours2;
    }

	public void printFeeInvoice() {
        System.out.println("Student Name: " + getStudentName());
        System.out.println("Student Id: " + getStudentId());
        System.out.println("CRN/Credit Hours for the first class: " + getCrn1() + "/" + getCreditHours1());
        System.out.println("CRN/Credit Hours for the second class: " + getCrn2() + "/" + getCreditHours2());
        
		// Print college information
        System.out.println("SIMPLE COLLEGE");
        System.out.println("ORLANDO FL 10101");
        System.out.println("******************************");
        
		// Print fee invoice header
		System.out.println("Fee Invoice Prepared for:");
		System.out.println("[" + getStudentName() + "][" + getStudentId() + "]");
		System.out.println("1 Credit Hour = $120.25");
		System.out.println();
		System.out.println("CRN    CREDIT HOURS");
		System.out.println(getCrn1() + "      " + getCreditHours1() + "     $" + (getCreditHours1() * 120.25));
		System.out.println(getCrn2() + "      " + getCreditHours2() + "     $" + (getCreditHours2() * 120.25));
		System.out.println("Health & id fees     $35.00");
		System.out.println("----------------------------------------");
		System.out.printf("Total Payments       $%.2f%n", calculateTotalPayment() + 35.00);
    }

	  private double calculateTotalPayment() {
	    return (creditHours1 * 100) + (creditHours2 * 100);
	  }
	}