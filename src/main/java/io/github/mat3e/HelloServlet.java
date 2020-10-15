package io.github.mat3e;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Hello",urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private final Logger logger= LoggerFactory.getLogger(HelloServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        logger.info("Got Request with parameters "+req.getParameterMap());
        var name=Optional.ofNullable(req.getParameter("name")).orElse("world");
       if(name.equals("mistrz"))
       resp.getWriter().write("Wywolanie z parametrem");
       else resp.getWriter().write("Hello "+ name);

    }
}
