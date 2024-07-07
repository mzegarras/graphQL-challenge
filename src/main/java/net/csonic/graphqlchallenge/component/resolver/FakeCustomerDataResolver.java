package net.csonic.graphqlchallenge.component.resolver;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import net.csonic.graphqlchallenge.codegen.DgsConstants;
import net.csonic.graphqlchallenge.codegen.types.Account;
import net.csonic.graphqlchallenge.codegen.types.Customer;
import net.csonic.graphqlchallenge.datasource.fake.FakeBookDataSource;
import net.csonic.graphqlchallenge.datasource.fake.FakeCustomerDataSource;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@DgsComponent
public class FakeCustomerDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.CustomersByName)
    public List<Customer> getCustomersByName(@InputArgument(name = DgsConstants.QUERY.CUSTOMERSBYNAME_INPUT_ARGUMENT.LastName)
                                              Optional<String> lastName) {


        if (lastName.isEmpty() || StringUtils.isBlank(lastName.get())) {
            return FakeCustomerDataSource.CUSTOMER_LIST;
        }
        return FakeCustomerDataSource.CUSTOMER_LIST.stream()
                .filter(b -> StringUtils.containsIgnoreCase(
                        b.getLastName(), lastName.get()
                )).collect(Collectors.toList());
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.CustomersByDocument)
    public List<Customer> getCustomersByDocument(@InputArgument(name = DgsConstants.QUERY.CUSTOMERSBYDOCUMENT_INPUT_ARGUMENT.DniNumber)
                                             Optional<String> dniNumber) {


        if (dniNumber.isEmpty() || StringUtils.isBlank(dniNumber.get())) {
            return FakeCustomerDataSource.CUSTOMER_LIST;
        }
        return FakeCustomerDataSource.CUSTOMER_LIST.stream()
                .filter(b -> StringUtils.containsIgnoreCase(
                        b.getDocument().getNumber(), dniNumber.get()
                )).collect(Collectors.toList());
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.AccountsById)
    public List<Account> getAccountsById(@InputArgument(name = DgsConstants.QUERY.ACCOUNTSBYID_INPUT_ARGUMENT.CustomerId)
                                                 Optional<String> customerId) {


        if (customerId.isEmpty() || StringUtils.isBlank(customerId.get())) {
            return null;
        }

        return FakeCustomerDataSource.CUSTOMER_LIST.stream()
                .filter(b -> StringUtils.containsIgnoreCase(b.getId(), customerId.get()
                ))
                .flatMap(p->p.getAccounts().stream())
                .collect(Collectors.toList());

    }
}
