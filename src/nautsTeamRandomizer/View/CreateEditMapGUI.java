package nautsTeamRandomizer.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import nautsTeamRandomizer.TeamRandomizerController;
import nautsTeamRandomizer.Model.AwesomenautsMap;

@SuppressWarnings({ "serial" })
public class CreateEditMapGUI extends JFrame{
	private JTextField mapNameField = new JTextField("Name");
	private JLabel mapCreateHelpLabel0 = new JLabel("Custom Gamemode will override");
	private JLabel mapCreateHelpLabel1 = new JLabel("the other two options");
	private JCheckBox supportsNormalBox = new JCheckBox("Supports Normal Play");
	private JCheckBox supportsTDMBox = new JCheckBox("Supports Team Deathmatch Play");
	private JCheckBox isCustomBox = new JCheckBox("Supports a Custom Gamemode");
	private JButton createEdit = new JButton("Create Map");
	GridBagLayout gridBagLayout = new GridBagLayout();
	GridBagConstraints constraints = new GridBagConstraints();
	
	public CreateEditMapGUI(TeamRandomizerController parent) {
		super("Create Map");
		setupWindow();
		createEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AwesomenautsMap map = createMap();
				parent.addNewMap(map);
				closeWindow();
			}
		});
		setVisible(true);
	}
	public CreateEditMapGUI(TeamRandomizerController parent, AwesomenautsMap map) {
		super("Edit Map");
		setupWindow();
		createEdit.setText("Edit Map");
		mapNameField.setText(map.getMapName());
		supportsNormalBox.setSelected(map.isSupportsNormalPlay());
		supportsTDMBox.setSelected(map.isSupportsTDMPlay());
		isCustomBox.setSelected(map.isCustomGamemode());
		createEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AwesomenautsMap newMap = createMap();
				newMap.setMapID(map.getMapID());
				parent.editMap(newMap);
				closeWindow();
			}
		});
		setVisible(true);
	}
	private void setupWindow() {
		setLayout(gridBagLayout);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(mapNameField, constraints);
		constraints.gridy = 1;
		add(mapCreateHelpLabel0, constraints);
		constraints.gridy = 2;
		add(mapCreateHelpLabel1, constraints);
		constraints.gridx = 2;
		constraints.gridy = 2;
		add(supportsNormalBox, constraints);
		constraints.gridy = 3;
		add(supportsTDMBox, constraints);
		constraints.gridy = 4;
		add(isCustomBox, constraints);
		constraints.gridx = 3;
		constraints.gridy = 0;
		add(createEdit, constraints);
		isCustomBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isCustomBox.isSelected()) {
					supportsNormalBox.setSelected(false);
					supportsTDMBox.setSelected(false);
				}
				
			}
		});
		mapNameField.requestFocus();
		mapNameField.selectAll();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
	}
	private AwesomenautsMap createMap() {
		AwesomenautsMap map = new AwesomenautsMap(mapNameField.getText(), supportsNormalBox.isSelected(), supportsTDMBox.isSelected(), isCustomBox.isSelected());
		return map;
	}
	private void closeWindow() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
}
