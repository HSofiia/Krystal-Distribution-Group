//package be.kdg.prog6.landside;
//
//import be.kdg.prog6.common.event.ActivityType;
//import be.kdg.prog6.family.adapter.out.PiggyBankActivityJpaEntity;
//import be.kdg.prog6.family.adapter.out.PiggyBankJpaEntity;
//import be.kdg.prog6.family.adapter.out.PiggyBankJpaRepository;
//import be.kdg.prog6.family.adapter.out.PiggyBankMessagingPublisher;
//import be.kdg.prog6.family.port.in.GiveMoneyCommand;
//import be.kdg.prog6.family.port.in.GiveMoneyUseCase;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class GiveMoneyUseCaseImplIntegrationTest extends AbstractDatabaseTest {
//    @Autowired
//    private GiveMoneyUseCase giveMoneyUseCase;
//    @MockBean
//    private PiggyBankMessagingPublisher publisher;
//    @Autowired
//    private PiggyBankJpaRepository piggyBankJpaRepository;
//
//    @Test
//    void shouldAddActivity() {
//        // Arrange
//        piggyBankJpaRepository.save(new PiggyBankJpaEntity(TestIds.OWNER_ID.id()));
//
//        // Act
//        giveMoneyUseCase.giveMoney(new GiveMoneyCommand(TestIds.OWNER_ID, 10));
//
//        // Assert
//        final PiggyBankJpaEntity piggyBank =
//            piggyBankJpaRepository.findByOwnerIdIncludingActivities(TestIds.OWNER_ID.id());
//        assertEquals(piggyBank.getBalance(), 10);
//        final List<PiggyBankActivityJpaEntity> activities = piggyBank.getActivities();
//        assertEquals(activities.size(), 1);
//        assertEquals(activities.get(0).getAmount(), 10);
//        assertEquals(activities.get(0).getType(), ActivityType.PUT_IN);
//    }
//}
