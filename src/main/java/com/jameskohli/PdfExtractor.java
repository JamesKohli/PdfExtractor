package com.jameskohli;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * PDF Extractor
 *
 */
public class PdfExtractor extends Application
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfExtractor.class);

    public static void main( String[] args ) {
        LOGGER.info("Starting PDF Extractor");
        launch(args);

       /* if (args.length != 1) {
            LOGGER.error("Please run with the following format: java -jar PdfExtractor Input.pdf");
            throw new IllegalArgumentException();
        }

        write(args[0], args[0].replace("pdf", "csv"));*/
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
        LOGGER.info("Successfully printed out " + file);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("PDF Extractor");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("PDF Extractor");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        Button button = new Button("Please select a pdf to extract...");
        button.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(button, 0, 1, 2, 1);
        final Text readingText = new Text();
        grid.add(readingText, 0, 5, 2, 1);
        final Text successText = new Text();
        grid.add(successText, 0, 6, 2, 1);

        final FileChooser fileChooser = new FileChooser();

        button.setOnAction(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent actionEvent) {
                                   File file = fileChooser.showOpenDialog(stage);
                                   if (file != null) {
                                       String fileName = file.getAbsolutePath();
                                       successText.setText("");
                                       readingText.setFill(Color.FIREBRICK);
                                       readingText.setText("Reading in pdf...");
                                       try {
                                           write(fileName, fileName.replace(".pdf", ".csv"));
                                           successText.setFill(Color.FIREBRICK);
                                           successText.setText("Successfully created " + fileName.replace(".pdf", ".csv") + "!");
                                       } catch (Exception e) {
                                           LOGGER.error("Error translation PDF", e);
                                           successText.setText("Error creating " + fileName.replace(".pdf", ".csv") + "!");
                                       }
                                   }
                               }
                           }
        );

        Scene scene = new Scene(grid, 600, 300);
        stage.setScene(scene);
        stage.show();
    }
}
