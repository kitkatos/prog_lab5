package dataLayer.collection;

import dataLayer.models.Movie;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetCollectionManager implements CollectionManager{
    private TreeSet<Movie> collection;
    private final Date creationDate;

    // setCollection()
    public TreeSetCollectionManager(TreeSet<Movie> collection){ // poamotret
        this.collection = collection;
        this.creationDate = new Date();
    }


    public String getInfoAboutCollection() {
        return "Тип данных: " + collection.getClass().getName() + "\n"
                + "Дата инициализации: " + creationDate + "\n"
                + "Количество элементов: " + collection.size();
    }

    @Override
    public Movie getElemById(int id) {
        if (collection.isEmpty()) {
            return null;
        }
        for (Movie movie : collection) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public String getCollectionType(){
        return collection.getClass().getName();
    }

    @Override
    public Date getCreationDate(){
        return creationDate;
    }

    @Override
    public int getCollectionSize(){
        return collection.size();
    }

    @Override
    public void addElem(Movie movie){
        collection.add(movie);
    }

    @Override
    public void updateElemById(int id, Movie newMovie) {
        Movie oldMovie = getElemById(id);
        if (oldMovie != null) {
            collection.remove(oldMovie);
            collection.add(newMovie);
            // я предполагаю, что в новом фильме уже задан верный айдишник
        }
    }

    @Override
    public void removeElemById(int id) {
        Movie movie = getElemById(id);
        if (movie != null) {
            collection.remove(movie);
        }
    }

    @Override
    public void deleteAllElem(){
        collection.clear();
    }

    @Override
    public TreeSet<Movie> getCollection(){
        return collection;
    }

    @Override
    public boolean addElemIfMax(Movie movie){
        if (collection.isEmpty() || movie.compareTo(collection.last()) > 0) {
            collection.add(movie);
            return true;
        }
        return false;
    }

    @Override
    public int removeGreaterElements(Movie movie){
        int before = getCollectionSize();
        collection.removeIf(m -> m.compareTo(movie) > 0);
        int after = getCollectionSize();
        return before - after;
    }

    @Override
    public Movie getElemWithMinCreationDate() {
        return collection.stream()
                .min(Comparator.comparing(Movie::getCreationDate))
                .orElse(null);
    }

    @Override
    public Movie getElemWithMaxId() {
        return collection.stream()
                .max(Comparator.comparing(Movie::getId))
                .orElse(null);
    }

    public TreeSet<Movie> getElemsWithMatchName(String filter){
        TreeSet<Movie> result = new TreeSet<>(collection.comparator()); // Сохраняем сортировку
        for (Movie movie : collection) {
            if (movie.getName().startsWith(filter)) {
                result.add(movie);
            }
        }
        return result;
    }
}
