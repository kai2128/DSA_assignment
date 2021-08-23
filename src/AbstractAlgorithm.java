import java.util.LinkedList;

public abstract class AbstractAlgorithm implements Algorithm{
    protected LinkedList<Parcel> parcelList;
    protected LinkedList<Truck> truckList = new LinkedList<>();

    protected boolean loadParcelToTruck(Truck truck, Parcel parcel){
        return truck.loadParcel(parcel);
    }

    public void removeFromList(Parcel parcel){
        // delete parcel from parcel list
        parcelList.remove(parcel);
    }

    public boolean notAllParcelBeingLoaded(){
        return parcelList.size() > 0;
    }

    public void createNewTruck(){
        truckList.addLast(new Truck());
    }

    @Override
    public void printResult() {
        for (Truck truck : truckList) {
            System.out.println(truck.printParcelStored());
        }

        System.out.println("Truck count: " + truckList.size());
    }
}
