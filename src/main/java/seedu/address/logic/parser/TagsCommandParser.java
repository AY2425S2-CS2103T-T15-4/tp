package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.TagsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new TagsCommand object
 */
public class TagsCommandParser implements Parser<TagsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagsCommand
     * and returns a TagsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagsCommand.MESSAGE_USAGE));
        }

        String[] tagKeywords = trimmedArgs.split("\\s+");

        return new TagsCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
    }

}
