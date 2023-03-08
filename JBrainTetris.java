import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.util.ArrayList;

/*
 * public static final int ROTATE = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int DROP = 3;
    public static final int DOWN = 4;
 */
/**
 * Write a description of class JBrainTetris here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JBrainTetris extends JTetris
{
    private JComboBox brainList;
    private Brain currbrain;
    private JButton brainEnabler;
    private boolean brainEnabled=false;
    private Move bestMove;
    JBrainTetris(int width, int height)
    {super(width,height);
    ArrayList<Brain> LOB=BrainFactory.createBrains();
    brainList= new JComboBox(LOB.toArray());
    brainEnabler=new JButton("Enable Brain");
    brainList.addActionListener(new brainTypeListener());
    brainEnabler.addActionListener(new enableBrainButton());
    currbrain=LOB.get(0);
    }
    @Override
    public Container createControlPanel()
    {
        Container panel =super.createControlPanel();
        
        panel.add(brainEnabler);
        panel.add(brainList);
        return panel;
    }
    
    @Override
    public Piece pickNextPiece()
    {
        Piece peez=super.pickNextPiece();
        bestMove=currbrain.bestMove(board,peez,HEIGHT+TOP_SPACE);
        return peez;
    }

    @Override 
    public void tick(int VERB){
        if(!brainEnabled){super.tick(VERB); return;}
        if(currentPiece.getSkirt()!=bestMove.getPiece().getSkirt())
        {
            super.tick(ROTATE);
        }
        if(currentX>bestMove.getX())
        {
            super.tick(LEFT);
        }
        if(currentX<bestMove.getX())
        {
            super.tick(RIGHT);
        }
        super.tick(VERB);
    }

    
    private class brainTypeListener implements ActionListener
    {
    
    
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            currbrain = (Brain)cb.getSelectedItem();
        }
    }
    private class enableBrainButton implements ActionListener
    {
    
    
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            brainEnabled =!(brainEnabled);
        }
    }
}