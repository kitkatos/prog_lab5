package commands.commands;

import commands.Command;
import console.ConsoleOutputHandler;
import console.movieInput.MovieFactory;
import core.ApplicationStatus;
import dataLayer.collection.TreeSetCollectionManager;
import dataLayer.models.Movie;

public class AddIfMax extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;
    private final MovieFactory factory;


    public AddIfMax(TreeSetCollectionManager manager, ConsoleOutputHandler coh, MovieFactory factory){
        super("add_if_max", "   добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.manager = manager;
        this.coh = coh;
        this.factory = factory;

    }

    @Override
    public ApplicationStatus execute(String arg) {
        Movie movie = factory.createMovie();
        boolean res = manager.addElemIfMax(movie);
        if (res){
            coh.printLine("Введенный элемент оказался наибольшим и добавлен в коллекцию");
        } else {
            coh.printLine("Введенный элемент не оказался наибольшим и не добавлен в коллекцию");
        }
        return ApplicationStatus.RUNNING;
    }
}

