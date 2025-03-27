package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddPropertyCommandParser implements Parser<AddPropertyCommand> {
    public AddPropertyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PRICE, PREFIX_LOCATION, PREFIX_TYPE, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_PRICE, PREFIX_LOCATION, PREFIX_TYPE, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
        }

        Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        Type type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());

        return new AddPropertyCommand(new Property(price, location, type, status));
    }
}