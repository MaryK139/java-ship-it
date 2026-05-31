import java.util.Objects;

abstract class Parcel {
    private String description;
    private int weight;
    private String deliveryAddress;
    private int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public abstract int getBaseCost();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

    public void setSendDay(int sendDay) {
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка <" + getDescription() + "> упакована");
    }

    public void deliver() {
        System.out.println("Посылка <" + getDescription() + "> доставлена по адресу " + getDeliveryAddress());
    }

    public double calculateDeliveryCost() {
        return weight * getBaseCost();
    }

    @Override
    public String toString() {
        return "Посылка: '" + description + "'\nвес: " + weight + "\nадресс отправки: '" +
                deliveryAddress + "'\nдата отправки: " + sendDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return weight == parcel.weight &&
                sendDay == parcel.sendDay &&
                Objects.equals(description, parcel.description) &&
                Objects.equals(deliveryAddress, parcel.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, weight, deliveryAddress, sendDay);
    }
}

