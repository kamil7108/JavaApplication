package io.github.mat3e.lang;


import io.github.mat3e.HibernateUtil;

import java.util.List;
import java.util.Optional;


public class LanguageRepo {

   public Optional<Language> findById(Integer id){
        var session= HibernateUtil.getSessionFactory().openSession();
        var transaction=session.beginTransaction();
        var result=session.get(Language.class,id);
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }

     public boolean  addNewLanguageToDB(String MSG,String code){
        var session= HibernateUtil.getSessionFactory().openSession();
        var transaction=session.beginTransaction();
        Language newLang=new Language();
        newLang.setCode(code);
        newLang.setMSG(MSG);
        session.save(newLang);
        try{
        transaction.commit();
        session.close();
        return true;
        }catch (Exception e) {
            session.close();
            return false;
        }
    }

    public List<Language> findAll(){
        var session= HibernateUtil.getSessionFactory().openSession();
        var transaction=session.beginTransaction();

        var result=session.createQuery("from Language",Language.class).list();

        transaction.commit();
        session.close();
        return result;

    }
}
