package com.example.InstallmentSystem.core.usercase.customer;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.gateway.CustomerGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FindAllCustomerUseCaseTest {

    @InjectMocks
    private FindAllCustomerUseCase underTest;

    @Mock
    private CustomerGateway customerGateway;

    @Test
    @DisplayName("When method findAll process, then should return a Page of Customer Successfully")
    void testReturnFindAll() {
        //given
        var pageable = PageRequest.of(1,1);
        Page<Customer> page = new PageImpl<>(Instancio.createList(Customer.class));
        given(customerGateway.findAll(pageable)).willReturn(page);

        //when
        var result = underTest.execute(pageable);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(page);

    }
}
