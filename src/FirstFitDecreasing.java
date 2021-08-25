import java.util.*;

public class FirstFitDecreasing extends AbstractAlgorithm {

    @Override
    public void startAlgorithm(){
        createNewTruck();
        while (notAllParcelBeingLoaded()){
            Parcel parcelToBeLoaded = parcelList.getFirst();
            boolean loaded = false;

            // scan for all truck and try to load parcel into first fittable truck
            for (Truck truck : truckList) {
                loaded = loadParcelToTruck(truck, parcelToBeLoaded);

                // if parcel is successfully load into a truck stop scanning
                if(loaded)
                    break;
            }

            // if fail to load after scanning all truck,
            // create a new truck and load the parcel into it
            if(!loaded){
                loadParcelIntoNewTruck(parcelToBeLoaded);
            }

            // remove it from the parcel list
            removeFromList(parcelToBeLoaded);
        }
    }

    public FirstFitDecreasing(List<Parcel> parcelList){
        // sort the parcel list reverse first
        parcelList.sort(Collections.reverseOrder());
        this.parcelList = new LinkedList<Parcel>(parcelList);
    }
}

