package information;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import java.awt.Color;

public class system{

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel filePath = new JLabel(" ");
	private JTextArea textPane = new JTextArea();
	private JTextField textField;
	private JScrollPane JSP=new JScrollPane(textPane);
	/**
	 * Launch the application.
	 */
	public String  getYear(){
		return textField_1.getText();
	}
	public String getMonth(){
		return textField_2.getText();
	}
	public String getDay(){
		return textField_3.getText();
	}
	public String getTime(){
		return textField.getText();
	}
	public String getStationNumber(){
		return textField_4.getText();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					system window = new system();
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
	public system() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(590, 300, 810, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("气象信息译码系统   姓名:李宗根   学号:20171346014   班级:网络工程1班");
		frame.getContentPane().setLayout(null);
		Toolkit tool = frame.getToolkit();
		Image myImage = tool.getImage("src/img/天气.png");
		frame.setIconImage(myImage);
	//	frame.back
		
		JButton btnNewButton = new JButton("添加文件夹");
		btnNewButton.setBounds(26, 305, 113, 27);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					btnNewButtonActionPerformed(e);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		

		JButton btnNewButton_1 = new JButton("开始解码");
		btnNewButton_1.setBounds(26, 345, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					btnNewButton_1ActionPerformed(e);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("请输入");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		lblNewLabel.setBounds(26, 26, 68, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("年份：");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(26, 61, 68, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("月份：");
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(26, 96, 68, 34);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("日期：");
		lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(26, 137, 72, 34);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel label = new JLabel("台站号：");
		label.setFont(new Font("楷体", Font.PLAIN, 18));
		label.setBounds(26, 206, 72, 34);
		frame.getContentPane().add(label);
		//年份
		textField_1 = new JTextField();
		textField_1.setBounds(94, 67, 86, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		//月份
		textField_2 = new JTextField();
		textField_2.setBounds(94, 104, 86, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		//日期
		textField_3 = new JTextField();
		textField_3.setBounds(94, 142, 86, 24);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		//站台号
		textField_4 = new JTextField();
		textField_4.setBounds(94, 212, 86, 24);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_1 = new JLabel("<html>请选择气象资料所<br>在的文件夹</html>");
		label_1.setFont(new Font("楷体", Font.PLAIN, 18));
		label_1.setBounds(26, 246, 164, 57);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("文件路径：");
		label_2.setFont(new Font("楷体", Font.PLAIN, 18));
		label_2.setBounds(210, 26, 90, 34);
		frame.getContentPane().add(label_2);
		
		
		JSP.setBounds(210, 99, 571, 269);
		frame.getContentPane().add(JSP);
		textPane.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textPane.setEditable(false);
		filePath.setBackground(Color.WHITE);
		filePath.setFont(new Font("楷体", Font.PLAIN, 18));
		
		JScrollPane JSP=new JScrollPane(filePath);
		JSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JSP.setFont(new Font("楷体", Font.PLAIN, 18));
		JSP.setBounds(293, 18, 488, 48);
		frame.getContentPane().add(JSP);

		JLabel label_4 = new JLabel("解码信息");
		label_4.setFont(new Font("楷体", Font.PLAIN, 18));
		label_4.setBounds(210, 61, 131, 34);
		frame.getContentPane().add(label_4);
		
		JLabel label_3 = new JLabel("时次：");
		label_3.setFont(new Font("楷体", Font.PLAIN, 18));
		label_3.setBounds(26, 172, 72, 34);
		frame.getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(94, 179, 86, 24);
		frame.getContentPane().add(textField);
	}
	private void btnNewButtonActionPerformed(ActionEvent e){
		JFileChooser fc=new JFileChooser();
		frame.getContentPane().add(fc);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
		String path=null;
		File f=null;
		int flag = 0;
		try{
			flag=fc.showOpenDialog(null);
		}
		catch(HeadlessException head){
			System.out.println("Open File Dialog ERROR!");
		}
		if(flag==JFileChooser.APPROVE_OPTION){
			//获得该文件
			f=fc.getSelectedFile();
			path=f.getPath();
			filePath.setText(path);
			app.dirName=path;
		}
	}
	private String getFileName(app newApp){
		newApp.year=getYear();
		newApp.month=getMonth();
		newApp.day=getDay();
		newApp.stationNumber=getStationNumber();
		newApp.time=getTime();
		if(newApp.day.length()<2){
			newApp.day="0"+newApp.day;
		}
		if(newApp.time.length()<2){
			newApp.time="0"+newApp.time;
		}
		if(newApp.month.length()<2)
		{
			newApp.month="0"+newApp.month;
		}
		return "AAXX"+newApp.month+newApp.day+".T"+newApp.time;
	}
	private void btnNewButton_1ActionPerformed(ActionEvent e) throws IOException{
		app newApp=new app();
		if(!inputIsTrue()){
			textPane.setText("输入的信息有误！请输入空白输入框中的信息！\r\n请注意：\r\n输入的内容只能为数字\r\n输入的年份长度必须为4\r\n输入的月份长度不能大于2\r\n输入的日期长度不能大于2\r\n输入的台站号长度必须为5");
		}else{
			app.name=this.getFileName(newApp);
			if(newApp.FileName(app.name)){
				String code=newApp.findStationNumber();
				if("notExist".equals(code)){
					textPane.setText("请求的台站号不存在！");
				}else{
					textPane.setText("请求的台站号编码为：");
					textPane.setText(textPane.getText()+"\r\n"+code);
					
					newApp.decode(code);
					textPane.setText(textPane.getText()+"\r\n"+"最低云的高度："+newApp.lowestCloudHeightString+" 米");
					textPane.setText(textPane.getText()+"\r\n"+"有效能见度："+newApp.effectiveVisibilityString+" 千米");
					textPane.setText(textPane.getText()+"\r\n"+"总云量："+newApp.totalCloudString);
					textPane.setText(textPane.getText()+"\r\n"+"风向："+newApp.cloudDirection);
					textPane.setText(textPane.getText()+"\r\n"+"风速："+newApp.cloudSpeedString+" 米/秒");
					textPane.setText(textPane.getText()+"\r\n"+"气温："+newApp.temperatureString+"℃");
					textPane.setText(textPane.getText()+"\r\n"+"露点温度："+newApp.dewPoint+" ℃");
					textPane.setText(textPane.getText()+"\r\n"+"站点气压："+newApp.localPressure+" hPa");
					textPane.setText(textPane.getText()+"\r\n"+"海平面气压："+newApp.seaPressure+" hPa");
					textPane.setText(textPane.getText()+"\r\n"+"气压倾向："+newApp.pressureChangeDesString);			
					textPane.setText(textPane.getText()+"\r\n"+"气压变量："+newApp.pressureChangeString+" hPa");
					textPane.setText(textPane.getText()+"\r\n"+"降水量："+newApp.precipitation+" 毫米");
					textPane.setText(textPane.getText()+"\r\n"+"天气现象："+newApp.weatherPhenomena);
					textPane.setText(textPane.getText()+"\r\n"+"低状云情况："+newApp.CLCloud);
					textPane.setText(textPane.getText()+"\r\n"+"中状云情况："+newApp.CMCloud);
					textPane.setText(textPane.getText()+"\r\n"+"高状云情况："+newApp.CHCloud);
					textPane.setText(textPane.getText()+"\r\n"+"总云量情况："+newApp.cloudiness);
				}
			}
			else{
				textPane.setText("未找到对应文件！");
			}			
		}
		
	}
	private boolean inputIsTrue(){
		boolean yearIsTrue=true;
		boolean monthIsTrue=true;
		boolean dayIsTrue=true;
		boolean timeIsTrue=true;
		boolean stationNumIsTrue=true;
		//year
		if(textField_1.getText().length()!=4||!checkIsNum(textField_1.getText())){
			textField_1.setText("");
			yearIsTrue=false;
		}
		//month
		if(textField_2.getText().length()>2||!checkIsNum(textField_2.getText())){
			textField_2.setText("");
			monthIsTrue=false;
		}
		//day
		if(textField_3.getText().length()>2||!checkIsNum(textField_3.getText())){
			textField_3.setText("");
			dayIsTrue=false;
		}
		//time
		if(textField.getText().length()>2||!checkIsNum(textField.getText())){
			textField.setText("");
			timeIsTrue=false;
		}
		//stationNumber
		if(textField_4.getText().length()!=5||!checkIsNum(textField_4.getText())){
			textField_4.setText("");
			stationNumIsTrue=false;
		}
		return yearIsTrue&&monthIsTrue&&dayIsTrue&&timeIsTrue&&stationNumIsTrue;
	}
	private static boolean checkIsNum(String str){
		for(int i=0;i<str.length();i++){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
