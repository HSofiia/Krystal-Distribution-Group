//package be.kdg.prog6.landside;
//
//import be.kdg.prog6.common.domain.PersonId;
//import be.kdg.prog6.common.event.ActivityType;
//import be.kdg.prog6.common.exception.NotFoundException;
//import be.kdg.prog6.family.core.GiveMoneyUseCaseImpl;
//import be.kdg.prog6.family.domain.Activity;
//import be.kdg.prog6.family.domain.ActivityWindow;
//import be.kdg.prog6.family.domain.Balance;
//import be.kdg.prog6.family.domain.PiggyBank;
//import be.kdg.prog6.family.port.in.GiveMoneyCommand;
//import be.kdg.prog6.family.port.out.LoadPiggyBankPort;
//import be.kdg.prog6.family.port.out.UpdatePiggyBankPort;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import org.mockito.ArgumentCaptor;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//class GiveMoneyUseCaseImplMockingTest {
//    private GiveMoneyUseCaseImpl sut;
//    private UpdatePiggyBankPort updatePiggyBankPort;
//
//    @BeforeEach
//    void setUp() {
//        updatePiggyBankPort = mock(UpdatePiggyBankPort.class);
//        final LoadPiggyBankPort loadPiggyBankPort = mock(LoadPiggyBankPort.class);
//        when(loadPiggyBankPort.loadPiggyBankByOwnerId(TestIds.OWNER_ID)).thenReturn(Optional.of(
//            createEmptyPiggyBank()
//        ));
//        sut = new GiveMoneyUseCaseImpl(loadPiggyBankPort, List.of(updatePiggyBankPort));
//    }
//
//    private static PiggyBank createEmptyPiggyBank() {
//        final Balance balance = new Balance(LocalDateTime.MIN, 0);
//        final ActivityWindow activityWindow = new ActivityWindow(
//            TestIds.OWNER_ID,
//            new ArrayList<>()
//        );
//        return new PiggyBank(TestIds.OWNER_ID, balance, activityWindow);
//    }
//
//    @Test
//    void shouldGiveMoney() {
//        // Act
//        final Balance balance = sut.giveMoney(new GiveMoneyCommand(TestIds.OWNER_ID, 20));
//
//        // Assert
//        assertEquals(balance.amount(), 20);
//
//        final ArgumentCaptor<PiggyBank> piggyCaptor = ArgumentCaptor.forClass(PiggyBank.class);
//        final ArgumentCaptor<Activity> activityCaptor = ArgumentCaptor.forClass(Activity.class);
//        verify(updatePiggyBankPort).activityAdded(piggyCaptor.capture(), activityCaptor.capture());
//
//        assertEquals(piggyCaptor.getValue().calculateBalance().amount(), 20);
//        assertEquals(piggyCaptor.getValue().getOwner(), TestIds.OWNER_ID);
//        assertEquals(activityCaptor.getValue().type(), ActivityType.PUT_IN);
//        assertEquals(activityCaptor.getValue().amount(), 20);
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
