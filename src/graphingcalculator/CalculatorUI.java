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
public class CalculatorUI extends JPanel{
    private ArrayList<Function> function;
    private Graph graph;
    JButton addFunction = new JButton("add function");
    ArrayList<JTextField> stuff = new ArrayList<JTextField>();
    BorderLayout layout = new BorderLayout();
    GridLayout grid = new GridLayout(20, 1);
    JPanel panel = new JPanel();
    
    public CalculatorUI(Graph view, ArrayList<Function> model){
        function = model;
        graph = view;
        this.setLayout(layout);
        panel.setLayout(grid);
        this.add(panel, BorderLayout.CENTER);
        this.add(addFunction, BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(200, 600));
        stuff.add(new JTextField());
        panel.add(stuff.get(0));
        Controller control = new Controller(addFunction, graph, function, this);
        addFunction.addActionListener(control);
    }
    
    public void update(){
        stuff.get(stuff.size()-1).setText(function.get(function.size()-1).getStringFunction());
        repaint();
    }
    
    public void addNewBox(){
        this.stuff.add(new JTextField());
        panel.add(stuff.get(stuff.size()-1));
    }
}
