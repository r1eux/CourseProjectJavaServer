//package RequestAnswers;
//
//import Entities.Cart;
//import Entities.Product;
//import Entities.User;
//import ServerLogic.Request;
//import ServerLogic.Response;
//import Service.*;
//import com.google.gson.Gson;
//import Enum.ResponseStatus;
//
//public class AddProduct {
//
//    public static Response AddProd(Request request){
//        Gson gson=new Gson();
//        Response response;
//        ProductService productService=new ProductService();
//        ColorService colorService=new ColorService();
//        ProducerService producerService=new ProducerService();
//
//        Product product = gson.fromJson(request.getRequestMessage(), Product.class);
//        productService = new ProductService();
//        if (productService.findAllEntities().stream().noneMatch(x -> x.getProdName().toLowerCase().equals(product.getProdName().toLowerCase()))) {
//                producerService.saveEntity(product.getProducer());
//                colorService.saveEntity(product.getColor());
//                productService.saveEntity(product);
//                Product backProduct=productService.findEntity(product.getProductId())
//;//            product.getProducer().getSex();
////            Cart cart=new Cart();
////
////            addressService.saveEntity(user.getPerson().getAddress());
////            personService.saveEntity(user.getPerson());
////            user.setSalt("2");
////            userService.saveEntity(user);
////            cart.setUser(user);
////            cartService.saveEntity(cart);
////            User backUser = userService.findEntity(user.getUserId());
//            response = new Response(ResponseStatus.OK, "Регистрация прошла успешно", gson.toJson(backProduct));
//        }else{
//            response = new Response(ResponseStatus.ERROR, "Товар с таким названием уже существует", "");
//
//        }
//        return response;
//
//
//
//    }
//}
package RequestAnswers;

import Entities.*;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.*;
import com.google.gson.Gson;
import Enum.ResponseStatus;

public class AddProduct {

    public static Response AddProd(Request request) {
        Gson gson = new Gson();
        Response response;
        ProductService productService = new ProductService();
        ColorService colorService = new ColorService();
        ProducerService producerService = new ProducerService();
        CategoryService categoryService = new CategoryService();

        Product product = gson.fromJson(request.getRequestMessage(), Product.class);
        System.out.println(gson.toJson(product));
        if (productService.findAllEntities().stream().noneMatch(x -> x.getProdName().equalsIgnoreCase(product.getProdName()))) {
            // Проверка и добавление цвета
            Color colorFromDB = colorService.findAllEntities().stream()
                    .filter(c -> c.getColor().equalsIgnoreCase(product.getColor().getColor()))
                    .findFirst()
                    .orElse(null);
            if (colorFromDB == null) {
                colorService.saveEntity(product.getColor());
            } else {
                product.setColor(colorFromDB);
            }

            // Проверка и добавление производителя
            Producer producerFromDB = producerService.findAllEntities().stream()
                    .filter(p -> p.getProducer().equalsIgnoreCase(product.getProducer().getProducer()))
                    .findFirst()
                    .orElse(null);
            if (producerFromDB == null) {
                producerService.saveEntity(product.getProducer());
            } else {
                product.setProducer(producerFromDB);
            }

            Category categoryFromDB = categoryService.findAllEntities().stream()
                    .filter(p -> p.getCategoryName().equalsIgnoreCase(product.getCategory().getCategoryName()))
                    .findFirst()
                    .orElse(null);
            if (categoryFromDB == null) {
                categoryService.saveEntity(product.getCategory());
            } else {
                product.setCategory(categoryFromDB);
            }

            // Добавление товара
            productService.saveEntity(product);

            Product backProduct = productService.findEntity(product.getProductId());
            response = new Response(ResponseStatus.OK, "Товар успешно добавлен", gson.toJson(backProduct));
        } else {
            response = new Response(ResponseStatus.ERROR, "Товар с таким названием уже существует", "");
        }

        return response;
    }
}
