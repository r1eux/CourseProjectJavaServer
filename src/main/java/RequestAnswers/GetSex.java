package RequestAnswers;

import Entities.Role;
import Entities.Sex;
import Entities.User;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.RoleService;
import Service.SexService;
import com.google.gson.Gson;
import Enum.ResponseStatus;
public class GetSex {

    public static Response SexGetting(Request request){

        Gson gson=new Gson();
        Response response;
        Sex sex=new Sex();
        SexService sexService=new SexService();
        boolean sexB=gson.fromJson(request.getRequestMessage(), Boolean.class);
        if(sexB){
            sex=sexService.findEntity(11);
        }else{
            sex=sexService.findEntity(10);
        }

        if(!sex.getSexValue().isEmpty()){

            response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(sex));
        } else {
            response = new Response(ResponseStatus.ERROR, "Произошла ошибка при поиске пола", "");
        }
        return response;
    }

    }

