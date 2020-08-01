package com.example.springboot21;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SpringBootApplication
public class Springboot21Application {

    public static void main(String[] args) throws LifecycleException {
        SpringApplication.run(Springboot21Application.class, args);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        Context context = tomcat.addContext("/","/");

        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                PrintWriter printWriter = resp.getWriter();
                printWriter.println("<html><head><title>");
                printWriter.println("hey,tomcat");
                printWriter.println("</title></head>");
                printWriter.println("<body><h1>hello Tomcat</h1></body>");
                printWriter.println("</html>");
            }
        };
        String servletName = "helloServlet";
        tomcat.addServlet("/",servletName,servlet);
        context.addServletMappingDecoded("/hello", servletName);
        tomcat.start();
        tomcat.getServer().await();;
    }

}
