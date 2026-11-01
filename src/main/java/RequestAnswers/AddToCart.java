package RequestAnswers;

import Entities.Cart;
import Entities.CartItem;
import Entities.Product;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.CartItemService;
import Service.ColorService;
import Service.ProducerService;
import Service.ProductService;
import com.google.gson.Gson;
import Enum.*;
public class AddToCart {

    public static Response Add(Request request){
        Gson gson=new Gson();
        Response response;
        CartItemService cartItemService=new CartItemService();
        ColorService colorService=new ColorService();
        ProducerService producerService=new ProducerService();
        ProductService productService=new ProductService();

        CartItem cartItem = gson.fromJson(request.getRequestMessage(), CartItem.class);

        if (cartItemService.findAllEntities().stream().noneMatch(x -> x.getProduct().getProdName().toLowerCase().equals(cartItem.getProduct().getProdName().toLowerCase()))) {
            System.out.println(gson.toJson(cartItem.getCart()));
           cartItemService.saveEntity(cartItem);
            response = new Response(ResponseStatus.OK, "!KORZINA NAIDENA!", "");
        }else{
            response = new Response(ResponseStatus.ERROR, "!KORIZNA NE NAIDENA!", "");

        }
        return response;

    }
}
