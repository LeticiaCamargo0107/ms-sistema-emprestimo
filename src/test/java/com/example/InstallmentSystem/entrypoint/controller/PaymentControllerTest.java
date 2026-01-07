package com.example.InstallmentSystem.entrypoint.controller;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.domain.ProcessPaymentFacade;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstallmentSystem.core.usercase.payment.CreatePaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.DeleteByIdPaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.FindAllPaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.GetByIdPaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.UpdatePaymentUseCase;
import com.example.InstallmentSystem.entrypoint.dto.PaymentDTO;
import com.example.InstallmentSystem.entrypoint.mapper.PaymentMapper;
import jakarta.validation.Valid;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController underTest;

    @Mock
    private CreatePaymentUseCase createPaymentUseCase;

    @Mock
    private DeleteByIdPaymentUseCase deleteByIdPaymentUseCase;

    @Mock
    private UpdatePaymentUseCase updatePaymentUseCase;

    @Mock
    private GetByIdPaymentUseCase getByIdPaymentUseCase;

    @Mock
    private FindAllPaymentUseCase findAllPaymentUseCase;

    @Mock
    private ProcessPaymentFacade processPaymentFacade;

    @Mock
    private PaymentMapper paymentMapper;


    @Test
    void testReturnGetById() throws ContractNotFoundException, PaymentNotFoundException {
        //given
        var id = "lala";
        var payment = Instancio.of(Payment.class).create();
        given(getByIdPaymentUseCase.execute(id)).willReturn(payment);

        //when
        var result = underTest.getById(id);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(payment);
    }

    @Test
    void findAll() {
        //given
        var pageable = Instancio.of(Pageable.class).create();
        //when
        var result = underTest.findAll(pageable);
        //then
        assertThat(result)
                .isNotNull();
    }

    @Test
    void create() throws PaymentMethodNotFoundException, PaymentAmountZeroException {
        //given
        var paymentDTO = Instancio.create(PaymentDTO.class);
        var payment = Instancio.create(Payment.class);
        given(paymentMapper.toDomain(paymentDTO)).willReturn(payment);
        given(processPaymentFacade.orchestrator(payment)).willReturn(payment);

        //when
        var result = underTest.create(paymentDTO);

        //then
        then(paymentMapper).should().toDomain(paymentDTO);
        then(processPaymentFacade).should().orchestrator(payment);
        assertThat(result).
                isNotNull()
                .isEqualTo(payment);
    }

    @Test
    void deleteById() {
        //given
        var id = "lala";
        //when
        var result = catchThrowable(() -> underTest.deleteById(id));
        //then
        assertThat(result);
    }

    @PutMapping("/{id}")
    public Payment update(@PathVariable String id, @RequestBody @Valid PaymentDTO paymentDTO) throws PaymentNotFoundException, PaymentAmountZeroException {

        var payment = paymentMapper.toDomain(paymentDTO);
        return updatePaymentUseCase.execute(id, payment);
    }

}