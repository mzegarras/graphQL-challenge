package net.csonic.graphqlchallenge.datasource;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {

    @Bean
    Faker faker(){
        return new Faker();
    }


}
