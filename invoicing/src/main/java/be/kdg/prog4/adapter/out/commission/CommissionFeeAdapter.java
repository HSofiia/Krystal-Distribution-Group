package be.kdg.prog4.adapter.out.commission;

import be.kdg.prog4.domain.CommissionFee;
import be.kdg.prog4.domain.PONumber;
import be.kdg.prog4.port.out.LoadCommissionFeePort;
import be.kdg.prog4.port.out.SaveCommissionFeePort;
import be.kdg.prog6.common.domain.SellerId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommissionFeeAdapter implements SaveCommissionFeePort, LoadCommissionFeePort {
    private final CommissionFeeJpaEntityRepository commissionFeeJpaEntityRepository;

    public CommissionFeeAdapter(CommissionFeeJpaEntityRepository commissionFeeJpaEntityRepository) {
        this.commissionFeeJpaEntityRepository = commissionFeeJpaEntityRepository;
    }

    @Override
    public void save(CommissionFee commissionFee) {
        CommissionJpaEntity jpa = toJpa(commissionFee);
        commissionFeeJpaEntityRepository.save(jpa);
    }


    @Override
    public Optional<CommissionFee> loadByPoNumber(PONumber poNumber){
        return commissionFeeJpaEntityRepository.findByPoNumber(poNumber.value()).map(this::toDomain);
    }

    private CommissionJpaEntity toJpa(CommissionFee fee) {
        return new CommissionJpaEntity(
                fee.getId(),
                fee.getSellerId().sellerId(),
                fee.getPoNumber().value(),
                fee.getAmount()
        );
    }

    private CommissionFee toDomain(CommissionJpaEntity e) {
        return new CommissionFee(
                e.getId(),
                new SellerId(e.getSellerId()),
                new PONumber(e.getPoNumber()),
                e.getAmount()
        );
    }
}
