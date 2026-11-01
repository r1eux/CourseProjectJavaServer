package RequestAnswers;

import Entities.Role;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.RoleService;
import com.google.gson.Gson;
import Enum.ResponseStatus;

public class GetRole {

    public static Response RoleGetting(Request request){

        Gson gson=new Gson();
        Response response;
        Role role=new Role();
        RoleService roleService=new RoleService();
        role=roleService.findEntity(4);
        if(!role.getRoleValue().isEmpty()){
        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(role));
    } else {
        response = new Response(ResponseStatus.ERROR, "Произошла ошибка при поиске роли", "");
    }
        return response;
    }
}
