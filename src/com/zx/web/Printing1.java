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

	 /*
	  * 医院打印机没有修改过
	  */

	public class Printing1 implements Printable {

		public int pageSize;//打印的总页数
		public double paperW=0;//打印的纸张宽度
		public double paperH=0;//打印的纸张高度
	    public String name="";
	    public String sex="";
	    public String year="";
	    public String month="";
	    public String day="";
	    public String birthweight="";
	    public String allergyhistory="";
	    public String province="";
	    public String city="";
	    public String county="";
	    public String township="";
	    public String father="";
	    public String mother="";
	    public String fathersworkunit="";
	    public String mothersWorkunit="";
	    public String contactnumber="";
	    public String mobilephone="";
	    public String movingtimeyear="";
	    public String movingtimemonth="";
	    public String movingtimeday="";
	    public String relocationprovince="";
	    public String relocationcity="";
	    public String relocationcounty="";
	    public String townshipstreets="";
	    public String certificationunit="";
	    public String contactnumbers="";
	    public String dateofissueyear="";
	    public String dateofissuemonth="";
	    public String dateofissueday="";
	    public float px;//起始横坐标
	    public float  py;//起始纵坐标
	    public  Font font =null; //设置字体
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
	      //此处50的单位是1/72(inch)，inch:英寸，所以这里的长度，在测量后需要进行转换
	      /*
	       * px代表横轴单位像素+9.8（疫苗本纵轴+0.9）
	       * py代表纵轴
	       */
	      //    
	      
	      
	      g2.drawString("祝沐辰",pxtoint(12.3),pxtoint(1.9));//姓名
	      g2.drawString("男", pxtoint(17), pxtoint(1.9));
	      g2.drawString("2018",pxtoint(12),pxtoint(2.9));
	      g2.drawString("09", pxtoint(13.3),pxtoint(2.9));
	      g2.drawString("20", pxtoint(14.5),pxtoint(2.9));
	      g2.drawString("", pxtoint(17.8),pxtoint(2.9));//体重
	      g2.drawString("",  pxtoint(12.3),  pxtoint(3.8));
	      g2.drawString("",  pxtoint(11.8),  pxtoint(4.7));
	      g2.drawString("",  pxtoint(13.3),  pxtoint(4.7));
	      g2.drawString("",  pxtoint(14.8),  pxtoint(4.7));
	      g2.drawString("",  pxtoint(13),  pxtoint(5.6));
	      g2.drawString("祝太阳",  pxtoint(13.3),  pxtoint(6.4));
	      g2.drawString("周玲",  pxtoint(16.3),  pxtoint(6.4));
	      g2.drawString("",  pxtoint(13.3),  pxtoint(7.3));
	      g2.drawString("",  pxtoint(16.3),  pxtoint(7.3));
	      g2.drawString("",  pxtoint(11.8),  pxtoint(8.2));
	      g2.drawString("",  pxtoint(15.8),  pxtoint(8.2));
	      g2.drawString("",  pxtoint(14.1),  pxtoint(9.0));
	      g2.drawString("",  pxtoint(16.3),  pxtoint(9.0));
	      g2.drawString("",  pxtoint(17.8),  pxtoint(9.0));
	      g2.drawString("",  pxtoint(13.9),  pxtoint(9.9));
	      g2.drawString("",  pxtoint(15),  pxtoint(9.9));
	      g2.drawString("",  pxtoint(16.3),  pxtoint(9.9));
	      g2.drawString("",  pxtoint(12.8),  pxtoint(10.8));
	      g2.drawString("",  pxtoint(13),  pxtoint(11.7));
	      g2.drawString("",  pxtoint(16.8),  pxtoint(11.7));
	      g2.drawString("",  pxtoint(12.3),  pxtoint(12.6));
	      g2.drawString("",  pxtoint(15.3),  pxtoint(12.6));
	      g2.drawString("",  pxtoint(17.3),  pxtoint(12.6));
	      
	   }

	   //连接打印机，弹出打印对话框

	  public void starPrint() {

	     try {

	        PrinterJob prnJob = PrinterJob.getPrinterJob();

	        PageFormat pageFormat = new PageFormat();

	        pageFormat.setOrientation(PageFormat.PORTRAIT);

	        prnJob.setPrintable(this);

	        //弹出打印对话框，也可以选择不弹出打印提示框，直接打印

	        if (!prnJob.printDialog())

	           return;

	        //获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)

	        paperW=pageFormat.getWidth();

	        //获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)

	        paperH=pageFormat.getHeight();

	        //System.out.println("paperW:"+paperW+";paperH:"+paperH);
	        prnJob.print();//启动打印工作
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
	  public static void godayin(int pagesize,double h,double w,float px,float py,String name, Font font,String sex,String year, String month, String day,
	     String birthweight,
	    String allergyhistory,
	    String province,
	     String city,
	     String county,
	     String township,
	     String father,
	     String mother,
	     String fathersworkunit,
	     String mothersWorkunit,
	     String contactnumber,
	     String mobilephone,
	     String movingtimeyear,
		 String movingtimemonth,
		 String movingtimeday,
		 String relocationprovince,
		 String relocationcity,
		 String relocationcounty,
		 String townshipstreets,
	     String certificationunit,
	     String contactnumbers,
	     String dateofissueyear,
		 String dateofissuemonth,
		 String dateofissueday) {
		  Printing1 pm = new Printing1();
		  pm.pageSize=pagesize;
		  pm.paperW=w;//打印的纸张宽度
		  pm.paperH=h;//打印的纸张高度
		  pm.name=name;
		  pm.sex=sex;
		  pm.year=year;
		  pm.month=month;
		  pm.day=day;
		  pm.birthweight=birthweight;
		  pm.allergyhistory=allergyhistory;
		  pm.province=province;
		  pm.city=city;
		  pm.county=county;
		  pm.township=township;
		  pm.father=father;
		  pm.mother=mother;
		  pm.fathersworkunit=fathersworkunit;
		  pm.mothersWorkunit=mothersWorkunit;
		  pm.contactnumber=contactnumber;
		  pm.mobilephone=mobilephone;
		  pm.movingtimeyear=movingtimeyear;
		  pm.movingtimemonth=movingtimemonth;
		  pm.movingtimeday=movingtimeday;
		  pm.relocationprovince=relocationprovince;
		  pm.relocationcity=relocationcity;
		  pm.relocationcounty=relocationcounty;
		  pm.townshipstreets=townshipstreets;
		  pm.certificationunit=certificationunit;
		  pm.contactnumbers=contactnumbers;
		  pm.dateofissueyear=dateofissueyear;
		  pm.dateofissuemonth=dateofissuemonth;
		  pm.dateofissueday=dateofissueday;
		  pm.px=px;//起始横坐标
	      pm.py=py;//起始纵坐标
	      pm.font =font; 
	      pm.starPrint();
	  }
	  
	  public static double pxTodouble(double oldpx) {
		  return oldpx*28.346;
	  }
	  
	  public static float pxtoint(double oldpx) {
		  return   (float) (oldpx*28.346);
	  }
	  
	   //入口方法

	  public static void main(String[] args) {

	    godayin(1,pxTodouble(13.6),pxTodouble(19.8), pxtoint(0),pxtoint(0),"李四",new Font("黑体", Font.PLAIN, 10),"男","2018","05","26","110","没有任何过敏史","山西省","运城市","垣曲县","垣曲县华峰乡","张三","王五","务农","在外打工","123456","110120","2018","05","14","浙江","舟山","定海区","临城街道","舟山市疾控","119120","2019","05","26");

	   }

	

	 
}
