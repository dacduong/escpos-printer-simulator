package biz.iteksolutions.escpos.parser;

import java.util.ArrayList;
import java.util.List;

public class UnknownDataSubCommand extends Command {
    protected List<Character> data;
    protected int dataSize;

    public UnknownDataSubCommand(List<Character> data, int dataSize) {
        this.data = data;
        this.dataSize = dataSize;
    }

    @Override
    public boolean addChar(Character c) {
        if (data.size() < dataSize) {
            data.add(c);
            return true;
        }
        return false;
    }
}
