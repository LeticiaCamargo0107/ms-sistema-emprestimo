package com.example.InstallmentSystem.entrypoint.controller;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstallmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstallmentSystem.core.usercase.contract.CreateContractUseCase;
import com.example.InstallmentSystem.core.usercase.contract.DeleteContractUseCase;
import com.example.InstallmentSystem.core.usercase.contract.FindAllContractUseCase;
import com.example.InstallmentSystem.core.usercase.contract.GetByIdContractUseCase;
import com.example.InstallmentSystem.core.usercase.contract.UpdateContractUseCase;
import com.example.InstallmentSystem.entrypoint.dto.ContractDTO;
import com.example.InstallmentSystem.entrypoint.mapper.ContractMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class ContractControllerTest {

    @InjectMocks
    private ContractController underTest;

    @Mock
    private GetByIdContractUseCase getByIdContractUseCase;

    @Mock
    private FindAllContractUseCase findAllContractUseCase;

    @Mock
    private CreateContractUseCase createContractUseCase;

    @Mock
    private DeleteContractUseCase deleteContractUseCase;

    @Mock
    private UpdateContractUseCase updateContractUseCase;

    @Mock
    private ContractMapper contractMapper;

    @Test
    @DisplayName("test Return Get By Id Contract in ContractController")
    void testReturnGetById() throws ContractNotFoundException {
        //given
        var id = "lala";
        var contract = Instancio.of(Contract.class).create();
        given(getByIdContractUseCase.execute(id)).willReturn(contract);

        //when
        var result = underTest.getById(id);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(contract);
    }

    @Test
    @DisplayName("test Return Find All Contract in ContractController")
    void findAll() {
        //given
        var pageable = PageRequest.of(0,1);
        //when/then
        assertThatCode(() -> underTest.findAll(pageable)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("test Return method create Contract in ContractController")
    void create() throws ContractPeriodZeroException, ContractRequestAmountZeroException {
        //given
        var contractDTO = Instancio.create(ContractDTO.class);
        var contract = Instancio.create(Contract.class);
        given(contractMapper.toDomain(contractDTO)).willReturn(contract);
        given(createContractUseCase.execute(contract)).willReturn(contract);

        //when
        var result = underTest.create(contractDTO);

        //then
        then(contractMapper).should().toDomain(contractDTO);
        then(createContractUseCase).should().execute(contract);
        assertThat(result).
                isNotNull()
                .isEqualTo(contract);
    }

    @Test
    @DisplayName("test Return delete By Id Contract in ContractController")
    void testReturnDeleteById() {
        //given
        var id = "lala";
        //when/then
        assertThatCode(() -> underTest.deleteById(id)).doesNotThrowAnyException();
    }


    @Test
    @DisplayName("test Return update By Id Contract in ContractController")
    void testReturnUpdate() throws ContractRequestAmountZeroException, ContractNotFoundException, ContractPeriodZeroException {
        //given
        var contractDTO = Instancio.create(ContractDTO.class);
        var contract = Instancio.create(Contract.class);
        given(contractMapper.toDomain(contractDTO)).willReturn(contract);
        given(updateContractUseCase.execute(contract.getId(), contract)).willReturn(contract);

        //when
        var result = underTest.update(contract.getId(), contractDTO);

        //then
        assertThat(result).
                isNotNull()
                .isEqualTo(contract);
    }
}