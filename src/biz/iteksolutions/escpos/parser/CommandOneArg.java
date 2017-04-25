package biz.iteksolutions.escpos.parser;

public class CommandOneArg extends Command {

    protected Character arg;

    @Override
    public boolean addChar(Character c) {
        if (arg == null) {
            arg = c;
            return true;
        }
        return false;
    }
}
