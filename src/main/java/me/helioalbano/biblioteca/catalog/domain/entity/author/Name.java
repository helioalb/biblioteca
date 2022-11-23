package me.helioalbano.biblioteca.catalog.domain.entity.author;

public class Name {

  private final String[] fullName;

  public Name(final String fullName) {
    if (fullName == null)
      throw new IllegalArgumentException("name.null");

    this.fullName = split(fullName);
  }

  private String[] split(String name) {
    var splitName = name.split(" ");

    if (splitName.length < 2)
      throw new IllegalArgumentException("name.length.less.than.two.parts");

    return splitName;
  }

  public String getFirstName() {
    return fullName[0];
  }

  public String getLastName() {
    return fullName[fullName.length - 1];
  }

  @Override
  public String toString() {
    return String.join(" ", fullName);
  }
}
