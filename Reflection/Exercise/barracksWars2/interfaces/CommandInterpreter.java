package barracksWars2.interfaces;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName);
}
