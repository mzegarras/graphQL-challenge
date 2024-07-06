package net.csonic.graphqlchallenge.datasource.fake;

import jakarta.annotation.PostConstruct;
import net.csonic.graphqlchallenge.codegen.types.*;
import net.datafaker.Faker;
import net.datafaker.providers.base.Gender;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeCustomerDataSource {

    private final Faker faker;

    public FakeCustomerDataSource(Faker faker) {
        this.faker = faker;
    }
    public static final List<Customer> CUSTOMER_LIST = new ArrayList<>();

    @PostConstruct
    private void postConstruct(){
        for (int i = 0; i < 20; i++) {

            var phones = new ArrayList<Phone>();

            for (int j = 0; j < ThreadLocalRandom.current().nextInt(1, 4); j++) {
                var address = Phone.newBuilder()
                        .id(faker.idNumber().valid())
                        .phone(faker.phoneNumber().cellPhone())
                        .build();

                phones.add(address);
            }


            var customer = Customer.newBuilder()
                    .id(faker.idNumber().ssnValid())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .gender(TypesGender.values()[ThreadLocalRandom.current().nextInt(TypesGender.values().length)])
                    .address(faker.address().fullAddress())
                    .birthDate(faker.date().birthday().toInstant().atOffset(ZoneOffset.ofHours(-5)))
                    .document(DocumentType.newBuilder()
                            .type(TypesDocument.values()[ThreadLocalRandom.current().nextInt(TypesDocument.values().length)])
                            .number(faker.numerify("########"))
                            .build())
                    .email(faker.address().mailBox())
                    .phones(phones)
                    .build();


            CUSTOMER_LIST.add(customer);
        }
    }
}
