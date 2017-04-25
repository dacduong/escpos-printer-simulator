package biz.iteksolutions.escpos.parser;

import java.util.ArrayList;
import java.util.List;

public class PrintBarcodeCommand extends Command implements IContentOutput {
    protected int m;
    protected Command subCommand;
    protected List<Character> data = new ArrayList<>();

    @Override
    public boolean addChar(Character c) {
        if (m == 0) {
            m = (int) c;
            if (m >= 0 && m <= 6) {
                subCommand = new BarcodeAData(data);
            } else if (m >= 65 && m <= 78) {
                subCommand = new BarcodeBData(data);
            }
            return true;
        }
        if (subCommand == null) {
            return false;
        }
        return subCommand.addChar(c);
    }

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i));
        }
        return "\\\\Bar{" + sb.toString() + "}";
    }

}
