package io.github.mat3e;


import java.util.Optional;


class LanguageRepo {

    Optional<Language> findById(Integer id){
        var session= HibernateUtil.getSessionFactory().openSession();
        var transaction=session.beginTransaction();
        var result=session.get(Language.class,id);
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
      boolean  addNewLanguageToDB(String MSG,String code){
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
        }catch (Exception e){
            session.close();
            return false;
        }


    }
}
