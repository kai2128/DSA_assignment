import java.util.*;

public class NextFit extends AbstractAlgorithm {
    @Override
    public void startAlgorithm() {
        // create a empty truck first
        createNewTruck();
        while (notAllParcelBeingLoaded()) {

            // get parcel to be loaded
            Parcel parcelToBeLoaded = parcelList.getFirst();
            boolean loaded = false;

            loaded = loadParcelToTruck(truckList.getLast(), parcelToBeLoaded);

            // if fail to load, create a new truck and load the parcel into it
            if (!loaded) {
                loadParcelIntoNewTruck(parcelToBeLoaded);
            }

            // remove parcel from parcel list
            removeFromList(parcelToBeLoaded);
        }
    }

    public NextFit(List<Parcel> parcelList) {
        this.truckList = new LinkedList<>();
        this.parcelList = new LinkedList<Parcel>(parcelList);
    }
}
