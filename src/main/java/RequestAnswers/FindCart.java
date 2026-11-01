package RequestAnswers;

import Entities.Cart;
import Service.CartService;
import com.google.gson.Gson;
import Enum.ResponseStatus;
import Entities.User;
import Service.UserService;
import ServerLogic.Request;
import ServerLogic.Response;

import java.util.List;



public class FindCart {


    public static Response Find(Request request) {
        Gson gson = new Gson();
        Response response;
        CartService cartService = new CartService();
        String something = gson.fromJson(request.getRequestMessage(), String.class);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+something);
        List<Cart> cart=cartService.findAllEntities();
        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL"+gson.toJson(cart));
        Cart cartOut=new Cart();
        for(Cart c:cart){
            if(c.getUser().getUserId()==Integer.parseInt(something)){
                cartOut.setUser(c.getUser());
                cartOut.setCartId(c.getCartId());
                System.out.println(gson.toJson(cartOut));
                break;
            }
        }
        //String cartCheck=gson.toJson(cart);
        //System.out.println("cartCheck:"+cartCheck);
        if(cartOut==null) {
            response=new Response(ResponseStatus.ERROR, "Все сломалось!","");
            return response;
        }
        String jsonResponse=gson.toJson(cartOut);
        System.out.println(jsonResponse);

        response = new Response(ResponseStatus.OK, "Готово!",jsonResponse);
        return response;

    }

}