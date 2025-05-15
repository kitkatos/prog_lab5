package commands.commands;

import commands.Command;
import console.ConsoleOutputHandler;
import core.ApplicationStatus;
import dataLayer.collection.TreeSetCollectionManager;
import dataLayer.models.Movie;

import java.util.TreeSet;

public class Info extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;

    public Info(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        coh.printLine(manager.getInfoAboutCollection()  );
        return ApplicationStatus.RUNNING;
    }
}
