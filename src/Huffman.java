import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Huffman {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public static boolean compare(Node a,Node b)
	{
		return b.GetValue()<a.GetValue();
	}
	public static Set<Character>set=new HashSet<Character>();
	public static int freq[]=new int[1000];
	public static Vector<Node >v=new Vector< Node >();
	public static HashMap<String, Character >m=new HashMap<String,Character>();
	public static HashMap<Character,String >m2=new HashMap<Character,String>();
	private JTextField textField_5;
	public static void sort()
	{
		for(int i=0 ; i<v.size() ; i++)
		{
			for(int j=i+1 ; j<v.size(); j++)
			{
				if(compare(v.elementAt(i),v.elementAt(j)))
				{
					Node a=v.elementAt(i),b=v.elementAt(j);
					String d=a.GetData(),c=a.GetCode();
					Node l =a.GetLeft(),r=a.GetRight();
					int val=a.GetValue();
					v.elementAt(i).SetCode(b.GetCode());
					v.elementAt(i).SetData(b.GetData());
					v.elementAt(i).SetLeft(b.GetLeft());
					v.elementAt(i).SetRight(b.GetRight());
					v.elementAt(i).SetValue(b.GetValue());
					v.elementAt(j).SetCode(c);
					v.elementAt(j).SetData(d);
					v.elementAt(j).SetLeft(l);
					v.elementAt(j).SetRight(r);
					v.elementAt(j).SetValue(val);
				}
			}
		}
	}
	public static Node SolveQueue()
	{
		while(v.size()>1)
		{
			sort();
			Node p1=v.elementAt(0);
			Node p2=v.elementAt(1);
			v.remove(0);
			v.remove(0);
			String tmp=p1.GetData()+p2.GetData();
			int val=p1.GetValue()+p2.GetValue();
			Node p=new Node(val,"",tmp,p1,p2);
			v.add(p);
		}
		return v.elementAt(0);
	}
	public static void GetCodes(Node x,String code)
	{
		if(x.GetLeft()==null )
		{
			m.put(code, x.GetData().charAt(0));
			if(code.length()==0)	code="0";
			m2.put(x.GetData().charAt(0),code);
			return;
		}
		GetCodes(x.GetLeft(),code+'1');
		GetCodes(x.GetRight(),code+'0');
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Huffman window = new Huffman();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Huffman() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public static String Read1(String FileName){
		String str,s="";
		try {
		    BufferedReader in = new BufferedReader(new FileReader(FileName));
		    
		    s="";
		    while ((str = in.readLine()) != null){
		    	s+=str;
		    }
		    in.close();
		} catch (IOException e) {
		}
		return s;
	}
	public static void Write1(String FileName) 
	{
        FileOutputStream out;
        PrintStream p;
        try
        {
             out = new FileOutputStream(FileName);
             p = new PrintStream( out );
             for(char i : set){
            	 p.println(i);
            	 p.println(m2.get(i));
             }
             p.close();
        }
        catch (Exception e)
        {
              System.err.println ("Error writing to file");
        }
	}
	public static void Write2(String FileName,String txt) 
	{
        FileOutputStream out;
        PrintStream p;
        try
        {
             out = new FileOutputStream(FileName);
             p = new PrintStream( out );
             p.println(txt);
             p.close();
        }
        catch (Exception e)
        {
              System.err.println ("Error writing to file");
        }
	}
	public static void Write3(String FileName,String txt) 
	{
        FileOutputStream out;
        PrintStream p;
        try
        {
             out = new FileOutputStream(FileName);
             p = new PrintStream( out );
             p.println(txt);
             p.close();
        }
        catch (Exception e)
        {
              System.err.println ("Error writing to file");
        }
	}
	public static void Read2(String FileName){
		try {
		    BufferedReader in = new BufferedReader(new FileReader(FileName));
		    String s1 =in.readLine();
		    while(s1!=null)
		    {
		    	String s2 =in.readLine();
			    m.put(s2, s1.charAt(0));
			    m2.put(s1.charAt(0), s2);
			    s1 = in.readLine();
		    }
		    in.close();
		} catch (IOException e) {
		}
	}
	public static String Read3(String FileName){
		String s="";
		try {
		    BufferedReader in = new BufferedReader(new FileReader(FileName));
		    String s1 =in.readLine();
		    while(s1!=null)
		    {
		    	s+=s1;
			    s1 = in.readLine();
		    }
		    in.close();
		} catch (IOException e) {
		}
		return s;
	}
	public static void Compress(String s)
	{
		for(int i=0 ; i<s.length() ; i++)
			set.add(s.charAt(i));
		for(int i=0 ; i<s.length() ; i++)
		{
			int x=s.charAt(i);
			++freq[x];
		}
		for(char i : set){
			int x=i;
			String tt=""+i;
			Node p=new Node(freq[x],"",tt,null,null);
			System.out.println(freq[x]+" "+tt);
			v.add(p);
		}
		Node ans= SolveQueue();
		System.out.println(ans.GetData());
		GetCodes(ans, "");
		//for(char i : set)
			//System.out.println(i+" "+m2.get(i));
	}
	public static String Decompress(String data)
	{
		String tmp="",ans="";
		for(int i=0 ; i<data.length() ; i++)
		{
			tmp+=data.charAt(i);
			if(m.get(tmp)!=null){
				ans+=m.get(tmp);
				tmp="";
			}
		}
		return ans;
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Compress");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String FileName=textField.getText();
				String s=Read1(FileName);
				Compress(s);
				FileName=textField_5.getText();
				Write1(FileName);
				String comp="";
				for(int i=0 ; i<s.length() ; i++)
					comp+=m2.get(s.charAt(i));
				FileName=textField_1.getText();
				Write2(FileName,comp);	
				JOptionPane.showMessageDialog(null,"The File Compressed Successfully ");
			}
		});
		btnNewButton.setBounds(23, 216, 115, 27);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("Decompress");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m.clear();
				m2.clear();
				String FileName=textField_3.getText();
				Read2(FileName);
				FileName=textField_2.getText();
				String d=Read3(FileName);
				System.out.println("Data : "+FileName+d);
				String NewData=Decompress(d);
				FileName=textField_4.getText();
				Write3(FileName,NewData);
				System.out.println("New : "+NewData);
				JOptionPane.showMessageDialog(null,"The File Decompressed Successfully");
			}
		});
		btnNewButton_1.setBounds(268, 216, 140, 27);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblDatafile = new JLabel("DataFile");
		lblDatafile.setBounds(26, 14, 72, 20);
		lblDatafile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JLabel lblNewCompressedFile = new JLabel("New Compressed File");
		lblNewCompressedFile.setBounds(23, 161, 127, 32);
		lblNewCompressedFile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JLabel lblCompressedFile = new JLabel("Compressed File");
		lblCompressedFile.setBounds(268, 11, 126, 27);
		lblCompressedFile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JLabel lblBinarycodesFile = new JLabel("BinaryCodes File");
		lblBinarycodesFile.setBounds(268, 92, 100, 17);
		lblBinarycodesFile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JLabel lblNewFileName = new JLabel("New File Name");
		lblNewFileName.setBounds(270, 165, 98, 24);
		lblNewFileName.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		textField = new JTextField();
		textField.setBounds(23, 35, 96, 20);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(23, 185, 86, 20);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(268, 35, 86, 20);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(268, 108, 86, 20);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(268, 185, 86, 20);
		textField_4.setColumns(10);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblDatafile);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(lblNewCompressedFile);
		frame.getContentPane().add(textField_1);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(textField_2);
		frame.getContentPane().add(textField_3);
		frame.getContentPane().add(textField_4);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(lblBinarycodesFile);
		frame.getContentPane().add(lblNewFileName);
		frame.getContentPane().add(lblCompressedFile);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(26, 108, 86, 20);
		frame.getContentPane().add(textField_5);
		
		JLabel lblNewbinarycodesFile = new JLabel("NewBinaryCodes File");
		lblNewbinarycodesFile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewbinarycodesFile.setBounds(23, 93, 127, 17);
		frame.getContentPane().add(lblNewbinarycodesFile);
	}
}
