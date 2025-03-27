package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Location;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Status;
import seedu.address.model.property.Type;

public class AddPropertyCommandParser implements Parser<AddPropertyCommand> {

    @Override
    public AddPropertyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_PRICE, PREFIX_LOCATION, PREFIX_TYPE, PREFIX_STATUS);

        // Validate all required prefixes are present
        if (!arePrefixesPresent(argMultimap, PREFIX_PRICE, PREFIX_LOCATION, PREFIX_TYPE, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
        }

        // Parse individual fields
        Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        Type type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());

        return new AddPropertyCommand(new Property(price, location, type, status));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}