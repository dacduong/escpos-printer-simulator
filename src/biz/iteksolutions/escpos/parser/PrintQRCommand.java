package biz.iteksolutions.escpos.parser;

public class PrintQRCommand extends DataCommand implements IContentOutput {

    private boolean contentFlag;

    public void setContentFlag(boolean contentFlag) {
        this.contentFlag = contentFlag;
    }

    @Override
    public String getText() {
        if (contentFlag) {
            StringBuilder sb = new StringBuilder();
            //TODO - check if need to ignore first character 0 from result
            for (int i = 1; i < data.size(); i++) {
                sb.append(data.get(i));
            }
            return "\\\\QR{" + sb.toString() + "}";
        }
        return "";
    }
}
