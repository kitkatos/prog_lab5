package commands.commands;

import commands.Command;
import console.*;
import core.ApplicationStatus;
import dataLayer.models.Movie;
import dataLayer.collection.*;
import console.movieInput.*;

public class Add extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;
    private final MovieFactory factory;

    public Add(TreeSetCollectionManager manager, ConsoleOutputHandler coh, MovieFactory factory){
        super("add", "добавить новый элемент в коллекцию");
        this.manager = manager;
        this.coh = coh;
        this.factory = factory;

    }

    @Override
    public ApplicationStatus execute(String arg) {
        Movie movie = factory.createMovie();
        manager.addElem(movie);

        coh.printLine("Фильм успешно добавлен");
        return ApplicationStatus.RUNNING;
    }
}
