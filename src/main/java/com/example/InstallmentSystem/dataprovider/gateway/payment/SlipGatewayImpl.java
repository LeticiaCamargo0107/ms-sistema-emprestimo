package com.example.InstallmentSystem.dataprovider.gateway.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstallmentSystem.core.gateway.PaymentMethodGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SlipGatewayImpl implements PaymentMethodGateway {

    @Override
    public void process(Payment payment) {
        log.info("process payment method slip");
    }

    @Override
    public boolean supports(PaymentMethod method) {
        return method == PaymentMethod.SLIP;
    }
}
