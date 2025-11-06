package com.fitlife;

import com.fitlife.dao.DatabaseManager; // Import our DatabaseManager

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * This is a ServletContextListener. This class runs ONE TIME when the web application starts up. We will use it to find the *real* path of our database and save it in the DatabaseManager.
 */
@WebListener // This annotation tells Tomcat to run this class on startup
public class AppLifecycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // This method runs when the app starts.

        //get the ServletContext
        javax.servlet.ServletContext context = sce.getServletContext();

        //get the "real, absolute path" to our database file
        // This will be C:\apache-tomcat-9\webapps\fitlife\fitlife.db 
        String dbPath = context.getRealPath("/fitlife.db");

        // Save this path in our DatabaseManager for all our DAOs to use.
        DatabaseManager.setDatabasePath(dbPath);

        System.out.println("-------------------------------------------------");
        System.out.println("DATABASE PATH SET TO: " + dbPath);
        System.out.println("-------------------------------------------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // This method runs when the app shuts down. We don't need it.
    }
}