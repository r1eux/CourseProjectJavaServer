package RequestAnswers;

import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.PersonService;
import Service.UserService;
import com.google.gson.Gson;
import Enum.ResponseStatus;

public class Login {

    public static Response login(Request request){
        Gson gson=new Gson();
        Response response;

        UserService userService=new UserService();

        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
        if (userService.findAllEntities().stream().anyMatch(x -> x.getLogin().toLowerCase().equals(requestUser.getLogin().toLowerCase())) && userService.findAllEntities().stream().anyMatch(x -> x.getPassword().equals(requestUser.getPassword()))) {
            User user = userService.findAllEntities().stream().filter(x -> x.getLogin().toLowerCase().equals(requestUser.getLogin().toLowerCase())).findFirst().get();
            user = userService.findEntity(user.getUserId());
            response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(user));
        } else {
            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
        }
        return response;




    }
}
