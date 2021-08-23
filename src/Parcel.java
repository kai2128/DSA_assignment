import java.util.WeakHashMap;

public class Parcel implements Comparable<Parcel> {
    private int weight;
    private Truck loadedAt = null;

    // ** for naming the parcel START **
    private static int count = 1;
    private final int label;

    public static  void resetLabel(){
        count = 1;
    }
    public String getLabel() {
        return "Parcel " + label;
    }
    // ** for naming the parcel  END **

    @Override
    public String toString() {
        return String.format("Parcel %d {%d kg}", label, weight);
    }

    public Parcel loadInto(Truck truck) {
        loadedAt = truck;
        return this;
    }

    public Truck getLoadedAt() {
        return this.loadedAt;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    public Parcel(int weight) {
        label = count;
        count++;
        this.weight = weight;
    }

    @Override
    public int compareTo(Parcel o) {
        if (weight == o.getWeight())
            return 0;
        else if (weight > o.getWeight())
            return 1;
        else
            return -1;
    }
}
