package RequestAnswers;

import Entities.Product;
import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.ProductService;
import Service.UserService;
import com.google.gson.Gson;
import Enum.*;

public class DeleteProduct {
    public static Response deleteProduct(Request request) {
        Response response;
        ProductService userService = new ProductService();
        Gson gson=new Gson();

        int userId = gson.fromJson(request.getRequestMessage(), int.class);
        System.out.println(userId);
        Product userdel = userService.findEntity(userId);
        if(userdel==null) {
            response=new Response(ResponseStatus.ERROR, "Все сломалось!","");
            return response;
        }
        try {
            userService.deleteEntity(userdel);
        }catch (Exception e) {
            e.printStackTrace();
            response=new Response(ResponseStatus.ERROR, "Все сломалось!","");
            return response;
        }

        response = new Response(ResponseStatus.OK, "Готово!","");
        return response;
    }

}
