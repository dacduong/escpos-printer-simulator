package biz.iteksolutions.escpos.parser;

public class CutCommand extends CommandTwoArgs implements IContentOutput {

    @Override
    public String getText() {
        int mode = (int) arg1;
        int lines = 0;
        if (arg2 != null) {
            lines = (int) arg2;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines; i++)
            sb.append("\n");
        if (mode == Printer.CUT_FULL || mode == Printer.CUT_FULL_FEED)
            sb.append("<<CUT_FULL>>");
        else if (mode == Printer.CUT_HALF || mode == Printer.CUT_HALF_FEED)
            sb.append("<<CUT_HALF>>");
        else
            sb.append("<<CUT_INVALID_CMD>>");
        return sb.toString();
    }
}
