package biz.iteksolutions.escpos.parser;

public class CommandThreeArgs extends Command {

    protected Character arg1;
    protected Character arg2;
    protected Character arg3;

    @Override
    public boolean addChar(Character c) {
        if (arg1 == null) {
            arg1 = c;
            return true;
        } else if (arg2 == null) {
            arg2 = c;
            return true;
        } else if (arg3 == null) {
            arg3 = c;
            return true;
        }
        return false;
    }
}
