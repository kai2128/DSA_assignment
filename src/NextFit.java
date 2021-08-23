import java.util.*;

public class NextFit extends AbstractAlgorithm {
    @Override
    public void startAlgorithm() {
        // create a empty truck first
        createNewTruck();
        while (notAllParcelBeingLoaded()) {

            // get parcel to be loaded
            Parcel parcelToBeLoaded = parcelList.getFirst();
            boolean isLoaded = false;

            isLoaded = loadParcelToTruck(truckList.getLast(), parcelToBeLoaded);

            // if successfully loaded remove parcel from parcel list
            if (isLoaded) {
                removeFromList(parcelToBeLoaded);
            } else
                // if fail to load, create a new truck
                createNewTruck();

        }
    }

    public NextFit(List<Parcel> parcelList) {
        this.truckList = new LinkedList<>();
        this.parcelList = new LinkedList<Parcel>(parcelList);
    }
}
