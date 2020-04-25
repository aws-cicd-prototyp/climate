package com.smartoffice.climate.validation.control;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
@ApplicationScoped
public class CustomDateValidator implements ConstraintValidator<CustomDate, String> {

  public final static String REQUIRED_DATE_FORMAT = "yyyy-MM-dd";

  @Override
  public void initialize(final CustomDate pCustomDate) {
    // no init. necessary
  }

  @Override
  public boolean isValid(final String input, final ConstraintValidatorContext context) {
    boolean isDateValid = isValid(input);
    if (!isDateValid) {
      addCustomValidationMessage(context);
    }
    return isDateValid;
  }

  private boolean isValid(String input) {
    if (input == null || input.isEmpty()) {
      return false;
    }
    try {
      LocalDate.parse(input);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }

  private void addCustomValidationMessage(ConstraintValidatorContext context) {
    context.buildConstraintViolationWithTemplate("date format must be " + REQUIRED_DATE_FORMAT)
        .addConstraintViolation()
        .disableDefaultConstraintViolation();
  }
}
