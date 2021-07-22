package oss.util;

public class NullCommand implements Command {
    @Override
    public void execute() {
        return;
    }
}
