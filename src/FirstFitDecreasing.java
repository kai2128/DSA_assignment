import java.util.*;

public class FirstFitDecreasing extends AbstractAlgorithm {

    @Override
    public void startAlgorithm(){
        createNewTruck();
        while (notAllParcelBeingLoaded()){
            Parcel parcelToBeLoaded = parcelList.getFirst();
            boolean isLoaded = false;

            // scan for all truck and try to load parcel into first fittable truck
            for (Truck truck : truckList) {
                isLoaded = loadParcelToTruck(truck, parcelToBeLoaded);

                // if parcel is successfully loaded
                if(isLoaded){
                    // remove it from the parcel list and add to loadedParcel
                    recordLoadedParcel(parcelToBeLoaded);
                    break;
                }
            }

            // if the parcel is not being load after scanning
            if(!isLoaded){
                // add a new truck
                createNewTruck();
            }
        }
    }

    public FirstFitDecreasing(List<Parcel> parcelList){
        // sort the parcel list reverse first
        parcelList.sort(Collections.reverseOrder());
        this.parcelList = new LinkedList<Parcel>(parcelList);
    }
}

