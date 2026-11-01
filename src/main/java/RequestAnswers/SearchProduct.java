package RequestAnswers;

import Entities.Product;
import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.ProductService;
import Service.UserService;
import com.google.gson.Gson;
import Enum.*;
import java.util.List;

public class SearchProduct {

    public static Response SearchProd(Request request){

        Response response;
        ProductService userService = new ProductService();
        Gson gson=new Gson();
        String jsonResponse;

        String something = gson.fromJson(request.getRequestMessage(), String.class);
        System.out.println(something);
        List<Product> userdel = userService.findByAnything(something);
        if(userdel==null) {
            response=new Response(ResponseStatus.ERROR, "Все сломалось!","");
            return response;
        }
        for(Product product : userdel) {
            System.out.println(product);
        }
        jsonResponse=gson.toJson(userdel);
        System.out.println(jsonResponse);

        response = new Response(ResponseStatus.OK, "Готово!",jsonResponse);
        return response;
    }
}
