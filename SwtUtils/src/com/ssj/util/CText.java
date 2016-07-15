package com.ssj.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/*********************************************************************************
 * SWT的Text太宽松了，所以写了CText来取代它。旨在，用在有输入限制的地方。 原SWT的样式可以直接用。 
 * <code><pre>
 * //一个只能输入整型的myText，注意：没限制输入长度
 * CText myText = new CText(parent, SWT.BORDER | CText.INT_ONLY);
 * </pre></code>
 * 
 * <code><pre>
 * //一个只能输入6个中文的myText
 * CText myText = new CText(parent, 6, CText.BORDER | CText.CH_ONLY);
 * </pre></code> 
 * CH_ONLY：只能输中文<br>
 * INT_ONLY：只能输整型<br>
 * FLOAT_ONLY：只能输浮点型<br>
 * EN_ONLY：只能输英文<br>
 * CH_EN_DOT：能输中文，英文，点号，空格。<p>
 *  注意：以上几个样式最好不要同时使用<p>
 *  新加入：NOT_NULL：不能为空，提示空的输入，能和其他样式同时使用。<br>
 * 		：NAME：名字类型：中英文，点号（.），和上下横杠（ _, - ）。<br>
 * 		：CODE：编码代码类型：很丰富的。
 * 		:NSNCODE: NSN编码，在数据库中是唯一的。	使用此样式须在new操作后紧跟setNSNCODEClass(String pack)方法。
				设置该NSN编码的对应类。
 * 
 * @version 1.0
 * @author ouy
 * @date 2009.11
 **********************************************************************************/

public class CText {
	private Text text;

	private CToolTip toolTip;

	private String message = "请输入";
	
	private String NSNCODEClass = "";

	private int limit;

	private String sage;
/**
 * 只汉字
 */
	public final static int CH_ONLY = 1 << 4;
/**
 * 只整型
 */
	public final static int INT_ONLY = 1 << 5;
/**
 * 只浮点
 */
	public final static int FLOAT_ONLY = 1 << 7;
/**
 * 只英文
 */
	public final static int EN_ONLY = 1 << 10;
/**
 * 只英文，中文，点号
 */
	public final static int CH_EN_DOT = 1 << 12;
/**
 * 非空，没什么用
 */
	public final static int NOT_NULL = 1 << 13;
	
	/**
	 * 名字类型：中英文，点，和上下横杠（ _, - ）
	 */ 
	public final static int NAME = 1<< 15;
	
	/**
	 * 编码,代码类型:英文字符，数字，上下横杠（_, -），点号（.），冒号（：），单双引号（",",','），
	 * 各种符号（+，-，*，/，&，%，￥，#，$，@，！，|，~，=，？），
	 * 正反斜杠（/, \），小中大括号（(，)，[，]，{，}）。
	 * 
	 */
	public final static int CODE = 1<< 16;
	
	/**
	 * NSN码，它在表中是唯一的，需查数据库。比较特殊。<br>
	 * 若用了此类型，则必须要用XXX方法设置该码所对应的表名。<br>
	 * 且须紧接new操作之后。
	 */
	public final static int NSNCODE = 1<< 18;
	
	
	
	
	public final static int NONE = SWT.NONE;

	public final static int BORDER = SWT.BORDER;

	public final static int CENTER = SWT.CENTER;

	public final static int RIGHT = SWT.RIGHT;

	public final static int LEFT = SWT.LEFT;

	public final static int WRAP = SWT.WRAP;

	public final static int MUTLI = SWT.MULTI;

	public final static int SINGLE = SWT.SINGLE;

	public final static int PASSWORD = SWT.PASSWORD;

	public final static int READ_ONLY = SWT.READ_ONLY;

	public final static int V_SCROLL = SWT.V_SCROLL;

	public final static int H_SCROLL = SWT.H_SCROLL;

	private int currentStyle;

