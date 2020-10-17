package io.github.mat3e;

import java.util.Optional;

class HelloService {
    static String defaultString="world";
    static Language defaultLanguage=new Language(1,"Hello","en");
    private LanguageRepo languageRepo;
    HelloService(){this(new LanguageRepo());}
    HelloService(LanguageRepo languageRepo) {
        this.languageRepo=languageRepo;
    }


    String doGreeting(String name,String lang){
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