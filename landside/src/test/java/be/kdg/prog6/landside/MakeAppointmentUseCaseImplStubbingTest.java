//package be.kdg.prog6.landside;
//
//import be.kdg.prog6.common.domain.PersonId;
//import be.kdg.prog6.common.event.ActivityType;
//import be.kdg.prog6.common.exception.NotFoundException;
//import be.kdg.prog6.family.core.GiveMoneyUseCaseImpl;
//import be.kdg.prog6.family.domain.Balance;
//import be.kdg.prog6.family.port.in.GiveMoneyCommand;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;

//class GiveMoneyUseCaseImplStubbingTest {
//    private GiveMoneyUseCaseImpl sut;
//    private UpdatePiggyBankPortStub updatePiggyBankPort;
//
//    @BeforeEach
//    void setUp() {
//        updatePiggyBankPort = new UpdatePiggyBankPortStub();
//        sut = new GiveMoneyUseCaseImpl(new LoadPiggyBankPortStub(), List.of(updatePiggyBankPort));
//    }
//
//    @Test
//    void shouldGiveMoney() {
//        // Act
//        final Balance balance = sut.giveMoney(new GiveMoneyCommand(TestIds.OWNER_ID, 20));
//
//        // Assert
//        assertEquals(balance.amount(), 20);
//        assertEquals(updatePiggyBankPort.getPiggyBank().calculateBalance().amount(), 20);
//        assertEquals(updatePiggyBankPort.getPiggyBank().getOwner(), TestIds.OWNER_ID);
//        assertEquals(updatePiggyBankPort.getActivity().type(), ActivityType.PUT_IN);
//        assertEquals(updatePiggyBankPort.getActivity().amount(), 20);
//    }
//
//    @Test
//    void shouldNotGiveMoneyToNonExistentPerson() {
//        // Arrange
//        final GiveMoneyCommand command = new GiveMoneyCommand(new PersonId(UUID.randomUUID()), 20);
//        final Executable action = () -> sut.giveMoney(command);
//
//        // Assert
//        assertThrows(NotFoundException.class, action);
//    }
//}