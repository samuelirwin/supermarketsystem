/**
 * @author Sharma Irwin
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Report_Controller.BuildReport;
import model.ItemSQL;
import model.EmployeeSQL;
import model.CustomerSQL;
import view.Report_View;

public class Report_Controller {
	
	private Report_View Report_View;
	public Object BuildReport;
	
	/** @Requires({reportGenerateView != null}) **/
	public Report_Controller(Report_View reportGenerateView)
	{
		this.Report_View = reportGenerateView;
		
		Report_View.Listener_Chart(new chartListener());		
		Report_View.Listener_PDF(new pdfListener());		
		Report_View.Listener_HTML(new htmlListener());
		Report_View.Listener_Exit(new exitListener());
	}
			
	/**
	 * Report Factory Pattern Test
	 * @author IrwinSharma
	 *
	 */
	
	interface Report{
		BuildReport createReport();
		BuildReport printReport();
	}
	
	/*
	 * The Build Report Abstract
	 */
	public abstract class BuildReport{
		public abstract void getReport();
	}
		
	/*
	 * Report Factory
	 */
	public class ReportFactory{
		
		public Report getReport(String type){
			if("chart".equals(type)){
				return new Chart();
			}
			else if("pdf".equals(type)){
				return new Pdf();
			}
			else if("html".equals(type)){
				return new Html();
			}
			return null;
		}
			
	}
		
	/*
	 * Build Report Type Chart, Pdf, html
	 */
	class Chart implements Report{

		public BuildReport createReport() {
			// TODO Auto-generated method stub
			System.out.println("Generating chart");
			return null;
		}

		public BuildReport printReport() {
			// TODO Auto-generated method stub
			System.out.println("Printing report in chart");
			return null;
		}
		
	}
	
	class Pdf implements Report{

		public BuildReport createReport() {
			// TODO Auto-generated method stub
			System.out.println("Generating pdf");
			return null;
		}

		public BuildReport printReport() {
			// TODO Auto-generated method stub
			System.out.println("Printing report in pdf");
			return null;
		}
		
	}
	
	class Html implements Report{

		public BuildReport createReport() {
			// TODO Auto-generated method stub
			System.out.println("Generating html");
			return null;
		}

		public BuildReport printReport() {
			// TODO Auto-generated method stub
			System.out.println("Printing report in html");
			return null;
		}
		
	}	
	
	/**
	 * Report Factory Pattern [End]
	 * @author IrwinSharma
	 *
	 */	
	
	class chartListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){	
			System.out.println("CHART Selected");
			ReportFactory reportFactory = new ReportFactory();
			Report report = reportFactory.getReport("chart");
			
			System.out.println("Processing " + report.createReport());
			System.out.println("Printing " + report.printReport());
		}
	}
	
	class pdfListener implements ActionListener{		
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("PDF Selected");
			ReportFactory reportFactory = new ReportFactory();
			Report report = reportFactory.getReport("pdf");
			
			System.out.println("Processing PDF " + report.createReport());
			System.out.println("Printing " + report.printReport());
		}
	}
	
	class htmlListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			System.out.println("HTML Selected");
			ReportFactory reportFactory = new ReportFactory();
			Report report = reportFactory.getReport("html");
			
			System.out.println("Processing HTML " + report.createReport());
			System.out.println("Printing " + report.printReport());
		}
	}
	
	class exitListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			Report_View.setVisible(false);
		}
	}
}
