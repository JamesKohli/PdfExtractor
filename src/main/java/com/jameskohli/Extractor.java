package com.jameskohli;

import org.apache.pdfbox.PDFBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * Created by James on 1/30/2015.
 */
public class Extractor {

  private final PDDocument pddoc;
  private static final Logger LOGGER = LoggerFactory.getLogger(Extractor.class);

  public Extractor(String pdfPath) throws IOException {
    pddoc = PDDocument.load(getClass().getResource(pdfPath));
  }

  public String getText() {
    String text = null;
    try {
      PDFTextStripper stripper = new PDFTextStripper();
      text = stripper.getText(pddoc);
    } catch (IOException e) {
      LOGGER.error("Could not get text from document", e);
    }
    return text;
  }
}
