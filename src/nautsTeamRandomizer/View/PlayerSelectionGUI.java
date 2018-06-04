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

import nautsTeamRandomizer.TeamRandomizerController;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;

public class PlayerSelectionGUI extends JFrame {
	private DefaultListModel<AwesomenautsPlayer> playerListModel = new DefaultListModel<AwesomenautsPlayer>();
	private JList<AwesomenautsPlayer> playerList = new JList<AwesomenautsPlayer>(playerListModel);
	private TeamRandomizerController parentController;
	private int playerNum;
	private JButton selectPlayer = new JButton("Select");
	
	public PlayerSelectionGUI(TeamRandomizerController parent, AwesomenautsPlayer[] list, int num) {
		super("Select Player");
		
		playerNum = num;
		add(playerList);
		add(selectPlayer);
		parentController = parent;
		for (AwesomenautsPlayer player : list) {
			playerListModel.addElement(player);
		}
		selectPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = playerList.getSelectedIndex();
				if (index == -1) {
					JOptionPane.showMessageDialog(playerList, "A player has not been selected", "No Selection Made",
							JOptionPane.WARNING_MESSAGE);
				} else {
					parentController.select(index, playerNum);
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
