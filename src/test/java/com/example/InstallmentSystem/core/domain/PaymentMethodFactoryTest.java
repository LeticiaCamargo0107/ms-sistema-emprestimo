package com.example.InstallmentSystem.core.domain;

import com.example.InstallmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.gateway.PaymentMethodGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PaymentMethodFactoryTest {

    private PaymentMethodFactory underTest;

    @Mock
    private PaymentMethodGateway firstPaymentMethodGateway;

    @Mock
    private PaymentMethodGateway secondPaymentMethodGateway;

    @BeforeEach
    void setUp() {
        underTest = new PaymentMethodFactory(List.of(
                firstPaymentMethodGateway,
                secondPaymentMethodGateway
        ));
    }

    @Test
    @DisplayName("Test for supply method of class Payment Method Factory")
    void testSupply() throws PaymentMethodNotFoundException {
        // Given
        given(firstPaymentMethodGateway.supports(any())).willReturn(false);
        given(secondPaymentMethodGateway.supports(any())).willReturn(true);

        // When
        var result = underTest.supply(PaymentMethod.CREDIT_CARD);

        // Then
        assertThat(result).isNotNull();
    }
}





