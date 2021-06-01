/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

/**
 *
 * @author ANKIT
 */
public class Notepad  extends JFrame{

    /**
     * @param args the command line arguments
     */
    JLabel jl1;
    JMenu menu,menu1,UI,action;
    JMenuBar mb;
    JMenuItem i1, i2, i3, i4,new1,help;
    JMenuItem action1, action2, action3, action4,action5;
    JMenuItem i1_1, i2_2, i3_3, i4_4,i5_5;
    JMenuItem UI_1, UI_2, UI_3, UI_4;
    JTextArea jta;
    Container c; 
    JScrollPane scrollableTextArea ;
    String filename="";
    ImageIcon image;
    String propertyfilename="";
    UndoManager manager = new UndoManager();

    Notepad()
    {
            addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            try {    
                String str =jta.getText();
                if(str.length() == 0)
                {
                     setVisible(false);
                }
                if(filename == "")
                {
                    JFileChooser jf1 = new JFileChooser();
                    int i=jf1.showSaveDialog(null);
                    if( i== JFileChooser.APPROVE_OPTION)
                    {
                        propertyfilename=jf1.getSelectedFile().getName();
                        filename = jf1.getSelectedFile().getAbsolutePath();
                    }
                }
            File file1 = new File(System.getProperty("user.dir")+"//"+propertyfilename+".properties");
            Font f = jta.getFont();
            Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize());
            Properties props = new Properties();
            props.setProperty("fontname",f.getFontName().toString());
            props.setProperty("fontstyle",String.valueOf(f.getStyle()));
            props.setProperty("fontsize",String.valueOf(f.getSize()));
            props.setProperty("fontcolor",String.valueOf(jta.getForeground().getRGB()));
            props.setProperty("bcolor",String.valueOf(jta.getBackground().getRGB()));
         FileOutputStream outputStrem = new FileOutputStream(file1);
         
      //Storing the properties file
        props.store(outputStrem, "This is a sample properties file");
        }  catch (IOException ea) {
            ea.printStackTrace();
        } finally {
            setVisible(false);
        }
    }
});
        String str=System.getProperty("user.dir")+"\\src\\image\\icon.png";
     
        image = new ImageIcon(str);
        setIconImage(image.getImage());
        menu=new JMenu("File");
        mb = new JMenuBar();
        jta=new JTextArea();
        scrollableTextArea = new JScrollPane(jta);  
            
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        new1 = new JMenuItem("New");
        i1 =  new JMenuItem("Open");
        i2 =  new JMenuItem("save");
        i3 =  new JMenuItem("save as");
        i4 =  new JMenuItem("Exit");
        i1.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));
        i2.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
        i3.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,java.awt.Event.SHIFT_MASK));
        i4.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q,java.awt.Event.CTRL_MASK));
        new1.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,java.awt.Event.CTRL_MASK));
        i1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser jf1 = new JFileChooser();
                int i = jf1.showOpenDialog(null);
                if( i == JFileChooser.APPROVE_OPTION)
                {
                    String path = jf1.getSelectedFile().getAbsolutePath();
                   filename=path;
                     try{  
                        BufferedReader br=new BufferedReader(new FileReader(path));    
                        String s1="",s2="";
                        while((s1=br.readLine())!=null){    
                        s2+=s1+"\n";    
                        }    
                        jta.setText(s2);    
                        br.close();    
                        }
                     catch (Exception ex) 
                     {ex.printStackTrace();  }
                     
                    propertyfilename=jf1.getSelectedFile().getName();
                 
                    try {  
                        FileReader reader=new FileReader(System.getProperty("user.dir")+"//"+propertyfilename+".properties");
                          Properties p=new Properties();  
                          p.load(reader);
                          String fname = p.getProperty("fontname");
                          String fstyle = p.getProperty("fontstyle");
                          String fsize = p.getProperty("fontsize");
                          String fcolor = p.getProperty("fontcolor");
                          String Bcolor = p.getProperty("bcolor");
                          Font f = new Font(fname,Integer.parseInt(fstyle),Integer.parseInt(fsize));
                          jta.setFont(f);
                          Color c = new Color(Integer.parseInt(fcolor));
                          Color c1 = new Color(Integer.parseInt(Bcolor));
                          jta.setForeground(c);
                          jta.setBackground(c1);
                          
                                  
                          
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        });
        i2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(filename!="")
                {
                    try {
                        FileWriter file = new FileWriter(filename);
                      
                        file.write(jta.getText());
                        file.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else
                {
                JFileChooser jf11 = new JFileChooser();
                int j=jf11.showSaveDialog(null);
                if(j == JFileChooser.APPROVE_OPTION)
                {
                    filename = jf11.getSelectedFile().getAbsolutePath();
                      propertyfilename=jf11.getSelectedFile().getName();
                    try {
                        FileWriter file = new FileWriter(filename);
                        file.write(jta.getText());
                        file.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                }
                }
            }
            
        });
        
        
           i3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser jf11 = new JFileChooser();
                int j=jf11.showSaveDialog(null);
                if(j == JFileChooser.APPROVE_OPTION)
                {
                    filename = jf11.getSelectedFile().getAbsolutePath();
                      propertyfilename=jf11.getSelectedFile().getName();
                    try {
                        FileWriter file = new FileWriter(filename);
                        file.write(jta.getText());
                        file.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                }
            }
            
        });
        
        
        new1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
              int a=  JOptionPane.showConfirmDialog(null, "Do you want to save ?");
               if( a == JOptionPane.YES_OPTION)
               {
                   JFileChooser jff = new JFileChooser();
                   int i=jff.showSaveDialog(null);
                   if(filename=="")
                   {
                       if(i == JFileChooser.APPROVE_OPTION)
                   {
                       filename = jff.getSelectedFile().getAbsolutePath();
                         propertyfilename=jff.getSelectedFile().getName();
                           try {
                               FileWriter fw = new FileWriter(filename);
                               fw.write(jta.getText());
                       fw.close();
                           } catch (IOException ex) {
                               Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       
                   }
                   }
                   else
                   {
                       try {
                           FileWriter fw = new FileWriter(filename);
                           fw.write(jta.getText());
                           fw.close();
                       } catch (IOException ex) {
                           Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                   }
               }
               else if( a == JOptionPane.CANCEL_OPTION)
               {
                    new Notepad().setVisible(true);
               }
               else
               {
                   new Notepad().setVisible(true);
               setVisible(false);
               }
            }
        });
        
         i4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
              int a=  JOptionPane.showConfirmDialog(null, "Do you want to Exit?");
               if( a == JOptionPane.YES_OPTION)
                    setVisible(false);
               else if( a == JOptionPane.CANCEL_OPTION)
               {
                    new Notepad().setVisible(true);
               }
               else
               setVisible(false);
              
            }
        });
    
        
        menu.add(new1);
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.add(i4);
        menu1 = new JMenu("Edit");
        i1_1 =  new JMenuItem("Replace All");
        i2_2 =  new JMenuItem("search word");
        i3_3 =  new JMenuItem("Font size");
        i4_4 =  new JMenuItem("Font Color");
        i3_3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
              String size = JOptionPane.showInputDialog(null,"Enter Size");
              int i=Integer.parseInt(size);
             Font f = jta.getFont();
             Font f2 = new Font(f.getFontName(), f.getStyle(), i);
             jta.setFont(f2);
            }
        });
        i4_4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
              Color c =JColorChooser.showDialog(null,"Choose", Color.BLACK);
              
              jta.setForeground(c);
            }
        });
        menu1.add(i1_1);
        menu1.add(i2_2);
        menu1.add(i3_3);
        menu1.add(i4_4);
        i1_1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
               String str = jta.getText();
               String word  = JOptionPane.showInputDialog(null,"Enter Word which you want to Replace");
               
               String new_word  = JOptionPane.showInputDialog(null,"Enter new Word ");
               str=str.replaceAll(word,new_word);
               jta.setText(str);
            }
        });
         i2_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
               String str = jta.getText();
               String word = JOptionPane.showInputDialog(null,"Enter Word which you want to search");
               int index = str.indexOf(word);
               JOptionPane.showMessageDialog(null, index);
            }
        });
        
        
        
        UI = new JMenu("UI");
        UI_1 =  new JMenuItem("Color");
        UI_2 =  new JMenuItem("WindowSize");
