/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphingcalculator;

/**
 *
 * @author Eric Hu
 */
public class Function {
  private double horizontal; //horizontal stretch/compression (for zooming purposes)
  private double vertical; //vertical stretch/compression (for zooming purposes)
  private double horizontalPhase; //horizontal shift(for scrolling purposes)
  private double verticalPhase; //vertical shift (for scrolling purposes)
  private double x; //x value of function
  private String function = ""; //function
  private final double H_VALUE = 0.000000000001; //h value for derivative
  
  public Function(){
    horizontal = 1; 
    vertical = 1; 
    verticalPhase = 0; 
    horizontalPhase = 0; 
  }
  //accessor method
  public String getStringFunction(){
    return function;
  }
  
  public void setFunction(String s){
      if(s == "clear"){
          function = "";
      }
      else{
        function += s;
      }
  }
 
  //gets y value from x values
  public double getFunction(int xValue, boolean isDerivative){
    double functionGraph;
    double yValue = 0;
    
    if(!isDerivative){
      x = (xValue/10.0 - horizontalPhase)*horizontal;
    }
    else{
      x = (xValue/10.0 - horizontalPhase)*horizontal + H_VALUE;
    }
    if(function == ""){
      yValue = 1/0.0;
    }
    else{
      StringBuffer buffer = new StringBuffer(function);
      String str = this.simplify(this.insertX(buffer, x), x);
      yValue = this.getValue(str, x);  
    }
    functionGraph = vertical*(yValue) + verticalPhase;
    return functionGraph;
  }
  
  public double getDerivative(int xValue){
    double derivative = (this.getFunction(xValue, true) - this.getFunction(xValue, false))/H_VALUE;
    
    return derivative;
  }
  
  private StringBuffer insertX(StringBuffer function, double x){
    String str = "";
    
    if(function.charAt(0) != '-'){
      function.insert(0, " ");
    }
    function.append(" ");
    for(int i = function.length()-1; i >= 0; i--){
      if(function.charAt(i) == 'x'){
        if(function.charAt(i-1) == ')' || (function.charAt(i-1) >= '0' && function.charAt(i-1) <= '9')){
          str = "*";
        }
        str += Double.toString(x);
        if(function.charAt(i+1) == '(' || (function.charAt(i+1) >= '0' && function.charAt(i+1) <= '9')){
          str += "*";
        }
        function.replace(i, i+1, str);    
      }
      str = "";
    }
    return function;
  }
  
//calculates all the brackets, sin, cos, tan, exponents and logs
  private String simplify(StringBuffer function, double x){
    String str = "";
    double value;

    for(int i = function.length() - 1; i >= 0; i--){
      if(function.charAt(i) == '('){
        if((function.charAt(i-1) <= '9' && function.charAt(i-1) >= '0') || function.charAt(i-1) == ')'){
          str = "*";
        }   
        
        int k = i;
        while(function.charAt(k) != ')'){
          k++;
        }
        
        value = this.getValue(function.substring(i+1, k), x);
        
      //calculates for log, sin, cos and tan and square roots
        if(function.charAt(i-1) == 'l' || function.charAt(i-1) == 's' || function.charAt(i-1) == 'c' || function.charAt(i-1) == 't' || function.charAt(i-1) == 'q'){
          if((function.charAt(i-2) <= '9' && function.charAt(i-2) >= '0') || function.charAt(i-2) == ')'){
            str = "*";
          }   
          if(function.charAt(i-1) == 'l'){
              value = Math.log(value);
          }
          else if(function.charAt(i-1) == 's'){
            value = Math.sin(value);
          }
          else if(function.charAt(i-1) == 'c'){
            value = Math.cos(value);
          }
          else if(function.charAt(i-1) == 't'){
            value = Math.tan(value);
          }
          else if(function.charAt(i-1) == 'q'){
              value = Math.sqrt(value);
          }
          i--;
        }  
      //calculates for exponent of a bracket
        else if(function.charAt(k+1) == '^'){
          int j = k+2;
          while(function.charAt(j) >= '0' && function.charAt(j) <= '9'){
            j++;
          }
          value = Math.pow(value, Double.parseDouble(function.substring(k+2, j)));
          k=j+1;  
        }
     //calculates for exponent of x or a number
        else if(function.charAt(i-1) == '^'){
          if(function.charAt(i-2) <= '9' && function.charAt(i-2) >= '0'){
            int j = i - 2;
            while((function.charAt(j) >= '0' && function.charAt(j) <= '9')|| function.charAt(j) == '.' || function.charAt(j) == '-'){
              j--;
            }
            value = Math.pow(Double.parseDouble(function.substring(j+1, i-1)), value);
            i-= i-j-1;
          }
        }
        str += Double.toString(value);
        if((function.charAt(k+1) <= '9' && function.charAt(k+1) >= '0') || function.charAt(k+1) == '('){
          str += '*';
        }        
        if(Double.isNaN(value) || Double.isInfinite(value)){
          str = "N";
        }
      //replaces substring with new calculated value
        function.replace(i, k+1, str);
        str = "";
      } 
    }
    if(function.charAt(0) != '-'){
      function.deleteCharAt(0);
    }
    function.deleteCharAt(function.length() - 1);
    return function.toString();
  }
  
