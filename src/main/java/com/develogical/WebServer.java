package com.develogical;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

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
            if(strings.length == 6) {
                Integer op1 = Integer.parseInt(strings[3]);
                Integer op2 = Integer.parseInt(strings[5]);
                Integer res = op1+op2;
                return res.toString();
            }
            else
            {
                
            }
        }

        if(query.contains("minus")) {
            String[] strings = query.split(" ");
            Integer op1 = Integer.parseInt(strings[3]);
            Integer op2 = Integer.parseInt(strings[5]);
            Integer res = op1-op2;
            return res.toString();
        }

        if(query.contains("multiplied")) {
            String[] strings = query.split(" ");
            Integer op1 = Integer.parseInt(strings[3]);
            Integer op2 = Integer.parseInt(strings[6]);
            Integer res = op1*op2;
            return res.toString();
        }

        if(query.contains("primes")) {
            String[] strings = query.split("primes:");
            String[] numbers = strings[1].replaceAll(" ", "").split(",");
            String primes = getPrimes(numbers);
            return primes;
        }


        System.out.println("Returning: " + result);
        return result;
    }

    private String getPrimes(String[] numbers) {

        Vector<Integer> primes = new Vector<Integer>();

        for (int i = 0; i < numbers.length; i++) {
            int n = Integer.parseInt(numbers[i]);

            boolean notPrime = false;
            for (int j = 2; j < n; j++) {
                
                if (n % j == 0) {
                    notPrime = true;
                    break;
                }
            }
            if(notPrime == false) {
                primes.add(new Integer(n));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<primes.size(); i++) {
            sb.append(primes.get(i));
            sb.append(", ");
        }
        
        String result = sb.toString();

        return result.substring(0, result.length() - 2);
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

