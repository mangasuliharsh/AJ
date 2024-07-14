
import java.util.*;

public class MissedCallDemo {
    public static void main(String args[]) {
        ArrayList<Contact> contact = new ArrayList<>();
        ArrayList<MissedCall> missed = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        contact.add(new Contact("Harsh", 73851371));
        contact.add(new Contact("Shivam", 99881122));
        contact.add(new Contact("Vaibhav", 99882012));
        contact.add(new Contact("Ashish", 44556611));

        while (true) {
            System.out.println("Options 1-Add 2-Display 3-Delete");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("Enter the Missed Call Number");
                    long num = sc.nextLong();
                    missed.add(new MissedCall(num, new Date()));

                    if (missed.size() > 10)
                        missed.remove(0);
                    break;

                case 2:
                    System.out.println("Contact List \n");
                    for (MissedCall mc : missed) {
                        boolean found = false;
                        for (Contact c : contact) {
                            if (mc.number == c.number) {
                                found = true;
                                System.out.println(c.name + ": " + c.number);
                            }
                        }
                        if (!found)
                            System.out.println("unknown: " + mc.number + ": " + mc.date);
                    }
                    break;

                case 3:
                    System.out.println("Delete By 1-Index 2-Number");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        int i = 1;
                        for (MissedCall mc : missed) {
                            System.out.println(i++ + " " + mc.number);
                        }
                        System.out.println("Enter the Index");
                        int ch = sc.nextInt();
                        missed.remove(ch - 1);
                        System.out.println("Deleted Successfully");
                    } else {
                        System.out.println("Enter Number");
                        long numb = sc.nextLong();
                        boolean flag = false;
                        for (int i = 0; i < missed.size(); i++) {
                            if (missed.get(i).number == numb) {
                                missed.remove(i);
                                flag = true;
                            }
                        }
                        if (flag)
                            System.out.println("Deleted Successfully");
                        else
                            System.out.println("Missed Call Not Deleted");
                    }
                    break;

                default:
                    System.exit(0);
            }
        }
    }
}

class Contact {
    String name;
    long number;

    public Contact(String name, long number) {
        super();
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return name + ": " + number + "\n";
    }
}

class MissedCall {
    long number;
    Date date;

    public MissedCall(long number, Date date) {
        super();
        this.number = number;
        this.date = date;
    }

    @Override
    public String toString() {
        return number + ": " + date + "\n";
    }
}
