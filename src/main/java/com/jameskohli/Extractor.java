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
  private final String text;

  /**
   * Create a new extractor for a pdf
   * @param pdfPath The pdf resource to load into the extractor
   * @throws IOException
   */
  public Extractor(String pdfPath) throws IOException {
    pddoc = PDDocument.load(getClass().getResource(pdfPath));
    PDFTextStripper stripper = new PDFTextStripper();
    text = stripper.getText(pddoc);
  }

  /**
   * Return the text of the pdf
   * @return get the pdf text
   */
  public String getText() {
    return text;
  }
}
