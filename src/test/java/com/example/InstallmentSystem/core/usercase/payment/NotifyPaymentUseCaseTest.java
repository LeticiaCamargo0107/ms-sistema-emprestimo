package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.gateway.NotifyGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class NotifyPaymentUseCaseTest {

    @InjectMocks
    private NotifyPaymentUseCase underTest;

    @Mock
    private NotifyGateway notifyGateway;

    @Test
    void testReturnExecute() {
        //Given
        var payment = Instancio.create(Payment.class);
        given(notifyGateway.createNotify(payment)).willReturn("payment made successfully");
        //When
        var result = underTest.execute(payment);

        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(payment);

        assertThat(result.getNotify())
                .isEqualTo("payment made successfully");
    }
}
