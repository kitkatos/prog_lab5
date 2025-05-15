package commands.commands;

import commands.Command;
import commands.CommandManager;
import console.ConsoleOutputHandler;
import console.ExecuteArgs;
import console.movieInput.MovieFactory;

import core.ApplicationStatus;
import dataLayer.collection.TreeSetCollectionManager;
import dataLayer.models.Movie;

public class Update extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;
    private final CommandManager commands;
    private final MovieFactory factory;


    public Update(TreeSetCollectionManager manager, ConsoleOutputHandler coh, CommandManager commands, MovieFactory factory){
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.manager = manager;
        this.coh = coh;
        this.commands = commands;
        this.factory = factory;
    }

    @Override
    public ApplicationStatus execute(String arg){
        try {
            int intArg = Integer.parseInt(arg);
            if (manager.getElemById(intArg) != null) {
                commands.executeCommand(new ExecuteArgs("remove_by_id", arg));
                Movie movie = factory.createMovieWithId(intArg);
                manager.addElem(movie);
                coh.printLine(String.format("Элемент с id=%s успешно обновлен", arg));
            } else {
                coh.printLine(String.format("Элемент с id=%s не сущенствует", arg));
            }
            return ApplicationStatus.RUNNING;
        } catch (NumberFormatException e){
            coh.printError(e.getMessage());
            return ApplicationStatus.ERROR;

        }
    }
}
