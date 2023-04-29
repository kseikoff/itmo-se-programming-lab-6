package commandsModule.handler;

import commands.CommandDescription;
import commandsModule.commands.*;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;
import serverModules.response.sender.ExecutionResultResponseSender;

import java.io.IOException;
import java.util.*;

/**
 * Command processing class.
 *
 * @authors Dmitrii Chebanenko and Alexey Kseikoff
 */

public class CommandHandler {
    private final Map<String, BaseCommand> commands;
    private static List<BaseCommand> history;

    public CommandHandler() {

        commands = new LinkedHashMap<>();
        history = getHistory();

        commands.put("add", new AddCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("clear", new ClearCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("help", new HelpCommand(this.commands));
        commands.put("exit", new ExitCommand());
        commands.put("update", new UpdateByIdCommand());
        commands.put("history", new HistoryCommand(this.commands));
        commands.put("sum_of_height", new SumOfHeightCommand());
        commands.put("average_of_height", new AverageOfHeightCommand());
        commands.put("print_field_descending_birthday", new PrintFieldDescendingBirthdayCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
    }

    public Map<String, BaseCommand> getCommands() {
        return commands;
    }

    public static List<BaseCommand> getHistory() {
        if (history == null) {
            history = new ArrayList<>();
        }
        return history;
    }

    public BaseCommand getCommandByDescription(CommandDescription description)  {
        return commands.get(description.getCommandName());
    }

    public void execute(ConnectionModule module, CallerBack callerBack, CommandExecutionRequest request) {
        String response = "";
        try {
            BaseCommand command = this.getCommandByDescription(request.getDescriptionCommand());
            if (command instanceof ParameterizedCommand parameterizedCommand) {
                parameterizedCommand.setArguments(request.getArgs());
                parameterizedCommand.execute();
                response = parameterizedCommand.getResponse();
            } else {
                command.execute();
                response = command.getResponse();
            }
            history.add(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ExecutionResultResponseSender().sendResponse(module, callerBack, new ExecutionResultResponse(response));
    }

}