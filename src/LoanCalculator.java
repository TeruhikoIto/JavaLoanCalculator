import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculator extends Application
{
	Label lbl1 = new Label("Annual Interest Rate:");
	Label lbl2 = new Label("Number of Years:");
	Label lbl3 = new Label("Loan Amount:");
	Label lbl4 = new Label("Monthly Payment:");
	Label lbl5 = new Label("Total Payment:");
	TextField tfAnuualInterestRate = new TextField(); 
	TextField tfNumberOfYears = new TextField(); 
	TextField tfLoanAmount = new TextField(); 
	TextField tfMonthlyPayment = new TextField(); 
	TextField tfTotalPayment = new TextField();
	Button btCalculate = new Button("Calculate");
	@Override
	public void start(Stage primaryStage)
	{
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(50,70,50,80));
		gridpane.setHgap(15);
		gridpane.setVgap(15);
		tfAnuualInterestRate.setAlignment(Pos.CENTER_RIGHT);
		tfNumberOfYears.setAlignment(Pos.CENTER_RIGHT);
		tfLoanAmount.setAlignment(Pos.CENTER_RIGHT);
		tfMonthlyPayment.setAlignment(Pos.CENTER_RIGHT);
		tfTotalPayment.setAlignment(Pos.CENTER_RIGHT);
		gridpane.add(lbl1, 0, 0);
		gridpane.add(lbl2, 0, 1);
		gridpane.add(lbl3, 0, 2);
		gridpane.add(lbl4, 0, 3);
		gridpane.add(lbl5, 0, 4);
		gridpane.add(tfAnuualInterestRate, 1, 0);
		gridpane.add(tfNumberOfYears, 1, 1);
		gridpane.add(tfLoanAmount, 1, 2);
		gridpane.add(tfMonthlyPayment, 1, 3);
		gridpane.add(tfTotalPayment, 1, 4);
		gridpane.add(btCalculate, 1, 5);
		GridPane.setHalignment(btCalculate, HPos.RIGHT);
		btCalculate.setOnAction((e) -> calculateLoanPayment(tfAnuualInterestRate.getText(), tfLoanAmount.getText(), tfNumberOfYears.getText()));
		Scene scene = new Scene(gridpane);
		primaryStage.setTitle("JavaFx Loan Cals.");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private void calculateLoanPayment(String interestrate, String loanamount, String numberofyear)
	{
		double interestRate = 0;
		double loanAmount = 0;
		int numberOfYears = 0;
		try
		{
			interestRate = Double.parseDouble(interestrate);
			loanAmount = Double.parseDouble(loanamount);
			numberOfYears = Integer.parseInt(numberofyear);
		}
		catch(NumberFormatException error)
		{
			System.out.println(error.getMessage());
		}
		
		Loan loan = new Loan(interestRate, numberOfYears, loanAmount);
		tfMonthlyPayment.setText(String.format("%.2f", loan.getMonthlyPayment()));
		tfTotalPayment.setText(String.format("%.2f", loan.getTotalPayment(loan.getMonthlyPayment())));
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}

class Loan 
{
	private double annualInterestRate;
	private int numberOfYears;
	private double loanAmount;
	
	public Loan(double annualinterestrate, int numberofyears, double loanamount)
	{
		annualInterestRate = annualinterestrate;
		numberOfYears = numberofyears;
		loanAmount = loanamount;
	}
	
	public double getAnnualInterestRate()
	{
		return annualInterestRate;
	}
	
	public void setAnnualInterestRate(double annualinterestrate)
	{
		annualInterestRate = annualinterestrate;
	}
	
	public int getNumberOfYears()
	{
		return numberOfYears;
	}
	
	public void setNumberOfYears(int numberofyears)
	{
		numberOfYears = numberofyears;
	}
	
	public double getLoanAmount()
	{
		return loanAmount;
	}
	
	public void setLoanAmount(double loanamount)
	{
		loanAmount = loanamount;
	}
	
	public double getMonthlyPayment()
	{
		double monthlyInterestRate = annualInterestRate / 1200;
		double monthlyPayment = loanAmount * monthlyInterestRate / (1 - (1 /Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
		return monthlyPayment;
	}
	
	public double getTotalPayment(double monthlypayment)
	{
		double totalPayment = monthlypayment * numberOfYears * 12;
		return totalPayment;
	}
}