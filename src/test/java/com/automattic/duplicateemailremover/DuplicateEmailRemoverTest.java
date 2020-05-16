package com.automattic.duplicateemailremover;


import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;


public class DuplicateEmailRemoverTest {

    @Test
    public void sizeShouldBeUpToMaxLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailDataProvider emailDataProvider = new EmailDataProvider();
            emailDataProvider.generateTestData(100000001, 100);
        });
    }

    @Test
    public void sizeShouldBeAtLeastMinLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailDataProvider emailDataProvider = new EmailDataProvider();
            emailDataProvider.generateTestData(0, 100);
        });
    }

    @Test
    public void duplicationPercentageShouldBeAtLeastMinLimit() {

        assertThrows(IllegalArgumentException.class, () -> {
            EmailDataProvider emailDataProvider = new EmailDataProvider();
            emailDataProvider.generateTestData(10, -5);
        });
    }

    @Test
    public void duplicationPercentageShouldBeUpToMaxLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailDataProvider emailDataProvider = new EmailDataProvider();
            emailDataProvider.generateTestData(1, 105);
        });
    }

    @Test
    public void checkTheSizeAfterRemovingDuplicates() {
        EmailDataProvider emailDataProvider = new EmailDataProvider();
        List<String> emailAddresses = emailDataProvider.generateTestData(100 * 1000, 50);
        DuplicateEmailRemover duplicateEmailRemover = new DuplicateEmailRemover(emailAddresses);

        List<String> emailAddressesUnique = assertTimeout(ofSeconds(1), () -> duplicateEmailRemover.letsRockWithHashSet());

        assertEquals(50 * 1000, emailAddressesUnique.size());
    }

    @Test
    public void checkTheSizeAfterRemovingDuplicatesBenchmark() {
        EmailDataProvider emailDataProvider = new EmailDataProvider();
        List<String> emailAddresses = emailDataProvider.generateTestData(1000 * 1000, 20);
        DuplicateEmailRemover duplicateEmailRemover = new DuplicateEmailRemover(emailAddresses);

        List<String> emailAddressesUnique = assertTimeout(ofSeconds(1), () -> duplicateEmailRemover.letsRockWithHashSet());

        assertEquals(200 * 1000, emailAddressesUnique.size());
    }
}
