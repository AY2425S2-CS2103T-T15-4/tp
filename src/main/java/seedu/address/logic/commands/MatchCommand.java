package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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

    private final Person person;

    public MatchCommand(Person person) {
        this.person = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Predicate<Person> matchPredicate = getMatchPredicate(person);
        model.updateFilteredPersonList(matchPredicate);

        int matchCount = model.getFilteredPersonList().size();
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_MATCH_OVERVIEW, matchCount));
    }

    private Predicate<Person> getMatchPredicate(Person person) {
        return candidate -> {
            if (person.getIsBuyer() == null || candidate.getIsBuyer() == null) {
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

            if (person.getIsBuyer()) {
                if (candidate.getIsBuyer()) {
                    return false;
                }

                return person.getDistrict().equals(candidate.getDistrict())
                        && candidate.getLandSize() >= person.getLandSize()
                        && candidate.getPrice() <= person.getPrice();
            } else {
                if (!candidate.getIsBuyer()) {
                    return false;
                }

                return person.getDistrict().equals(candidate.getDistrict())
                        && candidate.getLandSize() <= person.getLandSize()
                        && candidate.getPrice() >= person.getPrice();
            }
        };
    }
}

