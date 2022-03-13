package com.haufe.test.beer.catalogue.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

  public static LocalDate getDate(String date, String datePattern) {
    try {

      if(date == null){
        return null;
      }

      date = normalizeDate(date);

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
      return LocalDate.parse(date, formatter);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  private static String normalizeDate(String date) {

    Pattern pattern = Pattern.compile("^\\d{2}/\\d{4}", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(date);
    if(matcher.find()){
      date = "01/" + date;
    }

    return date;
  }
}
