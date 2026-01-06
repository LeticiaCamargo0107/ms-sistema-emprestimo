package com.example.InstallmentSystem.dataprovider.adapter;

import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.dataprovider.client.ViaCepClient;
import com.example.InstallmentSystem.dataprovider.dto.ViaCepResponse;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class AddressAdapter implements ViaCepClient {

    private final ViaCepClient client;

    @Retry(name = "ViaCep", fallbackMethod = "getAddressByZipcodeFallback")
    public ViaCepResponse getAddressByZipcode(String zipcode) {
        return client.getAddressByZipcode(zipcode);
    }

    public void getAddressByZipcodeFallback(String zipcode) throws CustomerAddressNotFoundException {
        log.error("zipcode not found");
        throw new CustomerAddressNotFoundException(zipcode);
    }
}
