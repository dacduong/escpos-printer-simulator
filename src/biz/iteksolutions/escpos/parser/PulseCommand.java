package biz.iteksolutions.escpos.parser;

public class PulseCommand extends CommandThreeArgs implements IContentOutput {

    @Override
    public String getText() {
        return "<<PULSE>>";
    }
}
