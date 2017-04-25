package biz.iteksolutions.escpos.parser;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Printer extends Command {

    public static final int CUT_FULL = 48;
    public static final int CUT_HALF = 49;
    public static final int CUT_FULL_FEED = 65;
    public static final int CUT_HALF_FEED = 66;

    private List<Command> commands;
    private Map<Character, String> search;
    private List<Character> searchStack;

    public Printer() {
        commands = new ArrayList<>();
        searchStack = new ArrayList<>();
        reset();
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void reset() {
        search = null;
        searchStack.clear();
    }

    @Override
    public boolean addChar(Character c) {
        if (searchStack.size() > 0) {
            navigateCommand(c);
            return true;
        }

        if (commands.size() > 0) {
            Command top = commands.get(commands.size() - 1);
            boolean ret = top.addChar(c);
            if (ret) {
                return true;
            }
        }

        if (commands.isEmpty() || !(commands.get(commands.size() - 1) instanceof TextCommand)) {
            TextCommand top = new TextCommand();

            //handle special content command
            handleTextCommand(top);

            if (top.addChar(c)) {
                commands.add(top);
                return true;
            }
        }

        search = Command.commandsMap;
        return navigateCommand(c);
    }

    private boolean navigateCommand(Character c) {
        searchStack.add(c);
        if (!search.keySet().contains(c)) {
            System.out.println("Warning - Unknown command: " + c);
            reset();
            return false;
        }

        String type = search.get(c);
        if (type.indexOf("Arr") > 0) {
            switch (type) {
                case Command.ESC_COMMAND_ARR:
                    search = Command.escCommandsMap;
                    break;
                case Command.GS_COMMAND_ARR:
                    search = Command.gsCommandsMap;
                    break;
                case Command.GS_OPEN_BRACKET_ARR:
                    search = Command.gsOpenBracketCommandsMap;
                    break;
            }
            return true;
        }

        try {
            Class<?> clazz = Class.forName(type);
            //Constructor<?> ctor = clazz.getConstructor(String.class);
            Constructor<?> ctor = clazz.getConstructor();
            Command cmd = (Command) ctor.newInstance();

            commands.add(cmd);
            reset();
            return true;
        } catch (Exception e) {
            System.out.println("Class has problem: " + type);
            e.printStackTrace();
        }

        return false;
    }

    private void handleTextCommand(TextCommand textCommand) {
        //check if TextChineseCommand
        if (commands.size() > 0) {
            Command top = commands.get(commands.size() - 1);
            if (top instanceof TextChineseCommand) {
                textCommand.setChinese(true);
            }
        }
    }

}
