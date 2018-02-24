package codemetropolis.toolchain.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.TransferHandler;
import javax.swing.text.DefaultCaret;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import codemetropolis.toolchain.gui.components.CMButton;
import codemetropolis.toolchain.gui.components.CMComboBox;
import codemetropolis.toolchain.gui.components.CMLabel;
import codemetropolis.toolchain.gui.components.CMTextArea;
import codemetropolis.toolchain.gui.utils.Translations;
import codemetropolis.toolchain.gui.ImportExportTransferHandler.ValueExportTransferHandler;



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
	@SuppressWarnings("unused")
	private CustomMapperController controller;
	private static final int COVER_IMAGE_COUNT = 4;
	private static final Random rng = new Random();
	protected CMTextArea textArea;
	Set<String> metricsset = new HashSet<String>(Arrays.asList(getMetrics()));
	String[] metricsarray = metricsset.toArray(new String[metricsset.size()]);
	CMLabel label = new CMLabel(Translations.t("gui_l_project_name"), 15, 325, 120, 30);
	CMComboBox<String> comboBox = new CMComboBox<String>(sortMetrics(metricsarray));
	CMDnDTabbedPane dndpanel = new CMDnDTabbedPane(); //tabbed
	
	
	
	/**
	 * Instantiates the customizer window.
	 * @param controller The {@link CustomMapperController} instance.
	 */
	
	public CustomMapperGUI(){
		
	}
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
		addMetricButton(panel);
		dndpanel.addTabbedPanel(panel);						//tabbed
		addSaveButton(panel);
		addClearButton(panel);
	}

	  
	  public void addMetricButton(JPanel panel){ //550,160, 220, 30
		  select = new CMDnDButton((String)comboBox.getSelectedItem(), 585, 195, 152, 30);		  
		  select.addActionListener(this);
		  select.setVisible(true);
		  select.setTransferHandler(new ValueExportTransferHandler((String)comboBox.getSelectedItem()));
		  select.addMouseMotionListener(new MouseAdapter() {
              public void mouseDragged(MouseEvent e) {
                  CMButton button = (CMButton) e.getSource();
                  TransferHandler handle = button.getTransferHandler();
                  handle.exportAsDrag(button, e, TransferHandler.COPY);
              } 
		  });
		  
		  select.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("mousereleased");
				textArea.append(dndpanel.lastModified()+"\n");
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			  
		  });

		  panel.add(select);
		  
	  }
	
	  private void addTextArea(JPanel panel) {
		    textArea = new CMTextArea();
		    textArea.setEditable(false);
		    textArea.setDragEnabled(true);
		    textArea.setTransferHandler(new CustomTransferHandler());
		    textArea.setText("Valami2");
		    DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		    JScrollPane pane = new JScrollPane(textArea);
		    pane.setBounds(550, 280, 220, 300);
		    panel.add(pane);
		  }
	  
	  public void addClearButton(JPanel panel){ //550,160, 220, 30
		  clear = new CMButton("Clear", 550, 590, 108, 30);
		  clear.addActionListener(this);
		  clear.setVisible(true);
		 panel.add(clear);
	  }
	  
	  public void addSaveButton(JPanel panel){ //550,160, 220, 30
		  save = new CMButton("Save", 665, 590, 108, 30);
		  //save.addActionListener(this);
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

	public String[] sortMetrics(String[] anArray){
		 Arrays.sort(anArray);
		return anArray;	
	}

	
	public String[] getMetrics(){
    	
    	String[] metrics = null;
        try {
            String xmlFile = "C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Roaming\\.minecraft\\.code-metropolis\\.temp\\converter-results.xml";
            File file = new File(xmlFile);
            if(file.exists()){
                DocumentBuilderFactory factory = 
                DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                org.w3c.dom.Document doc = builder.parse(xmlFile);
                NodeList list = doc.getElementsByTagName("property");
                int length = list.getLength();
            	metrics = new String[length];
                for (int i=0; i<list.getLength(); i++) {
                			Element element = (Element)list.item(i);
                		metrics[i] = element.getAttribute("name");
                }
            } else { System.out.print("File not found!"); }
            }
        catch (Exception e) {
            System.exit(1);
        }
		return metrics;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (comboBox == e.getSource()){
			String var = (String)comboBox.getSelectedItem();
			select.setTransferHandler(new ValueExportTransferHandler((String)comboBox.getSelectedItem()));
			select.setText(var);
			}
		if (clear == e.getSource()){
			dndpanel.floor1.setText("");
			dndpanel.floor1.acceptDrop = true;
			dndpanel.floor1.setColorFree();
			
			
			dndpanel.floor2.setText("");
			dndpanel.floor2.acceptDrop = true;
			dndpanel.floor2.setColorFree();
			
			dndpanel.floor3.setText("");
			dndpanel.floor3.acceptDrop = true;
			dndpanel.floor3.setColorFree();
			
			dndpanel.floor4.setText("");
			dndpanel.floor4.acceptDrop = true;
			dndpanel.floor4.setColorFree();
			
			dndpanel.floor5.setText("");
			dndpanel.floor5.acceptDrop = true;
			dndpanel.floor5.setColorFree();
			
			dndpanel.floor6.setText("");
			dndpanel.floor6.acceptDrop = true;
			dndpanel.floor6.setColorFree();
			
			dndpanel.garden1.setText("");
			dndpanel.garden1.acceptDrop = true;
			dndpanel.garden1.setColorFree();
			
			dndpanel.garden2.setText("");
			dndpanel.garden2.acceptDrop = true;
			dndpanel.garden2.setColorFree();
			
			dndpanel.garden3.setText("");
			dndpanel.garden3.acceptDrop = true;
			dndpanel.garden3.setColorFree();
			
			dndpanel.cellar1.setText("");
			dndpanel.cellar1.acceptDrop = true;
			dndpanel.cellar1.setColorFree();
			
			dndpanel.cellar2.setText("");
			dndpanel.cellar2.acceptDrop = true;
			dndpanel.cellar2.setColorFree();
			
			dndpanel.cellar3.setText("");
			dndpanel.cellar3.acceptDrop = true;
			dndpanel.cellar3.setColorFree();
			
			dndpanel.cellar4.setText("");
			dndpanel.cellar4.acceptDrop = true;
			dndpanel.cellar4.setColorFree();
			
			dndpanel.cellar5.setText("");
			dndpanel.cellar5.acceptDrop = true;
			dndpanel.cellar5.setColorFree();
			
			dndpanel.cellar6.setText("");
			dndpanel.cellar6.acceptDrop = true;
			dndpanel.cellar6.setColorFree();
		}
		

	}

}
