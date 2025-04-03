package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ClientType;
import seedu.address.model.person.Person;


/**
 * Filters and displays persons that match the housing criteria
 * of the person at the specified index.
 */
public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds and displays all persons matching the attributes of the person at the specified index.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    //Uses index to dynamically fetch the person from the model at runtime. Index is used for consistency with
    //parameter used in delete command
    public MatchCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(targetIndex.getZeroBased());

        Predicate<Person> matchPredicate = getMatchPredicate(person);
        model.updateFilteredPersonList(matchPredicate);

        int matchCount = model.getFilteredPersonList().size();
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_MATCH_OVERVIEW, matchCount));
    }

    private Predicate<Person> getMatchPredicate(Person person) {
        return candidate -> {
            if (person.getClientType() == ClientType.UNKNOWN || candidate.getClientType() == ClientType.UNKNOWN) {
                return false;
            }

            if (person.getDistrict() == null
                    || candidate.getDistrict() == null
                    || person.getLandSize() == null
                    || candidate.getLandSize() == null
                    || person.getPrice() == null
                    || candidate.getPrice() == null) {
                return false;
            }

            if (person.getClientType().equals(ClientType.BUYER)) {
                if (!candidate.getClientType().equals(ClientType.SELLER)) {
                    return false;
                }

                return person.getDistrict().equals(candidate.getDistrict())
                        && candidate.getLandSize().getValue() >= person.getLandSize().getValue()
                        && candidate.getPrice().getValue() <= person.getPrice().getValue();
            } else if (person.getClientType().equals(ClientType.SELLER)) {
                if (!candidate.getClientType().equals(ClientType.BUYER)) {
                    return false;
                }

                return person.getDistrict().equals(candidate.getDistrict())
                        && candidate.getLandSize().getValue() <= person.getLandSize().getValue()
                        && candidate.getPrice().getValue() >= person.getPrice().getValue();
            } else {
                return false;
            }
        };
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MatchCommand)) {
            return false;
        }

        MatchCommand otherMatch = (MatchCommand) other;
        return this.targetIndex == otherMatch.targetIndex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

}

