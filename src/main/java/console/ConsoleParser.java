package console;

public class ConsoleParser {
    public ExecuteArgs getRequest(String trimInput){
        String validatedInput = validate(trimInput);
        if (validatedInput.isEmpty()) {
            return new ExecuteArgs("", "");
        } else {
            String[] args = validatedInput.split("\\s+");
            String commandName = args[0];
            String argument = args.length > 1 ? args[1] : "";
            return new ExecuteArgs(commandName, argument);
        }
    }

    private String validate(String trimInput){
        if (trimInput == null || trimInput.trim().isEmpty()) {
            return "";
        }
        String commandSave = "^save\\s+.+";
        String commandExecuteScript = "^execute_script\\s+.+";
        String regex = "^[a-zA-Z_]+(\\s+\\d+)?$";

        if (trimInput.matches(commandSave) || trimInput.matches(regex) || trimInput.matches(commandExecuteScript)){
            return trimInput.toLowerCase();
        } else {
            return "";
        }
    }
}
