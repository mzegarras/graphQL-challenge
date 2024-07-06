package net.csonic.graphqlchallenge.datasource.fake;

import jakarta.annotation.PostConstruct;
import net.csonic.graphqlchallenge.codegen.types.Address;
import net.csonic.graphqlchallenge.codegen.types.Author;
import net.csonic.graphqlchallenge.codegen.types.Book;
import net.csonic.graphqlchallenge.codegen.types.ReleaseHistory;
import net.datafaker.Faker;

import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeBookDataSource {

    private final Faker faker;

    public FakeBookDataSource(Faker faker) {
        this.faker = faker;
    }
    public static final List<Book> BOOK_LIST = new ArrayList<>();

    @PostConstruct
    private void postConstruct(){

            for (int i = 0; i < 20; i++) {
                var addresses = new ArrayList<Address>();
                var author = Author.newBuilder().addresses(addresses)
                        .name(faker.book().author())
                        .originCountry(faker.country().name())
                        .build();
                var released = ReleaseHistory.newBuilder()
                        .printedEdition(faker.bool().bool())
                        .releasedCountry(faker.country().name())
                        .year(faker.number().numberBetween(2019, 2021))
                        .build();
                var book = Book.newBuilder().author(author)
                        .publisher(faker.book().publisher())
                        .title(faker.book().title())
                        .released(released)
                        .build();

                for (int j = 0; j < ThreadLocalRandom.current().nextInt(1, 3); j++) {
                    var address = Address.newBuilder()
                            .country(faker.address().country())
                            .city(faker.address().cityName())
                            .country(faker.address().country())
                            .street(faker.address().streetAddress())
                            .zipCode(faker.address().zipCode())
                            .build();

                    addresses.add(address);
                }

                BOOK_LIST.add(book);
            }
    }



}
