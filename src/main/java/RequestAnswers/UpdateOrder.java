package RequestAnswers;

import Entities.Order;
import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.OrderService;
import Service.UserService;
import com.google.gson.Gson;
import Enum.*;
public class UpdateOrder {

    public static Response Update(Request request){

        Response response;
        OrderService orderService = new OrderService();
        Gson gson=new Gson();
        String jsonResponse;
        Order order= new Order();
        Order orderFromFront=new Order();
        UserService userService = new UserService();

        orderFromFront = gson.fromJson(request.getRequestMessage(), Order.class);
        int id=orderFromFront.getUser().getUserId();
        System.out.println("ID: "+id);
        User user=userService.findEntity(id);
        System.out.println(order);
        order.setUser(user);
        order.setOrderDate(orderFromFront.getOrderDate());
        order.setTotalSum(orderFromFront.getTotalSum());
        order.setShippingDate(orderFromFront.getShippingDate());
        order.setOrderId(orderFromFront.getOrderId());
        order.setStatus(orderFromFront.getStatus());

        orderService.updateEntity(order);

        response=new Response(ResponseStatus.OK,"Все окей!","");
        return response;

    }
}
