package biz.iteksolutions.escpos.parser;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    public static final char LF = 0x0A;
    public static final char ESC = 0x1B;
    public static final char CR = 0x0D;
    public static final char NUL = 0x00;
    public static final char HT = 0x09;
    public static final char FF = 0x0C;
    public static final char GS = 0x1D;
    public static final char FS = 0x1C;
    public static final char DLE = 0x10;
    public static final char EOT = 0x04;
    public static final char ENQ = 0x05;
    public static final char CAN = 0x18;

    public static final Map<Character, String> commandsMap = new HashMap<>();
    public static final Map<Character, String> escCommandsMap = new HashMap<>();
    public static final Map<Character, String> escCCommandsMap = new HashMap<>();
    public static final Map<Character, String> gsCommandsMap = new HashMap<>();
    public static final Map<Character, String> gsOpenBracketCommandsMap = new HashMap<>();
    public static final Map<Character, String> gsVCommandsMap = new HashMap<>();

    public static final String ESC_COMMAND_ARR = "ESCCommandArr";
    public static final String ESC_C_COMMAND_ARR = "ESCCCommandArr";
    public static final String GS_COMMAND_ARR = "GSCommandArr";
    public static final String GS_OPEN_BRACKET_ARR = "gsOpenBracketArr";
    public static final String GS_V_ARR = "gsVArr";

    static {
        commandsMap.put(LF, LineFeedCommand.class.getName());
        commandsMap.put(ESC, ESC_COMMAND_ARR);
        commandsMap.put(CR, CarriageReturnCommand.class.getName());
        commandsMap.put(NUL, EscposCommand.class.getName());
        commandsMap.put(HT, EscposCommand.class.getName());
        commandsMap.put(FF, EscposCommand.class.getName());
        commandsMap.put(GS, GS_COMMAND_ARR);
        commandsMap.put(FS, TextChineseCommand.class.getName());
        commandsMap.put(DLE, EscposCommand.class.getName());
        commandsMap.put(CAN, EscposCommand.class.getName());

        escCommandsMap.put('@', InitCommand.class.getName());
        escCommandsMap.put('J', EscposCommand.class.getName());//print & feed
        escCommandsMap.put('d', LinesFeedCommand.class.getName());//print & feed lines
        escCommandsMap.put('p', PulseCommand.class.getName());//pulse
        escCommandsMap.put('!', CommandOneArg.class.getName());//select printer mode
        escCommandsMap.put('=', CommandOneArg.class.getName());//select peripheral device
        escCommandsMap.put('c', ESC_C_COMMAND_ARR);
        escCommandsMap.put('2', EscposCommand.class.getName());//SelectDefaultLineSpacing
        escCommandsMap.put('3', CommandOneArg.class.getName());//SelectLineSpacing
        escCommandsMap.put('R', CommandOneArg.class.getName());//SelectInternationalCharacterSet
        escCommandsMap.put('t', CommandOneArg.class.getName());//SelectCodeTable
        escCommandsMap.put('-', CommandOneArg.class.getName());//EnableUnderline
        escCommandsMap.put('G', CommandOneArg.class.getName());//EnableDoubleStrike
        escCommandsMap.put('M', CommandOneArg.class.getName());//SelectFont
        escCommandsMap.put('a', CommandOneArg.class.getName());//SelectJustification
        escCommandsMap.put('e', CommandOneArg.class.getName());//PrintAndReverseFeedLines
        escCommandsMap.put('$', CommandTwoArgs.class.getName());//SetAbsolutePrintPos
        escCommandsMap.put('E', CommandOneArg.class.getName());//EnableEmphasis

        escCCommandsMap.put('0', CommandOneArg.class.getName());//unknown command
        escCCommandsMap.put('1', CommandOneArg.class.getName());//unknown command
        escCCommandsMap.put('3', CommandOneArg.class.getName());//select paper end sensors
        escCCommandsMap.put('4', CommandOneArg.class.getName());//select print stop sensors
        escCCommandsMap.put('5', CommandOneArg.class.getName());//enable panel buttons

        gsCommandsMap.put('!', CommandOneArg.class.getName());//SelectCharacterSize
        gsCommandsMap.put('V', CutCommand.class.getName());//feed & cut
        gsCommandsMap.put('b', CommandOneArg.class.getName());//EnableSmoothing
        gsCommandsMap.put('h', CommandOneArg.class.getName());//barcode height
        gsCommandsMap.put('H', CommandOneArg.class.getName());//SelectHriPrintPos
        gsCommandsMap.put('k', PrintBarcodeCommand.class.getName());//print barcode
        gsCommandsMap.put('(', GS_OPEN_BRACKET_ARR);
        gsCommandsMap.put('v', GS_V_ARR);//gs v command

        gsOpenBracketCommandsMap.put('k', PrintQRCommand.class.getName());//QR code
        gsOpenBracketCommandsMap.put('C', EscposCommand.class.getName());
        gsOpenBracketCommandsMap.put('E', EscposCommand.class.getName());
        gsOpenBracketCommandsMap.put('K', EscposCommand.class.getName());
        gsOpenBracketCommandsMap.put('H', RequestResponseTransmissionCommand.class.getName());
        gsOpenBracketCommandsMap.put('L', GraphicsDataCommand.class.getName());

        gsVCommandsMap.put('0', PrintRasterBitImageCommand.class.getName());

    }

    public boolean addChar(Character c) {
        return false;
    }

}
