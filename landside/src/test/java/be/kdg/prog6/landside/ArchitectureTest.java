package be.kdg.prog6.landside;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "be.kdg.prog6", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {
    private static final String DOMAIN_LAYER = "be.kdg.prog6.landside.domain..";
    private static final String ADAPTER_LAYER = "be.kdg.prog6.landside.adapter..";
    private static final String CORE_LAYER = "be.kdg.prog6.landside.core..";
    private static final String PORT_LAYER = "be.kdg.prog6.landside.port..";

    @ArchTest
    static final ArchRule domainShouldNotDependOnAnyOtherLayerRule =
        noClasses().that().resideInAPackage(DOMAIN_LAYER)
            .should().dependOnClassesThat().resideInAnyPackage(
                ADAPTER_LAYER,
                PORT_LAYER,
                CORE_LAYER
            )
            .because("This conflicts with hexagonal architecture: Domain should not depend on other layers.");

    @Test
    void givenApplicationClasses_thenNoLayerViolationsShouldExist() {
        JavaClasses jc = new ClassFileImporter().importPackages("be.kdg.prog6.landside");

        final Architectures.LayeredArchitecture arch = layeredArchitecture().consideringOnlyDependenciesInLayers()
            .layer("DRIVING_ADAPTERS").definedBy("..adapter.in..")
            .layer("DRIVING_PORTS").definedBy("..port.in..")
            .layer("CORE").definedBy("..core..")
            .whereLayer("DRIVING_ADAPTERS").mayNotBeAccessedByAnyLayer()
            .whereLayer("DRIVING_PORTS").mayOnlyBeAccessedByLayers("DRIVING_ADAPTERS", "CORE");

        arch.check(jc);
    }
}
