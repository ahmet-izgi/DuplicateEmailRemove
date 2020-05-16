package com.automattic.duplicateemailremover;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateEmailRemover {

    private List<String> emailAddresses;

    public DuplicateEmailRemover(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public List<String> letsRockWithHashSet() {
        Set<String> set = new HashSet<>();
        List<String> newList = new ArrayList<>();
        for (String emailAddress : emailAddresses) {
            if (set.add(emailAddress)) {
                newList.add(emailAddress);
            }
        }

        emailAddresses.clear();
        emailAddresses.addAll(newList);

        return emailAddresses;
    }
}
