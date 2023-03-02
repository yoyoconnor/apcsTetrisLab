import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JButton;

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
    JBrainTetris(int width, int height)
    {super(width,height);
    brainList= new JComboBox(BrainFactory.createBrains().toArray());
    brainEnabler=new JButton("Enable Brain");
    brainList.addActionListener(new brainTypeListener());
    brainEnabler.addActionListener(new enableBrainButton());
    }
    @Override
    public Container createControlPanel()
    {
        Container panel =super.createControlPanel();
        
        panel.add(brainEnabler);
        
        return panel;
    }
    
    @Override
    public Piece pickNextPiece()
    {
        int pieceNum = (int)(this.pieces.length * this.random.nextDouble());
        return this.pieces[pieceNum];
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