	/**
	 * @param parent
	 *            父容器
	 * @param limit
	 *            限制CText输入的字数的个数
	 * @param style
	 *            样式： CText.CH_ONLY，CTexT.INT_ONLY, etc
	 */
	public CText(Composite parent, int limit, int style) {
		// 屏蔽了 14 个原来Text的样式，心痛啊。当然，屏蔽的都是很少用到的，或不可能用到的。
		int style1 = style
				& (~(1 << 4 | 1 << 5 | 1 << 7 | 1 << 10 | 1 << 12 | 1 << 13
						| 1 << 15 | 1 << 16 | 1 << 18 | 1 << 19 | 1 << 20
						| 1 << 21 | 1 << 23 | 1 << 25));

		this.currentStyle = style;

		text = new Text(parent, CText.NONE | style1);

		text.setTextLimit(limit);
		this.limit = limit;

		setTextStyle(style);

		String str = limit + "位";
		message += str;

		if (sage != null)
			message += sage;

		setShow();
	}

	/**
	 * @param parent
	 *            父容器
	 * @param style
	 *            样式 ：CText.CH_ONLY，CTexT.INT_ONLY，etc
	 */
	public CText(Composite parent, int style) {
		// 屏蔽了 14 个原来Text的样式，心痛啊。当然，屏蔽的都是很少用到的，或不可能用到的。
		int style1 = style
				& (~(1 << 4 | 1 << 5 | 1 << 7 | 1 << 10 | 1 << 12 | 1 << 13
						| 1 << 15 | 1 << 16 | 1 << 18 | 1 << 19 | 1 << 20
						| 1 << 21 | 1 << 23 | 1 << 25));

		text = new Text(parent, style1);

		this.currentStyle = style;

		setTextStyle(style);

		if (sage != null) {
			message += sage;
		} else {
			message = null;
		}
		setShow();
	}

