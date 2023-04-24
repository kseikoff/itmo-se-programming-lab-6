package commandsModule.commands;

import commands.CommandDescription;
import database.Database;
import defaultClasses.Person;

import java.io.IOException;

public class RemoveLowerCommand implements BaseCommand {
    private final String name = "remove_lower";
    private Person argument;
    private final Database dataBase;

    public RemoveLowerCommand(Database dataBase) {
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
        return "Removes from the collection all elements lower than the specified";
    }

    @Override
    public void execute() throws IOException {
        dataBase.removeLower(argument);
    }

}
