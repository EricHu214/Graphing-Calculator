/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphingcalculator;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Graph extends JPanel{
  private ArrayList<Function> function;
  private int xAxis = 300;
  private int yAxis = 300;
  public boolean isGraphing =  false;
  
  //constructor
  public Graph(ArrayList<Function> stuff){
    super();
    function = stuff;
    this.setPreferredSize(new Dimension(600, 600));
  }  
  
  //paints
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 600, 600);
    this.drawGrid(g);
    this.drawGraph(g);
    g.drawString(function.get(function.size()-1).getStringFunction(), 0, 15);
  }
  
  public void update(){
      this.repaint();
  }

  //draws the grid
  public void drawGrid(Graphics g){
    g.setColor(Color.BLACK);
    g.drawLine(0, xAxis, 600, xAxis);
    g.drawLine(yAxis, 0, yAxis, 600);
    
    //draws number lines
    for(int i = 0; i <= 600; i+=10){
      g.drawLine(i, xAxis - 5, i, xAxis + 5);
      g.drawLine(yAxis - 5, i, yAxis +5, i);
    }
  }
  
  //draws the graph
  public void drawGraph(Graphics g){
    if(isGraphing){
      int startingPoint = 0;
      
      for(int j = 0; j < function.size(); j++){
      for(int i = startingPoint; i <= 600; i ++){
        //hole drawing
        if(Double.isNaN(function.get(function.size()-1).getFunction(i - 300, false))){
          this.drawHole(g, i, j);
          //this.drawDerivativeHole(g, i, j);
          i++;
        }
        
        //draw regular graph 
        else if(!Double.isNaN(function.get(j).getFunction(i + 1- 300, false)) && !Double.isInfinite(function.get(j).getFunction(i + 1- 300, false)) && !Double.isInfinite(function.get(j).getFunction(i - 300, false))){   
          if((function.get(j).getFunction(i - 300, false) <= 300 && function.get(j).getFunction(i - 300, false) >= -300)||(function.get(j).getFunction(i + 1 - 300, false) <= 300 && function.get(j).getFunction(i + 1 - 300, false) >= -300))
            g.drawLine(i, (int)(xAxis - 1 - Math.round(10*(function.get(j).getFunction(i - 300, false)))), i + 1, (int)(xAxis - 1 - Math.round(10*(function.get(j).getFunction(i + 1 - 300, false)))));
            //this.drawDerivative(g, i, j);  
        }
      }
      }
    }
  }
  
  //draws derivative
  public void drawDerivative(Graphics g, int i, int j){
    g.setColor(Color.BLUE);
    g.drawLine(i, (int)(xAxis - Math.round(10*(function.get(j).getDerivative((i - 300))))), i + 1, (int)(xAxis - Math.round(10*(function.get(j).getDerivative(i + 1 - 300)))));
    g.setColor(Color.BLACK);
  }
  
  //draws hole
  public void drawHole(Graphics g, int i, int j){
    g.setColor(Color.WHITE);
    g.fillOval(i - 3, (int)(((xAxis - Math.round(10*(function.get(j).getFunction(i - 1 - 300, false)))))) - 3, 6, 6);
    g.setColor(Color.BLACK);
    g.drawOval(i - 3, (int)(((xAxis - Math.round(10*(function.get(j).getFunction(i - 1 - 300, false)))))) - 3, 6, 6);
  }
  
  //draws hole for derivative
  public void drawDerivativeHole(Graphics g, int i, int j){
    g.setColor(Color.WHITE);
    g.fillOval(i - 3, (int)(((xAxis - 1 - Math.round(10*(function.get(j).getDerivative(i - 1 - 300)))))) - 3, 6, 6);
    g.setColor(Color.BLACK);
    g.drawOval(i - 3, (int)(((xAxis - 1 - Math.round(10*(function.get(j).getDerivative(i - 1 - 300)))))) - 3, 6, 6);
  }
}