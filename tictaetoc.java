import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class tictaetoc extends JFrame implements ActionListener{
	
	final int RC= 9;
	final int a = 8;
	private boolean turn=true;
	
	//버튼과 보드. 턴을 알려주는 텍스트창.
	private JPanel panel;
	private JButton[] btn;
	private JButton restart;
	private JButton end;
	private JTextField txt;
	private HashSet<Integer> Red;
	private HashSet<Integer> Blue;
	private Integer[][] w= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
	private HashSet<Integer> win;
	
	//Game 승리 조건.//
	public void check() {
		
		if(this.turn)
		{
			for(int i=0;i<8;i++) {
				for(int j=0;j<3;j++) {
					win.add(w[i][j]);
				}
				win.retainAll(Red);
				//System.out.println("Red :" +win);
				if(win.size()==3) {
					win.clear();
					JOptionPane.showMessageDialog(null, "Red Win");
					break;
				}
				win.clear();
			}
		}
		else {
			for(int i=0;i<8;i++) {
				for(int j=0;j<3;j++) {
					win.add(w[i][j]);
				}
				win.retainAll(Blue);
				//System.out.println("Blue :" +win);
				if(win.size()==3) {
					win.clear();
					JOptionPane.showMessageDialog(null, "Blue Win");
					break;
				}
				win.clear();
			}
		}
		
	}
	
	public tictaetoc(){
		//승리 조건. Red팀이 선택한 위치, Blue 팀이 선택한 위치.
		win=new HashSet<Integer>();
		Red=new HashSet<Integer>();
		Blue=new HashSet<Integer>();
		
		//결과 텍스트 설정.
		txt= new JTextField();
		txt.setText("Red Turn");
		this.add(txt, BorderLayout.NORTH);
		
		//종료 버튼, 다시시작 버튼 추가.
		restart= new JButton("ReStart");
		restart.addActionListener(this);
		this.add(restart, BorderLayout.EAST);
		end = new JButton("End");
		end.addActionListener(this);
		this.add(end, BorderLayout.WEST);
		
		//패널 설정.
		panel=new JPanel();
		panel.setSize(300,180);
		this.setTitle("TicTacTok");
		panel.setLayout(new GridLayout(0,3));
		this.add(panel, BorderLayout.CENTER);
		
		//버튼 설정
		btn= new JButton[RC];
		for(int i=0;i<RC;i++) {
			btn[i]=new JButton("");
			btn[i].addActionListener(this);
			btn[i].setPreferredSize(new Dimension(100,40));
			panel.add(btn[i]);
		}
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//배열탐색 후 선택된 버튼찾기
		for(int i=0; i<RC; i++) {
				if(e.getSource()==btn[i]) {
					//버튼이 비어 있을 경우에만 실행.
					if(btn[i].getText().equals("")) {
						if(this.turn==true) {
							txt.setText("Blue Turn");
							btn[i].setText("O");
							btn[i].setBackground(new Color(255,0,0));
							Red.add(i);
							check();
							this.turn=false;							
							
						}
						else {
							txt.setText("Red Turn");
							btn[i].setText("X");
							btn[i].setBackground(new Color(0,0,255));
							Blue.add(i);
							check();
							this.turn=true;
							
						}
				}
			}
		}
		
		//종료 버튼 입력 시.
		if(e.getSource()==end) {
			System.exit(0);
		}
		
		//다시하기 버튼 입력 시.
		else if(e.getSource()==restart){
			for(int i=0;i<RC;i++) {
				btn[i].setText("");
				btn[i].setBackground(new Color(255,255,255));
			}
			Red.clear();
			Blue.clear();
			win.clear();
			this.turn= true;
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tictaetoc s = new tictaetoc();
	}


}
