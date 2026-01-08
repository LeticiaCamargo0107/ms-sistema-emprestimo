package com.example.InstallmentSystem.dataprovider.gateway;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.dataprovider.entity.PaymentEntity;
import com.example.InstallmentSystem.dataprovider.mapper.PaymentEntityMapper;
import com.example.InstallmentSystem.dataprovider.repository.PaymentRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
public class PaymentGatewayImplTest {

    @InjectMocks
    private PaymentGatewayImpl underTest;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentEntityMapper paymentMapper;

    @Test
    @DisplayName("test Return method save Payment in PaymentGatewayImpl")

    void testMethodSave() throws CustomerAddressNotFoundException {
        //given
        var payment = Instancio.of(Payment.class).create();
        var entity = Instancio.of(PaymentEntity.class).create();
        given(paymentRepository.save(entity)).willReturn(entity);
        given(paymentMapper.toEntity(payment)).willReturn(entity);
        given(paymentMapper.toDomain(entity)).willReturn(payment);

        //when
        var result = underTest.save(payment);

        //then
        then(paymentRepository).should().save(entity);
        then(paymentMapper).should().toDomain(entity);
        then(paymentMapper).should().toEntity(payment);
        assertThat(result)
                .isNotNull()
                .isEqualTo(payment);
    }


    @Test
    @DisplayName("test Return delete By Id Payment in PaymentGatewayImpl")
    void TestDelete() {
        //given
        var id = "lalala";
        //when
        var result = catchThrowable(() -> underTest.deleteById(id));
        //then
        then(paymentRepository).should().deleteById(id);
        assertThat(result);
    }

    @Test
    @DisplayName("test Return exists By Id Payment in PaymentGatewayImpl")
    void TestExistById() {
        //given
        var id = "lalala";
        //when
        var result = catchThrowable(() -> underTest.existById(id));
        //then
        then(paymentRepository).should().existsById(id);
        assertThat(result);
    }

    @Test
    @DisplayName("test Return Find By Id Payment in PaymentGatewayImpl")

    void testReturnFindByIdIsAPayment() {
        //given
        var paymentEntity = Instancio.of(PaymentEntity.class).create();
        var payment = Instancio.of(Payment.class).create();
        given(paymentRepository.findById(paymentEntity.getId())).willReturn(Optional.of(paymentEntity));
        given(paymentMapper.toDomain(paymentEntity)).willReturn(payment);

        //when
        var result = underTest.findById(paymentEntity.getId());

        //then
        then(paymentRepository).should().findById(paymentEntity.getId());
        then(paymentMapper).should().toDomain(paymentEntity);
        assertThat(result)
                .isNotNull()
                .isEqualTo(payment);
    }

    @Test
    @DisplayName("test Return Find By Id Is Null in PaymentGatewayImpl")
    void testReturnFindByIdIsNull() {
        //given
        var paymentEntity = Instancio.of(PaymentEntity.class).create();
        var payment = Instancio.of(Payment.class).create();
        given(paymentRepository.findById(paymentEntity.getId())).willReturn(Optional.empty());

        //when
        var result = underTest.findById(paymentEntity.getId());

        //then
        then(paymentRepository).should().findById(paymentEntity.getId());
        assertThat(result)
                .isNull();
    }


//    @Test
//    void testReturnFindAll(Pageable pageable) {
//        Page<PaymentEntity> entities = paymentRepository.findAll(pageable);
//        List<Payment> contracts = entities.map(paymentMapper::toDomain).getContent();
//        return new PageImpl<>(contracts, pageable, entities.getTotalElements());
//    }

}
