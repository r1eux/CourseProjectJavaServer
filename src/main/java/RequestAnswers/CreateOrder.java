package RequestAnswers;

import Entities.Order;
import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.OrderService;
import Service.UserService;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Enum.*;
public class CreateOrder {

    public static Response Create(Request request){

        Gson gson = new Gson();
        Response response;
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        Order order=gson.fromJson(request.getRequestMessage(), Order.class);
        int id=order.getUser().getUserId();
        User user=userService.findEntity(id);
        order.setUser(user);
//        String date=order.getOrderDate();
//        String dateShip=order.getShippingDate();
//        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate dateLocal= LocalDate.parse(date ,formatter);
//        LocalDate dateShipping=LocalDate.parse(dateShip,formatter);
        //order.setOrderDate(dateLocal);
        System.out.println("СЕЙЧАС БУДЕТ СОХРАНЕНИЕ ЗАКАЗА");
        orderService.saveEntity(order);
        Order backOrder=orderService.findEntity(order.getOrderId());
        System.out.println(backOrder.getOrderId());
        response = new Response(ResponseStatus.OK, "Добавление заказа прошло успешно",gson.toJson(backOrder));
        return response;
    }
}
