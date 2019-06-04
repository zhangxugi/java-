package com.zx.web;

import java.awt.Color;

	import java.awt.Font;

	import java.awt.Graphics;

	import java.awt.Graphics2D;

	import java.awt.print.PageFormat;

	import java.awt.print.Paper;

	import java.awt.print.Printable;

	import java.awt.print.PrinterException;

	import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;

	 /*
	  *预约登记
	  */

	public class Appointmentregistration implements Printable {


		public int pageSize=1;//打印的总页数
		//第一列
		public float beginx=new Float(10.5);
		public float beginy=new Float(11);
		//第二例
		public float beginy2=new Float(13);
		//第三列
		public float beginy3=new Float(15.5);
		//第四列
		public float beginy4=new Float(17.5);
		
		
		public double paperW=pxTodouble(19.8);//打印的纸张宽度
		public double paperH=pxTodouble(14);//打印的纸张高度
	    public String timeofappointment="";
	  	public String vaccinename ="";
	    public float px=pxtoint(0);//起始横坐标
	    public float  py=pxtoint(0);//起始纵坐标
	    public  Font font =new Font("黑体", Font.PLAIN, 10); //设置字体
	    
	    int num;//第几行
	    
	   //实现java.awt.print.Printable接口的打印方法

	   //pageIndex:打印的当前页，此参数是系统自动维护的，不需要手动维护，系统会自动递增

	  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)

	       throws PrinterException {

	     if (pageIndex >= pageSize)

	        //退出打印

	       return Printable.NO_SUCH_PAGE;

	     else {

	        Graphics2D g2 = (Graphics2D) graphics;
	        g2.setColor(Color.BLUE);

	        Paper p = new Paper();

	        //此处的paperW和paperH是从目标打印机的进纸规格中获取的，实际针式打印机的可打印区域是有限的，

	        //距纸张的上下左右1inch(英寸)的中间的距形框为实际可打印区域，超出范围的内容将不会打印出来(没有设置偏移的情况)

	        //如果设置偏移量，那么超出的范围也是可以打印的，这里的pageW和pageH我是直接获取打印机的进纸规格的宽和高

	        //也可以手动指定，从是如果手动指定的宽高和目标打印机的进纸规格相差较大，将会默认以A4纸为打印模版
	        

	        p.setImageableArea(0, 0, paperW, paperH);// 设置可打印区域

	        p.setSize(paperW,paperH);// 设置纸张的大小
	        pageFormat.setPaper(p);
	        drawCurrentPageText(g2, pageFormat);//调用打印内容的方法

	       return PAGE_EXISTS;

	      }

	   }

	 

	   // 打印内容

	  private void drawCurrentPageText(Graphics2D g2, PageFormat pf) {
		
	      //设置打印的字体
	      g2.setFont(font);// 设置字体
	      //如果大于说明是另一侧
	     if(num>20) {
	    	  g2.drawString(timeofappointment,pxtoint(beginy3),pxtoint(beginx+0.4*(num-20)));
	    	  g2.drawString(vaccinename,pxtoint(beginy4),pxtoint(beginx+0.4*(num-20)));
	      }else {
	    	  g2.drawString(timeofappointment,pxtoint(beginy),pxtoint(beginx+0.4*num));
	    	  g2.drawString(vaccinename,pxtoint(beginy2),pxtoint(beginx+0.4*num));
	      }
	     
	  /*for(int i=1;i<=20;i++) {
	    	  g2.drawString(timeofappointment,pxtoint(beginy),pxtoint(beginx+0.4*i));
	    	  g2.drawString(vaccinename,pxtoint(beginy2),pxtoint(beginx+0.4*i));
	      }
	      for(int i =21;i<=40;i++) {
	    	  g2.drawString(timeofappointment,pxtoint(beginy3),pxtoint(beginx+0.4*(i-20)));
	    	  g2.drawString(vaccinename,pxtoint(beginy4),pxtoint(beginx+0.4*(i-20)));
	      }*/
	  }

	   //连接打印机，弹出打印对话框

	  public void starPrint() {

	     try {

	        PrinterJob prnJob = PrinterJob.getPrinterJob();

	        PageFormat pageFormat = new PageFormat();

	       // pageFormat.setOrientation(PageFormat.PORTRAIT);
	  //预约疫苗打印为横向
	        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	         aset.add(OrientationRequested.LANDSCAPE);
	    
	        prnJob.setPrintable(this);

	        //弹出打印对话框，也可以选择不弹出打印提示框，直接打印

	     if (!prnJob.printDialog())

	           return;

	        //获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)

	        paperW=pageFormat.getWidth();

	        //获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)

	        paperH=pageFormat.getHeight();
	        //prnJob.print();
	        prnJob.print(aset);//启动打印工作
	      } catch (PrinterException ex) {

	        ex.printStackTrace();

	        System.err.println("打印错误：" + ex.toString());

	      }

	   }

	  /***
	   *  //此处h、w、px、py的单位是1/72(inch)，inch:英寸，所以这里的长度，在测量后需要进行转换
	   * @param pagesize //页数
	   * @param h//打印区域高度
	   * @param w//打印区域宽度
	   * @param px//打印起始的横坐标
	   * @param py//打印起始的纵坐标
	   * @param content//打印内容
	   * @param font//字体
	   */
	  public static void godayin(int num,String timeofappointment,String vaccinename) {
		  Appointmentregistration pm = new Appointmentregistration();
		  pm.timeofappointment=timeofappointment;
		  pm.vaccinename=vaccinename;
	      pm.num=num;
	      pm.starPrint();
	  }
	  
	  public static double pxTodouble(double oldpx) {
		  return oldpx*28.346;
	  }
	 
	  public static float pxtoint(double oldpx) {
		  return   (float) (oldpx*28.346);
	  }
	  
	   //入口方法② ④⑤⑥⑦⑧⑨⑪⑫⑬c⑮⑯⑰⑱

	  public static void main(String[] args) {
		    godayin(1,"2019/05/28","肺炎疫苗①");
		
	   }
}
