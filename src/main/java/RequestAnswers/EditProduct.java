package RequestAnswers;

import Entities.Category;
import Entities.Color;
import Entities.Producer;
import Entities.Product;
import ServerLogic.Request;
import ServerLogic.Response;
import Service.CategoryService;
import Service.ColorService;
import Service.ProducerService;
import Service.ProductService;
import com.google.gson.Gson;
import Enum.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EditProduct {

    public static Response Edit(Request request) throws IOException {
        Response response;
        ProductService productService = new ProductService();
        ColorService colorService = new ColorService();
        ProducerService producerService = new ProducerService();
        CategoryService categoryService = new CategoryService();
        Gson gson = new Gson();
        System.out.println("Полученный JSON: " + request.getRequestMessage());
        Product productFromFront = gson.fromJson(request.getRequestMessage(), Product.class);
        int id = productFromFront.getProductId();

        Product productFromDB = productService.findEntity(id);
        if (productFromDB == null) {
            return new Response(ResponseStatus.ERROR, "Товар не найден", "");
        }

        productFromDB.setProdName(productFromFront.getProdName());
        productFromDB.setPrice(productFromFront.getPrice());
        productFromDB.setImage(productFromFront.getImage());

        System.out.println("VSEMU PIZDA AAAAAAAAAAAAAAAAAAAAAAAAA"+productFromFront.getAmount());
        productFromDB.setAmount(productFromFront.getAmount());
        productFromDB.setCategory(productFromFront.getCategory());
        productFromDB.setWarranty(productFromFront.getWarranty());
        if (productFromFront.getColor() != null) {
            System.out.println("WERE IN THE COLOR IF!!!!!!!!!!!!!!!!!!!!!!!!");
            Color colorFromDB = colorService.findEntity(productFromFront.getColor().getColorId());
            if (colorFromDB == null||!(colorFromDB.getColor().equals(productFromFront.getColor().getColor()))) {
                // Если цвет не найден в базе, сохраняем новый
                System.out.println("colorFromDB == null!!!!!!!!!!!!!!!!!!!!!!!!");
                colorService.saveEntity(productFromFront.getColor());
                colorFromDB = productFromFront.getColor();

            }
            productFromDB.setColor(colorFromDB);
            System.out.println("WERE OVERWRITED OBJECT COLOR!!!!!!!!!!!!!!!!!!!!!!!!"+productFromDB.getColor());// Перезаписываем объект
        }

        if (productFromFront.getProducer() != null) {
            System.out.println("WERE IN THE PRODUCER IF!!!!!!!!!!!!!!!!!!!!!!!!");
            Producer producerFromDB = producerService.findEntity(productFromFront.getProducer().getProducerId());
            if (producerFromDB == null||!(producerFromDB.getProducer().equals(productFromFront.getProducer().getProducer()))) {
                // Если производитель не найден в базе, сохраняем нового
                System.out.println("producerFromDB == null!!!!!!!!!!!!!!!!!!!!!!!");
                producerService.saveEntity(productFromFront.getProducer());
                producerFromDB = productFromFront.getProducer();
            }
            producerFromDB.setProducer_country(productFromFront.getProducer().getProducer_country());
            productFromDB.setProducer(producerFromDB);
            System.out.println("WERE OVERWRITED OBJECT PRODUCER!!!!!!!!!!!!!!!!!!!!!!!!"+productFromDB.getProducer().getProducer_country());// Перезаписываем объект
        }

        if (productFromFront.getCategory() != null) {
            System.out.println("WERE IN THE CATEGORY IF!!!!!!!!!!!!!!!!!!!!!!!!");
            Category categoryFromDB = categoryService.findEntity(productFromFront.getCategory().getCategoryId());
            if (categoryFromDB == null||!(categoryFromDB.getCategoryName().equals(productFromFront.getCategory().getCategoryName()))) {
                // Если категория не найдена в базе, сохраняем новую
                System.out.println("categoryFromDB == null!!!!!!!!!!!!!!!!!!!!!!!");
                categoryService.saveEntity(productFromFront.getCategory());
                categoryFromDB = productFromFront.getCategory();
            }
            productFromDB.setCategory(categoryFromDB); // Перезаписываем объект
            System.out.println("WERE OVERWRITED OBJECT CATEGORY!!!!!!!!!!!!!!!!!!!!!!!!"+productFromDB.getCategory().getCategoryName());// Перезаписы
        }

        System.out.println("ША ОТПРАВЛЯТЬ БУДИМ ААААААААААААА"+gson.toJson(productFromDB));
        productService.updateEntity(productFromDB);
        return new Response(ResponseStatus.OK, "Товар успешно обновлен", "");
    }

}
