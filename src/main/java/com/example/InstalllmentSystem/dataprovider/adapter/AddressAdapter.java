package com.example.InstalllmentSystem.dataprovider.adapter;

import com.example.InstalllmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstalllmentSystem.dataprovider.client.ViaCepClient;
import com.example.InstalllmentSystem.dataprovider.dto.ViaCepResponse;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class AddressAdapter {

    private final ViaCepClient client;

    @Retry(name = "ViaCepClient", fallbackMethod = "getAddressByZipcodeFallback")
    public ViaCepResponse getAddressByZipcode(String zipcode) {
        return client.getAddressByZipcode(zipcode);
    }

    public ViaCepResponse getAddressByZipcodeFallback(String zipcode) throws CustomerAddressNotFoundException {
        log.error("zipcode not found or");
        throw new CustomerAddressNotFoundException(zipcode);
    }
}
