package io.github.mat3e.lang;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lang", urlPatterns = {"/api/langs"})
public class LangServlet extends HttpServlet {

    private final Logger       logger= LoggerFactory.getLogger(LangServlet.class);
    private final LanguageRepo languageRepo;
    private final ObjectMapper mapper;

    /**
     * This constructor is needed for Jetty
     */
    @SuppressWarnings("unused")
    public LangServlet()
    {this(new ObjectMapper(),new LanguageRepo());}

    LangServlet(ObjectMapper mapper,LanguageRepo languageRepo)
    {
        this.mapper=mapper;
    this.languageRepo=languageRepo;}

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        logger.info("Got Request with parameters "+ req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(),languageRepo.findAll());
    }


}
