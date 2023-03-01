import java.awt.Container;
/**
 * Write a description of class JBrainTetris here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JBrainTetris extends JTetris
{
    
    private Brain currbrain;
    JBrainTetris(int width, int height)
    {super(width,height);

    }
    @Override
    public Container createControlPanel()
    {
        Container panel =super.createControlPanel();
        BrainFactory.creatBrains();
    }
    
    private class JComboBox implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            tick(LEFT);
        }
    }
}