  //calculates linear functions or simple arithmetics
  private double getValue(String function, double x){
    int start = 0;
    int end;
    double returnValue = 0;
    double temp = 0;
    boolean numbers = false;
    int operator = 0;
    double otherTemp = 0;
    int originalStart = 0;
    
    if(function.charAt(0) != '-'){
      function = " " + function;
    }
    function += " ";
    
    for(int i = 0; i < function.length(); i++){
      
      if(function.charAt(i) == 'N'){
        returnValue = 1/0.0;
        break;
      }
      if(!numbers && function.charAt(i) <= '9' && function.charAt(i) >= '0'){
        start = i; 
        numbers = true;
      }
      
      else if(numbers && (function.charAt(i) > '9' || function.charAt(i) < '0') && function.charAt(i) != '.' && function.charAt(i) != 'N'){
        end = i;
        temp = Double.parseDouble(function.substring(start, end));
        
        if(operator != 0){  
          start = originalStart;
          if(operator == 1){
            temp *= otherTemp;
          }
          
          else if(operator == 2){
            temp = otherTemp/temp; 
          }
          operator = 0;
        }
        else if(function.charAt(i) == '/'){
          if(function.charAt(i+1) == '-'){
            temp *= -1;
            i++;
          }
          if(function.charAt(i+1) <= '9' && function.charAt(i+1) >= '0' && operator == 0){
            operator = 2;
            otherTemp = temp;
            originalStart = start;
          }  
        }
        else if(function.charAt(i) == '*'){
          if(function.charAt(i+1) == '-'){
            temp *= -1;
            i++;
          }
          
          if(function.charAt(i+1) <= '9' && function.charAt(i+1) >= '0' && operator == 0){
            operator = 1;
            otherTemp = temp;
            originalStart = start;
          }  
        }
        else if(function.charAt(i) == 'E'){
          int k = i+1;
          while((function.charAt(k) <= '9' && function.charAt(k) >= '0') || function.charAt(k) == '-'){
            k++;
          }
          temp *= Math.pow(10, Double.parseDouble(function.substring(i+1, k)));
          i+= k-i-1;
        }   
        numbers = false;
      }
      if(!numbers && i > 0 && (function.charAt(i) == '+' || function.charAt(i) == '-' || function.charAt(i) == ' ')&& operator == 0){
        if(function.charAt(start - 1) == '-'){
          returnValue -= temp;
        }
        
        else if(function.charAt(start - 1) == '+' || function.charAt(start - 1) == ' '){
          returnValue += temp;
        }
      }
    }    
    return returnValue;
  }
}