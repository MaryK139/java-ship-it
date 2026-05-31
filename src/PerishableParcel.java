import java.util.Objects;

public class PerishableParcel extends Parcel{
    private static final int BASE_COST = 3;
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }
    @Override
    public void packageItem() {
        System.out.println("Посылка <" + getDescription() + "> обёрнута в защитную плёнку");
        System.out.println("Посылка <" + getDescription() + "> упакована");
    }

    public boolean isExpired(int currentDay) {
        return (getSendDay() + timeToLive) < currentDay;
    }

    @Override
    public int getBaseCost() {
        return BASE_COST;
    }

    @Override
    public String toString() {
        return "Посылка: '" + getDescription() + "'\nвес: " + getWeight() + "\nадресс отправки: '" +
                getDeliveryAddress() + "'\nдата отправки: " + getSendDay() + "\nсрок хранения: " + timeToLive;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerishableParcel that = (PerishableParcel) o;
        return getWeight() == that.getWeight() &&
                getSendDay() == that.getSendDay() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getDeliveryAddress(), that.getDeliveryAddress()) &&
                timeToLive == that.timeToLive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timeToLive);
    }
}
