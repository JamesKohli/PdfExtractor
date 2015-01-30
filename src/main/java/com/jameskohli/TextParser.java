package com.jameskohli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by James on 1/30/2015.
 * Parses pdf text and allows it to be written to a file.
 */
public class TextParser {

  private static final Logger LOGGER = LoggerFactory.getLogger(TextParser.class);
  private final String text;
  private String name;

  /**
   *
   * @param text The text to be parsed into csv format
   */
  public TextParser(String text) {
    this.text = parse(text);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   *
   * @return The post parsed text
   */
  public String getText() {
    return text;
  }

  /**
   * Writes out a csv of the TextParser's text
   * @param fileName the name of the file to be written
   */
  public void write(String fileName) {
    try {
      PrintWriter writer = new PrintWriter(fileName, "UTF-8");
      LOGGER.info("Writing out file " + fileName);
      writer.write(text);
      writer.close();
      LOGGER.info("Successfully wrote out file " + fileName);
    } catch (FileNotFoundException e) {
      LOGGER.error("Error writing file", e);
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("Error writing file", e);
    }
  }

  private String parse(String text) {
    text = text.replaceAll("\\,", ""); //Delete all initial commas
    text = text.replaceAll("\\.\\.+", ","); //Replace all strings of periods with commas
    text = text.replaceAll("(?m)^[ \t]*\r?\n", ""); // Delete all blank lines
    return text;
  }

}
