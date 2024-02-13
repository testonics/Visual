package in.testonics.omni.image.model;

import in.testonics.omni.image.VisualComparisonUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Data transfer objects which contains all the needed data for result of the comparison.
 */
public class VisualComparisonResult {

    /**
     * {@link BufferedImage} object of the expected.
     */
    private BufferedImage expected;

    /**
     * {@link BufferedImage} object of the actual.
     */
    private BufferedImage actual;

    /**
     * {@link BufferedImage} object of the comparison result.
     */
    private BufferedImage result;

    /**
     * State of the comparison.
     */
    private VisualComparisonState imageComparisonState;

    /**
     * The difference percentage between two images.
     */
    private float differencePercent;

    /**
     * Rectangles of the differences
     */
    private List<Rectangle> rectangles;

    /**
     * Create default instance of the {@link VisualComparisonResult} with {@link VisualComparisonState#SIZE_MISMATCH}.
     *
     * @param expected          expected {@link BufferedImage} object.
     * @param actual            actual {@link BufferedImage} object.
     * @param differencePercent the percent of the differences between images.
     * @return instance of the {@link VisualComparisonResult} object.
     */
    public static VisualComparisonResult defaultSizeMisMatchResult(BufferedImage expected, BufferedImage actual,
                                                                   float differencePercent) {
        return new VisualComparisonResult()
                .setImageComparisonState(VisualComparisonState.SIZE_MISMATCH)
                .setDifferencePercent(differencePercent)
                .setExpected(expected)
                .setActual(actual)
                .setResult(actual)
                .setRectangles(Collections.emptyList());
    }

    /**
     * Create default instance of the {@link VisualComparisonResult} with {@link VisualComparisonState#MISMATCH}.
     *
     * @param expected expected {@link BufferedImage} object.
     * @param actual   actual {@link BufferedImage} object.
     * @param differencePercent the persent of the differences between images.
     * @return instance of the {@link VisualComparisonResult} object.
     */
    public static VisualComparisonResult defaultMisMatchResult(BufferedImage expected, BufferedImage actual, float differencePercent) {
        return new VisualComparisonResult()
                .setImageComparisonState(VisualComparisonState.MISMATCH)
                .setDifferencePercent(differencePercent)
                .setExpected(expected)
                .setActual(actual)
                .setResult(actual);
    }

    /**
     * Create default instance of the {@link VisualComparisonResult} with {@link VisualComparisonState#MATCH}.
     *
     * @param expected expected {@link BufferedImage} object.
     * @param actual   actual {@link BufferedImage} object.
     * @return instance of the {@link VisualComparisonResult} object.
     */
    public static VisualComparisonResult defaultMatchResult(BufferedImage expected, BufferedImage actual) {
        return new VisualComparisonResult()
                .setImageComparisonState(VisualComparisonState.MATCH)
                .setExpected(expected)
                .setActual(actual)
                .setResult(actual)
                .setRectangles(Collections.emptyList());
    }

    /**
     * Save the image to the provided {@link File} object.
     *
     * @param file the provided {@link File} object.
     * @return this {@link VisualComparisonResult} object.
     */
    public VisualComparisonResult writeResultTo(File file) {
        VisualComparisonUtil.saveImage(file, result);
        return this;
    }

    public BufferedImage getExpected() {
        return expected;
    }

    public VisualComparisonResult setExpected(BufferedImage expected) {
        this.expected = expected;
        return this;
    }

    public BufferedImage getActual() {
        return actual;
    }

    public VisualComparisonResult setActual(BufferedImage actual) {
        this.actual = actual;
        return this;
    }

    public BufferedImage getResult() {
        return result;
    }

    public VisualComparisonResult setResult(BufferedImage result) {
        this.result = result;
        return this;
    }

    public VisualComparisonState getImageComparisonState() {
        return imageComparisonState;
    }

    public VisualComparisonResult setImageComparisonState(VisualComparisonState imageComparisonState) {
        this.imageComparisonState = imageComparisonState;
        return this;
    }

    public float getDifferencePercent() {
        return differencePercent;
    }

    VisualComparisonResult setDifferencePercent(float differencePercent) {
        this.differencePercent = differencePercent;
        return this;
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public VisualComparisonResult setRectangles(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
        return this;
    }
}
