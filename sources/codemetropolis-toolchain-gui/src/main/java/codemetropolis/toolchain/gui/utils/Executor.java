package codemetropolis.toolchain.gui.utils;


/**
 * Interface used by {@link ExecutionWorker}, it calls the {@link execute()} method.
 * @author Norbi
 *
 */
public interface Executor {
	
	/**
	 * Used to call other functions within its body.
	 */
	void execute();

}
