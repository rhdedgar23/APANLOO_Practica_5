package RadioButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ventana3 extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	int decimalValue;
	int octalValue;
	int hexaValue;
	
	JTextField inOutT;

	JRadioButton decimalRB;
	JRadioButton binarioRB;
	JRadioButton octalRB;
	JRadioButton hexaRB;
	
	ButtonGroup grupo;
	
	ArrayList<Integer> binaryValues;
	ArrayList<Integer> octalValues;
	ArrayList<String> hexaValues;
	

	public Ventana3() {
		super("Convertidor Decimal");
		this.setSize(700, 600);
		//this.setLayout(null);
		
		//------JPanels------
		JPanel panelSuperior= new JPanel();
		JPanel panelCentral= new JPanel();
		
		panelSuperior.setBackground(Color.gray);
		panelCentral.setBackground(Color.DARK_GRAY);
		
		panelSuperior.setPreferredSize(new Dimension(100, 120));
		
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		
		//------panelSuperior------
		inOutT= new JTextField();
		
		inOutT.setPreferredSize(new Dimension(450, 90));
		inOutT.setHorizontalAlignment(JTextField.CENTER);
		inOutT.setFont(new Font("Arial", Font.BOLD, 30));
		inOutT.addKeyListener(this);
		
		panelSuperior.add(inOutT);
		
		//------panelCentral------
		panelCentral.setLayout(new GridLayout(4,1));
	
		decimalRB= new JRadioButton("Decimal");
		binarioRB= new JRadioButton("Binario");
		octalRB= new JRadioButton("Octal");
		hexaRB= new JRadioButton("Hexadecimal");
		
		Font botones= new Font("Arial", Font.PLAIN, 28);
		decimalRB.setFont(botones);
		binarioRB.setFont(botones);
		octalRB.setFont(botones);
		hexaRB.setFont(botones);
		decimalRB.setHorizontalAlignment(SwingConstants.CENTER);
		binarioRB.setHorizontalAlignment(SwingConstants.CENTER);
		octalRB.setHorizontalAlignment(SwingConstants.CENTER);
		hexaRB.setHorizontalAlignment(SwingConstants.CENTER);
		
		grupo= new ButtonGroup(); 
		grupo.add(decimalRB);
		grupo.add(binarioRB);
		grupo.add(octalRB);
		grupo.add(hexaRB);
		
		decimalRB.addActionListener(this);
		binarioRB.addActionListener(this);
		octalRB.addActionListener(this);
		hexaRB.addActionListener(this);
		
		panelCentral.add(decimalRB);
		panelCentral.add(binarioRB);
		panelCentral.add(octalRB);
		panelCentral.add(hexaRB);
		
		decimalRB.setEnabled(false);
		binarioRB.setEnabled(false);
		octalRB.setEnabled(false);
		hexaRB.setEnabled(false);
		
		//------Visibilidad del frame------
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if(e.getSource()==decimalRB) {
				inOutT.setText("");
				inOutT.requestFocus();
			}
			else if(e.getSource()==(binarioRB)){
				decimalToBinary();
			}
			else if(e.getSource()==octalRB) {
				decimalToOctal();
			}
			else if(e.getSource()==hexaRB) {
				decimalToHexa();
			}
		} catch (Exception e1) {
			System.out.println("El numero que ingresaste no es valido. Por favor ingresa un numero valido a convertir");
			inOutT.setText(""); 
			decimalRB.setSelected(true);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
			
		if(e.getSource()==inOutT) {
			decimalRB.setEnabled(true);
			binarioRB.setEnabled(true);
			octalRB.setEnabled(true);
			hexaRB.setEnabled(true);
		}
	}
	
	public void decimalToBinary() {
		
		decimalValue= Integer.parseInt(inOutT.getText());
		
		binaryValues= new ArrayList<>(1);
		String binaryOutput="";
		
		if(decimalValue==0) {
			binaryValues.add(0);
		}
		
		while(decimalValue != 0) {
			binaryValues.add(decimalValue%2);
			decimalValue= decimalValue/2;
		}
		
		for(int i=binaryValues.size()-1; i>-1; i--) {
			binaryOutput= binaryOutput + String.valueOf(binaryValues.get(i));
		}
		
		inOutT.setText(binaryOutput);
	}
	
	public void decimalToOctal() {
		
		decimalValue= Integer.parseInt(inOutT.getText());
		
		octalValues= new ArrayList<>(1);
		String octalOutput= "";
		
		if(decimalValue==0) {
			octalValues.add(0);
		}
		
		while(decimalValue != 0) {
			octalValues.add(decimalValue%8);
			decimalValue= decimalValue/8;
		}
		
		for(int i=octalValues.size()-1; i>-1; i--) {
			octalOutput= octalOutput + String.valueOf(octalValues.get(i));
		}
		
		inOutT.setText(octalOutput);
	}
	
	public void decimalToHexa() {
		
		decimalValue= Integer.parseInt(inOutT.getText());
		
		hexaValues= new ArrayList<>(1);
		String hexaOutput= "";
		
		if(decimalValue==0) {
			hexaValues.add("0");
		}
		
		while(decimalValue != 0) {
			hexaValues.add(sobrante(decimalValue));
			decimalValue= decimalValue/16;
		}
		
		for(int i=hexaValues.size()-1; i>-1; i--) {
			hexaOutput= hexaOutput + hexaValues.get(i);
		}
		
		inOutT.setText(hexaOutput);
	}
	
	public String sobrante(int decimalValue) {
		
		int sobrante= decimalValue%16;
		
		if(sobrante<10) {
			return String.valueOf(sobrante);
		}
		
		else{
			
			switch(sobrante) {
			case 10: 
				return "A";
			case 11: 
				return "B";
			case 12: 
				return "C";
			case 13: 
				return "D";
			case 14:
				return "E";
			case 15: 
				return "F";
			default:
				return "";
			}
		}
	}
}
