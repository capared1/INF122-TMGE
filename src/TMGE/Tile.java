package TMGE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class Tile {
    protected Color color;
    protected String label;
    protected Board board;
    protected Point coord;
    protected JButton tilePanel = new JButton("");


    public abstract void trigger();
    public abstract Boolean isMatch(Tile other);
    public abstract Object getValue();

    public JButton getTilePanel(){
        return this.tilePanel;
    }

    public void setCoord(Point coord){
        this.coord = coord;
    }

    public Point getCoord(){
        return this.coord;
    }

    public Color setColor(Color _color)
    {
        this.color = _color;
    }

    public Color getColor()
    {
        return this.color;
    }

    public boolean isEnabled(){
        return tilePanel.isEnabled();
    }

    public void setEnabled(Boolean enabled){
        tilePanel.setEnabled(enabled);
    }

    public void setBackground(Color color){
        tilePanel.setBackground(color);
    }

    public Tile(Board board, Color color, String label){
        this.board = board;
        this.color = color;
        this.label = label;
        this.tilePanel.setBackground(color);
        this.tilePanel.setText(label);
        this.tilePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trigger();
            }
        });
    }

}
