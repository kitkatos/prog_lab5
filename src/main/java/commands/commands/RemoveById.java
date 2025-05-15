package commands.commands;

import commands.Command;

import console.ConsoleOutputHandler;
import console.movieInput.MovieFactory;
import core.ApplicationStatus;
import dataLayer.collection.TreeSetCollectionManager;


public class RemoveById extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;


    public RemoveById(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        try {
            int intArg = Integer.parseInt(arg);
            if (manager.getElemById(intArg) != null){
                manager.removeElemById(intArg);
                coh.printLine(String.format("Элемент с id=%s успешно заменен", arg));
            } else {
                coh.printLine(String.format("Элемента с id=%s не существует", arg));
            }
            return ApplicationStatus.RUNNING;
        } catch (NumberFormatException e){
            coh.printError(e.getMessage());
            return ApplicationStatus.ERROR;
        }
    }
}
