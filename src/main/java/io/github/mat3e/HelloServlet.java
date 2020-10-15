package io.github.mat3e;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Hello",urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private final Logger logger= LoggerFactory.getLogger(HelloServlet.class);
    private final String NAME_PARM="name";
    private HelloService service;

    /**
     * This constructor is needed for Jetty
     */
    @SuppressWarnings("unused")
    public HelloServlet(){this(new HelloService());}

    HelloServlet(HelloService service){this.service=service;}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        logger.info("Got Request with parameters "+req.getParameterMap());
        resp.getWriter().write(service.doGreeting(req.getParameter(NAME_PARM)));
    }


}
