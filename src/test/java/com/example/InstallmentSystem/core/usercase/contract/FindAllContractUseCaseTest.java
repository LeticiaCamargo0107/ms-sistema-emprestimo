package com.example.InstallmentSystem.core.usercase.contract;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.gateway.ContractGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FindAllContractUseCaseTest {

    @InjectMocks
    private FindAllContractUseCase underTest;

    @Mock
    private ContractGateway contractGateway;

    @Test
    @DisplayName("When method findAll process, then should return a Page of Contract Successfully")
    void testReturnFindAll() {
        //given
        var pageable = PageRequest.of(1,1);
        var page = new PageImpl<>(Instancio.createList(Contract.class));

        given(contractGateway.findAll(pageable)).willReturn(page);

        //when
        var result = underTest.execute(pageable);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(page);

    }
}
