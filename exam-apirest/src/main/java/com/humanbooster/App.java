package com.humanbooster;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.humanbooster.apirest.ApiApplication;
import com.humanbooster.dao.TaskDao;
import com.humanbooster.model.Task;

public class App {
    public static void main(String[] args) {
        // STARTS & CONNECTS TO DB
        SessionFactory sessionFactory = runDB();
        
        TaskDao taskDao = new TaskDao(sessionFactory);
        Task task0 = new Task("First Task", "Testing Task");
        taskDao.creer(task0);        
        // RUN SERVLET
        try {
            runServlet();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // CLEAN UP DB
        for(Task t : taskDao.tout()) taskDao.supprimer(t.getId());

    }

    private static SessionFactory runDB(){
        System.out.println("Démarrage de l'application");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
        Metadata metadata = new MetadataSources(registry).buildMetadata();
        System.out.println("Connexion réussie !");
        return metadata.buildSessionFactory();
    }

    private static void runServlet() throws Exception {
        ResourceConfig config = new ApiApplication();
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servlet, "/*");
        server.start();
        server.join();
    }
}