package nautsTeamRandomizer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6530073372542178903L;
	private JButton createPlayer = new JButton("Create Player");
	private JButton deletePlayer = new JButton("Delete Player");
	private JButton editPlayer = new JButton("Edit Player");
	private DefaultListModel<AwesomenautsPlayer> playerListModel = new DefaultListModel<AwesomenautsPlayer>();
	private JList<AwesomenautsPlayer> playerList = new JList<AwesomenautsPlayer>(playerListModel);
	private JScrollPane listScroller = new JScrollPane(playerList);
	private JPanel playerPanel = new JPanel();
	private JPanel generationPanel = new JPanel();
	
	public MainGUI() {
		super("Awesomenaut Team Randomizer");
		
		add(playerPanel);
		playerPanel.add(playerList);
		playerPanel.add(createPlayer);
		playerPanel.add(deletePlayer);
		playerPanel.add(editPlayer);
		playerPanel.setLayout(null);
		createPlayer.setBounds(35, 670, 120, 25);
		createPlayer.setActionCommand("Create Player");
		createPlayer.addActionListener(new buttonClicked());
		editPlayer.setBounds(165, 670, 120, 25);
		editPlayer.setActionCommand("Create Player");
		editPlayer.addActionListener(new buttonClicked());
		deletePlayer.setBounds(105, 700, 120, 25);
		deletePlayer.setActionCommand("Delete Player");
		deletePlayer.addActionListener(new buttonClicked());
		playerList.setBounds(10, 10, 300, 600);
		
		add(generationPanel);
		
		
		// Set the List options
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerList.setLayoutOrientation(JList.VERTICAL);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		// Add the action listeners. Hey! Listen!
		createPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerListModel.insertElementAt(new AwesomenautsPlayer("Test"), 0);
			}
		});
		deletePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = playerList.getSelectedIndex();
				if(index == -1) {
					JOptionPane.showMessageDialog(playerList, "A player has not been selected", "No Selection Made", JOptionPane.WARNING_MESSAGE);
				} else {
					playerListModel.remove(index);
				}
				
			}
		});
		
		// The preset layouts are annoying to work with
		setLayout(new GridLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		setVisible(true);
	}
	private class buttonClicked implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			if(button.equals("Create Player")) {
				
			} else if(button.equals("Edit Player")) {
				
			} else if(button.equals("Delete Player")) {
				
			}
		}
	}
	public static void main(String[] args) {
		MainGUI gui = new MainGUI();
	}
}
