import java.io.File;

public class Main {

    private static final int NEW_WIDTH = 300;
    private static final String SRC_FOLDER = "src/main/src";
    private static final String DST_FOLDER = "src/main/dst";
    private static final File SRC_DIR = new File(SRC_FOLDER);
    private static final File[] FILES = SRC_DIR.listFiles();
    private static final int ONE_CORE = (int) Math.ceil(FILES.length / 6.0);


    public static void main(String[] args) {
        File[] files1 = new File[ONE_CORE];
        newThread(0, files1);

        File[] files2 = new File[ONE_CORE];
        newThread(ONE_CORE, files2);

        File[] files3 = new File[ONE_CORE];
        newThread(ONE_CORE * 2, files3);

        File[] files4 = new File[ONE_CORE];
        newThread(ONE_CORE * 3, files4);

        File[] files5 = new File[ONE_CORE];
        newThread(ONE_CORE * 4, files5);

        File[] files6 = new File[FILES.length - ONE_CORE * 5];
        newThread(ONE_CORE * 5, files6);
    }

    public static void newThread (int srcPosition, File[] newFiles) {
        System.arraycopy(FILES, srcPosition, newFiles, 0, newFiles.length);
        ImageResizer resizer = new ImageResizer(newFiles, NEW_WIDTH, DST_FOLDER, System.currentTimeMillis());
        new Thread(resizer).start();
    }
}