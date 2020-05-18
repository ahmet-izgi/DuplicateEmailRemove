package com.automattic.duplicateemailremover;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateEmailRemover {

    public static List<String> execute(List<String> emailAddresses) {
        Set<String> set = new HashSet<>();
        List<String> newList = new ArrayList<>();
        for (String emailAddress : emailAddresses) {
            if (set.add(emailAddress)) {
                newList.add(emailAddress);
            }
        }

        return newList;
    }
}
