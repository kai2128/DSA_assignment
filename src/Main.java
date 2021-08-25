import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static LinkedList<Parcel> parcelList = new LinkedList<>();
    private static LinkedList<Parcel> defaultParcelList;
    private static Algorithm algorithm;

    public static void main(String[] args) {
        initDefaultList();

        String choice;
        do {
            System.out.println("---- Main menu ----");
            System.out.println("  1. Add parcel");
            System.out.println("  2. View current parcel list");
            System.out.println("  3. Generate a random parcel list");
            System.out.println("  4. Reset list");
            System.out.println("  5. Set truck load limit (current: " + Truck.getLoadLimit() + " kg)");
            System.out.println("  6. First Fit Decreasing Algorithm");
            System.out.println("  7. Next Fit Algorithm");
            System.out.println("  0. Exit");

            System.out.println("If no parcel is added, a default list will be used.");
            System.out.print("Enter your choice (0-6): ");
            choice = scanner.nextLine();
            switch (choice) {
                case "0" -> {
                    break;
                }
                case "1" -> addParcel();
                case "2" -> listParcel();
                case "3" -> generateRandomList();
                case "4" -> resetList();
                case "5" -> setLoadLimit();
                case "6" -> firstFitDecreasing();
                case "7" -> nextFit();
                default -> System.out.println("Not a valid choice.");
            }
        } while (!choice.equals("0"));
    }


    private static void enterToContinue() {
        System.out.println("Enter to continue... ");
        scanner.nextLine();
    }

    // A default list as a sample
    // used to init the default parcel list
    private static void initDefaultList() {
        defaultParcelList = new LinkedList<>();
        defaultParcelList.add(new Parcel((int) calculateDefaultParcelWeight(2)));
        defaultParcelList.add(new Parcel((int) calculateDefaultParcelWeight(5)));
        defaultParcelList.add(new Parcel((int) calculateDefaultParcelWeight(4)));
        defaultParcelList.add(new Parcel((int) calculateDefaultParcelWeight(7)));
        defaultParcelList.add(new Parcel((int) calculateDefaultParcelWeight(1)));
        defaultParcelList.add(new Parcel((int) calculateDefaultParcelWeight(3)));
        defaultParcelList.add(new Parcel((int) calculateDefaultParcelWeight(8)));
        // reset parcel label to start with 1
        Parcel.resetLabel();
    }

    private static double calculateDefaultParcelWeight(double weight) {
        return weight / 10 * Truck.getLoadLimit();
    }


    // if parcel list not yet being added any item, return default list
    private static List<Parcel> checkIsParcelListEmpty() {
        if (parcelList.size() == 0) {
            return new LinkedList<>(defaultParcelList);
        } else {
            return new LinkedList<>(parcelList);
        }
    }


    // use to prompt user input a integer, throw exception if not a number or negative number is inputted
    private static int getIntInput(String promptMsg) {
        int input = 0;
        while (true) {
            try {
                System.out.print(promptMsg);
                input = Integer.parseInt(scanner.next());
                scanner.nextLine();
                if (input < 0) {
                    throw new IllegalArgumentException("Cannot be negative numbers.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    /*
    For user to input parcel one by one
     */
    public static void addParcel() {
        while (true) {
            int weight = getIntInput("Enter parcel weight kg (0 when finish adding): ");

            if (weight == 0)
                break;

            if (weight > Truck.getLoadLimit()) {
                System.out.println("Parcel weight cannot be more than current load limit.");
                continue;
            }

            Parcel parcel = new Parcel(weight);
            parcelList.addLast(parcel);
            System.out.println(parcel + " added");
            System.out.println();
        }
        listParcel();
    }

    /*
    Generate a random list base on current load limit
     */
    public static void generateRandomList() {
        Parcel.resetLabel();
        LinkedList<Parcel> tempList = new LinkedList<>();

        int min = 1;
        int max = Truck.getLoadLimit();

        int randomNumber = (int) (Math.random() * ((int) (Math.random() * 20)));

        for (int i = 0; i < randomNumber; i++) {
            int randomWeight = min + (int) (Math.random() * ((max - min) + 1));
            tempList.addLast(new Parcel(randomWeight));
        }
        parcelList = tempList;
        System.out.println("List generated: ");
        listParcel();
    }

    /*
    Print current parcel list
     */
    public static void listParcel() {
        List<Parcel> printList = checkIsParcelListEmpty();
        int totalWeight = 0;

        StringBuilder parcelEntered = new StringBuilder();
        for (Parcel parcel : printList) {
            totalWeight += parcel.getWeight();
            parcelEntered.append(parcel).append(", ");
        }
        // delete last ", "
        parcelEntered = new StringBuilder(parcelEntered.substring(0, parcelEntered.length() - 2));
        System.out.println();
        System.out.println("Current parcel list: ");
        System.out.println(parcelEntered);
        System.out.printf("The optimal number of Trucks: %.0f", Math.ceil((double) totalWeight / Truck.getLoadLimit()));
        System.out.println();
        enterToContinue();
    }

    /*
    Reset current parcel list
     */
    public static void resetList() {
        Parcel.resetLabel();
        parcelList = new LinkedList<>();
        System.out.println("Parcel List resetted");
        enterToContinue();
    }


    /*
    Use to modify the load limit
     */
    public static void setLoadLimit() {
        int newLoadLimit = 0;
        while (true){
            newLoadLimit = getIntInput("Enter new load limit: ");
            if(newLoadLimit <= 0)
                System.out.println("Load limit cannot be less than or equal to 0.");
            else
                break;
        }
        Truck.setLoadLimit(newLoadLimit);

        // adjust weight of default parcel list
        initDefaultList();
        System.out.println("Load limit set to " + newLoadLimit + " kg");
        enterToContinue();
    }


    // ** ALGORITHM METHOD START **
    private static void startAlgorithm() {
        algorithm.startAlgorithm();
        algorithm.printResult();
        Truck.resetLabel();
    }

    public static void nextFit() {
        List<Parcel> parcels = checkIsParcelListEmpty();
        System.out.println(" --- Next Fit ---");
        algorithm = new NextFit(parcels);
        startAlgorithm();
        System.out.println();
        enterToContinue();
    }


    public static void firstFitDecreasing() {
        List<Parcel> parcels = checkIsParcelListEmpty();

        System.out.println(" --- First Fit Decreasing ---");
        algorithm = new FirstFitDecreasing(parcels);
        startAlgorithm();
        System.out.println();
        enterToContinue();
    }
    // ** ALGORITHM METHOD END **
}

