package com.example.InstallmentSystem.dataprovider.gateway;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.dataprovider.entity.ContractEntity;
import com.example.InstallmentSystem.dataprovider.mapper.ContractEntityMapper;
import com.example.InstallmentSystem.dataprovider.repository.ContractRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
public class ContractGatewayImplTest {

    @InjectMocks
    private ContractGatewayImpl underTest;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private ContractEntityMapper contractMapper;

    @Test
    void testMethodSave() {
        //given
        var contract = Instancio.of(Contract.class).create();
        var contractEntity = Instancio.of(ContractEntity.class).create();
        given(contractMapper.toEntity(contract)).willReturn(contractEntity);
        given(contractRepository.save(contractEntity)).willReturn(contractEntity);

        //when
        var result = catchThrowable(() -> underTest.save(contract));

        //then
        then(contractRepository).should().save(contractEntity);
        then(contractMapper).should().toDomain(contractEntity);
        assertThat(result)
                .isNotNull()
                .isEqualTo(contract);
    }

    public void deleteById(String id) {

        contractRepository.deleteById(id);
    }

    public boolean existById(String id) {

        return contractRepository.existsById(id);
    }

    public Contract findById(String id) {
        var entity = contractRepository.findById(id);
        return contractMapper.toDomain(entity.orElse(null));
    }


    public Page<Contract> findAll(Pageable pageable) {
        Page<ContractEntity> entities = contractRepository.findAll(pageable);
        List<Contract> contracts = entities.map(contractMapper::toDomain).getContent();
        return new PageImpl<>(contracts, pageable, entities.getTotalElements());
    }
}
