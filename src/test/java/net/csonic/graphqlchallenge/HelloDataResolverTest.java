package net.csonic.graphqlchallenge;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HelloDataResolverTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Test
    void textOneHello(){
        var graphQLQuery = """
                {
                  oneHello{
                    text
                    randomNumber
                  }
                }
                """;
        String text = dgsQueryExecutor.executeAndExtractJsonPath(graphQLQuery,"data.oneHello.text");
        Integer number = dgsQueryExecutor.executeAndExtractJsonPath(graphQLQuery,"data.oneHello.ramdomNumber");

        assertNotNull(text);
        assertNotNull(number);
    }


    @Test
    void testAllHellos() {
        var graphqlQuery = """
                {
                  allHellos {
                    text
                    randomNumber
                  }
                }
                """;

        List<String> texts = dgsQueryExecutor.executeAndExtractJsonPath(
                graphqlQuery, "data.allHellos[*].text");
        List<Integer> randomNumbers = dgsQueryExecutor.executeAndExtractJsonPath(
                graphqlQuery, "data.allHellos[*].randomNumber");

        assertNotNull(texts);
        assertFalse(texts.isEmpty());
        assertNotNull(randomNumbers);
        assertEquals(texts.size(), randomNumbers.size());
    }
}
