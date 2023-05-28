package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.SingleArgumentCommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import defaultClasses.Person;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import generators.PersonGenerator;
import requests.SingleArgumentCommandExecutionRequest;
import response.responses.CommandExecutionResponse;

import java.io.IOException;
import java.io.StreamCorruptedException;

/**
 * A class that represents the person single argument command execution result receiver.
 */

public class PersonCommandResultReceiver implements CommandReceiver {

    /**
     * A method that receives the simplified uncommon argument command, sends request to a server, gets response
     * and calls the {@link ExecutionResultHandler#handleResponse(CommandExecutionResponse)} method.
     *
     * @param command simplified command
     * @param args simplified command arguments
     * @param dataTransferConnectionModule client core
     */

    @Override
    public void receiveCommand(CommandDescription command, String[] args, DataTransferConnectionModule dataTransferConnectionModule) {
        Person generatedPerson = new PersonGenerator().generate();

        SingleArgumentCommandExecutionRequest<Person> commandRequest = new SingleArgumentCommandExecutionRequest<>(command, args, generatedPerson);
        CommandExecutionResponse executionResponse;
        try {
            executionResponse = new SingleArgumentCommandExecutionRequestSender().sendRequest(dataTransferConnectionModule, commandRequest);

            new ExecutionResultHandler().handleResponse(executionResponse);

            CommandHandler.getMissedCommands().remove(command, args);
        } catch (StreamCorruptedException | ServerUnavailableException | ResponseTimeoutException e) {
            CommandHandler.getMissedCommands().put(command, args);
        } catch (IOException e) {
            System.out.println("Something went wrong during I/O operations");
        } catch (NullPointerException e) {
            System.out.println("Empty response received");
        }
    }

}
