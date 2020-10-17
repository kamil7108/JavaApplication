package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    LanguageRepo mockRepo=new LanguageRepo(){
        @Override
        Optional<Language> findById(Integer id) {
            return Optional.of(new Language(1,"Hello","en"));
        }
    };

    private static final String welcome="Hello";

    @Test
    public void test_of_null_from_doGreeting_returnsGreatingWithFallBack()  {
        var SUT=new HelloService(mockRepo);
        //given+when
        var respons=SUT.doGreeting(null,"-1");
        //then
        assertEquals( welcome+" "+HelloService.defaultString,respons);
    }
    @Test
    public void test_tekst_form_doGreeting(){
        HelloService SUT = new HelloService(mockRepo);
        var name ="test";
        //given+when
        var respons=SUT.doGreeting(name,"-1");
        //then
        assertEquals(welcome+" "+name,respons);
    }
    @Test
    public void test_of_nonnumericalID_returnsGreatingWithFallBack()  {
        var fallbackwelcome="Hola";
        var SUT=new HelloService(new LanguageRepo(){
            @Override
            Optional<Language> findById(Integer id) {
                if(id.equals(HelloService.defaultLanguage.getId())){return Optional.of(new Language(null,fallbackwelcome,null));}
                else return Optional.empty();
            }
        });
        //given+when
        var respons=SUT.doGreeting(null,"abc");
        //then
        assertEquals( fallbackwelcome+" "+HelloService.defaultString,respons);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang(){
        var SUT=new HelloService(new LanguageRepo(){
            @Override
            Optional<Language> findById(Integer id) {
                 return Optional.empty();
            }
        });

        //given+when
        var respons=SUT.doGreeting(null,"-1");
        //then
        assertEquals(HelloService.defaultLanguage.getMSG()+" "+HelloService.defaultString,respons);
    }
}