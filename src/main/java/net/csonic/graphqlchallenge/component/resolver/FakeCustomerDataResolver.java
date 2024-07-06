package net.csonic.graphqlchallenge.component.resolver;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import net.csonic.graphqlchallenge.codegen.DgsConstants;
import net.csonic.graphqlchallenge.codegen.types.Customer;
import net.csonic.graphqlchallenge.datasource.fake.FakeCustomerDataSource;
import java.util.List;


@DgsComponent
public class FakeCustomerDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.AllCustomers)
    public List<Customer> getAllCustomers() {
        return FakeCustomerDataSource.CUSTOMER_LIST;
    }
}
