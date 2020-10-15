package io.github.mat3e;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class LanguageRepo {
    private List<Language> languageList;
    LanguageRepo(){
        languageList=new ArrayList<>();
        languageList.add(new Language(1L,"Hello","en"));
        languageList.add(new Language(2L,"Czesc","pl"));
    }
    Optional<Language> findById(Long id){
        return languageList.stream().
                filter(l -> l.getId().equals(id)).
                findFirst();
    }
}
