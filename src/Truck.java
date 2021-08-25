import java.util.LinkedList;

public class Truck {
    private static int loadLimit = 3000;
    private LinkedList<Parcel> parcelLoaded;
    private int loadedWeight = 0;

    // ** for naming truck START **
    private static int count = 65;
    private final char label;

    public static  void resetLabel(){
        count = 65;
    }
    public String getLabel() {
        return  "Truck " + label;
    }
    // ** for naming truck END **


    public static int getLoadLimit(){
        return loadLimit;
    }

    public static void setLoadLimit(int newLoadLimit){
        loadLimit = newLoadLimit;
    }

    public boolean isFull() {
        return loadedWeight >= loadLimit;
    }

    public boolean canLoad(Parcel newParcel) {
        return loadedWeight + newParcel.getWeight() <= loadLimit;
    }


    public String printParcelStored() {
        StringBuilder parcelStored = new StringBuilder();
        for (Parcel parcel : parcelLoaded) {
            parcelStored.append(parcel).append(", ");
        }
        // delete last ", "
        parcelStored = new StringBuilder(parcelStored.substring(0, parcelStored.length() - 2));

        return String.format("Truck %c - available: %5d kg | loaded: [%s]", label,  loadLimit - loadedWeight, parcelStored.toString());
    }

    public boolean loadParcel(Parcel parcel) {
        if (canLoad(parcel)) {
            parcelLoaded.add(parcel.loadInto(this));
            loadedWeight += parcel.getWeight();
            return true;
        }
        return false;
    }

    public Truck() {
        label = (char) count;
        count++;
        this.parcelLoaded = new LinkedList<>();
    }
}

