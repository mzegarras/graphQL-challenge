package net.csonic.graphqlchallenge.component.resolver;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import net.csonic.graphqlchallenge.datasource.fake.FakeHelloDataSource;
import net.csonic.graphqlchallenge.codegen.DgsConstants;
import net.csonic.graphqlchallenge.codegen.types.Hello;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@DgsComponent
public class HelloDataResolver {

    //@DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.AllHellos)
    @DgsQuery
    public List<Hello> allHellos(){
        return FakeHelloDataSource.HELLO_LIST;
    }

    @DgsQuery
    //@DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.OneHello)
    public Hello oneHello(){
        return FakeHelloDataSource.HELLO_LIST.get(
                ThreadLocalRandom.current().nextInt(FakeHelloDataSource.HELLO_LIST.size())
        );
    }
}
