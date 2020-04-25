package com.smartoffice.climate.validation.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import javax.validation.ConstraintValidatorContext;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author michael_loibl
 * @since 16.03.20
 */
public class CustomDateValidatorTest {

  @Mock
  private ConstraintValidatorContext constraintValidatorContext;
  @Mock
  private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;
  @Captor
  private ArgumentCaptor<String> messageCaptor;

  @BeforeEach
  public void setUp() {
    initMocks(this);
    when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString()))
        .thenReturn(constraintViolationBuilder);
    when(constraintViolationBuilder.addConstraintViolation())
        .thenReturn(constraintValidatorContext);
  }

  @Test
  public void isValidIsTrueCorrectDateFormat() {
    //given
    final CustomDateValidator validator = new CustomDateValidator();

    //when
    boolean validationResult = validator.isValid("2020-02-10", constraintValidatorContext);

    //then
    assertThat(validationResult, is(true));
  }

  @Test
  public void isValidIsFalseBecauseNullDate() {
    //given
    final CustomDateValidator validator = new CustomDateValidator();

    //when
    boolean validationResult = validator.isValid(null, constraintValidatorContext);

    //then
    assertThat(validationResult, is(false));
    verify(constraintValidatorContext).disableDefaultConstraintViolation();
    verify(constraintValidatorContext).buildConstraintViolationWithTemplate(messageCaptor.capture());
    assertThat(messageCaptor.getValue(), equalTo("date format must be yyyy-MM-dd"));
  }

  @Test
  public void isValidIsFalseBecauseEmptyDate() {
    //given
    final CustomDateValidator validator = new CustomDateValidator();

    //when
    boolean validationResult = validator.isValid("", constraintValidatorContext);

    //then
    assertThat(validationResult, is(false));
    verify(constraintValidatorContext).disableDefaultConstraintViolation();
    verify(constraintValidatorContext).buildConstraintViolationWithTemplate(messageCaptor.capture());
    assertThat(messageCaptor.getValue(), equalTo("date format must be yyyy-MM-dd"));
  }

  @Test
  public void isValidIsFalseIncorrectDateFormat() {
    //given
    final CustomDateValidator validator = new CustomDateValidator();

    //when
    boolean validationResult = validator.isValid("20200210", constraintValidatorContext);

    //then
    assertThat(validationResult, is(false));
    verify(constraintValidatorContext).disableDefaultConstraintViolation();
    verify(constraintValidatorContext).buildConstraintViolationWithTemplate(messageCaptor.capture());
    assertThat(messageCaptor.getValue(), equalTo("date format must be yyyy-MM-dd"));
  }
}
