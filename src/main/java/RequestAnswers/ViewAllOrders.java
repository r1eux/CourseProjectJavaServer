package RequestAnswers;

import Entities.Order;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.OrderService;
import com.google.gson.Gson;
import Enum.*;

import java.util.List;

public class ViewAllOrders {

    public static Response View(Request request) {
        Gson gson = new Gson();
        Response response;
        OrderService orderService = new OrderService();

        try {
            System.out.println("Получен запрос на получение всех заказов.");
            List<Order> orders = orderService.findAllEntities();

            if (!orders.isEmpty()) {
                // Преобразуем список заказов в JSON
                String jsonResponse = gson.toJson(orders);
                System.out.println("Список заказов найден. Отправляем ответ: " + jsonResponse);
                response = new Response(ResponseStatus.OK, "Готово!", jsonResponse);
            } else {
                System.out.println("Список заказов пуст.");
                response = new Response(ResponseStatus.ERROR, "Список заказов пуст", "");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при обработке запроса: " + e.getMessage());
            e.printStackTrace();
            response = new Response(ResponseStatus.ERROR, "Произошла ошибка при поиске заказов: " + e.getMessage(), "");
        }

        return response;
    }
}
