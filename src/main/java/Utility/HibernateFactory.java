package Utility;

import Entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateFactory {
    private static SessionFactory sessionFactory;

    private HibernateFactory() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Создаем конфигурацию Hibernate
                Configuration configuration = new Configuration();

                // Загружаем настройки из hibernate.properties
                Properties properties = new Properties();
                try (FileInputStream fis = new FileInputStream("C:\\Users\\Antony\\IdeaProjects\\CursServerTrue\\src\\main\\resources\\hibernate.properties")) {
                    properties.load(fis);  // Загружаем файл properties
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении hibernate.properties: " + e.getMessage());
                }

                // Устанавливаем параметры в конфигурацию
                configuration.setProperties(properties);

                // Добавляем аннотированные классы
                configuration.addAnnotatedClass(Sex.class)
                        .addAnnotatedClass(Address.class)
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Cart.class)
                        .addAnnotatedClass(CartItem.class)
                        .addAnnotatedClass(Color.class)
                        .addAnnotatedClass(Order.class)
                        .addAnnotatedClass(OrderItem.class)
                        .addAnnotatedClass(Buyer.class)
                        .addAnnotatedClass(Review.class)
                        .addAnnotatedClass(Category.class)
                        .addAnnotatedClass(Producer.class)
                        .addAnnotatedClass(Product.class)
                        .addAnnotatedClass(Role.class);

                // Создаем стандартный сервис реестра для Hibernate 6
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                registryBuilder.applySettings(configuration.getProperties());

                // Создаем сервис реестра
                ServiceRegistry serviceRegistry = registryBuilder.build();

                // Строим фабрику сессий
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.err.println("Ошибка при создании фабрики сессий: " + e.getMessage());
                e.printStackTrace(); // Для подробного вывода исключения
            }
        }
        return sessionFactory;
    }
}