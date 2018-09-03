package nautsTeamRandomizer.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import nautsTeamRandomizer.Model.AwesomenautsPlayer;

@SuppressWarnings("serial")
public class PlayerSelectionGUI extends JFrame {
	private JList<AwesomenautsPlayer> playerList;
	private JButton selectPlayer = new JButton("Select");
	
	public PlayerSelectionGUI(MainGUI parent, DefaultListModel<AwesomenautsPlayer> listModel, int num) {
		super("Select Player");
		playerList = new JList<AwesomenautsPlayer>(listModel);
		add(playerList);
		add(selectPlayer);
		selectPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = playerList.getSelectedIndex();
				if (index == -1) {
					JOptionPane.showMessageDialog(playerList, "A player has not been selected", "No Selection Made",
							JOptionPane.WARNING_MESSAGE);
				} else {
					parent.playerSelect(listModel.getElementAt(index), num);
					closeWindow();
				}

			}
		});
		setLayout(new GridLayout(2, 1));
		setSize(500, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	private void closeWindow() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
