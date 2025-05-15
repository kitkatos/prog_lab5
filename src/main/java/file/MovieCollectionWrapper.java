package file;
import dataLayer.models.Movie;

import jakarta.xml.bind.annotation.*;
import java.util.TreeSet;

@XmlRootElement(name = "movies")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieCollectionWrapper {
    @XmlElement(name = "movie")
    private TreeSet<Movie> collection = new TreeSet<>();

    public MovieCollectionWrapper() {}

    public MovieCollectionWrapper(TreeSet<Movie> collection) {
        this.collection = collection;
    }

    public TreeSet<Movie> getCollection() {
        return collection;
    }

    public void setCollection(TreeSet<Movie> collection) {
        this.collection = collection;
    }
}