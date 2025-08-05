//package be.kdg.prog6.landside;
//
//import be.kdg.prog6.common.domain.PersonId;
//import be.kdg.prog6.family.domain.ActivityWindow;
//import be.kdg.prog6.family.domain.Balance;
//import be.kdg.prog6.family.domain.PiggyBank;
//import be.kdg.prog6.family.port.out.LoadPiggyBankPort;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Optional;
//
//class LoadPiggyBankPortStub implements LoadPiggyBankPort {
//    @Override
//    public Optional<PiggyBank> loadPiggyBankByOwnerId(final PersonId ownerId) {
//        if (TestIds.OWNER_ID.equals(ownerId)) {
//            final Balance balance = new Balance(LocalDateTime.MIN, 0);
//            final ActivityWindow activityWindow = new ActivityWindow(
//                TestIds.OWNER_ID,
//                new ArrayList<>()
//            );
//            return Optional.of(new PiggyBank(TestIds.OWNER_ID, balance, activityWindow));
//        }
//        return Optional.empty();
//    }
//}
