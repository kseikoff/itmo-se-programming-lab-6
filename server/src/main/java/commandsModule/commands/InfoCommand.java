package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;

public class InfoCommand implements BaseCommand {
    private final String name = "info";
    private final DataBase dataBase;

    public InfoCommand(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CommandDescription getCommandDescriptionObj() {
        return new CommandDescription(name);
    }

    @Override
    public String describe() {
        return "Prints information about the collection to the standard" +
                " output stream (type, initialization date, number of elements, etc.)";
    }

    @Override
    public void execute() {
        dataBase.info();
    }

}