class RoomBookingSystem {
    public void checkAvailability() {
        System.out.println("Проверка доступности номеров...");
    }

    public void bookRoom(String name) {
        System.out.println("Номер забронирован для: " + name);
    }

    public void cancelBooking(String name) {
        System.out.println("Бронирование отменено для: " + name);
    }
}

class RestaurantSystem {
    public void reserveTable(String name) {
        System.out.println("Стол забронирован для: " + name);
    }

    public void orderFood(String food) {
        System.out.println("Заказана еда: " + food);
    }
}

class EventManagementSystem {
    public void bookHall(String event) {
        System.out.println("Зал забронирован для: " + event);
    }

    public void orderEquipment(String equipment) {
        System.out.println("Оборудование заказано: " + equipment);
    }
}

class CleaningService {
    public void scheduleCleaning(String room) {
        System.out.println("Уборка запланирована для номера: " + room);
    }

    public void cleanNow(String room) {
        System.out.println("Уборка выполнена для номера: " + room);
    }
}

class TaxiService {
    public void callTaxi(String name) {
        System.out.println("Такси вызвано для: " + name);
    }
}

class HotelFacade {
    private RoomBookingSystem roomSystem;
    private RestaurantSystem restaurant;
    private EventManagementSystem eventSystem;
    private CleaningService cleaning;
    private TaxiService taxi;

    public HotelFacade() {
        roomSystem = new RoomBookingSystem();
        restaurant = new RestaurantSystem();
        eventSystem = new EventManagementSystem();
        cleaning = new CleaningService();
        taxi = new TaxiService();
    }

    public void bookRoomWithServices(String name) {
        System.out.println("\n=== Бронирование номера с услугами ===");
        roomSystem.checkAvailability();
        roomSystem.bookRoom(name);
        restaurant.orderFood("Ужин");
        cleaning.scheduleCleaning("101");
    }

    public void organizeEvent(String event, String organizer) {
        System.out.println("\n=== Организация мероприятия ===");
        eventSystem.bookHall(event);
        eventSystem.orderEquipment("Проектор");
        roomSystem.bookRoom(organizer);
    }

    public void reserveTableWithTaxi(String name) {
        System.out.println("\n=== Ресторан + такси ===");
        restaurant.reserveTable(name);
        taxi.callTaxi(name);
    }

    public void cancelRoom(String name) {
        roomSystem.cancelBooking(name);
    }

    public void requestCleaning(String room) {
        cleaning.cleanNow(room);
    }
}

public class MainHotel {
    public static void main(String[] args) {

        HotelFacade hotel = new HotelFacade();

        hotel.bookRoomWithServices("Ерасыл");
        hotel.organizeEvent("Конференция IT", "Компания ABC");
        hotel.reserveTableWithTaxi("Ерасыл");

        hotel.requestCleaning("101");
        hotel.cancelRoom("Ерасыл");
    }
}