	/**
	 * 设置提示信息框出现。
	 */
	private void setShow() {

		text.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {
				//获得焦点时，若为" "，则设为""
//				if(text.getText().equals(" ")){
//					text.setText(" ");
//				}
				
				if (message != null) {
					toolTip = new CToolTip(text, message);
//					toolTip.setHideOnMouseDown(true);
					toolTip.setPopupDelay(200);
//					toolTip.setHideDelay(2000);
					toolTip.show(new Point(-4, -text.getSize().y - 7));
				}
				
			}

			public void focusLost(FocusEvent arg0) {
				if (toolTip != null) {
					toolTip = null;
				}
					if ((currentStyle & NOT_NULL) != 0) {
						CToolTip toolTip = new CToolTip(text, "此处不允许为空！");
						if (text.getText().length() == 0) {
							toolTip.setHideOnMouseDown(true);
							toolTip.setHideDelay(3000);
							toolTip.show(new Point(-4, -text.getSize().y - 7));
						}else if(toolTip != null){
							toolTip.hide();
							toolTip.deactivate();
						}
						
					}
					
					if((currentStyle & NSNCODE) != 0){
						
						
					}
					
				}
//			}
		});
	}

	/**
	 * 
	 * @param keyCode
	 * @return 若按键为普通建，则返回true
	 */
	private boolean checkKey(int keyCode){
		if((keyCode != SWT.BS) && (keyCode != SWT.DEL) && (keyCode != SWT.ARROW_RIGHT) && (keyCode != SWT.ARROW_LEFT))
			return true;
		else
			return false;
	}
	
	
	/**
	 * 设置框的类型，限制框的输入
	 * @param style CText 的类型
	 */
	private void setTextStyle(int style) {
		/*
		 * 只能输入整型
		 */
		if ((style & INT_ONLY) != 0) {
			sage = "整数";
			text.addKeyListener(new KeyAdapter() {
				
				public void keyPressed(KeyEvent e) {
					String s = String.valueOf(e.character);
					
					
					String str = text.getText() + s;
					
					//在末尾输入时
					if (checkKey(e.keyCode)) {
						e.doit = Pattern.matches("^[1-9]\\d*|0$", str);
					}
					//当内容全选时
					if(text.getSelectionCount() == text.getText().length()){
						if(checkKey(e.keyCode))
							e.doit = Pattern.matches("^[1-9]\\d*|0$", s);
					}
					
					
					if (!e.doit && toolTip != null) {
						toolTip.show(new Point(-4, -text.getSize().y - 7));
					}
				}
			});
		}

		/*
		 * 只能输入浮点数
		 */
		else if ((style & FLOAT_ONLY) != 0) {
			sage = "小数或整数";
			text.addKeyListener(new KeyAdapter() {
				
				public void keyPressed(KeyEvent e) {
					String s = String.valueOf(e.character);
					if (text.getText().equals("")) {
						e.doit = Pattern.matches("[0-9]", s);
					} else if (checkKey(e.keyCode)) {
					
						String str = text.getText() + e.character;
						if (str.length() > 1 && e.character != '.') {
							if(text.getText().equals("0")){
								text.setText("");
								e.doit = true;
							}else{
							e.doit = Pattern
									.matches(
											"^[1-9]\\d*\\.?\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$",
											str);
							}
						} else {
							e.doit = Pattern.matches(
									"^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]?\\d*$",
									str);
						}
					
					}
					
//					当内容全选时
					if(text.getSelectionCount() == text.getText().length()){
						if(checkKey(e.keyCode))	
							e.doit = Pattern.matches("[0-9]", s);
					}
					
					if (!e.doit && toolTip != null) {
						toolTip.show(new Point(-4, -text.getSize().y - 7));
					}
				}
			});
		}

		/*
		 * 只能输入汉字
		 */
		else if ((style & CH_ONLY) != 0) {
			sage = "汉字";
			text.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (checkKey(e.keyCode)) {
						// if(e.text.length()>1)
						e.doit = Pattern.matches("[\u4e00-\u9fa5]", String
								.valueOf(e.character));
					}
					if (!e.doit) {
						toolTip.show(new Point(-4, -text.getSize().y - 7));
					}
				}
			});
		}

		/*
		 * 只能输入英文
		 */
		else if ((style & EN_ONLY) != 0) {
			sage = "英文";
			text.addKeyListener(new KeyAdapter() {
				
				public void keyPressed(KeyEvent e) {
					if (checkKey(e.keyCode)) {
						e.doit = Pattern.matches("[a-zA-Z]", String
								.valueOf(e.character));
					}
					if (!e.doit && toolTip != null) {
						toolTip.show(new Point(-4, -text.getSize().y - 7));
					}
				}
			});
		}

		/*
		 * 中英文，点和空格
		 */
		else if ((style & CH_EN_DOT) != 0) {
			sage = "中、英文，空格或点号";
			text.addKeyListener(new KeyAdapter() {
				
				public void keyPressed(KeyEvent e) {
					if (checkKey(e.keyCode)) {
						e.doit = Pattern.matches("[a-z. A-Z]|[\u4e00-\u9fa5]",
								String.valueOf(e.character));
					}
					if (!e.doit && toolTip != null) {
						toolTip.show(new Point(-4, -text.getSize().y - 7));
					}
				}
			});
		} else if (limit > 0) {

			text.addKeyListener(new KeyAdapter() {
				
				public void keyPressed(KeyEvent e) {

					if (checkKey(e.keyCode)) {
						String str = text.getText()
								+ String.valueOf(e.character);
						if (str.length() > limit && toolTip != null) {
							toolTip.show(new Point(-4, -text.getSize().y - 7));
						}
					}
				}
			});
		}
		else if((style & NAME) != 0){
			 
			sage = "名字";
			
			text.addKeyListener(new KeyAdapter() {
				
				public void keyPressed(KeyEvent e) {
					if (checkKey(e.keyCode)) {
						e.doit = Pattern.matches("[a-z.& A-Z]|[\u4e00-\u9fa5] |[0-9]",
								String.valueOf(e.character));
					}
					if (!e.doit && toolTip != null) {
						toolTip.show(new Point(-4, -text.getSize().y - 7));
					}
				}
			});
			
		}
		else if((style & CODE) != 0){
			//
			sage = "编码";
			
			text.addKeyListener(new KeyAdapter() {
				
				public void keyPressed(KeyEvent e) {
					if (checkKey(e.keyCode)) {
						e.doit = Pattern.matches("[a-z.&-_ A-Z]|[\u4e00-\u9fa5] |[0-9]",
								String.valueOf(e.character));
					}
					if (!e.doit && toolTip != null) {
						toolTip.show(new Point(-4, -text.getSize().y - 7));
					}
				}
			});
		}
		else if((style & NSNCODE) != 0){
			
			sage = "NSN编码";
			
			text.addKeyListener(new KeyAdapter() {
				
				public void keyPressed(KeyEvent e) {
					if (checkKey(e.keyCode)) {
						e.doit = Pattern.matches("[a-z.&-_ A-Z]|[\u4e00-\u9fa5] |[0-9]",
								String.valueOf(e.character));
					}
					if (!e.doit && toolTip != null) {
						toolTip.show(new Point(-4, -text.getSize().y - 7));
					}
				}
			});
			
			
		}
		
		
	}
	
	public void setNSNCODEClass(String NSNCODEClass){
		this.NSNCODEClass = NSNCODEClass;
		
		
		
		
		
	}
	

	public void addModifyListener(ModifyListener listener) {
		text.addModifyListener(listener);
	}

	public void addVerifyListener(VerifyListener listener) {
		text.addVerifyListener(listener);
	}

	public void addMouseListener(MouseListener listener) {
		text.addMouseListener(listener);
	}

	public void addMouseMoveListener(MouseMoveListener listener) {
		text.addMouseMoveListener(listener);
	}

	public void addSelectionListener(SelectionListener listener) {
		text.addSelectionListener(listener);
	}

	private void addFocusListener(FocusListener listener) {
		text.addFocusListener(listener);
	}

	public void setBounds(int x, int y, int width, int height) {
		text.setBounds(x, y, width, height);
	}

	public void setEchoChar(char echo) {
		text.setEchoChar(echo);
	}

	public char getEchoChar() {
		return text.getEchoChar();
	}

	public void setEditable(boolean editable) {
		text.setEditable(editable);
	}

	public boolean getEditable() {
		return text.getEditable();
	}

	public void setEnabled(boolean enabled) {
		text.setEnabled(enabled);
	}

	public boolean getEnable() {
		return text.getEnabled();
	}

	public void setFont(Font font) {
		text.setFont(font);
	}

	public Font getFont() {
		return text.getFont();
	}

	/**
	 * 使CText获得焦点
	 */
	public void setFoucs() {
		text.setFocus();
	}

	/**
	 * 设置CText的初值
	 * @param string 初值
	 */
	public void setText(String string) {
		text.setText(string);
	}

	/**
	 * @return text.getText().trim() CText中的字符串值
	 */
	public String getText() {
		return text.getText();
	}

	/**
	 * 设置CText的字数限制
	 * @param limit 限制数
	 */
	public void setTextLimit(int limit) {
		text.setTextLimit(limit);
	}

	public int getTextLimit() {
		return text.getTextLimit();
	}

	public int getLineCount() {
		return text.getLineCount();
	}

	public int getLineHeight() {
		return text.getLineHeight();
	}

	public Point getSelection() {
		return text.getSelection();
	}
	
	public int getSelectionCount(){
		return text.getSelectionCount();
	}

	public String getSelectionText() {
		return text.getSelectionText();
	}

	public void selectAll() {
		text.selectAll();
	}

	public void setRedraw(boolean redraw) {
		text.setRedraw(redraw);
	}

	public void setSelection(int start) {
		text.setSelection(start);
	}

	public void setSelection(int start, int end) {
		text.setSelection(start, end);
	}

	public void setLayoutData(Object data) {
		text.setLayoutData(data);
	}

	public boolean isFocusControl() {
		return text.isFocusControl();
	}

	/**
	 * 返回SWT的Text控件本身
	 * 
	 * @return text SWT的Text。
	 */
	public Text getControl() {
		return text;
	}

	/**
	 * @return  message 提示信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置提示信息
	 * @param message 提示信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
