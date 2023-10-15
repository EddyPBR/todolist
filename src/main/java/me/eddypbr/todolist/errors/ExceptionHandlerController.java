package me.eddypbr.todolist.errors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ExceptionHandlerController {

  private static final Pattern ENUM_MSG = Pattern.compile("values accepted for Enum class: \\[([^\\]]+)]");

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

    if (e.getCause() != null && e.getCause() instanceof InvalidFormatException) {
      Matcher match = ENUM_MSG.matcher(e.getCause().getMessage());

      if (match.find()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Enum type expected: " + match.group(1));
      }
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
  }
}
