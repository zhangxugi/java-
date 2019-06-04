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
	  * 一类疫苗（）
	  */

	public class PrintingHistory1 implements Printable {

		public int pageSize=1;//打印的总页数
		
		public float beginx=new Float(2.6);
		public float beginy=new Float(2.8);
		//第二页y轴
		public float beginnexty=new Float(12.6);
		
		
		public double paperW=pxTodouble(14);//打印的纸张宽度
		public double paperH=pxTodouble(19.8);//打印的纸张高度
	    public String contont="";
	  	
	    public float px=pxtoint(0);//起始横坐标
	    public float  py=pxtoint(0);//起始纵坐标
	    public  Font font =new Font("黑体", Font.PLAIN, 10); //设置字体
	    int num;//页面传第几行
	    /*
	     * 页面如果是传的多行定义为数组
	     *  int []num;//第几行
	     */
	    
	    
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
	      /*
	       * 如果是一类疫苗第一页那最后一行数字是7
	       * 第二页第一行数字是10
	       * 如果是第三页最后一行数字是9
	       * 第四页最后一行是18
	       */
	 if(num>9) {
	    	  g2.drawString(contont,pxtoint(beginx),pxtoint(beginnexty+0.7*(num-9)));
	      }else {
	    	  g2.drawString(contont,pxtoint(beginx),pxtoint(beginy+0.7*num));
	      }
	    /* for(int i=1;i<=9;i++) {
    	  g2.drawString(contont,pxtoint(beginx),pxtoint(beginy+0.7*i));
      }*/
     /*for(int i =10;i<=18;i++) {
    	  g2.drawString(contont,pxtoint(beginx),pxtoint(beginnexty+0.7*(i-9)));
      }*/
      /*
       * 页面传过来多行进行遍历
       */
      /*for(int s:num) {
	      if(s>9) {
	    	  g2.drawString(contont,pxtoint(beginx),pxtoint(beginnexty+0.7*(s-9)));
	      }else {
	    	  g2.drawString(contont,pxtoint(beginx),pxtoint(beginy+0.7*s));
	      }
	      
	     }*/
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
	  public static void godayin(int num,String content) {
		  PrintingHistory1 pm = new PrintingHistory1();
		  pm.contont=content;
	      pm.num=num;
	      pm.starPrint();
	  }
	  /*
	   * 页面传过来是多行
	   */
	/*  public static void godayin(int []num,String content) {
		  PrintingHistory1 pm = new PrintingHistory1();
		  pm.contont=content;
	      pm.num=num;
	      pm.starPrint();
	  }
	  */
	  public static double pxTodouble(double oldpx) {
		  return oldpx*28.346;
	  }
	 
	  public static float pxtoint(double oldpx) {
		  return   (float) (oldpx*28.346);
	  }
	  
	   //入口方法

	  public static void main(String[] args) {
		  /*
		   * 页面传过来是多行
		   */
		/*  int [] n= {1,2};
		  godayin(n,"②2019/01/17 成都生物/201802A031-2 舟山新城 右上臂 郑丹羽");	 */
		  /*
		   * 单行
		   */
		    godayin(1,"②2019/01/17 成都生物/201802A031-2 舟山新城 右上臂 郑丹羽");
	
	   }
}
