package com.app.userService.user.domain.valueObjects;

import com.app.userService.user.domain.exceptions.ValueObjectValidationException;

import java.util.Objects;

public class UserName  extends ValueObjectAbstract {

  private final String value;

  private UserName(String value) {
    this.value = validate(value);
  }

  public static UserName of(String value) {
    return new UserName(value);
  }

  public String getValue() {
    return value;
  }

  private String validate(String value) {
    validateNotNullOrEmpty(value, "User name");
    if (value.trim().isEmpty()) {
      throw new ValueObjectValidationException("UserName","User name cannot be empty");
    }
    if (value.length() < 3 || value.length() > 100) {
      throw new ValueObjectValidationException("UserName","The User name must be between 3 and 100 characters long.");
    }

    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserName that = (UserName) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value;
  }
}