package lesson_4.util;


import lesson_4.model.Post;
import lesson_4.model.PostComment;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Util {

    private static final String PASSWORD_KEY = "mypassword";
    private static final String USERNAME_KEY = "myuser";
    private static final String URL = "jdbc:postgresql://localhost:5432/mydatabase";
    private static final String DRIVER_NAME = "org.postgresql.Driver";


    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        configuration.setProperty(Environment.DRIVER, DRIVER_NAME);
        configuration.setProperty(Environment.URL, URL);
        configuration.setProperty(Environment.USER, USERNAME_KEY);
        configuration.setProperty(Environment.PASS, PASSWORD_KEY);
        configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");


        configuration.setProperty(Environment.SHOW_SQL, "false");
        configuration.setProperty(Environment.FORMAT_SQL, "true");
        configuration.setProperty(Environment.HBM2DDL_AUTO, "update");
        return configuration;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = getConfiguration();

        configuration.addAnnotatedClass(Post.class);
        configuration.addAnnotatedClass(PostComment.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
