package ServerLogic;

import RequestAnswers.*;
import Service.PersonService;
import Service.UserService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket clientSocket;
    private Request request;
    private Response response;
    private Gson gson;
    private BufferedReader in;
    private PrintWriter out;

    public Handler(Socket clientSocket) throws IOException {
        response = new Response();
        request = new Request();
        this.clientSocket = clientSocket;
        gson = new Gson();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                String message = in.readLine();

                request = gson.fromJson(message, Request.class);
                System.out.println(gson.toJson(request.getRequestType()));

                switch (request.getRequestType()) {

                    case REGISTER :{
                        response=Register.registration(request);
                        break;
                    }
                    case LOGIN:{
                        response= Login.login(request);
                        break;
                    }
                    case GET_ROLE:{

                        response= GetRole.RoleGetting(request);
                        break;
                    }
                    case GET_SEX:{
                        response= GetSex.SexGetting(request);
                        break;
                    }case GET_ALL_USER:{
                        response= GetAllUsers.Get_users(request);
                        break;
                    }case DELETE_USER:{
                        response=DeleteUser.Delete(request);
                        break;
                    }case FIND_USER_BY_ANYTHING:{
                        response=FinUserByAnything.Find(request);
                        break;
                    } case EDIT_USER:{
                        response=EditUser.Edit(request);
                        break;
                    }case ADD_PRODUCT:{
                        response=AddProduct.AddProd(request);
                        break;
                    } case FIND_PRODUCT_BY_ANYTHING:{
                        response=SearchProduct.SearchProd(request);
                        break;
                    }case VIEW_ALL_PRODUCTS:{
                        response=ViewProduct.View(request);
                        break;
                    }case DELETE_PRODUCT:{
                        response=DeleteProduct.deleteProduct(request);
                        break;
                    }case EDIT_PRODUCT:{
                        response=EditProduct.Edit(request);
                        break;
                    } case FIND_CART:{
                        response=FindCart.Find(request);
                        break;
                    }case ADD_CART_ITEM:{
                        response=AddToCart.Add(request);
                        break;
                    } case VIEW_ALL_PRODUCTS_IN_CART:{
                        response=ViewAllProductsInCart.View(request);
                        break;
                    }case INCREASE_AMOUNT_ITEM:{
                        response=IncreaseAmountItem.Edit(request);
                        break;
                    }case CREATE_ORDER:{
                        response=CreateOrder.Create(request);
                        break;
                    }case CREATE_ORDER_ITEMS:{
                        response=CreateOrderItems.Create(request);
                        break;
                    } case DELETE_FROM_CART:{
                        response=DeleteFromCart.Delete(request);
                        break;
                    }case UPDATE_ORDER:{
                        response=UpdateOrder.Update(request);
                        break;
                    }case VIEW_ALL_ORDERS:{
                        response=ViewAllOrders.View(request);
                        break;
                    } case GET_ALL_ORDER_ITEMS:{
                        response=GetAllOrderItems.Get_users(request);
                        break;
                    }
                    case CREATE_REVIEW:{
                        response=ReviewCreate.Create(request);
                        break;
                    }
                    case CHECK_REVIEW:{
                        response=ReviewCheck.View(request);
                        break;
                    }case CHECK_ALL_REVIEWS:{
                        response=ReviewCheckAll.View(request);
                        break;
                    }
                }
                out.println(gson.toJson(response));
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " закрыл соединение.");
            try {

                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
