package util;

public class ManReturn {
    String commandName;
    String man;

    public ManReturn(String name, String manField) {
        commandName = name;
        man = manField;
    }

    public String getCommandName() {
        return commandName;
    }
    public String getMan() {
        return man;
    }
}
