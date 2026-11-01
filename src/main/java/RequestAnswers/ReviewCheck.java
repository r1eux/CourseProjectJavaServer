package RequestAnswers;

import Entities.Product;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.ProductService;
import Service.ReviewService;
import com.google.gson.Gson;
import Entities.Review;
import java.util.List;
import Enum.*;
public class ReviewCheck {


    public static Response View(Request request) {
        Gson gson = new Gson();
        Response response;
        ReviewService reviewService = new ReviewService();
        String id=gson.fromJson(request.getRequestMessage(),String.class);
        System.out.println(id);
        try {
            System.out.println("Получен запрос на получение всех review.");
            List<Review> review = reviewService.findAllReviews(Integer.parseInt(id));

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
