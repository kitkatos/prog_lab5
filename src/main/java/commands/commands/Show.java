package commands.commands;

import commands.Command;
import console.ConsoleOutputHandler;
import console.ExecuteArgs;
import core.ApplicationStatus;
import dataLayer.collection.TreeSetCollectionManager;
import dataLayer.models.Movie;

import java.util.TreeSet;

public class Show extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;

    public Show(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        TreeSet<Movie> collection = manager.getCollection();
        if (collection.isEmpty()) {
            coh.printLine("Коллеккция пуста");
        } else {
            for (Movie movie : collection) {
                String strMovie = movie.toString();
                coh.printLine(strMovie);
            }
            coh.printLine("Список элементов коллекции отображен");
        }
        return ApplicationStatus.RUNNING;
    }
}
