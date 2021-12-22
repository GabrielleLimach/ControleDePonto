package br.com.rh.batidas.utils;

import javax.xml.bind.ValidationException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateUtils {

    private LocalDateUtils() throws ValidationException {
        throw new ValidationException("classe não pode ser instaciada");
    }

    public static boolean isFinalDeSemana(LocalDate data) {
        return (data.getDayOfWeek().equals(DayOfWeek.SATURDAY) || data.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }

    public static LocalDateTime toConvertStringLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    public static Integer diferencaEntreHoras(LocalDateTime dtInicial, LocalDateTime dtFinal) {
        Integer diferenca = dtInicial.getHour() - dtFinal.getHour();
        return diferenca *(-1);
    }
}
