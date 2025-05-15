package console.movieInput;


import console.exception.*;
import dataLayer.models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class MovieInputValidator {
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String validateName(String rawName){
        return rawName;
    }

    public long validateOscarsCount(String rawOscars) throws OutOfRangeException, InvalidFormatException{
        try {
            long value = Long.parseLong(rawOscars);
            if (value <= 0) {
                throw new OutOfRangeException("Количество Оскаров должно быть больше 0");
            }
            return value;
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Оскары задаются с помощью числа");
        }
    }

    public Long validateUsaBoxOffice(String rawBoxOffice) throws OutOfRangeException, InvalidFormatException{
        try {
            Long value = Long.parseLong(rawBoxOffice);
            if (value <= 0) {
                throw new OutOfRangeException("Сборы должны быть больше 0");
            }
            return value;
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Сборы должны быть числом");
        }
    }

    public Double validateCoordinateX(String rawX) throws OutOfRangeException, InvalidFormatException{
        try {
            Double value = Double.valueOf(rawX);
            if (value <= -382) {
                throw new OutOfRangeException("Координата X должна быть больше -382");
            }
            return value;
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Координата X должна быть числом");
        }
    }

    public double validateCoordinateY(String rawY) throws InvalidFormatException{
        try {
            return Double.parseDouble(rawY);
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Координата Y должна быть числом");
        }
    }

    public MovieGenre validateGenre(String rawGenre) throws InvalidFormatException {
        if (rawGenre.isEmpty()) {
            return null;
        }
        try {
            return MovieGenre.valueOf(rawGenre.toUpperCase());
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Жанр указан некорректно");
        }
    }

    public MpaaRating validateMpaaRating(String rawRating) throws InvalidFormatException {
        if (rawRating.isEmpty()) {
            return null;
        }
        try {
            return MpaaRating.valueOf(rawRating.toUpperCase());
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Рейтинг MPAA указан некорректно");
        }
    }

    public boolean validatePersonIsEmpty(String ans) throws InvalidFormatException{
        String word = ans.toLowerCase();
        if (word.equals("yes")) {
            return true;
        } else if (word.equals("no")) {
            return false;
        } else {
            throw new InvalidFormatException("Введите да или нет");
        }
    }
    public String validateDirectorName(String rawName){
        return rawName;
    }

    public Date validateDirectorBirthday(String rawBirthday) throws OutOfRangeException, InvalidFormatException{
        try {
            Date date = DATE_FORMAT.parse(rawBirthday);
            if (date.after(new Date())) {
                throw new OutOfRangeException("Дата не может быть в будущем");
            }
            return date;
        } catch (ParseException e) {
            throw new InvalidFormatException("Неверный формат даты. Используйте yyyy-MM-dd HH:mm:ss");
        }
    }

    public Long validateDirectorHeight(String rawHeight)  throws OutOfRangeException, InvalidFormatException{
        try {
            Long value = Long.valueOf(rawHeight);
            if (value <= 0) {
                throw new OutOfRangeException("Рост должен быть больше 0");
            }
            return value;
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Рост должен быть числом");
        }
    }

    public Integer validateDirectorWeight(String rawWeight)  throws OutOfRangeException, InvalidFormatException {
        try {
            int value = Integer.parseInt(rawWeight);
            if (value <= 0) {
                throw new OutOfRangeException("Вес должен быть больше 0");
            }
            return value;
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Вес должен быть числом");
        }
    }

    public String validateDirectorPassportID(String rawPassportID)  throws OutOfRangeException{
        if (rawPassportID.length() < 6 || rawPassportID.length() > 47) {
            throw new OutOfRangeException("Длина паспорта должна быть от 6 до 47 символов");
        }
        return rawPassportID;
    }
}
