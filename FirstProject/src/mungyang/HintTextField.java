package mungyang;

import java.awt.Color;    

import java.awt.event.FocusAdapter;  

import java.awt.event.FocusEvent;  

import javax.swing.JTextField;  

  

@SuppressWarnings("serial")
public class HintTextField extends JTextField {  


 
  public HintTextField(final String hint) {  

  

    setText(hint);  


    setForeground(Color.gray);  
    
  

    this.addFocusListener(new FocusAdapter() {  

  

      @Override  

      public void focusGained(FocusEvent e) {  

        if (getText().equals(hint)) {  

          setText(getText()); 
          setText("");  

        } else {  

          setText(getText());  
 

        }  

      }  

  

      @Override  

      public void focusLost(FocusEvent e) {  

        if (getText().equals(hint)|| getText().length()==0) {  

          setText(hint);  
          

          setForeground(Color.GRAY);  

        } else {  

          setText(getText());  


          setForeground(Color.BLACK);  

        }  

      }  



    });  

  

  }

  

} 