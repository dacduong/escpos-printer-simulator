package biz.iteksolutions.escpos.parser;

import java.util.List;

public class BarcodeAData extends Command {

    protected List<Character> data;
    protected boolean done;

    public BarcodeAData(List<Character> data) {
        this.data = data;
    }

    @Override
    public boolean addChar(Character c) {
        if (done) {
            return false;
        }
        if (c == Command.NUL) {
            done = true;
        } else {
            data.add(c);
        }
        return true;
    }
}
