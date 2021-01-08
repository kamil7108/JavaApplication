package io.github.mat3e.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Todo", urlPatterns = {"/api/todos/*"})
public class TodoServlet extends HttpServlet {

    private final Logger       logger = LoggerFactory.getLogger(io.github.mat3e.todo.Todo.class);
    private final TodoRepository todoRepository;
    private final ObjectMapper mapper;

    /**
     * This constructor is needed for Jetty
     */
    @SuppressWarnings("unused")
    public TodoServlet()
    {this(new ObjectMapper(),new TodoRepository());}

    TodoServlet(ObjectMapper mapper,TodoRepository todoRepository)
    {
        this.mapper=mapper;
        this.todoRepository=todoRepository;}

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Got Request with parameters "+ req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(),todoRepository.findAll());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var path = req.getPathInfo();
        try{
            var result = todoRepository.toggleTodo(Integer.parseInt(path.substring(1)));
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(),result);
        }
        catch (Exception e){
            logger.warn(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var result = mapper.readValue(req.getInputStream(),Todo.class);
        result.setDone(false);
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(),todoRepository.addTodo(result));
    }
}
