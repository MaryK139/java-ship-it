import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ParcelTest {
    private static ParcelBox<Parcel> box;
    private static Parcel standard;
    private static Parcel fragile;
    private static PerishableParcel perishable;
    private static Parcel lightParcel;
    private static Parcel heavyParcel;
    private static Parcel zeroWeight;

    @BeforeEach
    void setUp() {
        box = new ParcelBox<>(10); // коробка на 10 кг
        standard = new StandardParcel("Документы", 1, "Москва", 100);
        fragile = new FragileParcel("Ваза", 2, "СПб", 80);
        perishable = new PerishableParcel("Овощи", 3, "Ближайшая область",
                20, 3);
        lightParcel = new StandardParcel("Книга", 1, "Москва", 80);
        heavyParcel = new StandardParcel("Монитор", 11, "СПб", 90);
        zeroWeight = new StandardParcel("Пустой предмет", 0, "Москва", 50);
    }

    @Test
        void testStandardParcelCost() { // Стандартный сценарий: обычная посылка
        assertEquals(2, standard.calculateDeliveryCost(),
                "Стоимость стандартной посылки должна быть 2 руб.");
    }

    @Test
    void testFragileParcelCost() { // Стандартный сценарий: хрупкая посылка
        assertEquals(8, fragile.calculateDeliveryCost(),
                "Стоимость хрупкой посылки (2 кг) должна быть 8 руб.");
    }

    @Test
    void testPerishableParcelCostWithShortDistance() {
        // Граничный сценарий: скоропортящаяся посылка на малое расстояние
        assertEquals(9, perishable.calculateDeliveryCost(),
                "Скоропортящаяся посылка должна (3 кг) должна стоить 9 руб.");
    }

    @Test
    void testPerishableParcelNotExpired() { // Стандартный сценарий: посылка в пределах срока
        assertFalse(perishable.isExpired(10), "Посылка не должна быть испорчена");
    }

    @Test
    void testPerishableParcelExpired() {
        // Граничный сценарий: посылка просрочена
        assertFalse(perishable.isExpired(23), "Посылка не должна быть испорчена");
    }

    @Test
    void testStandardParcelNeverExpires() { // Стандартная сценарий: посылка просрочена

        assertTrue(perishable.isExpired(30), "Посылка должна быть испорченной");
    }

    @Test
    void testAddZeroWeightParcel() { // Добавление пустого предмета
        box.addParcel(zeroWeight);
        assertEquals(0, box.getCurrentWeight()); // Вес не должен измениться
    }

    @Test
    void testAddLightParcel() { // Стандартный сценарий: добавляем лёгкую посылку
        box.addParcel(lightParcel);
        assertEquals(1, box.getCurrentWeight());
    }

    @Test
    void testCannotAddOverweightParcel() { // Граничный сценарий: посылка тяжелее доступного места
        box.addParcel(heavyParcel); // 8 кг > 10 кг (коробка пуста!)
        assertEquals(0, box.getCurrentWeight(),
                "Вес не должен измениться, если посылка не добавлена");
    }

    @Test
    void testAddingMultipleParcelsUntilFull() { // Проверяем последовательное добавление до заполнения коробки
        for (int i = 0; i < 10; i++) {
            box.addParcel(lightParcel); // 1 кг × 10 раз
        }
        assertEquals(10, box.getCurrentWeight()); // коробка полная

        // Пытаемся добавить ещё одну посылку — не должно получиться
        box.addParcel(heavyParcel);
        assertEquals(10, box.getCurrentWeight());
    }
}

