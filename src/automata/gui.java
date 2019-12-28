/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

 

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class gui extends JFrame implements ActionListener {
    
         public static  ArrayList<row> data=new ArrayList();
         public static ArrayList q=new ArrayList();
         public static  ArrayList <String>input=new ArrayList();
    
    JButton btnNew;
    JButton btnLoad;
    JButton btnSave;
    DefaultTableModel model ,model2;
    JLabel l1,l2,l3;
    JButton b1;
    JTextField t1,t2,t3;
    JPanel  p1,p2;
    JTable table,table2 ;
    JScrollPane scrol, scrol2;
    JTextArea a1;
    String pathSave;
    int page=0;
   
     public gui(){
        super("DFA");
        this.setLayout(null);
        
          btnNew=new JButton("New");
          btnNew.setBounds(20, 30, 100, 30);
          add(btnNew);
          
          btnLoad=new JButton("Load");
          btnLoad.setBounds(120, 30, 100, 30);
          add(btnLoad);
          
           btnSave=new JButton("Save");
           btnSave.setBounds(220, 30, 100, 30);
           add(btnSave);
           
           
           p1=new JPanel();
           p1.setBounds(0,100,500,300);
           add(p1);
           
           p2=new JPanel();
           p2.setBounds(0,100,500,300);
           add(p2);
           p2.hide();
           
            model=new DefaultTableModel();
            table = new JTable(model);
            scrol = new JScrollPane(table);
            p1.add(scrol);

           model.addColumn("State");
           model.addColumn("input Symbol");
           model.addColumn("Next State");
           for(int i =0; i<40;i++){
               model.addRow(new Object[]{} );
           }
           
           
            model2=new DefaultTableModel();
            table2 = new JTable(model2);
            scrol2 = new JScrollPane(table2);
            p2.add(scrol2);

           model2.addColumn("State");
           model2.addColumn("input Symbol");
           model2.addColumn("Next State");
          
           
            l1=new JLabel("Initial state");
            l1.setBounds(510, 100, 80, 30);
            add(l1);
            t1=new JTextField();
            t1.setBounds(590, 100, 100, 30);
            add(t1);
          
            l2=new JLabel("final state");
            l2.setBounds(510, 140, 80, 30);
            add(l2);
            t2=new JTextField();
            t2.setBounds(590, 140, 100, 30);
            add(t2);
          
            l3=new JLabel("word");
            l3.setBounds(510, 180, 80, 30);
            add(l3);
            t3=new JTextField();
            t3.setBounds(510, 210, 180, 30);
            add(t3);
           
             b1=new JButton("accepted ?");
             b1.setBounds(510, 250, 100, 30);
             add(b1);
             
             JLabel l4=new JLabel("Output");
             l4.setBounds(270,430,100,30);
             add(l4);
             
             a1=new JTextArea();
             a1.setBounds(20,460,600,100);
             a1.setEditable(false);
             add(a1);

                     b1.addActionListener(this);
                     btnNew.addActionListener(this);
                     btnLoad.addActionListener(this);
                     btnSave.addActionListener(this);

           
           
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(360, 70);
        this.setResizable(false);
        this.setSize(730, 600);
        this.setVisible(true);
    }

      
    @Override
    public void actionPerformed(ActionEvent e) {
        // save
        if(e.getSource() == btnSave){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");   
                  FileNameExtensionFilter filter=new FileNameExtensionFilter("DFA","dfa");
                    fileChooser.setFileFilter(filter);
                    
                    


                int userSelection = fileChooser.showSaveDialog(this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String nameFileSave= fileChooser.getName();
                   // System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                    pathSave=fileToSave.getAbsolutePath();
                    
                   PrintWriter p1 =null;
         
                  if(page==0){
                       try{
                           p1=new PrintWriter(pathSave);

                    for(int i=0;i<model.getRowCount();i++){
                   for(int j=0;j<model.getColumnCount();j++){
                         
                            String x=(String) model.getValueAt(i, j);
                            if(x!=null){
                                
                            p1.println(x);
                        
                            }
                       
                }
               }
                   }catch(Exception ee){

                        }finally{
                           p1.close();
                       }
              }else if(page ==1){
                      try{
                           p1=new PrintWriter(pathSave);

                    for(int i=0;i<model2.getRowCount();i++){
                   for(int j=0;j<model2.getColumnCount();j++){
                         
                            String x=(String) model2.getValueAt(i, j);
                            p1.println(x);
                        
                       
                }
               }
                   }catch(Exception ee){

                        }finally{
                           p1.close();
                       }
              }
                   
                }
           }
        
        
         // load
                if(e.getSource()== btnLoad){
                a1.setText("");
                t3.setText("");
                t1.setText("");
                t2.setText("");
                model2.setNumRows(0);
                p1.hide();
                p2.show();
                page=1;
                gui.data.clear();
                      
                    JFileChooser open=new JFileChooser();
                    FileNameExtensionFilter filter=new FileNameExtensionFilter("DFA","dfa");
                    open.setFileFilter(filter);
                    int result= open.showOpenDialog(this);
                    if(result == 0){
                            File path = open.getSelectedFile();
                            BufferedReader p=null;
                           
                   try{
                       p=new BufferedReader(new FileReader(path.toString()));
                       String x;
                       row r=new row();
                       int i=0;
                       while(( x=p.readLine())!=null){
                          if(i==0){
                              r.state=x;
                              i++;

                          }else if(i==1){
                              r.input=x;
                              i++;
                          }else if(i ==2){
                              r.nextState=x;
                              i++;
                              model2.addRow(new Object[]{r.state,r.input,r.nextState} );
                              i=0;
                              r=new row();
                          }

                       } 
                           }catch(Exception ee){

                                     }
                                 }
                        }

        // new 
        if(e.getSource()== btnNew){  
            gui.data.clear();
            model.setNumRows(0);
           for(int i =0; i<40;i++){
               model.addRow(new Object[]{} );
           }
            page=0;
            a1.setText("");
            t3.setText("");
            t1.setText("");
            t2.setText("");
         
            p1.show();
            p2.hide();
         }
        
       // accepted 
          if(e.getSource()== b1){   
           gui.data.clear();
           q.clear();
           input.clear();
           String word=t3.getText();
           String intial=t1.getText();
           String finalState=t2.getText();
            if( !word.isEmpty()  ){
             if( !intial.isEmpty()){
                if(!finalState.isEmpty()){
                           if(page==0){
                    for(int i=0;i<model.getRowCount();i++){
                   row r=new row();
                   for(int j=0;j<model.getColumnCount();j++){
                       if(j == 0){
                       r.state = (String) model.getValueAt(i, j);    
                       }else if(j == 1){
                         r.input =  (String)  model.getValueAt(i, j); 
                          
                          
                         
                       }else if(j == 2){
                       r.nextState =  (String) model.getValueAt(i, j);   
                       }

                   }
                     if(r.state != null && r.input != null && r.nextState != null){
                                  gui.data.add(r);

               }
                }
              }else if(page ==1){
                    for(int i=0;i<model2.getRowCount();i++){
               row r=new row();
               for(int j=0;j<model2.getColumnCount();j++){
                   if(j == 0){
                   r.state = (String) model2.getValueAt(i, j);    
                   }else if(j == 1){
                     r.input =  (String)  model2.getValueAt(i, j);   
                   }else if(j == 2){
                   r.nextState =  (String) model2.getValueAt(i, j);   
                   }
         
               }
               if(r.state != null && r.input != null && r.nextState != null){
                                  gui.data.add(r);

                    }
                  }
              }

           if(checkDFA()){
             if(q.contains(intial)){
                 if(q.contains(finalState)){
                   String txt = null;
           char w[]=word.toCharArray();
           String out[]=new String[w.length];
           String x = null;
                  for(int i = 0;i<w.length;i++){
                  if( i==0){
                            x=indexNun(intial,w[i]);
                            out[i]=x;
                            txt=intial+"--"+w[i]+"-->";
      
                 }else{
                      txt=txt+x+"--"+w[i]+"-->";    
                      x=indexNun(x,w[i]);
                       out[i]=x;
                   }
                 }
                   
                    
               
             try{
                     if(out[out.length-1].equals(finalState) ){
                         txt=txt+x;
                         txt=txt+"  >>>>>> accepted";
                         a1.setText(txt);
                         
                       }else{
                          txt=txt+x;
                         txt=txt+"  >>>>>> not accepted";
                         a1.setText(txt);
                       }
                     
                     
                 
                         
             }catch(Exception ee){
                       JOptionPane.showMessageDialog(null, "Please enter the fields correctly");

             }           
                 }else{
                     JOptionPane.showMessageDialog(null, "please enter correct value  final state");
                 }
             }else{
                JOptionPane.showMessageDialog(null, "please enter correct value  intial state");
             } 
           
           }else{
               JOptionPane.showMessageDialog(null, "Is not Dfa !! , please try again");
           }
         
         
                }else{
                    JOptionPane.showMessageDialog(null, "please enter the fianl state");
                } 
             }else{
                 JOptionPane.showMessageDialog(null, "please enter the intial");
             }
           }else{
                  JOptionPane.showMessageDialog(null, "please enter the word");

           }
            
        }
    }
    
    
    public  String indexNun(String q,char c){
          

        for(int i=0;i<gui.data.size();i++){
              String s=String.valueOf(c);  
           
            if( ( gui.data.get(i).state.equals(q)) && (s.equals(gui.data.get(i).input)) ){
    
                return gui.data.get(i).nextState;
            }
        }
      return null;   
    }
    
    public void addInputs(){
        for(int i=0;i<data.size();i++){
            String in=data.get(i).input;
            if(input.isEmpty()){
                input.add(in);
            }else{
                for(int j=0;j<input.size();j++){
                    if(!input.contains(in)){
                        input.add(in);
                    }
                }
            }
            
            String state=data.get(i).state;
            if(q.isEmpty()){
                q.add(state);
            }else{
                for(int j=0;j<q.size();j++){
                    if(!q.contains(state)){
                        q.add(state);
                    }
                }
            }
            
              String nextState=data.get(i).nextState;
            if(q.isEmpty()){
                q.add(nextState);
            }else{
                for(int j=0;j<q.size();j++){
                    if(!q.contains(nextState)){
                        q.add(nextState);
                    }
                }
            }
            
        }
        
      
        
       
    }
    
    public boolean checkDFA(){
        int ok = 0;
         addInputs();
         for(int i=0;i<q.size();i++){
             String state=(String) q.get(i);
           for(int j=0;j<input.size();j++){
               String inp=input.get(j);
               for(int r=0;r<data.size();r++){
                   if(data.get(r).state.equals(state)  && data.get(r).input.equals(inp) ){
                       ok++;
                   }
               }
               if(ok==0 || ok>1){
                   return false;
               }
               ok=0;
           }
         }
         return true;
    }
    
    

    
}

class row{
    String state;
    String input;
    String nextState;
}
