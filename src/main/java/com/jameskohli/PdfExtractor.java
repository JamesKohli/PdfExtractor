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
        if (args.length != 1) {
            LOGGER.error("Please run with the following format: java -jar PdfExtractor Input.pdf");
            throw new IllegalArgumentException();
        }

        write(args[0], args[0].replace("pdf", "csv"));
    }

    private static void write(String file, String outputFile) {
        LOGGER.info("Printing out pdf " + file);
        try {
            Extractor e = new Extractor(file);
            TextParser tp = new TextParser(e.getText());
            LOGGER.trace(tp.getText());
            tp.write(outputFile);
        } catch (IOException e1) {
            LOGGER.error("Error printing " + file, e1);
        }
    }
}
