/*
* 	Oscar Hedblad 
* 	COP 3330, Howework 5b (Sierpinski Trangles)
*	Arup Ghosh
*
*	DESCRIPTION: Program that using GUI's creates a canvas then draws 
*					 triangles on it. It firsts asks the user to enter the
*					 recursive depth. Once entered it draws five trangles, 
*					 and they all can be changed to be of different color. 
*					 The program also has a "randomize color" function that
*					 allows the user to let the program randomly decide 
*					 what color the triangles get drawn in.
*
*/


/* Imports the needed */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* Enumerator that holds all colors that will be put into the combo boxes */
enum allColors{
	GRAY("Gray", Color.GRAY),
	BLUE("Blue", Color.BLUE),
	RED("Red", Color.RED),
	YELLOW("Yellow", Color.YELLOW),
	CYAN("Cyan", Color.CYAN),
	GREEN("Green", Color.GREEN),
	MAGENTA("Magenta", Color.MAGENTA),
	DARKGRAY("Dark Gray", Color.DARK_GRAY),
	ORANGE("Orange", Color.ORANGE);
	
	
/* Declaration */
	public String Colors;
	private Color Selected;
	private allColors(String Colors, Color Selected){
	this.Colors = Colors;
	this.Selected = Selected;
	}
	public Color getColor(){
		return Selected;
	}
	public String toString(){
		return Colors;
	}
}

class Sierpinski implements ActionListener{

/* Declaration of various variables */
	private JFrame window;
	private JButton draw;
	private Canvas canvas;
	private int EntDepth;
	private Graphics triangle;
	private JTextField Depth;
	private JComboBox ColorList1, ColorList2, ColorList3, ColorList4, ColorList5;
	private JPanel ComboCheckBox;
	private JCheckBox CheckBox;
	private boolean CheckBoxChoice=false;
	
/* Comes into use when using the "randomize color" function */
	Color Col1=Color.getHSBColor((float)Math.random(),1.0f,1.0f);
	Color Col2=Color.getHSBColor((float)Math.random(),1.0f,1.0f);
	Color Col3=Color.getHSBColor((float)Math.random(),1.0f,1.0f);
	Color Col4=Color.getHSBColor((float)Math.random(),1.0f,1.0f);
	Color Col5=Color.getHSBColor((float)Math.random(),1.0f,1.0f);
	
	public Sierpinski(){
	
		window = new JFrame("Sierpinski Visulizer");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(750, 550);
		window.setResizable(false);
		JPanel grid = new JPanel(new FlowLayout());
		
/* Puts canvas to the frame */
		canvas = new Canvas();
		canvas.setBackground(Color.black);
		
		canvas.setSize(512, 512);
		grid.add(canvas);
		
/* Generates grid for right side of GUI */
		JPanel RightGrid = new JPanel(new GridLayout(8,1));
		JPanel TextLabFlow = new JPanel(new FlowLayout());
		TextLabFlow.add(new JLabel("Recursion Depth:"));
		Depth = new JTextField(4);
		TextLabFlow.add(Depth);
		RightGrid.add(TextLabFlow);
		
		JPanel Color1 = new JPanel(new FlowLayout());
		Color1.add(new JLabel("Color 1"));
		
/* Generates first Combo Box */
		ColorList1 = new JComboBox(allColors.values());
		Color1.add(ColorList1);
		RightGrid.add(Color1);
		
		JPanel Color2 = new JPanel(new FlowLayout());
		Color2.add(new JLabel("Color 2"));

/* Generates second Combo Box */
		ColorList2 = new JComboBox(allColors.values());
		Color2.add(ColorList2);
		RightGrid.add(Color2);
	
		JPanel Color3 = new JPanel(new FlowLayout());
		Color3.add(new JLabel("Color 3"));
		
/* Generates third Combo Box */
		ColorList3 = new JComboBox(allColors.values());
		Color3.add(ColorList3);
		RightGrid.add(Color3);
	
		JPanel Color4 = new JPanel(new FlowLayout());
		Color4.add(new JLabel("Color 4"));
		
/* Generates forth Combo Box */
		ColorList4 = new JComboBox(allColors.values());
		Color4.add(ColorList4);
		RightGrid.add(Color4);
	
		JPanel Color5 = new JPanel(new FlowLayout());
		Color5.add(new JLabel("Color 5"));
		
/* Generates fifth Combo Box */
		ColorList5 = new JComboBox(allColors.values());
		Color5.add(ColorList5);
		RightGrid.add(Color5);
	
		ComboCheckBox = new JPanel();
		CheckBox = new JCheckBox("Randomizes colors at each level");
		ComboCheckBox.add(CheckBox);
		RightGrid.add(ComboCheckBox);
		CheckBox.addActionListener(this);
	
		grid.add(RightGrid);
		
		draw = new JButton("Draw");
		RightGrid.add(draw);
		draw.addActionListener(this);

		//Adds the whole layout to the window
		window.add(grid);
		window.setVisible(true);
	}
	
