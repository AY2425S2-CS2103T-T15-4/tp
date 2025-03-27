package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s name, phone, or email matches any of the given keywords.
 * Name matching uses fuzzy logic (Levenshtein distance ≤ 2), while phone and email use substring matching.
 * Matching is case-insensitive.
 */
public class MultiFieldFuzzyPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public MultiFieldFuzzyPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword -> {
            String lowerKeyword = keyword.toLowerCase();
            String lowerName = person.getName().toString().toLowerCase();
            return lowerName.contains(lowerKeyword)
                    || isFuzzyMatch(lowerName, lowerKeyword)
                    || (person.getPhone() != null && person.getPhone().toString().contains(lowerKeyword))
                    || (person.getEmail() != null && person.getEmail().toString().contains(lowerKeyword));
        });
    }

    private boolean isFuzzyMatch(String source, String target) {
        return levenshtein(source, target) <= 2; // Allow up to 2 edits
    }

    private int levenshtein(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1),
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MultiFieldFuzzyPredicate
                && keywords.equals(((MultiFieldFuzzyPredicate) other).keywords));
    }
}
