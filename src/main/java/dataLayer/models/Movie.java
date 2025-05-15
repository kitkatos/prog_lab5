package dataLayer.models;

import java.util.Date;
import java.lang.Comparable;
import jakarta.xml.bind.annotation.*;

public class Movie implements Validatable, Comparable<Movie>{
    private int id;

    int nextId = 1;

    private String name;

    private Coordinates coordinates;

    private Date creationDate;

    private long oscarsCount;

    private Long usaBoxOffice;

    private MovieGenre genre;

    private MpaaRating mpaaRating;

    private Person director;

    public Movie(int id, String name, Coordinates coordinates, long oscarsCount,
                 Long usaBoxOffice, MovieGenre genre, MpaaRating mpaaRating, Person director){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.oscarsCount = oscarsCount;
        this.usaBoxOffice = usaBoxOffice;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
    }

    public Movie(String name, Coordinates coordinates, long oscarsCount,
                 Long usaBoxOffice, MovieGenre genre, MpaaRating mpaaRating, Person director){
        this.id = nextId++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.oscarsCount = oscarsCount;
        this.usaBoxOffice = usaBoxOffice;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }    
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    public java.util.Date getCreationDate(){
        return creationDate;
    }
    public void setCreationDate(java.util.Date creatinDate){
        this.creationDate = creatinDate;
    }
    public long getOscarsCount(){
        return oscarsCount;
    }
    public void setOscarsCount(long oscarsCount){
        this.oscarsCount = oscarsCount;
    }
    public Long getUsaBoxOffice(){
        return usaBoxOffice;
    }
    public void setUsaBoxOffice(Long usaBoxOffice){
        this.usaBoxOffice = usaBoxOffice;
    }
    public MovieGenre getMovieGenre(){
        return genre;
    }
    public void setMovieGenre(MovieGenre genre){
        this.genre = genre;
    }
    public MpaaRating getMpaaRating(){
        return mpaaRating;
    }
    public void setMpaaRating(MpaaRating mpaaRating){
        this.mpaaRating = mpaaRating;
    }
    public Person getDirector(){
        return director;
    }
    public void setDirector(Person director){
        this.director = director;
    }


    @Override
    public String toString(){
        return String.format(
            "Movie{id=%s, name='%s', coordinates=%s, creationDate=%s, oscarsCount=%s, usaBoxOffice=%s%s%s%s}",
            id, name, coordinates, creationDate, oscarsCount, usaBoxOffice,
            (genre != null ? ", genre=" + genre : ""),
            (mpaaRating != null ? ", mpaaRating=" + mpaaRating : ""),
            (director != null ? ", director=" + director : "")
        );
    }

    @Override
    public boolean validate(){
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (oscarsCount == 0) return false;
        if (usaBoxOffice == 0) return false;
        if (genre == null) return false;
        if (mpaaRating == null) return false;
        return director != null;

    }

    @Override
    public int compareTo(Movie movie){
        int res = Long.compare(this.oscarsCount, movie.oscarsCount);

        if (res == 0) {
            return Long.compare(this.usaBoxOffice, movie.usaBoxOffice);
        }

        return res;
        /*
            this > movie 1
            this = movie 0
            this < movie -1
         */
    }
}