package RequestAnswers;

import Entities.Review;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.ReviewService;
import com.google.gson.Gson;

import Enum.*;
public class ReviewCreate {



    public static Response Create(Request request){


        Gson gson = new Gson();
        Response response = new Response();
        ReviewService reviewService = new ReviewService();
        Review review = gson.fromJson(request.getRequestMessage(),Review.class);
        reviewService.saveEntity(review);
        response = new Response(ResponseStatus.OK, "Отзыв добавлен", "");
        return response;
    }
}
