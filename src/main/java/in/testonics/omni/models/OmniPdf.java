package in.testonics.omni.models;

import in.testonics.omni.utils.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OmniPdf extends FileUtils {

    public List<String> CompareFiles(File pdfFile1, File pdfFile2, int pageNumber) throws Exception {
        setColumnNames();
        System.out.println("Comparing PDF files (" + pdfFile1 + "," + pdfFile2 + ")");
        PDDocument pdf1 = PDDocument.load(pdfFile1);
        PDDocument pdf2 = PDDocument.load(pdfFile2);
        PDPageTree pdf1pages = pdf1.getDocumentCatalog().getPages();
        PDPageTree pdf2pages = pdf2.getDocumentCatalog().getPages();
        int numberOfPages = pdf1pages.getCount();

        try {
            if (pdf1pages.getCount() != pdf2pages.getCount()) {
                String message = "Number of pages in the files (" + pdfFile1 + "," + pdfFile2 + ") do not match. pdfFile1 has " + pdf1pages.getCount() + " no pages, while pdf2pages has " + pdf2pages.getCount() + " no of pages";
                System.out.println(message);
                throw new Exception(message);
            }

            //Overwritten protected method to get font and size of the text
            PDFTextStripper pdfStripper1 = new PDFTextStripper() {
                String prevBaseFont = "";
                String prevBaseFontSize = "";

                protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                    StringBuilder builder = new StringBuilder();

                    for (TextPosition position : textPositions) {
                        String baseFont = position.getFont().getFontDescriptor().getFontName();
                        String baseFontSize = String.valueOf(position.getFontSizeInPt());
                        if (baseFont != null && !baseFont.equals(prevBaseFont)) {
                            builder.append('[').append(baseFont).append(']');
                            prevBaseFont = baseFont;
                        }
                        if (!baseFontSize.equals(prevBaseFontSize)) {
                            builder.append('[').append(baseFontSize).append(']');
                            prevBaseFontSize = baseFontSize;
                        }
                        builder.append(position.getUnicode());
                    }
                    writeString(builder.toString());
                }
            };


            //Overwritten protected method to get font and size of the text
            PDFTextStripper pdfStripper2 = new PDFTextStripper() {
                String prevBaseFont = "";
                String prevBaseFontSize = "";

                protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                    StringBuilder builder = new StringBuilder();

                    for (TextPosition position : textPositions) {
                        String baseFont = position.getFont().getFontDescriptor().getFontName();
                        String baseFontSize = String.valueOf(position.getFontSizeInPt());
                        if (baseFont != null && !baseFont.equals(prevBaseFont)) {
                            builder.append('[').append(baseFont).append(']');
                            prevBaseFont = baseFont;
                        }
                        if (!baseFontSize.equals(prevBaseFontSize)) {
                            builder.append('[').append(baseFontSize).append(']');
                            prevBaseFontSize = baseFontSize;
                        }
                        builder.append(position.getUnicode());
                    }
                    writeString(builder.toString());
                }
            };

            //To validate only a specific page in PDF
            if (!(pageNumber==0)) numberOfPages = 1;

            System.out.println("pdf1pages.size() is :- " + pdf1pages.getCount());
            for (int i = 0; i < numberOfPages; i++) {
                int pageNumberToValidate = (i+1);
                if (!(pageNumber==0)) pageNumberToValidate = pageNumber;
                pdfStripper1.setStartPage(pageNumberToValidate);
                pdfStripper1.setEndPage(pageNumberToValidate);
                pdfStripper2.setStartPage(pageNumberToValidate);
                pdfStripper2.setEndPage(pageNumberToValidate);
                String pdf1PageText = pdfStripper1.getText(pdf1);
                String pdf2PageText = pdfStripper2.getText(pdf2);
                String[] pdf1PageTextLines = pdf1PageText.split("\n");
                String[] pdf2PageTextLines = pdf2PageText.split("\n");
                if (!pdf1PageText.equals(pdf2PageText)) {
                    if (pdf1PageTextLines.length != pdf2PageTextLines.length) {
                        throw new Exception("Number of lines are not on same on page # " + pageNumberToValidate + ". Hence no further validation done");
                    } else {
                        for (int j = 0; j < pdf1PageTextLines.length; j++) {
                            if (!pdf1PageTextLines[j].equals(pdf2PageTextLines[j])) {
                                errors.add("Page#: " + pageNumberToValidate + " | Line#: " + (j + 1) + "," + pdf1PageTextLines[j] + "," + pdf2PageTextLines[j]);
                            }
                        }
                    }
                }
            }
            System.out.println("Returning True , as PDF Files (" + pdfFile1 + "," + pdfFile2 + ") get matched");
        } finally {
            pdf1.close();
            pdf2.close();
        }
        return errors;
    }

    public String getFileText(File pdfFile) throws Exception {
        return getFileText(pdfFile,0);
    }

    public String getFileText(String pdfFile, int pageNumber) throws Exception{
        return getFileText(new File(pdfFile),pageNumber);
    }

    public String getFileText(File pdfFile, int pageNumber) throws Exception{
        PDDocument pdf = PDDocument.load(pdfFile);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        if (pageNumber != 0){
            pdfStripper.setStartPage(pageNumber);
            pdfStripper.setEndPage(pageNumber);
        }
        return pdfStripper.getText(pdf);
    }
}
