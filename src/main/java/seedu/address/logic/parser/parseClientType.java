package seedu.address.logic.parser;

import seedu.address.model.person.ClientType;

public class parseClientType {
    public static boolean isValidClientType(String clientType) {
        return clientType.equalsIgnoreCase("buyer") || clientType.equalsIgnoreCase("b") ||
                clientType.equalsIgnoreCase("seller") || clientType.equalsIgnoreCase("s");
    }

    public static ClientType assignClientType(String clientType) {
        if (clientType.equalsIgnoreCase("buyer") || clientType.equalsIgnoreCase("b")) {
            return ClientType.BUYER;
        }
        return ClientType.SELLER;
    }
}
