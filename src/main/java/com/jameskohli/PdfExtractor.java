package com.jameskohli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * PDF Extractor
 *
 */
public class PdfExtractor
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfExtractor.class);

    public static void main( String[] args )
    {
        LOGGER.info("Starting PDF Extractor");
        if (args.length == 0) {
            LOGGER.error("Please run with a pdf file path parameter. eg: java -jar PdfExtractor.java sample-pdf.pdf");
            throw new IllegalArgumentException();
        }

        for (String arg : args) {
            LOGGER.info("Printing out pdf " + arg);
            try {
                Extractor e = new Extractor(arg);
                LOGGER.info(e.getText());
            } catch (IOException e1) {
                LOGGER.error("Error printing " + arg, e1);
            }
        }
    }
}
