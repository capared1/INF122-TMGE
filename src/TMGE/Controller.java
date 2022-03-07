package TMGE;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static JFrame frame;
    public static List<Game> games;
    public static List<Profile> profiles = new ArrayList<>();

    private HomePanel hp;
    private static GameFactory factory;
    static JPanel cardPanel = new JPanel(new CardLayout());

    static Profile player1;
    static Profile player2;


    public static void switchPlayer(Profile p1, Profile p2){
        System.out.println("Palyer 1 Switched -- " + p1.toString());
        System.out.println("Palyer 2 Switched -- " + p2.toString());

        player1 = p1;
        player2 = p2;

    }


    public static void startGame(Game game){

        System.out.println("started game --" + game);
        ((CardLayout)cardPanel.getLayout()).show(cardPanel, game.toString());
    }

    public Controller(JFrame frame, List<Game> games, GameFactory factory){
        Controller.frame = frame;
        Controller.games = games;
        Controller.factory = factory;

        hp = new HomePanel();

        frame.add(cardPanel, BorderLayout.CENTER);
        cardPanel.add(hp.getPanel(), "HOME");

        for(Game g : games){
            JPanel gamePanel = new JPanel(new GridLayout(1,1));
            gamePanel.add(factory.create(g.getName()).getGamePanel());
            cardPanel.add(gamePanel, g.toString());
        }

        frame.setVisible(true);

    }
}
