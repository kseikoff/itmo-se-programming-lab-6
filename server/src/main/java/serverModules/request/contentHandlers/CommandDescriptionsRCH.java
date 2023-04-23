package serverModules.request.contentHandlers;

import commands.CommandDescription;
import commandsModule.CommandDescriptionsContainer;
import responses.CommandDescriptionsResponse;
import serverModules.context.ServerContext;
import serverModules.response.sender.CommandDescriptionsResponseSender;

import java.util.List;

public class CommandDescriptionsRCH implements RequestContentHandleAble {

    @Override
    public void handleRequestContent(ServerContext context) {
        List<CommandDescription> commands = CommandDescriptionsContainer.getCommands();
        CommandDescriptionsResponse response = new CommandDescriptionsResponse(commands);
        new CommandDescriptionsResponseSender().sendResponse(context.getConnectionModule(), context.getCallerBack(), response);
    }
}