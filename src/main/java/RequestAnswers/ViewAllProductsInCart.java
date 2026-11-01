package RequestAnswers;

import Entities.CartItem;
import Entities.Product;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.CartItemService;
import Service.ProductService;
import com.google.gson.Gson;
import Enum.*;
import java.util.List;

public class ViewAllProductsInCart {

    public static Response View(Request request) {
        Gson gson = new Gson();
        Response response;
        CartItemService userService = new CartItemService();

        try {
            System.out.println("Получен запрос на получение всех KORZIN.");
            List<CartItem> users = userService.findAllEntities();

            if (!users.isEmpty()) {
                String jsonResponse = gson.toJson(users);
                System.out.println("Список KORZIN найден. Отправляем ответ: " + jsonResponse);
                response = new Response(ResponseStatus.OK, "Готово!", jsonResponse);
            } else {
                System.out.println("Список KORZIN пуст.");
                response = new Response(ResponseStatus.ERROR, "Список пользователей пуст", "");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при обработке запроса: " + e.getMessage());
            e.printStackTrace();
            response = new Response(ResponseStatus.ERROR, "Произошла ошибка при поиске пользователей: " + e.getMessage(), "");
        }

        return response;
    }
}
