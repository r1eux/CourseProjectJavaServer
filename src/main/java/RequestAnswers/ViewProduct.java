package RequestAnswers;



import Entities.Product;
import Service.ProductService;
import com.google.gson.Gson;
import Enum.ResponseStatus;
import Entities.Role;
import Entities.User;
import Service.RoleService;
import Service.UserService;
import ServerLogic.Request;
import ServerLogic.Response;

import java.util.List;

public class ViewProduct {
    public static Response View(Request request) {
        Gson gson = new Gson();
        Response response;
        ProductService userService = new ProductService();

        try {
            System.out.println("Получен запрос на получение всех tovarov.");
            List<Product> users = userService.findAllEntities();

            if (!users.isEmpty()) {
                String jsonResponse = gson.toJson(users);
                System.out.println("Список пользователей найден. Отправляем ответ: " + jsonResponse);
                response = new Response(ResponseStatus.OK, "Готово!", jsonResponse);
            } else {
                System.out.println("Список пользователей пуст.");
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