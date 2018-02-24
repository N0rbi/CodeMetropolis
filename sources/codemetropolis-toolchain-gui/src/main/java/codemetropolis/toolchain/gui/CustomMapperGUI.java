package codemetropolis.toolchain.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.text.DefaultCaret;

import codemetropolis.toolchain.gui.components.CMButton;
import codemetropolis.toolchain.gui.components.CMComboBox;
import codemetropolis.toolchain.gui.components.CMDnDButton;
import codemetropolis.toolchain.gui.components.CMDnDTabbedPane;
import codemetropolis.toolchain.gui.components.CMLabel;
import codemetropolis.toolchain.gui.components.CMTextArea;
import codemetropolis.toolchain.gui.components.listeners.CustomTransferHandler;
import codemetropolis.toolchain.gui.components.listeners.DragAndDropListener;
import codemetropolis.toolchain.gui.components.listeners.SaveListener;
import codemetropolis.toolchain.gui.components.listeners.ImportExportTransferHandler.ValueExportTransferHandler;
import codemetropolis.toolchain.gui.mapping.MetricItemType;
import codemetropolis.toolchain.gui.utils.Translations;
import codemetropolis.toolchain.gui.utils.XmlFileFilter;



/**
 * The customizer window. It works like the designer in VisualStudio / AndroidStudio etc. It has a drag and drop tab and
 * an xml editor tab.
 * 
 * @author N0rbi
 *
 */
public class CustomMapperGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private CMDnDButton select;
	private CMButton clear;
	private CMButton save;
	private static final long serialVersionUID = -8111464758014701842L;
	private CustomMapperController controller;
	private static final int COVER_IMAGE_COUNT = 4;
	private static final Random rng = new Random();
	protected CMTextArea textArea;
	private String[] metricsarray;
	private CMComboBox<String> comboBox;
	private CMDnDTabbedPane dndpanel;
	
	private DragAndDropListener dndListener;
	
	
	
	/**
	 * Instantiates the customizer window.
	 * @param controller The {@link CustomMapperController} instance.
	 */
	public CustomMapperGUI(CustomMapperController controller) {
		this.controller = controller;
		
		
	    ToolTipManager.sharedInstance().setInitialDelay(0);
	    ToolTipManager.sharedInstance().setDismissDelay(500);
	    
		JPanel panel = createBasePanel();
		addHeaderImages(panel);
		
	    this.setResizable(false);
	    this.setTitle("CodeMetropolis - Mapping");
	    this.setContentPane(panel);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    
	    Set<String> metricsset = mapMetricsString();
		metricsarray = sortMetrics(metricsset.toArray(new String[metricsset.size()]));
		comboBox = new CMComboBox<String>(sortMetrics(metricsarray));
	    
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    comboBox.setBounds(550,160, 220, 30);
	    comboBox.setEditable(true);
	    comboBox.addActionListener(this);

	    AutoCompletion.enable(comboBox);
	    JTextField text = (JTextField)comboBox.getEditor().getEditorComponent();
		text.setFocusable(true);	
		CMLabel selectLabel = new CMLabel(Translations.t("gui_c_selectMetric"), 550, 130, 120, 30);	
		CMLabel assignmentLabel = new CMLabel(Translations.t("gui_c_assignMetric"), 550, 250, 120, 30);
		panel.add(selectLabel);
		panel.add(assignmentLabel);		
		panel.add(comboBox); 
		addTextArea(panel);
		dndpanel = new CMDnDTabbedPane(textArea, controller);
		addMetricButton(panel);
		bindDragAndDrop();
		dndpanel.addTabbedPanel(panel);
		addSaveButton(panel);
		addClearButton(panel);
		
	}

	  
	  private void bindDragAndDrop() {
		  dndListener = new DragAndDropListener();
		  
		  select.addMouseMotionListener(dndListener);
		  select.addMouseListener(dndListener);
	}


	private Set<String> mapMetricsString() {
		Set<String> ss = new HashSet<>();
		Set<MetricItemType> metrics = this.controller.getMetrics();
		for(MetricItemType m : metrics){
			ss.add(m.getName());
		}
		return ss;
	  }
	  
	  public String[] sortMetrics(String[] anArray){
			 Arrays.sort(anArray);
			return anArray;	
		}


	public void addMetricButton(JPanel panel){ //550,160, 220, 30
		  select = new CMDnDButton((String)comboBox.getSelectedItem(), 585, 195, 152, 30);		  
		  select.addActionListener(this);
		  select.setVisible(true);
		  select.setTransferHandler(new ValueExportTransferHandler((String)comboBox.getSelectedItem()));

		  panel.add(select);
		  
	  }
	
	  private void addTextArea(JPanel panel) {
		    textArea = new CMTextArea();
		    textArea.setEditable(false);
		    textArea.setDragEnabled(true);
		    textArea.setTransferHandler(new CustomTransferHandler());
		    DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		    JScrollPane pane = new JScrollPane(textArea);
		    pane.setBounds(550, 280, 220, 270);
		    panel.add(pane);
		  }
	  
	  public void addClearButton(JPanel panel){ //550,160, 220, 30
		  clear = new CMButton("Clear", 665, 560, 108, 30);
		  clear.addActionListener(this);
		  clear.setVisible(true);
		 panel.add(clear);
	  }
	  
	  public void addSaveButton(JPanel panel){ //550,160, 220, 30
		  save = new CMButton("Save", 665, 590, 108, 30);
		  save.addActionListener(new SaveListener(JFileChooser.FILES_ONLY, new XmlFileFilter(), controller, textArea));
		  save.setVisible(true);
		  panel.add(save);
	  }

	private final void addHeaderImages(JPanel panel) {
	    JPanel headerPanel = new JPanel();
	    headerPanel.setLayout(null);
	    Image coverImage = new ImageIcon(ClassLoader.getSystemResource("images/cm-background-"
	    + (rng.nextInt(COVER_IMAGE_COUNT) + 1) + ".png")).getImage().getScaledInstance(810, 500, Image.SCALE_SMOOTH);
	    ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("images/cm-logo-border.png"));
	    JLabel coverImageContainer = new JLabel(new ImageIcon(coverImage));
	    coverImageContainer.setBounds(0, 0, 810, 250);
	    setIconImage(logoIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
	    headerPanel.setBounds(0, 0, 810, 100);
	    headerPanel.add(coverImageContainer);
	    panel.add(headerPanel);
	  }
	
	  /**
	   * Creates the base panel for the Customizer GUI.
	   *
	   * @return The generated {@link JPanel}.
	   */
	private static final JPanel createBasePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 650);
	
		Dimension size = new Dimension(800, 650);
		panel.setMinimumSize(size);
		panel.setPreferredSize(size);
		panel.setMaximumSize(size);
	
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (comboBox == e.getSource()){
			String var = (String)comboBox.getSelectedItem();
			select.setTransferHandler(new ValueExportTransferHandler((String)comboBox.getSelectedItem()));
			select.setText(var);
			}
		if (clear == e.getSource()){
			dndpanel.clearAll();
		}
		

	}

}
