package biz.iteksolutions.escpos.parser;

import java.util.ArrayList;
import java.util.List;

public class DataCommand extends Command {
    protected Character p1;
    protected Character p2;
    protected Character arg1;
    protected Character arg2;
    protected List<Character> data = new ArrayList<>();
    protected int dataSize;
    protected Command subCommand;

    @Override
    public boolean addChar(Character c) {
        if (p1 == null) {
            p1 = c;
            return true;
        } else if (p2 == null) {
            p2 = c;
            dataSize = p1 + p2 * 256;
            return true;
        } else if (arg1 == null) {
            arg1 = c;
            return true;
        } else if (arg2 == null) {
            arg2 = c;
            subCommand = getSubCommand(arg1, arg2, dataSize - 2);
            return true;
        }
        return subCommand.addChar(c);
    }

    public Command getSubCommand(Character arg1, Character arg2, int len) {
        return new UnknownDataSubCommand(data, len);
    }
}
