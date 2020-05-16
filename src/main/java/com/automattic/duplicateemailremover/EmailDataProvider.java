package com.automattic.duplicateemailremover;

import java.util.*;

public class EmailDataProvider {

    private static final int MAX_EMAIL_NAME_LENGTH = 9;
    private static final int MIN_EMAIL_NAME_LENGTH = 1;
    private static final int MAX_EMAIL_DOMAIN_LENGTH = 5;
    private static final int MIN_EMAIL_DOMAIN_LENGTH = 1;
    private static final int MAX_EMAIL_TOP_LEVEL_LENGTH = 2;
    private static final int MIN_EMAIL_TOP_LEVEL_LENGTH = 1;
    private static final int MAX_EMAIL_LIST_SIZE = 100000000;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final Random randomizer = new Random();


    /**
     * Generates an email address list with the given size and percentage of duplication.
     *
     * @param size                  The size of the list which contains email addresses. Maximum is MAX_EMAIL_LIST_SIZE
     * @param duplicationPercentage An array with the size of 200 with the percentage of 50 gives 100 email address duplicated in the list.
     * @return randomly generated email address list
     */
    public List<String> generateTestData(int size, int duplicationPercentage) {
        if (!isSizeAndPercentageAtRange(size, duplicationPercentage)) {
            throw new IllegalArgumentException("given size or duplication is not within the range.");
        }
        int uniqueCount = size * duplicationPercentage / 100;

        Set<String> uniqueEmailAddresses = new HashSet<>();

        while (uniqueEmailAddresses.size() < uniqueCount) {
            uniqueEmailAddresses.add(generateEmailWithRandomLengths());
        }

        List<String> emailAddresses = new ArrayList<>(uniqueEmailAddresses);

        if (uniqueCount < size) {
            fillArrayWithDuplicates(emailAddresses, uniqueCount);
        }

        Collections.shuffle(emailAddresses);
        return emailAddresses;
    }

    /**
     * Generates an email address with random lengths
     *
     * @return the email address
     */
    private String generateEmailWithRandomLengths() {
        int nameLength;
        int domainLength;
        int topLevelLength;

        nameLength = randomizer.nextInt(MAX_EMAIL_NAME_LENGTH) + MIN_EMAIL_NAME_LENGTH;
        domainLength = randomizer.nextInt(MAX_EMAIL_DOMAIN_LENGTH) + MIN_EMAIL_DOMAIN_LENGTH;
        topLevelLength = randomizer.nextInt(MAX_EMAIL_TOP_LEVEL_LENGTH) + MIN_EMAIL_TOP_LEVEL_LENGTH;

        return generateEmail(nameLength, domainLength, topLevelLength);
    }

    /**
     * Check given parameters within the valid range
     *
     * @param size                  Size of the list
     * @param duplicationPercentage Duplication percentage of the email list
     * @return TRUE if the parameters are valid.
     */
    private boolean isSizeAndPercentageAtRange(int size, int duplicationPercentage) {
        return ((size <= MAX_EMAIL_LIST_SIZE && size >= 2) && (duplicationPercentage >= 0 && duplicationPercentage <= 100));
    }

    /**
     * Duplicates email addresses until the array is full
     *
     * @param emailAddresses The email address list to be filled with duplicate values.
     * @param uniqueCount    Unique email address count
     */
    private void fillArrayWithDuplicates(List<String> emailAddresses, int uniqueCount) {
        int duplicateCount = emailAddresses.size() - uniqueCount;
        for (int i = 0; i < duplicateCount; i++) {
            emailAddresses.add(emailAddresses.get(i % uniqueCount));
        }
    }

    /**
     * Generates random email with the length of the required parameters
     *
     * @param nameLength     length of the first part of email (4 "abcd"@email.com)
     * @param domainLength   length of the domain part of email (5 abcd@"email".com)
     * @param topLevelLength length of the top level part of email (3 abcd@email."com")
     * @return generated email
     */
    private String generateEmail(int nameLength, int domainLength, int topLevelLength) {
        StringBuilder builder = new StringBuilder();
        builder.append(generateString(nameLength));
        builder.append("@");
        builder.append(generateString(domainLength));
        builder.append(".");
        builder.append(generateString(topLevelLength));
        return builder.toString();
    }

    /**
     * Generates random string values with the given length
     *
     * @param length Length of the string to be generated.
     * @return Randomly geneated string.
     */
    private String generateString(int length) {
        StringBuilder generated = new StringBuilder();
        while (generated.length() < length) { // length of the random string.
            int index = (int) (randomizer.nextFloat() * CHARACTERS.length());
            generated.append(CHARACTERS.charAt(index));
        }
        return generated.toString();
    }
}