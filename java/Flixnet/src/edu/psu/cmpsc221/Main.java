/*
 * @name  -> Dan Tracy
 * @ID    -> 927347146
 * @Group -> Me
 */
package edu.psu.cmpsc221;

import javax.swing.SwingUtilities;

public class Main
{
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AutoGUI().setVisible(true);
            }
        });
    }
    
}
