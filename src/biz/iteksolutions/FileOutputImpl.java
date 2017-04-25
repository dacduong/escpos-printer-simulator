package biz.iteksolutions;

import java.io.File;

public class FileOutputImpl implements IPrinterOutput {

    private String path;
    private int size;

    public FileOutputImpl(String path, int size) {
        this.path = path;
        this.size = size;
    }

    @Override
    public void setText(String text) {
        File file = new File(path);
        if (file.exists() && file.length() > size * 1000) {
            file.delete();
        }
        Helper.writeToFile(path, text);
    }
}
