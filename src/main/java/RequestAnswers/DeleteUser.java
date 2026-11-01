package RequestAnswers;

import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.UserService;
import com.google.gson.Gson;
import Enum.ResponseStatus;
public class DeleteUser {

    public static Response Delete(Request request) {

        Response response;
        UserService userService = new UserService();
        Gson gson=new Gson();

        int userId = gson.fromJson(request.getRequestMessage(), int.class);
        System.out.println(userId);
        User userdel = userService.findEntity(userId);
        if(userdel==null) {
            response=new Response(ResponseStatus.ERROR, "Все сломалось!","");
            return response;
        }
        try {
            userService.deleteEntity(userdel);
        }catch (Exception e) {
            e.printStackTrace();
            response=new Response(ResponseStatus.ERROR, "Все сломалось!","");
            return response;
        }

        response = new Response(ResponseStatus.OK, "Готово!","");
        return response;
    }
}
