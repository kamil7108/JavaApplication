package io.github.mat3e.todo;

import io.github.mat3e.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TodoRepository {

    private final Logger logger = LoggerFactory.getLogger(TodoRepository.class);
    List<Todo> findAll(){
       var sessionFactory = HibernateUtil.getSessionFactory().openSession();
       var transaction = sessionFactory.beginTransaction();
       var result=sessionFactory.createQuery("from Todo",Todo.class).list();
       transaction.commit();
       sessionFactory.close();
       return result;
    }

    Todo toggleTodo(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Todo.class,id);
        try{
        result.setDone(!result.getDone());
        transaction.commit();
        session.close();
        return result;
        }catch (Exception exception){
            logger.warn(exception.getMessage());
            transaction.rollback();
            session.close();
            return null;
        }
    }

    Todo addTodo(Todo todo){
        var session = HibernateUtil.getSessionFactory().openSession();
        var trasaction = session.beginTransaction();
        try{
            session.persist(todo);
            trasaction.commit();
        } catch (Exception e){
            logger.warn(e.getMessage());
            trasaction.rollback();
        }
        finally {
            session.close();
            return todo;
        }
    }

}
