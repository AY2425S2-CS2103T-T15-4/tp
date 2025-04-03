package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.exceptions.InvalidPredicateException;

/**
 * Tests that a {@code Person}'s name, phone, or email matches any of the given keywords.
 * Name matching uses fuzzy logic (Levenshtein distance ≤ 2), while phone and email use substring matching.
 * Matching is case-insensitive.
 */
public class MultiFieldFuzzyPredicate implements Predicate<Person> {
    private static final Logger logger = LogsCenter.getLogger(MultiFieldFuzzyPredicate.class.getName());
    public static final int MAX_LEVENSHTEIN_DISTANCE = 2;
    public static final String MESSAGE_EMPTY_KEYWORDS = "Keywords list cannot be empty.";

    private final List<String> keywords;

    /**
     * Constructs a predicate with the given keywords.
     * @param keywords List of keywords to match against.
     */
    public MultiFieldFuzzyPredicate(List<String> keywords) throws InvalidPredicateException {
        requireNonNull(keywords, "Keywords list must not be null");
        if (keywords.isEmpty()) {
            logger.warning("Attempted to create MultiFieldFuzzyPredicate with empty keywords");
            throw new InvalidPredicateException(MESSAGE_EMPTY_KEYWORDS);
        }
        this.keywords = keywords;
        logger.fine("Initialized MultiFieldFuzzyPredicate with keywords: " + keywords);
    }

    @Override
    public boolean test(Person person) {
        requireNonNull(person, "Person must not be null");
        assert person.getName() != null : "Person name must not be null";

        return keywords.stream().anyMatch(keyword -> {
            String lowerKeyword = keyword.toLowerCase();
            logger.finer("Testing keyword: " + lowerKeyword);

            // Name matching (fuzzy)
            String lowerName = person.getName().toString().toLowerCase();
            boolean nameMatch = lowerName.contains(lowerKeyword) || isFuzzyMatch(lowerName, lowerKeyword);
            if (nameMatch) {
                logger.fine("Name match found: " + lowerName + " with " + lowerKeyword);
                return true;
            }

            // Phone matching (substring)
            assert person.getPhone() != null : "Person phone must not be null";
            boolean phoneMatch = person.getPhone().toString().contains(lowerKeyword);
            if (phoneMatch) {
                logger.fine("Phone match found: " + person.getPhone() + " with " + lowerKeyword);
                return true;
            }

            // Email matching (substring)
            assert person.getEmail() != null : "Person email must not be null";
            boolean emailMatch = person.getEmail().toString().contains(lowerKeyword);
            if (emailMatch) {
                logger.fine("Email match found: " + person.getEmail() + " with " + lowerKeyword);
                return true;
            }

            return false;
        });
    }

    private boolean isFuzzyMatch(String source, String target) {
        requireNonNull(source, "Source string must not be null");
        requireNonNull(target, "Target string must not be null");
        int distance = levenshtein(source, target);
        logger.finer("Levenshtein distance between '" + source + "' and '" + target + "': " + distance);
        return distance <= MAX_LEVENSHTEIN_DISTANCE;
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
        if (other == this) {
            return true;
        }
        if (!(other instanceof MultiFieldFuzzyPredicate otherPredicate)) {
            return false;
        }
        return keywords.equals(otherPredicate.keywords);
    }

    private static void requireNonNull(Object obj, String message) {
        if (obj == null) {
            logger.severe("Null detected: " + message);
            throw new NullPointerException(message);
        }
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
