package org.jhipster.chiffrage;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.jhipster.chiffrage");

        noClasses()
            .that()
            .resideInAnyPackage("org.jhipster.chiffrage.service..")
            .or()
            .resideInAnyPackage("org.jhipster.chiffrage.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.jhipster.chiffrage.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
