package codemetropolis.toolchain.gui.utils;

import java.io.PipedOutputStream;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * {@link SwingWorker} sublclass for executing the CodeMetropolis toolchain.
 *
 * @author Adam Bankeszi {@literal <BAAVAGT.SZE>}
 */
public class ExecutionWorker extends SwingWorker<Void, Integer> {

  private boolean successful = false;
  private boolean showSuccessDialog;

  private JButton actionButton;
  private Executor executor;

  /**
   * Instantiates the {@link ExecutionWorker}.
   *
   * @param actionButton The button used to start the execution. This reference is required because at the start and at the end
   *   of the execution it will be disabled and re-enabled, respectively.
   * @param executor The controller instance that will do the actual execution.
   */
  public ExecutionWorker(JButton actionButton, Executor executor, PipedOutputStream out, boolean showSuccessDialog) {
    this.actionButton = actionButton;
    this.executor = executor;
    this.showSuccessDialog = showSuccessDialog;
  }
  
  @Override
  protected Void doInBackground() throws Exception {
    actionButton.setEnabled(false);
    executor.execute();
    successful = true;
    return null;
  }

  @Override
  protected void done() {

    actionButton.setEnabled(true);
    if(showSuccessDialog){
	    if (successful) {
	      JOptionPane.showMessageDialog(null, Translations.t("gui_info_world_gen_successful"),
	        Translations.t("gui_info_finished"), JOptionPane.INFORMATION_MESSAGE);
	    } else {
	      JOptionPane.showMessageDialog(null, Translations.t("gui_err_world_gen_failed"),
	        Translations.t("gui_err_title"), JOptionPane.ERROR_MESSAGE);
	    }
    }
    super.done();
  }

}
