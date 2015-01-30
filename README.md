# PdfExtractor
This reads in pdfs and converts them into csv format, so they can be loaded into excel.
As of version 1.0, usage is as follows:

java -jar PdfExtractor-1.0.jar FileName.pdf

The output file will be saved to the same location with a .csv extension.

It also does some preliminary parsing to get it into a usable table format. At the moment, it just deletes blank lines and replaces strings of . characters with commas, as that's how the initial target pdfs appeared. I'm happy to add more parsing options if it would be helpful.
