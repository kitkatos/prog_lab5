package file;

import console.ConsoleOutputHandler;
import dataLayer.models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserXML {
    ConsoleOutputHandler console;
    public ParserXML(ConsoleOutputHandler console){
        this.console = console;
    }
    public String convertCollectionToXMLString(TreeSet<Movie> collection){
        String ans = "<collection>\n";
        for (Movie movie : collection) {
            ans += "\t<movie>\n"
            +  "\t\t<id>" + movie.getId() + "</id>\n"
            +  "\t\t<name>" + movie.getName() + "</name>\n"
            +  "\t\t<coordinates>\n"
            +  "\t\t\t<x>" + movie.getCoordinates().getX() + "</x>\n"
            +  "\t\t\t<y>" + movie.getCoordinates().getY() + "</y>\n"
            +  "\t\t</coordinates>\n"
            +  "\t\t<creationDate>" + movie.getCreationDate() + "</creationDate>\n"
            +  "\t\t<usaBoxOffice>" + movie.getUsaBoxOffice() + "</usaBoxOffice>\n"
            +  "\t\t<genre>" + (movie.getMovieGenre() == null ? "" : movie.getMovieGenre()) + "</genre>\n"
            +  "\t\t<mpaaRating>" + (movie.getMovieGenre() == null ? "" : movie.getMovieGenre()) + "</mpaaRating>\n";
            if (movie.getDirector() != null) {
                ans += "\t\t<director>\n"
                +  "\t\t\t<directorName>" + movie.getDirector().getName() + "</directorName>\n"
                +  "\t\t\t<directorBirthday>" + movie.getDirector().getBirthday() + "</directorBirthday>\n"
                +  "\t\t\t<directorHeight>" + movie.getDirector().getHeight() + "</directorHeight>\n"
                +  "\t\t\t<directorWeight>" + movie.getDirector().getWeight() + "</directorWeight>\n"
                +  "\t\t\t<directorPassportId>" + movie.getDirector().getPassportID() + "</directorPassportId>\n"
                +  "\t\t</director>\n";
            } else {
                ans  += "\t\t<director></director>\n";
            }
            ans += "\t</movie>\n";
        }
        ans += "</collection>";
        return ans;
    }

    public TreeSet<Movie> getCollectionFromXML(List<String> xmlLines){
        String xml = String.join("\n", xmlLines);
        TreeSet<Movie> movies = new TreeSet<>();
        Pattern regMovieContent = Pattern.compile("<movie>(.+?)</movie>", Pattern.DOTALL);// требуется доп проверка на то, что это работает
        Matcher movieMatcher = regMovieContent.matcher(xml);

        while (movieMatcher.find()) {
            String movieXML = movieMatcher.group(1);
            Movie movie = parseMovie(movieXML);
            movies.add(movie);
        }
        return movies;
    }
    public Movie parseMovie(String xmlMovie){
        String name = getValue(xmlMovie, "name");

        Double x = Double.valueOf(getValue(xmlMovie, "x"));
        double y = Double.parseDouble(getValue(xmlMovie, "y"));
        Coordinates coordinates = new Coordinates(x, y);

        long oscarsCount = Long.parseLong(getValue(xmlMovie, "oscarsCount"));
        Long usaBoxOffice = Long.valueOf(getValue(xmlMovie, "usaBoxOffice"));

        MovieGenre genre = MovieGenre.valueOf(getValue(xmlMovie, "genre"));
        MpaaRating mpaaRating = MpaaRating.valueOf(getValue(xmlMovie, "mpaaRating"));
        Person director;
        if (getValue(xmlMovie, "director").isEmpty()){
            director = null;
        } else {
            String directorName = getValue(xmlMovie, "directorName");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date birthday;
            try {
                birthday = format.parse(getValue(xmlMovie, "birthday"));
            } catch (ParseException e) {
                console.printError("Ошибка парсинга даты в ParseXML: " + e.getMessage());
                birthday = new Date();
            }
            Long height = Long.valueOf(getValue(xmlMovie, "height"));
            int weight = Integer.parseInt(getValue(xmlMovie, "weight"));
            String passportID = getValue(xmlMovie, "passportID");
            director = new Person(directorName, birthday, height, weight, passportID);
        }
        return new Movie(name, coordinates, oscarsCount, usaBoxOffice, genre, mpaaRating, director);
    }

    private String getValue(String xml, String tagName) {
        Pattern pattern = Pattern.compile("<" + tagName + ">(.*?)</" + tagName + ">", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(xml);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return "";
        }    }
}
