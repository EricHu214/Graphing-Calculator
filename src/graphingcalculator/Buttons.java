/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphingcalculator;

/**
 *
 * @author Eric Hu
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Buttons extends JPanel{
    private JButton[] buttons = new JButton[27];
    private ArrayList<Function> model;
    private Graph view;
    
    public Buttons(Graph graph, ArrayList<Function> function){
        model = function;
        view = graph;
        addButtons();
        addListener();
        this.setPreferredSize(new Dimension(800, 75));
    }
    
    public void addListener(){
      Controller control = new Controller(buttons, view, model);
      
      for(int i = 0; i < buttons.length; i++){
          buttons[i].addActionListener(control);
      }
    }
    
    public void addButtons(){
        for(int i = 0; i < buttons.length; i++){
            String str = "";
            if(i >= 0 && i <= 9){
                str = Integer.toString(i);
            }
            else if(i == 10){
                str = "*";
            }
            else if(i == 11){
                str = "/";
            }
            else if(i == 12){
                str = "+";
            }
            else if(i == 13){
                str = "-";
            }
            else if(i == 14){
                str = "x";
            }
            else if(i == 15){
                str = "x^2";
            }else if(i == 16){
                str = "x^n";
            }
            else if(i == 17){
                str = "SQRT";
            }
            else if(i == 18){
                str = "sin";
            }
            else if(i == 19){
                str = "cos";
            }
            else if(i == 20){
                str = "tan";
            }
            else if(i == 21){
                str = "log";
            }
            else if(i == 22){
                str = "(";
            }
            else if(i == 23){
                str = ")";
            }
            else if(i == 24){
                str = ".";
            }
            else if(i == 25){
                str = "clear";
            }
            else if(i == 26){
                str = "graph";
            }
            buttons[i] = new JButton(str);
            this.add(buttons[i]);
        }
    }
}
