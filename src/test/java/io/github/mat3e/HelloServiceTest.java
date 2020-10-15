package io.github.mat3e;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    @Test
    public void test_of_null_from_doGreeting()  {
        HelloService SUT=new HelloService();
        //given+when
        var respons=SUT.doGreeting(null);
        //then
        assertEquals("Hello "+HelloService.defaultString,respons);
    }
    @Test
    public void test_tekst_form_doGreeting(){
        HelloService SUT=new HelloService();
        var name ="test";
        //given+when
        var respons=SUT.doGreeting(name);
        //then
        assertEquals("Hello "+name,respons);
    }
}