package RequestAnswers;

import Entities.CartItem;
import Entities.Order;
import Entities.OrderItem;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.OrderItemService;
import Service.OrderService;
import Service.ProductService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import Enum.*;
public class CreateOrderItems {

    public static Response Create(Request request) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<OrderItem>>() {}.getType();
        List<OrderItem> orderItems = gson.fromJson(request.getRequestMessage(), listType);

        OrderItemService orderItemService = new OrderItemService();
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();
        for (OrderItem orderItem : orderItems) {
             System.out.println(orderItem.getOrder().getOrderId());
            if (orderItem.getOrder() != null) {
                int orderId = orderItem.getOrder().getOrderId();
               // Получаем ID заказа
                System.out.println("вошло!!!!!!УРАААААА!!!!!");
                // Загружаем managed объект Order из базы данных
                Order managedOrder = orderService.findEntity(orderId);

                // Устанавливаем этот managed объект в OrderItem
                orderItem.setOrder(managedOrder);
            }

            // Используем merge для сохранения OrderItem
            orderItemService.saveEntity(orderItem);

            System.out.println("!СОХРАНЕНИЕ ЭЛЕМЕНТОВ ЗАКАЗА");
            System.out.println(orderItem);
        }

        Response response = new Response(ResponseStatus.OK, "Элементы заказа успешно сохранены", "");
        return response;
    }

}
