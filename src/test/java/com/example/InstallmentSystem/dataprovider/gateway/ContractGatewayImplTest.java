package com.example.InstallmentSystem.dataprovider.gateway;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.dataprovider.entity.ContractEntity;
import com.example.InstallmentSystem.dataprovider.mapper.ContractEntityMapper;
import com.example.InstallmentSystem.dataprovider.repository.ContractRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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
    @DisplayName("test Return method save Contract in ContractGatewayImpl")
    void testMethodSave() {
        //given
        var contract = Instancio.of(Contract.class).create();
        var entity = Instancio.of(ContractEntity.class).create();
        given(contractRepository.save(entity)).willReturn(entity);
        given(contractMapper.toEntity(contract)).willReturn(entity);
        given(contractMapper.toDomain(entity)).willReturn(contract);

        //when
        var result = underTest.save(contract);

        //then
        then(contractRepository).should().save(entity);
        then(contractMapper).should().toDomain(entity);
        then(contractMapper).should().toEntity(contract);
        assertThat(result)
                .isNotNull()
                .isEqualTo(contract);
    }

    @Test
    @DisplayName("test Return delete By Id Contract in ContractGatewayImpl")
    void TestDelete() {
        //given
        var id = "lalala";
        //when
        var result = catchThrowable(() -> underTest.deleteById(id));
        //then
        then(contractRepository).should().deleteById(id);
        assertThat(result);
    }

    @Test
    @DisplayName("test Return exists By Id Contract in ContractGatewayImpl")
    void TestExistById() {
        //given
        var id = "lalala";
        //when
        var result = catchThrowable(() -> underTest.existById(id));
        //then
        then(contractRepository).should().existsById(id);
        assertThat(result);
    }


    @Test
    @DisplayName("test Return Find By Id Contract in ContractGatewayImpl")
    void testReturnFindByIdIsAContract() {
        //given
        var contractEntity = Instancio.of(ContractEntity.class).create();
        var contract = Instancio.of(Contract.class).create();
        given(contractRepository.findById(contractEntity.getId())).willReturn(Optional.of(contractEntity));
        given(contractMapper.toDomain(contractEntity)).willReturn(contract);

        //when
        var result = underTest.findById(contractEntity.getId());

        //then
        then(contractRepository).should().findById(contractEntity.getId());
        then(contractMapper).should().toDomain(contractEntity);
        assertThat(result)
                .isNotNull()
                .isEqualTo(contract);
    }

    @Test
    @DisplayName("test Return Find By Id Is Null in ContractGatewayImpl")
    void testReturnFindByIdIsNull() {
        //given
        var contractEntity = Instancio.of(ContractEntity.class).create();
        given(contractRepository.findById(contractEntity.getId())).willReturn(Optional.empty());

        //when
        var result = underTest.findById(contractEntity.getId());

        //then
        then(contractRepository).should().findById(contractEntity.getId());
        assertThat(result)
                .isNull();
    }

}
