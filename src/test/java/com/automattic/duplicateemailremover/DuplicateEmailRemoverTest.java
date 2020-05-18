package com.automattic.duplicateemailremover;


import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

        List<String> emailAddressesUnique = assertTimeout(ofSeconds(1), () -> DuplicateEmailRemover.execute(emailAddresses));

        assertEquals(50 * 1000, emailAddressesUnique.size());
    }

    @Test
    public void checkTheOrderAfterRemovingDuplicates() {
        EmailDataProvider emailDataProvider = new EmailDataProvider();
        List<String> emailAddresses = emailDataProvider.generateTestData(10, 1);
        List<String> emailAddressesUnique = assertTimeout(ofSeconds(1), () -> DuplicateEmailRemover.execute(emailAddresses));

        int indexUnique = 0, indexDuplicate = 0;

        // We will go through two list.
        while (indexDuplicate < emailAddresses.size() && indexUnique < emailAddressesUnique.size()) {
            // If we found an unique element in the list, increment both indexes. Otherwise increment only indexDuplicate.
            if (emailAddresses.get(indexDuplicate).equals(emailAddressesUnique.get(indexUnique))) {
                indexUnique++;
            }
            indexDuplicate++;
        }
        assertEquals(indexUnique, emailAddressesUnique.size());
    }

    @Test
    public void checkTheSizeAfterRemovingDuplicatesBenchmark() {
        EmailDataProvider emailDataProvider = new EmailDataProvider();
        List<String> emailAddresses = emailDataProvider.generateTestData(1000 * 1000, 20);

        List<String> emailAddressesUnique = assertTimeout(ofSeconds(1), () -> DuplicateEmailRemover.execute(emailAddresses));

        assertEquals(200 * 1000, emailAddressesUnique.size());
    }
}
