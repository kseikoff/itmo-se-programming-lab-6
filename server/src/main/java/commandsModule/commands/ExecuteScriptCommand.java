package commandsModule.commands;

import commands.CommandDescription;
import commandsModule.master.CommandHandler;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class ExecuteScriptCommand implements BaseCommand, ParameterizedCommand {
    private final String name = "execute_script";
    private final CommandHandler handler;
    private String parameter;
    public static LinkedList<String> historyOfDangerScript = new LinkedList<>();

    public ExecuteScriptCommand(CommandHandler handler) {
        this.handler = handler;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public CommandDescription getCommandDescriptionObj() {
        return new CommandDescription(name);
    }

    @Override
    public String describe() {
        return "Reads and executes a script from the specified file";
    }

    @Override
    public void execute() throws IOException {
        if (historyOfDangerScript.contains(parameter)) {
            System.out.println("Script in loop");
            return;
        }
        File file = new File(parameter);
        try (InputStream in = new FileInputStream(file)) {
            String contents = IOUtils.toString(in, StandardCharsets.UTF_8);
            var a = contents.split("\n");
            for (var t : a) {
                if (t.split(" ")[0].equals("execute_script")) {
                    historyOfDangerScript.add(parameter);
                }
                handler.handleCommand(t);
            }
        } catch (IOException e) {
            System.out.println("Incorrect script");
        }
        historyOfDangerScript.clear();
    }

}