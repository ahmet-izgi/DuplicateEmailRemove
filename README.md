[![CircleCI](https://circleci.com/gh/ahmet-izgi/DuplicateEmailRemove.svg?style=svg)](https://circleci.com/gh/ahmet-izgi/DuplicateEmailRemove)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ahmet-izgi_DuplicateEmailRemove&metric=alert_status)](https://sonarcloud.io/dashboard?id=ahmet-izgi_DuplicateEmailRemove)
# Duplicate Email Remover

Small java utility that removes all duplicates from an unsorted list of email addresses and returns unique list of emails. Method utilized to handle 1.3M addresses with %50 duplication percentage under 1 second.

## Test Data Generator

EmailDataProvider class developed for generating random email addresses with given random lengths. Generated addresses are valid email addresses.

## Unit Scenarios

Test scenarios covers both DuplicateEmailRemover and EmailDataProvider.

  - sizeShouldBeUpToMaxLimit()
  - sizeShouldBeAtLeastMinLimit()
  - duplicationPercentageShouldBeAtLeastMinLimit()
  - duplicationPercentageShouldBeUpToMaxLimit()
  - checkTheSizeAfterRemovingDuplicates()
  - checkTheOrderAfterRemovingDuplicates()
  - checkTheSizeAfterRemovingDuplicatesBenchmark()
