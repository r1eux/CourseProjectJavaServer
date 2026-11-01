//package RequestAnswers;
//
//import Entities.Cart;
//import Entities.User;
//import ServerLogic.Request;
//import ServerLogic.Response;
//import Service.*;
//import com.google.gson.Gson;
//import Enum.ResponseStatus;
//public class Register {
//
//
//    public static  Response registration(Request request){
//       Gson gson=new Gson();
//       Response response;
//       PersonService personService=new PersonService();
//       AddressService addressService=new AddressService();
//        CartService cartService=new CartService();
//        SexService sexService=new SexService();
//      UserService userService=new UserService();
//        User user = gson.fromJson(request.getRequestMessage(), User.class);
//        personService = new PersonService();
//        if (userService.findAllEntities().stream().noneMatch(x -> x.getLogin().toLowerCase().equals(user.getLogin().toLowerCase()))) {
//            user.getPerson().getSex();
//            Cart cart=new Cart();
//
//            addressService.saveEntity(user.getPerson().getAddress());
//            personService.saveEntity(user.getPerson());
//            user.setSalt("2");
//            userService.saveEntity(user);
//            cart.setUser(user);
//            cartService.saveEntity(cart);
//            User backUser = userService.findEntity(user.getUserId());
//            response = new Response(ResponseStatus.OK, "Регистрация прошла успешно", gson.toJson(backUser));
//        }else{
//            response = new Response(ResponseStatus.ERROR, "Пользователь с таким именем уже существует", "");
//
//        }
//        return response;
//
//
//
//    }
//}

package RequestAnswers;

import Entities.Cart;
import Entities.User;
import Entities.Address;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.*;
import com.google.gson.Gson;
import Enum.ResponseStatus;

public class Register {

    public static Response registration(Request request) {
        Gson gson = new Gson();
        Response response;
        PersonService personService = new PersonService();
        AddressService addressService = new AddressService();
        CartService cartService = new CartService();
        SexService sexService = new SexService();
        UserService userService = new UserService();

        User user = gson.fromJson(request.getRequestMessage(), User.class);

        if (userService.findAllEntities().stream()
                .noneMatch(x -> x.getLogin().equalsIgnoreCase(user.getLogin()))) {

            Cart cart = new Cart();

            // Проверка существования адреса
            Address existingAddress = addressService.findAllEntities().stream()
                    .filter(a -> a.equals(user.getBuyer().getAddress())) // Проверьте, что метод equals переопределён корректно
                    .findFirst()
                    .orElse(null);

            if (existingAddress == null) {
                addressService.saveEntity(user.getBuyer().getAddress());
            } else {
                user.getBuyer().setAddress(existingAddress);
            }

            // Сохранение пользователя и связанных сущностей
            personService.saveEntity(user.getBuyer());

            userService.saveEntity(user);
            cart.setUser(user);
            cartService.saveEntity(cart);

            User backUser = userService.findEntity(user.getUserId());
            response = new Response(ResponseStatus.OK, "Регистрация прошла успешно", gson.toJson(backUser));
        } else {
            response = new Response(ResponseStatus.ERROR, "Пользователь с таким именем уже существует", "");
        }

        return response;
    }
}
