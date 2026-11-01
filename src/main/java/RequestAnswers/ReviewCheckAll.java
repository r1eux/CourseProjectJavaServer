package RequestAnswers;

import Entities.Review;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.ReviewService;
import com.google.gson.Gson;

import java.util.List;
import Enum.*;
public class ReviewCheckAll {



    public static Response View(Request request) {
        Gson gson = new Gson();
        Response response;
        ReviewService reviewService = new ReviewService();
        try {
            System.out.println("Получен запрос на получение всех review.");
            List<Review> review = reviewService.findAllEntities();

            if (!review.isEmpty()) {
                String jsonResponse = gson.toJson(review);
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
