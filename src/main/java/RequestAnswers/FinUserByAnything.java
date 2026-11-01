package RequestAnswers;

import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.UserService;
import com.google.gson.Gson;
import Enum.ResponseStatus;

import java.util.List;

public class FinUserByAnything {
    public static Response Find(Request request){
        Response response;
        UserService userService = new UserService();
        Gson gson=new Gson();
        String jsonResponse;

        String something = gson.fromJson(request.getRequestMessage(), String.class);
        System.out.println(something);
        List<User> userdel = userService.findByAnything(something);
        if(userdel==null) {
            response=new Response(ResponseStatus.ERROR, "Все сломалось!","");
            return response;
        }
        for(User user : userdel) {
            System.out.println(user);
        }
        jsonResponse=gson.toJson(userdel);
        System.out.println(jsonResponse);

        response = new Response(ResponseStatus.OK, "Готово!",jsonResponse);
        return response;

    }
}
