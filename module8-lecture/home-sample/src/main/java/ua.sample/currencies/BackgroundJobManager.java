package ua.sample.currencies;

import org.json.JSONObject;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackgroundJobManager implements ServletContextListener {

    private ScheduledExecutorService scheduled;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduled = Executors.newSingleThreadScheduledExecutor();
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    sce.getServletContext().setAttribute("list", Application.getOrganizations(new JSONObject(Application.getCurrencyJSONPro())));
                    sce.getServletContext().setAttribute( "timeStamp", LocalDateTime.now());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 10, TimeUnit.MINUTES);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
