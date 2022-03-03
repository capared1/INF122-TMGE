package TMGE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel implements UserControlPanel{
    // Note: There will only be one HomePanel per Application

    private JPanel panel = new JPanel(new GridBagLayout());


    JButton startGameBtn = new JButton("START");
    JComboBox player1CB = new JComboBox(Controller.profiles.toArray());
    JComboBox player2CB = new JComboBox(Controller.profiles.toArray());
    JComboBox gameCB;

    public JPanel getPanel(){
        return this.panel;
    }

    public static void addProfile(Profile profile){
        Controller.profiles.add(profile);
    }

    private void initializePanel(){

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        initailizeSelectGamePanel(gbc);
        initializeProfileSelectionPanel(gbc);
        initializeCreateProfilePanel(gbc);

        startGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player1CB.getSelectedItem() == null ||
                        player2CB.getSelectedItem() == null){
                    Notification.showErrorMsg("Not enough players:(");
                }
                else if(player1CB.getSelectedItem() == player2CB.getSelectedItem()){
                    Notification.showErrorMsg("Player 1 cannot be the same as  Player 2 :(");
                }
                else{
                    Notification.showSuccessMsg("Game Start !");
                    Controller.switchPlayer((Profile)player1CB.getSelectedItem(), (Profile)player2CB.getSelectedItem());
                    Controller.startGame((Game)gameCB.getSelectedItem());
                }
            }
        });

        panel.add(startGameBtn, gbc);
        gbc.gridx ++;

    }

    private void initializeProfileSelectionPanel(GridBagConstraints gbc){
        panel.add(new JLabel("Choose Your Players :"), gbc);
        gbc.gridy ++;
        panel.add(player1CB, gbc);
        gbc.gridx ++;
        panel.add(player2CB, gbc);
        gbc.gridx = 0;
        gbc.gridy ++;
    }

    private void initializeCreateProfilePanel(GridBagConstraints gbc){
        panel.add(new JLabel("Or ... Create a new profile :"), gbc);
        gbc.gridy ++;

        panel.add(new JLabel("Name :"), gbc);
        gbc.gridx ++;

        JTextField nameField = new JTextField(20);
        panel.add(nameField, gbc);
        gbc.gridx ++;

        JButton createProfileBtn = new JButton("create");
        createProfileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Add duplicate name check and empty string
                Profile newProfile = new Profile(nameField.getText());
                addProfile(newProfile);
                Notification.showSuccessMsg("New Profile Has Been Created : " + nameField.getText());
                player1CB.addItem(newProfile);
                player2CB.addItem(newProfile);
                nameField.setText("");
            }
        });

        panel.add(createProfileBtn, gbc);
        gbc.gridx = 0;
        gbc.gridy ++;
    }


    private void initailizeSelectGamePanel(GridBagConstraints gbc){
        gameCB = new JComboBox(Controller.games.toArray());
        panel.add(new JLabel("Game to Play :"), gbc);
        gbc.gridx ++;

        panel.add(gameCB, gbc);
        gbc.gridx = 0;
        gbc.gridy ++;
    }


    public HomePanel(){
        initializePanel();
    }
}
