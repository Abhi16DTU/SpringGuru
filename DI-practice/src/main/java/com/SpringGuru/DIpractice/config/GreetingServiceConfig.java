package com.SpringGuru.DIpractice.config;

import com.SpringGuru.DIpractice.datasource.FakeDataSource;
import com.SpringGuru.DIpractice.repository.EnglishGreetingRepository;
import com.SpringGuru.DIpractice.repository.EnglishGreetingRepositoryImpl;
import com.SpringGuru.DIpractice.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import pets.PetService;
import pets.PetServiceFactory;

@Configuration
@ImportResource("classpath:di-practice-config.xml")
//@PropertySource("classpath:datasource.properties")  // now springboot will pick up application.properties
public class GreetingServiceConfig {

    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Bean
    @Profile("cat")
    PetService catPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("cat");
    }

    @Bean
    @Profile({"dog", "default"})
    PetService dogPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("dog");
    }

    @Bean("i18n")
    @Profile("ES")
    i18nSpanishGreetingService i18nSpanishGreetingService() {
        return new i18nSpanishGreetingService();
    }

    @Bean      //the bean name is the method name
    @Primary
    primaryGreetingService primaryGreetingService() {
        return new primaryGreetingService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository() {
        return new EnglishGreetingRepositoryImpl();
    }

    @Bean
    @Profile({"EN", "default"})
    i18nEnglishGreetingService i18n(EnglishGreetingRepository englishGreetingRepository) {
        return new i18nEnglishGreetingService(englishGreetingRepository);
    }

    @Bean
    propertyInjectedGreetingService propertyInjectedGreetingService(){
        return new propertyInjectedGreetingService();
    }

    @Bean
    setterInjectedGreetingService setterInjectedGreetingService(){
        return new setterInjectedGreetingService();
    }

//    @Bean
//    GreetingServiceImpl greetingService(){
//        return new GreetingServiceImpl();
//    }

    @Bean
    FakeDataSource fakeDataSource(@Value("${mydb.username}") String username,
                                  @Value("${mydb.password}") String password,
                                  @Value("${mydb.url}") String url) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUser(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(url);
        return fakeDataSource;
    }



}
