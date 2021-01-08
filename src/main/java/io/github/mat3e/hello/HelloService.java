package io.github.mat3e.hello;

import io.github.mat3e.lang.Language;
import io.github.mat3e.lang.LanguageRepo;

import java.util.Optional;

public class HelloService {
    public static String       defaultString   ="world";
    public static Language     defaultLanguage =new Language(1,"Hello","en");
    private       LanguageRepo languageRepo;
    HelloService(){this(new LanguageRepo());}
   public HelloService(LanguageRepo languageRepo) {
        this.languageRepo=languageRepo;
    }


    public String doGreeting(String name,String lang){
        Integer langId;

        try{
            langId=Optional.ofNullable(lang).map(Integer::valueOf).orElse(defaultLanguage.getId());
        }catch (NumberFormatException e){
            langId=defaultLanguage.getId();
        }
        var welcomeMsg=languageRepo.findById(langId).orElse(defaultLanguage).getMSG();
        var text= Optional.ofNullable(name).orElse(defaultString);
        return welcomeMsg+ " " + text;
    }
     boolean addLanguagesToDB(String MSG,String code){ return languageRepo.addNewLanguageToDB(MSG,code);}
}