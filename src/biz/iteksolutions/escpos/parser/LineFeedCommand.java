package biz.iteksolutions.escpos.parser;

public class LineFeedCommand extends Command implements IContentOutput {

    @Override
    public String getText() {
        return "\n";
    }
}
