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
 * SWT��Text̫�����ˣ�����д��CText��ȡ������ּ�ڣ��������������Ƶĵط��� ԭSWT����ʽ����ֱ���á� 
 * <code><pre>
 * //һ��ֻ���������͵�myText��ע�⣺û�������볤��
 * CText myText = new CText(parent, SWT.BORDER | CText.INT_ONLY);
 * </pre></code>
 * 
 * <code><pre>
 * //һ��ֻ������6�����ĵ�myText
 * CText myText = new CText(parent, 6, CText.BORDER | CText.CH_ONLY);
 * </pre></code> 
 * CH_ONLY��ֻ��������<br>
 * INT_ONLY��ֻ��������<br>
 * FLOAT_ONLY��ֻ���両����<br>
 * EN_ONLY��ֻ����Ӣ��<br>
 * CH_EN_DOT���������ģ�Ӣ�ģ���ţ��ո�<p>
 *  ע�⣺���ϼ�����ʽ��ò�Ҫͬʱʹ��<p>
 *  �¼��룺NOT_NULL������Ϊ�գ���ʾ�յ����룬�ܺ�������ʽͬʱʹ�á�<br>
 * 		��NAME���������ͣ���Ӣ�ģ���ţ�.���������º�ܣ� _, - ����<br>
 * 		��CODE������������ͣ��ܷḻ�ġ�
 * 		:NSNCODE: NSN���룬�����ݿ�����Ψһ�ġ�	ʹ�ô���ʽ����new���������setNSNCODEClass(String pack)������
				���ø�NSN����Ķ�Ӧ�ࡣ
 * 
 * @version 1.0
 * @author ouy
 * @date 2009.11
 **********************************************************************************/

public class CText {
	private Text text;

	private CToolTip toolTip;

	private String message = "������";
	
	private String NSNCODEClass = "";

	private int limit;

	private String sage;
/**
 * ֻ����
 */
	public final static int CH_ONLY = 1 << 4;
/**
 * ֻ����
 */
	public final static int INT_ONLY = 1 << 5;
/**
 * ֻ����
 */
	public final static int FLOAT_ONLY = 1 << 7;
/**
 * ֻӢ��
 */
	public final static int EN_ONLY = 1 << 10;
/**
 * ֻӢ�ģ����ģ����
 */
	public final static int CH_EN_DOT = 1 << 12;
/**
 * �ǿգ�ûʲô��
 */
	public final static int NOT_NULL = 1 << 13;
	
	/**
	 * �������ͣ���Ӣ�ģ��㣬�����º�ܣ� _, - ��
	 */ 
	public final static int NAME = 1<< 15;
	
	/**
	 * ����,��������:Ӣ���ַ������֣����º�ܣ�_, -������ţ�.����ð�ţ���������˫���ţ�",",','����
	 * ���ַ��ţ�+��-��*��/��&��%������#��$��@������|��~��=��������
	 * ����б�ܣ�/, \����С�д����ţ�(��)��[��]��{��}����
	 * 
	 */
	public final static int CODE = 1<< 16;
	
	/**
	 * NSN�룬���ڱ�����Ψһ�ģ�������ݿ⡣�Ƚ����⡣<br>
	 * �����˴����ͣ������Ҫ��XXX�������ø�������Ӧ�ı�����<br>
	 * �������new����֮��
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
	 *            ������
	 * @param limit
	 *            ����CText����������ĸ���
	 * @param style
	 *            ��ʽ�� CText.CH_ONLY��CTexT.INT_ONLY, etc
	 */
	public CText(Composite parent, int limit, int style) {
		// ������ 14 ��ԭ��Text����ʽ����ʹ������Ȼ�����εĶ��Ǻ����õ��ģ��򲻿����õ��ġ�
		int style1 = style
				& (~(1 << 4 | 1 << 5 | 1 << 7 | 1 << 10 | 1 << 12 | 1 << 13
						| 1 << 15 | 1 << 16 | 1 << 18 | 1 << 19 | 1 << 20
						| 1 << 21 | 1 << 23 | 1 << 25));

		this.currentStyle = style;

		text = new Text(parent, CText.NONE | style1);

		text.setTextLimit(limit);
		this.limit = limit;

		setTextStyle(style);

		String str = limit + "λ";
		message += str;

		if (sage != null)
			message += sage;

		setShow();
	}

	/**
	 * @param parent
	 *            ������
	 * @param style
	 *            ��ʽ ��CText.CH_ONLY��CTexT.INT_ONLY��etc
	 */
	public CText(Composite parent, int style) {
		// ������ 14 ��ԭ��Text����ʽ����ʹ������Ȼ�����εĶ��Ǻ����õ��ģ��򲻿����õ��ġ�
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
	 * ������ʾ��Ϣ����֡�
	 */
	private void setShow() {

		text.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {
				//��ý���ʱ����Ϊ" "������Ϊ""
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
						CToolTip toolTip = new CToolTip(text, "�˴�������Ϊ�գ�");
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
	 * @return ������Ϊ��ͨ�����򷵻�true
	 */
	private boolean checkKey(int keyCode){
		if((keyCode != SWT.BS) && (keyCode != SWT.DEL) && (keyCode != SWT.ARROW_RIGHT) && (keyCode != SWT.ARROW_LEFT))
			return true;
		else
			return false;
	}
	
	
	/**
	 * ���ÿ�����ͣ����ƿ������
	 * @param style CText ������
	 */
	private void setTextStyle(int style) {
		/*
		 * ֻ����������
		 */
		if ((style & INT_ONLY) != 0) {
			sage = "����";
			text.addKeyListener(new KeyAdapter() {
				
				public void keyPressed(KeyEvent e) {
					String s = String.valueOf(e.character);
					
					
					String str = text.getText() + s;
					
					//��ĩβ����ʱ
					if (checkKey(e.keyCode)) {
						e.doit = Pattern.matches("^[1-9]\\d*|0$", str);
					}
					//������ȫѡʱ
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
		 * ֻ�����븡����
		 */
		else if ((style & FLOAT_ONLY) != 0) {
			sage = "С��������";
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
					
//					������ȫѡʱ
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
		 * ֻ�����뺺��
		 */
		else if ((style & CH_ONLY) != 0) {
			sage = "����";
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
		 * ֻ������Ӣ��
		 */
		else if ((style & EN_ONLY) != 0) {
			sage = "Ӣ��";
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
		 * ��Ӣ�ģ���Ϳո�
		 */
		else if ((style & CH_EN_DOT) != 0) {
			sage = "�С�Ӣ�ģ��ո����";
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
			 
			sage = "����";
			
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
			sage = "����";
			
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
			
			sage = "NSN����";
			
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
	 * ʹCText��ý���
	 */
	public void setFoucs() {
		text.setFocus();
	}

	/**
	 * ����CText�ĳ�ֵ
	 * @param string ��ֵ
	 */
	public void setText(String string) {
		text.setText(string);
	}

	/**
	 * @return text.getText().trim() CText�е��ַ���ֵ
	 */
	public String getText() {
		return text.getText();
	}

	/**
	 * ����CText����������
	 * @param limit ������
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
	 * ����SWT��Text�ؼ�����
	 * 
	 * @return text SWT��Text��
	 */
	public Text getControl() {
		return text;
	}

	/**
	 * @return  message ��ʾ��Ϣ
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * ������ʾ��Ϣ
	 * @param message ��ʾ��Ϣ
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
