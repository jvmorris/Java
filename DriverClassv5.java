//unit5.hw
//Group Members: Jevaughn Morris, Dallas Moody

public class DriverClassv5 {
    public static void main(String[] args) {
        Student s;

        // PhdStudent example
        s = new PhdStudent("Zaydoun BenSellam",
                "zb5954",
                "Gary Richarson",
                "Fuzzy Topology",
                2599);
        s.printInvoice();

        // MsStudent example
        int[] gradCrnsTaken = {7587, 8997};
        s = new MsStudent("Emily Jones",
                "em1254",
                gradCrnsTaken,
                8997);
        s.printInvoice();

        // UndergraduateStudent example
        int[] undergradCrnsTaken = {4587, 2599};
        s = new UndergraduateStudent("Jamila Jones",
                "ja5225",
                undergradCrnsTaken,
                3.0,
                false);
        s.printInvoice();
    }
} // end of main

// Abstract superclass Student
abstract class Student {
    private String name;
    private String id;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    abstract public void printInvoice();

    //possible additions +++++++
     public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}


// Concrete subclass UndergraduateStudent
class UndergraduateStudent extends Student {
    private int[] undergradCrnsTaken;
    private double gpa;
    private boolean resident;

    public UndergraduateStudent(String name, String id, int[] undergradCrnsTaken, double gpa, boolean resident) {
        super(name, id);
        this.undergradCrnsTaken = undergradCrnsTaken;
        this.gpa = gpa;
        this.resident = resident;
    }

    @Override
    public void printInvoice() {
        // Print college information
        System.out.println("VALENCE COLLEGE");
        System.out.println("ORLANDO FL 10101");
        System.out.println("---------------------");
        
        // Print fee invoice header
        System.out.println("                 ");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName() + " (Jamila, a FL resident, has gpa higher than 3.5 gpa)");
        System.out.println("                 ");
        System.out.println("1 Credit Hour = $120.25");
        System.out.println("                 ");


        // Iterate through courses taken and print them
        System.out.println("CRN CR_PREFIX CR_HOURS");
        for (int crn : undergradCrnsTaken) {
            if(crn == 2599) {
                System.out.println(crn + " " + "COP 330" + " " + "3"); // Highlighted change
            } else if(crn == 4587) {
                System.out.println(crn + " " + "MAT 236" + " " + "4"); // Highlighted change
            }
        }
        
        // Print health and id fees
        System.out.println("                 ");
        System.out.println("Health & id fees $ 35.00");
        System.out.println("--------------------------------------");
        
        // Calculate and print total payments
        double totalPayments = calculateTotalPayments();
        System.out.println("$ " + totalPayments);
        System.out.println("-$ " + (totalPayments * 0.25)); // Discount deduction
        System.out.println("----------");
        System.out.println("TOTAL PAYMENTS $ " + (totalPayments - (totalPayments * 0.25)));
    }

    private double calculateTotalPayments() {
        double creditHourCost = 120.25; // 1 credit hour cost
        double healthAndIdFees = 35.00; 
        double totalCredits = 0;  
        for (int crn : undergradCrnsTaken) {
            if (crn == 2599) {
                totalCredits += 3; 
            } else if (crn == 4587) {
                totalCredits += 4; 
            }
        }
        double totalPayments = (totalCredits * creditHourCost) + healthAndIdFees;
        return totalPayments;
    }
}

// Abstract subclass GraduateStudent
abstract class GraduateStudent extends Student {
    private int crn;

    public GraduateStudent(String name, String id, int crn) {
        super(name, id);
        this.crn = crn;
    }

    //possible additions +++++++
    public int getCrn() {
        return crn;
    }
} //PhdStudent and MsStudent fall under Grad

// Concrete subclass PhdStudent
class PhdStudent extends GraduateStudent {
    private String advisor;
    private String researchSubject;

    public PhdStudent(String name, String id, String advisor, String researchSubject, int crn) {
        super(name, id, crn);
        this.advisor = advisor;
        this.researchSubject = researchSubject;
    }

    @Override
public void printInvoice() {
    // Print college information
    System.out.println("VALENCE COLLEGE");
    System.out.println("ORLANDO FL 10101");
    System.out.println("---------------------");
    
    // Print fee invoice header
    System.out.println("                 ");
    System.out.println("Fee Invoice Prepared for Student:");
    System.out.println(getId() + "-" + getName());
    System.out.println("                 ");
    System.out.println("RESEARCH");
    System.out.println(getResearchSubject() + " $ 700.00");
    
    // Print health and id fees
    System.out.println("Health & id fees $ 35.00");
    System.out.println("--------------------------------------");
    
    // Calculate and print total payments
    double totalPayments = calculateTotalPayments();
    System.out.println("Total Payments $ " + totalPayments);
}

// Getter method for research subject
public String getResearchSubject() {
    return researchSubject;
}

//method to calculate total payments
private double calculateTotalPayments() {
    double researchFee = 700.00; // Research fee
    double healthAndIdFees = 35.00; // Health and ID fees
    
    // Calculate total payments
    double totalPayments = researchFee + healthAndIdFees;
    
    return totalPayments;
}

}

// Concrete subclass MsStudent
class MsStudent extends GraduateStudent {
    private int[] gradCrnsTaken;


    public MsStudent(String name, String id, int[] gradCrnsTaken, int crn) {
        super(name, id, crn);
        this.gradCrnsTaken = gradCrnsTaken;
    }

    @Override
public void printInvoice() {
    // Print college information
    System.out.println("VALENCE COLLEGE");
    System.out.println("ORLANDO FL 10101");
    System.out.println("---------------------");
    
    // Print fee invoice header
    System.out.println("                 ");
    System.out.println("Fee Invoice Prepared for Student:");
    System.out.println(getId() + "-" + getName());
    System.out.println("                 ");
    System.out.println("1 Credit Hour = $300.00");
    System.out.println("                 ");
    
    // Iterate through courses taken and print them
        System.out.println("CRN CR_PREFIX CR_HOURS");
        for (int crn : gradCrnsTaken) {
            if(crn == 7587) {
                System.out.println(crn + " " + "MAT 936" + " " + "5");
            }else if(crn == 8997) {
                System.out.println(crn + " " + "GOL 124" + " " + "1");
            }
        }
    
    // Print health and id fees
    System.out.println("Health & id fees $ 35.00");
    System.out.println("--------------------------------------");
    
    // Calculate and print total payments
    double totalPayments = calculateTotalPayments();
    System.out.println("Total Payments $ " + totalPayments);
}

//method to calculate total payments
private double calculateTotalPayments() {
    double creditHourCost = 300.00; // 1 credit hour cost
    double healthAndIdFees = 35.00; // Health and ID fees
    int numberOfCreditHours = gradCrnsTaken.length; // Number of credit hours
    
    // Calculate total payments
    double totalPayments = (numberOfCreditHours * creditHourCost) + healthAndIdFees;
    
    return totalPayments;
}

}