//        UI_3 =  new JMenuItem("Cyan");
//        UI_4 =  new JMenuItem("Light black");
        UI_1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
               Color c=JColorChooser.showDialog(null,"Choose",Color.CYAN);  
               jta.setBackground(c);   
            }
        });
        UI_2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            setSize(830,640);
        }
        
        });
        
        UI.add(UI_1);
        UI.add(UI_2);
//        UI.add(UI_3);
//        UI.add(UI_4);
        
        action = new JMenu("Action");
        action1 = new JMenuItem("CountWord");
        action2 = new JMenuItem("Count Occurance of a word");
        action3 = new JMenuItem("Undo");
        action4 = new JMenuItem("Redo");
        action5 = new JMenuItem("calculator");
        action3.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z,java.awt.Event.CTRL_MASK));
        action4.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,java.awt.Event.CTRL_MASK));
        action.add(action1);
        action1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
               String str = jta.getText();
               methods method = new methods();
               JOptionPane.showMessageDialog(null, method.countWords(str));
            }
        });
        
        action2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
               String str = jta.getText();
               String word=JOptionPane.showInputDialog(null);
               methods method = new methods();
               JOptionPane.showMessageDialog(null, method.countOccurences(str, word));
            }
        });
        
        action3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                
                    manager.undo();
               
            }
        });
        
        action4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                
                    manager.redo();
               
            }
        });
        jta.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				manager.addEdit(e.getEdit());
			}
		});
        action5.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            new calculator().setVisible(true);
        }
        });
        action.add(action2);
        action.add(action3);
        action.add(action4);
        action.add(action5);
        help = new JMenuItem("Help");
        mb=new JMenuBar();
        help.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae)
           {
               new Help().setVisible(true);
           }
        });
        mb.add(menu);
        mb.add(menu1);
        mb.add(UI);
        mb.add(action);
        mb.add(help);
        setTitle("Notepad");
        
        
        setJMenuBar(mb);
        add(scrollableTextArea);
        
        setSize(800,600);
        setLocation(200,100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
   
   
    public static void main(String[] args) {
        // TODO code application logic here
        new Notepad();
    }
    
}
