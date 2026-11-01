package RequestAnswers;

import Entities.CartItem;
import Entities.Product;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.CartItemService;
import com.google.gson.Gson;
import Enum.*;
public class DeleteFromCart {

    public static Response Delete(Request request){

        Gson gson = new Gson();
        CartItemService cartItemService = new CartItemService();
        CartItem prodFromClient=gson.fromJson(request.getRequestMessage(),CartItem.class);
        cartItemService.deleteEntity(prodFromClient);
        System.out.println("UDALENIE ZAEBIS!!!!!!!!!!!1");
        Response response = new Response();
        response = new Response(ResponseStatus.OK, "Готово!","");
        return response;
    }
}
