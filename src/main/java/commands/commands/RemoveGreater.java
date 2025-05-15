package commands.commands;

import commands.Command;
import console.ConsoleOutputHandler;
import console.movieInput.MovieFactory;
import core.ApplicationStatus;
import dataLayer.collection.TreeSetCollectionManager;
import dataLayer.models.Movie;

public class RemoveGreater extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;
    private final MovieFactory factory;


    public RemoveGreater(TreeSetCollectionManager manager, ConsoleOutputHandler coh, MovieFactory factory){
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
        this.manager = manager;
        this.coh = coh;
        this.factory = factory;
    }

    @Override
    public ApplicationStatus execute(String arg){
        Movie movie = factory.createMovie();
        int removedCount = manager.removeGreaterElements(movie);
        coh.printLine(String.format("Было удалено %d элемнетов, привышающих значение заданного", removedCount));
        return ApplicationStatus.RUNNING;
    }
}
