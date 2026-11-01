package RequestAnswers;

import Entities.CartItem;
import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.CartItemService;
import Service.UserService;
import com.google.gson.Gson;
import Enum.*;

import java.util.ArrayList;
import java.util.List;

public class IncreaseAmountItem {

    public static Response Edit(Request request){

        Response response;
        CartItemService userService = new CartItemService();
        Gson gson=new Gson();
        String jsonResponse;
        CartItem user1= new CartItem();
        CartItem userFromFront=new CartItem();

        userFromFront = gson.fromJson(request.getRequestMessage(), CartItem.class);
        int id=userFromFront.getCart().getCartId();
        System.out.println("ID: "+id);
        System.out.println("AMOUNt: "+userFromFront.getAmount());
        List<CartItem> crList=userService.findAllEntities();
        for(CartItem cartItem:crList){
            if(cartItem.getCart().getCartId()==id && cartItem.getProduct().getProductId()==userFromFront.getProduct().getProductId()){
                int amount=cartItem.getAmount();
                System.out.println("NIGGERS!!!!!!!!!!!!!!!!!!!!!!"+cartItem.getAmount());
                cartItem.setAmount(amount+userFromFront.getAmount());

                userService.updateEntity(cartItem);
            }
        }
        //user1=userService.findEntity(id);


        response=new Response(ResponseStatus.OK,"Все окей!","");
        return response;

    }
}
