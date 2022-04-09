package Checkbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	int decimalValue;
	int octalValue;
	int hexaValue;
	
	JButton botonCalcular;
	
	JCheckBox binarioC;
	JCheckBox octalC;
	JCheckBox hexaC;
	
	JTextField decimalT;
	JTextField binarioT;
	JTextField octalT;
	JTextField hexaT;
	
	ArrayList<JCheckBox> checkboxes;
	ArrayList<Integer> binaryValues;
	ArrayList<Integer> octalValues;
	ArrayList<String> hexaValues;
	

	public Ventana() {
		super("Convertidor Decimal");
		this.setSize(700, 600);
		//this.setLayout(null);
		
		//------JPanels------
		JPanel panelSuperior= new JPanel();
		JPanel panelCentral= new JPanel();
		JPanel panelInferior= new JPanel();
		
		panelSuperior.setBackground(Color.gray);
		panelCentral.setBackground(Color.DARK_GRAY);
		panelInferior.setBackground(Color.gray);
		
		panelSuperior.setPreferredSize(new Dimension(100, 120));
		panelInferior.setPreferredSize(new Dimension(100, 120));
		
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		this.add(panelInferior, BorderLayout.SOUTH);
		
		//------panelSuperior------
		decimalT= new JTextField();
		
		decimalT.setPreferredSize(new Dimension(450, 90));
		decimalT.setHorizontalAlignment(JTextField.CENTER);
		decimalT.setFont(new Font("Arial", Font.BOLD, 30));
		
		panelSuperior.add(decimalT);
		
		//------panelCentral------
		panelCentral.setLayout(new GridLayout(3,2));
	
		//Checkbox
		binarioC= new JCheckBox();
		octalC= new JCheckBox();
		hexaC= new JCheckBox();
		checkboxes= new ArrayList<>(3);
		checkboxes.add(binarioC);
		checkboxes.add(octalC);
		checkboxes.add(hexaC);
		Font casillas= new Font("Arial", Font.PLAIN, 28);
		
		binarioC.setText("Binario");
		octalC.setText("Octal");
		hexaC.setText("Hexadecimal");
		binarioC.setFocusable(false);
		octalC.setFocusable(false);
		hexaC.setFocusable(false);
		binarioC.setFont(casillas);
		octalC.setFont(casillas);
		hexaC.setFont(casillas);
		
		//Textfield 
		
		binarioT= new JTextField();
		octalT= new JTextField();
		hexaT= new JTextField();
		Font textfields= new Font("Arial", Font.BOLD, 30);
		
		binarioT.setFont(textfields);
		octalT.setFont(textfields);
		hexaT.setFont(textfields);
		
		//add
		panelCentral.add(binarioC);
		panelCentral.add(binarioT);
		panelCentral.add(octalC);
		panelCentral.add(octalT);
		panelCentral.add(hexaC);
		panelCentral.add(hexaT);
		
		//------Panel Inferior------
		
		botonCalcular= new JButton("Calcular");
		
		botonCalcular.setPreferredSize(new Dimension(250, 70));
		botonCalcular.setFont(new Font("Arial", Font.PLAIN, 26));
		botonCalcular.addActionListener(this);
		
		panelInferior.add(botonCalcular);
		
		//------Visibilidad del frame------
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//este metodo primero checa si la fuente del evento es el boton Calcular. 
		//Si si lo es, entonces convierte el texto ingresado, en el textfield de decimales, a tipo int
		//Luego, del ArrayList checkboxes que contiene a los JCheckBox, checa uno por uno si estan seleccionados.
		//En caso de que sea cierto que esta seleccionado el i-esimo JCheckBox, analiza cual JCheckBox fue:
		//binarioC, octalC, hexaC. 
		//e implementa el metodo relacionado para realizar la conversion correspondiente. 
		
		if(e.getSource()==botonCalcular) {
			
			for(int i=0; i<checkboxes.size(); i++) {
				
				if(checkboxes.get(i).isSelected()==true) {
					
					try {
						checaCualCheckbox(i);
						//System.out.println(i);
					} catch (Exception e1) {
						System.out.println("El numero que ingresaste no es valido. Por favor ingresa un numero valido a convertir");
						decimalT.setText("");
						binarioT.setText("");
						octalT.setText("");
						hexaT.setText("");
						binarioC.setSelected(false);
						octalC.setSelected(false);
						hexaC.setSelected(false);
					}
				}
				
				else {
					checaCualTextfield(i);
				}
			}
		}
	}
	
	public void checaCualCheckbox(int i) {
		
		switch(i) {
		case 0: 
			decimalToBinary();
			break;
		case 1: 
			decimalToOctal();
			break;
		case 2:
			decimalToHexa();
			break;
		}
	}
	
	public void checaCualTextfield(int i) {
		
		switch(i) {
		case 0: 
			binarioT.setText("");;
			break;
		case 1: 
			octalT.setText("");
			break;
		case 2:
			hexaT.setText("");
			break;
		}
	}
	
	public void decimalToBinary() {
		
		decimalValue= Integer.parseInt(decimalT.getText());
		
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
			System.out.println("cero");
			binaryOutput= binaryOutput + String.valueOf(binaryValues.get(i));
		}
		
		binarioT.setText(binaryOutput);
	}
	
	public void decimalToOctal() {
		
		decimalValue= Integer.parseInt(decimalT.getText());
		
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
		
		octalT.setText(octalOutput);
	}
	
	public void decimalToHexa() {
		
		decimalValue= Integer.parseInt(decimalT.getText());
		
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
		
		hexaT.setText(hexaOutput);
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
