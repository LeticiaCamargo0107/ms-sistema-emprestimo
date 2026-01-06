package com.example.InstallmentSystem.dataprovider.gateway.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.gateway.NotifyGateway;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifyGatewayImpl implements NotifyGateway {

    @Override
    public String createNotify(Payment payment) {
        payment.setNotify("payment made successfully");
        return payment.getNotify();
    }
}