	public static void main(String[] args){
		new Sierpinski();
	}
	
	
	void draw(int d, int x, int y, int S) {
		
		if(d==0) return;
		//Set corner points of triangle into array
		int X[]={x,x+S/2, x+S};
		int Y[]={y+S, y, y+S};
		triangle = canvas.getGraphics();
		int CreatedDepth=EntDepth-d+1;
		//Getting the color at depth one and will reuse it at depth six if it is needed
		if(CreatedDepth==1||CreatedDepth==6){
			//If the user does not want random colors, this will get their choice from the Combo box
			if(CheckBoxChoice==false){
				//Gets their desired color choice and sets it to the correct depth
				allColors UserColChoice = (allColors)ColorList1.getSelectedItem();
				Color CSelect = UserColChoice.getColor();
				triangle.setColor(CSelect);
			}
			//If the user wants random colors, this depth will have a random color assigned to it
			if(CheckBoxChoice==true){
				triangle.setColor(Col1);
			}
		}
		//Getting the color at depth two and will reuse it at depth seven if it is needed
		if(CreatedDepth==2||CreatedDepth==7){
			//If the user does not want random colors, this will get their choice from the Combo box
			if(CheckBoxChoice==false){
				//Gets their desired color choice and sets it to the correct depth
				allColors UserColChoice = (allColors)ColorList2.getSelectedItem();
				Color CSelect = UserColChoice.getColor();
				triangle.setColor(CSelect);
			}
			//If the user wants random colors, this depth will have a random color assigned to it
			if(CheckBoxChoice==true){
				triangle.setColor(Col2);
			}
		}
		//Getting the color at depth three and will reuse it at depth eight if it is needed
		if(CreatedDepth==3||CreatedDepth==8){
			//If the user does not want random colors, this will get their choice from the Combo box
			if(CheckBoxChoice==false){
				//Gets their desired color choice and sets it to the correct depth
				allColors UserColChoice = (allColors)ColorList3.getSelectedItem();
				Color CSelect = UserColChoice.getColor();
				triangle.setColor(CSelect);
			}
			//If the user wants random colors, this depth will have a random color assigned to it
			if(CheckBoxChoice==true){
				triangle.setColor(Col3);
			}
		}
		//Getting the color at depth four and will reuse it at depth nine if it is needed
		if(CreatedDepth==4||CreatedDepth==9){
			//If the user does not want random colors, this will get their choice from the Combo box
			if(CheckBoxChoice==false){
				//Gets their desired color choice and sets it to the correct depth
				allColors UserColChoice = (allColors)ColorList4.getSelectedItem();
				Color CSelect = UserColChoice.getColor();
				triangle.setColor(CSelect);
			}
			//If the user wants random colors, this depth will have a random color assigned to it
			if(CheckBoxChoice==true){
				triangle.setColor(Col4);
			}
		}
		//Getting the color at depth five and will reuse it at depth ten if it is needed
		if(CreatedDepth==5||CreatedDepth==10){
			//If the user does not want random colors, this will get their choice from the Combo box
			if(CheckBoxChoice==false){
				//Gets their desired color choice and sets it to the correct depth
				allColors UserColChoice = (allColors)ColorList5.getSelectedItem();
				Color CSelect = UserColChoice.getColor();
				triangle.setColor(CSelect);
			}
			//If the user wants random colors, this depth will have a random color assigned to it
			if(CheckBoxChoice==true){
				triangle.setColor(Col5);
			}
		}
		//Fills the triangles using the arrays of points and number of sides that it has
		triangle.fillPolygon(X, Y, 3);
		// Draw the subtriangles. The self-similarity of fractals means
		// that they are themselves Sierpinski triangles of depth d-1.
		draw(d-1, x+S/4, y, S/2);
		draw(d-1, x, y+S/2, S/2);
		draw(d-1, x+S/2, y+S/2, S/2);
	}
	public void actionPerformed(ActionEvent e) {
		//Gets the status of the check box
		if(e.getSource() == CheckBox ){
			//If the user clicks to randomize the colors, this will make the combo boxes disabled
			ColorList1.setEnabled(false);
			ColorList2.setEnabled(false);
			ColorList3.setEnabled(false);
			ColorList4.setEnabled(false);
			ColorList5.setEnabled(false);
			CheckBoxChoice= true;
		}
		//When the user clicks the draw button this action is then performed
		if(e.getSource()==draw){
			try {
			//Getting the depth from the text field that is a string and converts it into and integer
			int USERenteredDEPTH=Integer.parseInt(Depth.getText());
			//Checks to see if the entered depth is between 1 and 10
			 if( USERenteredDEPTH> 0 && USERenteredDEPTH<= 10){
				 EntDepth = USERenteredDEPTH;
				 //Draws the trianles at the specified depth
				 draw(EntDepth,0,0,512);
				 return;
			 }
			 //If the user enters an depth that is larger than 10, it will show a message informing them that the depth is too large
			 else{
			 JOptionPane.showMessageDialog(null, "The depth cannot exceed 10!");
			 Depth.setText("");
			 
			 }
		}
		//Catches an exception the deals with the text field 
		catch(NumberFormatException ex) {
				Depth.setText("");
		}
		}
		}
	
}
