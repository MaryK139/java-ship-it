import java.util.List;
import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private List<T> box = new ArrayList<>();
    private int maxWeight;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if ((currentWeight + parcel.getWeight()) <= maxWeight) {
            box.add(parcel);
            currentWeight += parcel.getWeight();
        } else {
            System.out.println("Превышен максимальный вес коробки");
        }
    }

    public void getAllParcels() {
        System.out.println(box);
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int sumWeight) {
        this.currentWeight = sumWeight;
    }

    public List<T> getBox() {
        return box;
    }

    public void setBox(List<T> box) {
        this.box = box;
    }
}
