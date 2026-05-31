import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();
    private static final ParcelBox<StandardParcel> box1 = new ParcelBox<>(100);
    private static final ParcelBox<PerishableParcel> box2 = new ParcelBox<>(100);
    private static final ParcelBox<FragileParcel> box3 = new ParcelBox<>(100);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    checkReportStatus();
                    break;
                case 5:
                    showParcelsInBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 - Отследить местоположение посылок");
        System.out.println("5 - Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        System.out.println("Какую посылку нужно отправить?");
        System.out.println("1 - стандартная посылка, 2 - скоропортящаяся посылка, 3 - хрупкая посылка");
        int parcelType = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Что нужно отправить?");
        String description = scanner.nextLine();
        System.out.println("Какой вес посылки (кг)?");
        int weight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Куда нужно отправить?");
        String destination = scanner.nextLine();
        System.out.println("Какая дата отправки?");
        int sendDay = scanner.nextInt();
        scanner.nextLine();
        Parcel parcel = null;
        switch (parcelType) {
            case 1:
                parcel = new StandardParcel(description, weight, destination, sendDay);
                box1.addParcel((StandardParcel) parcel);// Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
                break;
            case 2:
                System.out.println("Каков срок годности посылки?");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                parcel = new PerishableParcel(description, weight, destination, sendDay, timeToLive);
                box2.addParcel((PerishableParcel) parcel);
                break;
            case 3:
                parcel = new FragileParcel(description, weight, destination,sendDay);
                box3.addParcel((FragileParcel) parcel);
                break;
            default:
                System.out.println("Неизвестный тип посылки");
        }
        allParcels.add(parcel);
        if (parcel instanceof Trackable) {
            trackableParcels.add((Trackable) parcel);
        }
        System.out.println("Посылка успешна добавлена!");
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver(); // Пройти по allParcels, вызвать packageItem() и deliver()
        }
    }

    private static void calculateCosts() {
        int sumCost = 0;
        for (Parcel parcel : allParcels) {
            sumCost += (int) parcel.calculateDeliveryCost();// Посчитать общую стоимость всех доставок и вывести на экран
        }
        System.out.println("Общая стоимость доставки составила - " + sumCost);
    }

    private static void checkReportStatus() {
        System.out.println("Введите новый адресс отправки");
        String newLocation = scanner.nextLine();
        for (Trackable parcel : trackableParcels) {
            parcel.reportStatus(newLocation);
        }
    }

    private static void showParcelsInBox() {
        System.out.println("Содержимое какой коробки вы хотите посмотреть");
        System.out.println("1 - станрдартные посылки, 2 - скоропортящаюся посылки, 3 - хрупкие посылки");
        int typeOfBox = scanner.nextInt();
        scanner.nextLine();
        switch (typeOfBox) {
            case 1:
                box1.getAllParcels();
                break;
            case 2:
                box2.getAllParcels();
                break;
            case 3:
                box3.getAllParcels();
                break;
            default:
                System.out.println("Такой коробки нет в наличии");
        }
    }
}
