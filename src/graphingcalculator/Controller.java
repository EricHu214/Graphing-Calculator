/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphingcalculator;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Eric Hu
 */
public class Controller implements ActionListener{
    
    private JButton[] buttons = new JButton[27];
    private Graph graph;
    private ArrayList<Function> function;
    private JButton button = new JButton();
    private CalculatorUI ui;
    
    public Controller(JButton[] button, Graph stuff, ArrayList<Function> model){
        buttons = button;
        graph = stuff;
        function = model;
    }
    
    public Controller(JButton stuff, Graph view, ArrayList<Function> model, CalculatorUI calc){
        graph = view;
        function = model;
        button = stuff;
        ui = calc;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = "";
        
        if(!graph.isGraphing){
            if(e.getSource() == buttons[0]){
                str = "0";
            }
            else if(e.getSource() == buttons[1]){
                str = "1";
            }
            else if(e.getSource() == buttons[2]){
                str = "2";
            }
            else if(e.getSource() == buttons[3]){
                str = "3";
            }
            else if(e.getSource() == buttons[4]){
                str = "4";
            }
            else if(e.getSource() == buttons[5]){
                str = "5";
            }
            else if(e.getSource() == buttons[6]){
                str = "6";
            }
            else if(e.getSource() == buttons[7]){
                str = "7";
            }
            else if(e.getSource() == buttons[8]){
                str = "8";
            }
            else if(e.getSource() == buttons[9]){
                str = "9";
            }
            else if(e.getSource() == buttons[10]){
                str = "*";
            }
            else if(e.getSource() == buttons[11]){
                str = "/";
            }
            else if(e.getSource() == buttons[12]){
                str = "+";
            }
            else if(e.getSource() == buttons[13]){
                str = "-";
            }
            else if(e.getSource() == buttons[14]){
                str = "x";
            }
            else if(e.getSource() == buttons[15]){
                str = "^(2)";
            }else if(e.getSource() == buttons[16]){
                str = "^(";
            }
            else if(e.getSource() == buttons[17]){
                str = "q(";
            }
            else if(e.getSource() == buttons[18]){
                str = "s(";
            }
            else if(e.getSource() == buttons[19]){
                str = "c(";
            }
            else if(e.getSource() == buttons[20]){
                str = "t(";
            }
            else if(e.getSource() == buttons[21]){
                str = "l(";
            }
            else if(e.getSource() == buttons[22]){
                str = "(";
            }
            else if(e.getSource() == buttons[23]){
                str = ")";
            }
            else if(e.getSource() == buttons[24]){
                str = ".";
            }
        }
        if(e.getSource() == buttons[25]){
            str = "clear";
            graph.isGraphing = false;
        }
        else if(e.getSource() == buttons[26]){
            str = "";
            graph.isGraphing = true;
        }
//        else if(e.getSource() == button){
//            ui.addNewBox();
//            function.add(new Function());
//        }
        
        //if(e.getSource() != button){
            function.get(function.size()-1).setFunction(str);
            graph.update();
            //ui.update();
        //}
    }
}
