package com.develogical;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// http://192.168.40.30:3000/players/d2936180

public class WebServer extends HttpServlet {

    /**
     * Handle a GET request to the server
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("q");
        if (query != null) {
            // Log each query out to the console
            System.out.println("Query: " + query);
        }
        
        resp.setContentType("text/plain");
        resp.getWriter().println(process(query));
    }

    /**
     * Process a given query, producing a text string as a result
     */
    public String process(String query) {
        String result = "Unknown";

        if (query == null) {
            return result;
        }

        if(query.contains("which of the following numbers is the largest")){
            String[] strings = query.split(":");
            System.out.println("Length: " + strings.length);

            String[] numbers = strings[2].replaceAll(" ", "").split(",");

            return getMax(numbers).toString();
        }

        if(query.contains("plus")) {
            String[] strings = query.split(" ");
            Integer op1 = Integer.parseInt(strings[3]);
            Integer op2 = Integer.parseInt(strings[5]);
            Integer res = op1+op2;
            return res.toString();
        }


        System.out.println("Returning: " + result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        int port;
        try {
            port = Integer.valueOf(System.getenv("PORT"));
        } catch (Exception e) {
            // could not parse PORT env variable - using present port;
            port = 5000;
        }

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new WebServer()), "/*");
        server.start();
        server.join();
    }
    
    public String getMax(String[] list){
        Integer max = 0;
        
        if(list.length < 1) {
            max = -1;
        }
        else{
            max= Integer.parseInt(list[0]);
            for(int i=0; i<list.length; i++) {
                if(max < Integer.parseInt(list[i])) {
                    max = Integer.parseInt(list[i]);
                }
            }
        }
        
        return max.toString();
    }

}

