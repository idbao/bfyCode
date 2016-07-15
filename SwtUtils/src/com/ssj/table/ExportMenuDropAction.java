package com.ssj.table;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;
import com.ssj.util.MpStaticName;
import com.ssj.util.SWTResourceManager;
import com.ssj.util.ShowClickImage;


public class ExportMenuDropAction implements IMenuCreator {
	private TableViewer gridViewer;
	private final static int HTML=5,PDF=4,WORD=6;
	private Color borderColor=new Color(0 ,191, 255),
	headColor=new Color(30, 144, 255 ),
	rowEvenColor=new Color(255 ,250 ,250),
	rowOddColor=new Color(230 ,230 ,250),
	textColor=new Color(176 ,48 ,96 );
	public void dispose() {

	}

	public ExportMenuDropAction(TableViewer grid) {
		this.gridViewer = grid;
	}


	public Menu getMenu(Menu par) {
		Menu sd = new Menu(par);
		MenuItem item = new MenuItem(sd, SWT.NONE);
		item.setText(MpStaticName.exportPDF);
		item.setImage(SWTResourceManager.getImage(ExportMenuDropAction.class, "/images/pdf.bmp"));
		item.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 得到保存路径
				FileDialog sd = new FileDialog(new Shell(Display.getCurrent()));
				sd.setFilterExtensions(new String[] { ".pdf" });
				final String path = sd.open()+".pdf";
				if(path.equals("null.pdf")){
					return;
				}
				new ShowClickImage(){
					public void setting(){
						create(gridViewer,path,PDF);//在这里写自己要做的事情
					}
					
				}.run();
				
			}

		});
		MenuItem item2 = new MenuItem(sd, SWT.NONE);
		item2.setText(MpStaticName.exportHTML);
		item2.setImage(SWTResourceManager.getImage(ExportMenuDropAction.class, "/images/html.bmp"));
		item2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 得到保存路径
				FileDialog sd = new FileDialog(new Shell(Display.getCurrent()));
				sd.setFilterExtensions(new String[] { ".htm" });
				final String path = sd.open()+".htm";
				if(path.equals("null.htm")){
					return;
				}
				new ShowClickImage(){
					public void setting(){
						create(gridViewer,path,HTML);//在这里写自己要做的事情
					}
					
				}.run();
				
			}

		});
		MenuItem item3 = new MenuItem(sd, SWT.NONE);
		item3.setText(MpStaticName.exportXLS);
		item3.setImage(SWTResourceManager.getImage(ExportMenuDropAction.class, "/images/xls.bmp"));
		item3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 得到保存路径
				FileDialog sd = new FileDialog(new Shell(Display.getCurrent()));
				sd.setFilterExtensions(new String[] { ".xls" });
				final String path = sd.open()+".xls";
				if(path.equals("null.xls")){
					return;
				}
				new ShowClickImage(){
					public void setting(){
						createXLS(gridViewer,path);//在这里写自己要做的事情
					}
					
				}.run();
				
			}

		});
		MenuItem item4 = new MenuItem(sd, SWT.NONE);
		item4.setText(MpStaticName.exportWORD);
		item4.setImage(SWTResourceManager.getImage(ExportMenuDropAction.class, "/images/doc.bmp"));
		item4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 得到保存路径
				FileDialog sd = new FileDialog(new Shell(Display.getCurrent()));
				sd.setFilterExtensions(new String[] { ".doc" });
				final String path = sd.open()+".doc";
				if(path.equals("null.doc")){
					return;
				}
				new ShowClickImage(){
					public void setting(){
						createDoc(gridViewer,path);//在这里写自己要做的事情
					}
					
				}.run();
				
			}

		});
		return sd;
	}

	protected void createXLS(TableViewer gridViewer, String path) {

		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(path));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)//以及单元格内容为
		org.eclipse.swt.widgets.Table grid=gridViewer.getTable();
			int colmnCount=grid.getColumnCount();
			int itemCount=grid.getItemCount();
			
			//表头  0行
			for(int i=0;i<colmnCount;i++){
				Label testLabel =new Label(i,0,grid.getColumn(i).getText());
				sheet.addCell(testLabel);
			}
			
			//表内容 1行
			for(int i=0;i<itemCount;i++){
				for(int j=0;j<colmnCount;j++){
					String text=grid.getItem(i).getText(j);
					Pattern pattern = Pattern.compile("^[0-9]+\\.{0,1}[0-9]{0,2}$");
					if(text!=null&&!text.equals("")){
					Matcher isNum = pattern.matcher(text);
					if(!isNum.matches())
					{
						Label testLabel =new Label(j,i,text);
						sheet.addCell(testLabel);
					}else{
						double d=Double.valueOf(text);
						
						Number number = new Number(j, i,d);
						sheet.addCell(number);
					}
					}
					
				}
			}
			
			book.write();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void create(TableViewer gridViewer,String path,int type) {

		//创建一个文档。文档是 PDF 文档的所有元素的容器。
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		//参数依次为:页面大小,左右上下的距离

		try {
			
			//创建PdfWriter写入器
			if(type==PDF){
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(path));
			}else if(type==HTML){
				HtmlWriter writer=HtmlWriter.getInstance(document, 
						new FileOutputStream(path));
			}
			//第一个参数是对文档的引用，第二个为路径
			document.open();
			//打开文档，以写入内容
			BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		   Font FontChinese = new Font(bf, 10, Font.NORMAL,textColor);   
		    
			
						
			//创建一个新段落
			Paragraph title1 = new Paragraph(MpStaticName.exportTitle,FontChinese);
			//创建一个章节，参数为所属的段落
			Chapter chapter1 = new Chapter("", 1);
			chapter1.setNumberDepth(0);
			//创建一个小节，参数为所属的章节
			Section section1 = chapter1.addSection(title1);
			org.eclipse.swt.widgets.Table grid=gridViewer.getTable();
			int colmnCount=grid.getColumnCount();
			int itemCount=grid.getItemCount();
			Table t=new Table(colmnCount,itemCount);
			t.setBorderColor(borderColor);//边界颜色
			t.setPadding(2);
			t.setSpacing(0);//单元格的边界
			t.setBorderWidth(1);//table的边界
			
			Cell c=new Cell();
			c.setColspan(colmnCount);
			c.setBackgroundColor(headColor);
			c.setHeader(true);
			t.addCell(c);
			
			//表头
			for(int i=0;i<colmnCount;i++){
				Cell c1 = new Cell(new Phrase(grid.getColumn(i).getText(),FontChinese));
				c1.setHeader(true);
				t.addCell(c1);
			}
			t.endHeaders();
			
			//表内容
			for(int i=0;i<itemCount;i++){
				for(int j=0;j<colmnCount;j++){
					Cell cell=new Cell(new Phrase(grid.getItem(i).getText(j),FontChinese));
					if(i%2==0){
						cell.setBackgroundColor(rowEvenColor);
					}else{
						cell.setBackgroundColor(rowOddColor);
					}
					t.addCell(cell);
				}
			}
			
			
			section1.add(t);
			
			document.add(chapter1);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	
		
		
	}

	public void createDoc(TableViewer gridViewer,String path) {
		try{
		Document document = new Document(PageSize.A4); 
		 document.addTitle("Title"); 
		 document.addHeader("header","Header"); 
		 BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		   Font FontChinese = new Font(bf, 10, Font.NORMAL,textColor);
		RtfWriter2.getInstance(document, new FileOutputStream(path)); 

		document.open(); 
//		 add a word 
		document.add(new Paragraph(MpStaticName.exportTitle,FontChinese)); 
//		 add a table 
		org.eclipse.swt.widgets.Table grid=gridViewer.getTable();
		int colmnCount=grid.getColumnCount();
		int itemCount=grid.getItemCount();
		   
		Table t=new Table(colmnCount,itemCount);
		t.setBorderColor(borderColor);//边界颜色
		t.setPadding(2);
		t.setSpacing(0);//单元格的边界
		t.setBorderWidth(1);//table的边界
		
		Cell c=new Cell();
		c.setColspan(colmnCount);
		c.setBackgroundColor(headColor);
		c.setHeader(true);
		t.addCell(c);
		
		//表头
		for(int i=0;i<colmnCount;i++){
			Cell c1 = new Cell(new Phrase(grid.getColumn(i).getText(),FontChinese));
			c1.setHeader(true);
			t.addCell(c1);
		}
//		表内容
		for(int i=0;i<itemCount;i++){
			for(int j=0;j<colmnCount;j++){
				Cell cell=new Cell(new Phrase(grid.getItem(i).getText(j),FontChinese));
				if(i%2==0){
					cell.setBackgroundColor(rowEvenColor);
				}else{
					cell.setBackgroundColor(rowOddColor);
				}
				t.addCell(cell);
			}
		}

		document.add(t); 

		document.close(); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Menu getMenu(Control arg0) {
		
		return null;
	}

}
