package biz.iteksolutions.escpos.parser;

import java.util.List;

public class BarcodeBData extends Command {
    protected List<Character> data;
    protected int len;

    public BarcodeBData(List<Character> data) {
        this.data = data;
    }

    @Override
    public boolean addChar(Character c) {
        if (len == 0) {
            len = (int) c;
            return true;
        }
        if (data.size() < len) {
            data.add(c);
            return true;
        }
        return false;
    }
}
