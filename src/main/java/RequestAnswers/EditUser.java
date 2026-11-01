package RequestAnswers;

import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.UserService;
import com.google.gson.Gson;
import Enum.*;

public class EditUser {

    public static Response Edit(Request request){

        Response response;
        UserService userService = new UserService();
        Gson gson=new Gson();
        String jsonResponse;
        User user1= new User();
        User userFromFront=new User();

        userFromFront = gson.fromJson(request.getRequestMessage(), User.class);
        int id=userFromFront.getUserId();
        System.out.println("ID: "+id);
        user1=userService.findEntity(id);
        System.out.println(user1);
        user1.setLogin(userFromFront.getLogin());
        user1.setPassword(userFromFront.getPassword());

        user1.setBuyer(userFromFront.getBuyer());

        userService.updateEntity(user1);

        response=new Response(ResponseStatus.OK,"Все окей!","");
        return response;

    }
}
