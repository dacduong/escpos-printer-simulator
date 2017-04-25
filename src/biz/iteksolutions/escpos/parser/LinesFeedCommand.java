package biz.iteksolutions.escpos.parser;

public class LinesFeedCommand extends CommandOneArg implements IContentOutput {

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        int lines = (int) arg;
        //System.out.println("LinesFeedCommand: " + lines);
        for (int i = 0; i < lines; i++)
        {
            sb.append("\n");
        }
        return sb.toString();
     }
}
