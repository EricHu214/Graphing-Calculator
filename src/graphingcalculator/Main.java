/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphingcalculator;
import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 *
 * @author Eric Hu
 */
public class Main {

  public static void main(String[] args){
      
    
    ArrayList<Function> function = new ArrayList<Function>();
    function.add(new Function());
    Graph graph = new Graph(function);
    Buttons buttonsPanel = new Buttons(graph, function);
    CalculatorUI ui = new CalculatorUI(graph, function);
    
    JPanel panel = new JPanel();
    panel.add(graph);
    BorderLayout layout = new BorderLayout();
    panel.setLayout(layout);
    panel.add(graph, BorderLayout.CENTER);
    panel.add(buttonsPanel, BorderLayout.SOUTH);
    panel.add(ui, BorderLayout.EAST);
    
    JFrame frame = new JFrame("Graphing Calculator");
    frame.setContentPane(panel);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.pack();
  }
}
