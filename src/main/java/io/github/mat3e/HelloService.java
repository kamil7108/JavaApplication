package io.github.mat3e;

import java.util.Optional;

class HelloService {
    static String defaultString="world";
    static Language defaultLanguage=new Language(1L,"Hello","en");
    private LanguageRepo languageRepo;
    HelloService(){this(new LanguageRepo());}
    HelloService(LanguageRepo languageRepo) {
        this.languageRepo=languageRepo;
    }


    String doGreeting(String name,String lang){
        Long langId;
        try{
            langId=Optional.ofNullable(lang).map(Long::valueOf).orElse(defaultLanguage.getId());
        }catch (NumberFormatException e){
            langId=defaultLanguage.getId();
        }
        var welcomeMsg=languageRepo.findById(langId).orElse(defaultLanguage).getWelcomeMSG();
        var text= Optional.ofNullable(name).orElse(defaultString);
        return welcomeMsg+ " " + text;
    }
}