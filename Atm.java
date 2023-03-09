import java.util.*;
import java.io.IOException; 
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.io.LineNumberReader; 
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.nio.file.*;

class Atm
{
   private String acc_number="",card_num="";
   protected int pin=0;
   public static void main(String args[]){
   Scanner sc= new Scanner(System.in);
	
   System.out.println("Cardless Deposit Press 1");
   System.out.println("Card Transactions Press 2");
   System.out.print("Choice Your Option : ");
   int op = sc.nextInt();
	
   switch(op)
   {	
      case 1:

	cldeposit cld = new cldeposit();
	break;
		
      case 2:
	
	//Input From User
	System.out.print("Enter Your Card Number : ");
	String input = sc.next();
	
	System.out.println("If you have a PIN Press 1");
	System.out.println("Generate the PIN  Press 2");

	System.out.print("Enter Your Choice : ");
	int ch = sc.nextInt();
	
	switch(ch)
	{	
		case 1:
			
			String acc_number="",card_num="",acc_hname="", acc_type="",card_inp="";
			int i, pin=0,ic=0;
			float acc_balance=0,new_bal;
			int pin_inp=0;
			card_inp = input;
	
			//Creating a File object for directory
			File directoryPath = new File("/Volumes/BAVAN SSD/Semester 4/java_PRO");

			//List of all files and directories
      			File filesList[] = directoryPath.listFiles();

			String cnf= card_inp +".txt";

			for(File file : filesList) 
			{
         			file.getName();
				
				if(cnf.compareTo(file.getName()) == 0)
				{
					//System.out.println(file.getName());
					ic++;
					System.out.print("Enter your PIN : ");
					pin_inp = sc.nextInt();
      					try (BufferedReader br = new BufferedReader(new FileReader(cnf))) 
					{
						long lines = 0;
						LineNumberReader lnr = new LineNumberReader(new FileReader(cnf));
						while (lnr.readLine() != null) ;
						{
         					 lines = lnr.getLineNumber();
						}
						if(lines<=11)
						{
							System.out.println("\nYour Card Didn't have a PIN");
							System.out.println("Please Generate the PIN");
							System.exit(0);
						}
						//System.out.print(lines);
          					for (i = 0; i <= 12; i++)
						{
              					if(i==2)
						{
              						br.readLine();
          						acc_number = br.readLine();	
							//System.out.println("Account Number: " + acc_number);
						}
						else if(i==4)
						{
              						br.readLine();
          						card_num = br.readLine();
          						//System.out.println("CardNumber: " + card_num);
						}
						else if(i==6)
						{
              						br.readLine();
          						acc_type = br.readLine();
          						//System.out.println("AccountType: " + acc_type);
						}
						else if(i==8)
						{
              						br.readLine();
          						acc_hname = br.readLine();
          						//System.out.println("AccountHolderName: " + acc_hname);
						}
						
						else if(i==10)
						{
              						br.readLine();
          						acc_balance = Float.parseFloat(br.readLine());
							//System.out.println("Account Balance: " + acc_balance);
						}
						else if(i==12)
						{
              						br.readLine();
          						pin = Integer.parseInt(br.readLine());
          						//System.out.println("PIN: " + pin);
						}
			
					}
      					}
      					catch(IOException e)
					{
        						System.out.println(e);
      					}
				}
			}
			if(ic== 0)
			{
				System.out.println("\nInvalid Card Please Contact bank");
				System.out.println("Card Exist");
				System.exit(0);
					
			}
			if(card_num.equals(card_inp) && pin_inp == pin)
			{
				System.out.println("\nHi " + acc_hname);
		
				System.out.print("\nPRESS 1 FOR WITHDRAWAL");
				System.out.print("\nPRESS 2 FOR DEPOSIT");
				System.out.print("\nPRESS 3 FOR VIEW BALANCE");
				System.out.print("\nPRESS 4 FOR PIN CHANGE");
				System.out.println("\nPRESS 5 FOR STATEMENT");
				System.out.print("\nEnter Your Choice : ");
				int choice = sc.nextInt();
	
				switch(choice)
				{

					case 1:
						
						String ch_type = accTypeCheck();
						
						if(ch_type.equals(acc_type))
						{
							System.out.print("\nEnter Your Withdrawal Amount : ");
							Float wamount = sc.nextFloat();
							if(wamount<= acc_balance)
							{
								new_bal = acc_balance-wamount;
								System.out.println("\nSuccesses Fully Completed your Transaction");										 	
								System.out.println("Available balance is "+ new_bal);
								System.out.println("\nCard Exist");		
								BalaceUpdate B = new BalaceUpdate(new_bal, acc_balance, cnf);
								String D="Debit";
								history(wamount, acc_balance, D, cnf);
							}
							else
							{
								System.out.println("\nInsufficient amount");
								System.out.println("Card Exist");
							}
						}
						else
						{
							System.out.println("\nInvalid Account Type");
							System.out.println("Card Exist");
						}
        	 					break;

					case 2:
						
						ch_type = accTypeCheck();
						
						if(ch_type.equals(acc_type))
						{
							System.out.print("\nEnter Your Deposit Amount : ");
							Float damount = sc.nextFloat();	
							new_bal = acc_balance+damount;
							System.out.println("\nSuccesses Fully Completed your Transaction");												 	
							System.out.println("Card Exist");
							BalaceUpdate B = new BalaceUpdate(new_bal, acc_balance, cnf);
							String C="Credit";
							history(damount, acc_balance, C, cnf);
						}
						else
						{
							System.out.println("\nInvalid Account Type");
							System.out.println("Card Exist");
						}	
						break;
			
					case 3:

						ch_type = accTypeCheck();
						
						if(ch_type.equals(acc_type))
						{
							System.out.println("Available balance is "+ acc_balance);
							System.out.println("Card Exist");
						}
						else
						{
							System.out.println("\nInvalid Account Type");
							System.out.println("Card Exist");
						}	
						break;

					case 4:
						
						System.out.print("\nEnter Your  Old Pin : ");
						int oldpin = sc.nextInt();
						if(oldpin ==pin)
						{
							System.out.print("\nEnter New Pin : ");
							int crepin = sc.nextInt();
							System.out.print("ReEnter New Pin : ");
							int recrepin = sc.nextInt();
							if(crepin == recrepin && crepin >=1000)
							{
								String screpin =String.valueOf(crepin);  
								String soldpin =String.valueOf(oldpin); 

								String originalFilePath = cnf;
								String originalFileContent = "";

								BufferedReader reader = null;
       								BufferedWriter writer = null;
								
								System.out.println("Successfully Pin Created");
								System.out.println("Card Exist");
	
								try {
									reader = new BufferedReader(new FileReader(originalFilePath));
									String currentReadingLine = reader.readLine();
									while (currentReadingLine != null) 
									{
              									originalFileContent += currentReadingLine + System.lineSeparator();
               									currentReadingLine = reader.readLine();
            								}
									String modifiedFileContent = originalFileContent.replaceAll(soldpin, screpin);
									writer = new BufferedWriter(new FileWriter(originalFilePath));
									writer.write(modifiedFileContent);
								} 
								catch (IOException e){
									} 
								finally {
            	
           							try {
                								if (reader != null) {
                    							reader.close();
                							}

               							if (writer != null) {
                    							writer.close();
               							}

            							} catch (IOException e) {
                
           				 				}
       								}
							}
								
							else
							{
								System.out.println("\nInvalid ReEntered Pin ");
								System.out.println("Card Exist");
							}
						}
						else
						{
							System.out.println("\nInvalid Old Pin");	
							System.out.println("Card Exist");
						}
						break;
					case 5:
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
  						LocalDateTime now = LocalDateTime.now();
						long count=0;
						String print="";
						try {
      							Path file = Paths.get(cnf);
     							count = Files.lines(file).count();

   						 } 						
						catch (Exception e) {
   						 }
						try (BufferedReader br = new BufferedReader(new FileReader(cnf))) 
						{

              						br.readLine();
       							br.readLine();
          						br.readLine();
							br.readLine();
							br.readLine();
							br.readLine();
							br.readLine();
							br.readLine();
							br.readLine();
							br.readLine();
							br.readLine();
							br.readLine();
							for(int j=0;j<=count-13;j++)
							{
              								print= br.readLine();
									System.out.println(print);
            						}
						}
						catch (IOException e) {
     						}
						System.out.println("\n"+dtf.format(now)+"  Account Balance : " + acc_balance);
						System.out.println("\n");
						break;
						

					default:
						System.out.println("\nInvalid Input");
						System.out.println("Card Exist");
						break;
				}	
			}
			else
			{
				System.out.println("Invalid PIN");
				System.out.println("Card Exist");
			}			
			break;

		case 2:
			CreatePin P = new CreatePin(input);
			break;
		
		default:
			System.out.println("\nInvalid Input");
			System.out.println("Card Exist");
			break;
		}
   	 break;

      default:
	 System.out.println("\nInvalid Input");
	 System.out.println("Card Exist");
	 break;
	
    }
  }
  static String accTypeCheck()
  {
	Scanner sc= new Scanner(System.in);	
	String  ch_type="";
	System.out.println("\nSelect Your Account Type");
	System.out.println("PRESS 1 FOR SAVINGS");
	System.out.println("PRESS 2 FOR CURRENT");
	System.out.print("\nEnter Your Account Type : ");
	int act = sc.nextInt();	

	if(act==1)
	{
		ch_type = "Savings";
	}
	if(act==2)
	{
		ch_type = "Current";
	}
	return ch_type;
  }
   
  static void history(float amount,float acc_balance, String m, String cnf)
  {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
  	LocalDateTime now = LocalDateTime.now();
	
	try 
	{
      		File file1 = new File(cnf);
		FileWriter fr = new FileWriter(file1, true);
		BufferedWriter bw = new BufferedWriter(fr);
		PrintWriter pr = new PrintWriter(bw);   
		pr.println(dtf.format(now)+"  Available Balance " + acc_balance +"   " + m +" "+ amount );
		pr.close();
		bw.close();
		fr.close();
      		
    	} 
	catch (IOException e) {
     	}
  }

}
 

class CreatePin
{
	Scanner sc= new Scanner(System.in);
	CreatePin(String input){
		
		String acc_number1="",card_num1="",card_inp1="", acc_number_input="", name="";
		int i,ic=0;

		card_inp1 = input;

		File directoryPath = new File("/Volumes/BAVAN SSD/Semester 4/java_PRO");
		File filesList[] = directoryPath.listFiles();

		String cnf= card_inp1 +".txt";
		//System.out.println(cnf);

		for(File file : filesList) 
		{
         		file.getName();
			if(cnf.compareTo(file.getName()) == 0)
			{
				ic++;
				String checkpin="";
				//System.out.println(file.getName());
			

      			try (BufferedReader br = new BufferedReader(new FileReader(cnf))) 
			{
              			br.readLine();
          			acc_number1 = br.readLine();
          			
				br.readLine();
         			card_num1 = br.readLine();
          			br.readLine();
				br.readLine();
				br.readLine();
				name = br.readLine();
				br.readLine();
				br.readLine();
				checkpin=br.readLine();
				
				if(checkpin == null){
				System.out.print("Enter your Account Number : ");
				acc_number_input= sc.nextLine();
				if(acc_number1.equals(acc_number_input) && card_num1.equals(card_inp1))
				{
					System.out.println("\nWelcome " + name);
					System.out.println("\nAccount Number: " + acc_number1);
					System.out.println("CardNumber: " + card_num1);

					System.out.print("\nEnter New Pin : ");
					int newpin = sc.nextInt();
					System.out.print("ReEnter New Pin : ");
					int renewpin = sc.nextInt();
					if(newpin== renewpin && newpin >=1000)
					{
						//String str1 = Integer.toString(newpin);
						System.out.println("Successfully Pin Created");
						System.out.println("Card Exist");
						File file1 = new File(cnf);
						FileWriter fr = new FileWriter(file1, true);
						BufferedWriter bw = new BufferedWriter(fr);
						PrintWriter pr = new PrintWriter(bw);   
						pr.println("\nPIN");
						pr.println(newpin);
						pr.println("Transaction History");
						pr.close();
						bw.close();
						fr.close();
						
					}
					else
					{
						System.out.println("\nInvalid Pin");
						System.out.println("Card Exist");
					}
				}
				else
				{
					System.out.println("\nInvalid Account Please Contact bank");
					System.out.println("Card Exist");
				}
				
			}
			else
				{
					System.out.println("\nPIN ALREADY GENERATED ");
					System.out.println("Card Exist");
					System.exit(0);
				}	
			}
			
      			catch(IOException e)
				{
        				System.out.println(e);
      				}
			}
			
		}	
		if(ic== 0)
		{
			System.out.println("\nInvalid Card Please Contact bank");
			System.out.println("Card Exist");
		}
			
	}
}

class cldeposit
{
	
	Scanner sc= new Scanner(System.in);
	cldeposit()
	{
		
		String acc[]=new String[100];
		String cnum[]=new String[100];
		String cnf;
		int cou=0;

		//Customers ACC Details
		acc[0] = "1002003004005";
		cnum[0]= "123456789123";
		acc[1] = "1002003004006";
		cnum[1]= "192837461928";
		acc[2] = "1112223334445";
		cnum[2]= "987654321321";
		acc[3] = "1002003004007";
		cnum[3]= "998877665544";
		
		System.out.print("Enter Your Account Number : ");
		String accnum = sc.nextLine();
		
		for(int j=0;j<4;j++)
		{
			if(accnum.equals(acc[j]))
			{
				cou++;
				float acc_bala=0;
				File directoryPath = new File("/Volumes/BAVAN SSD/Semester 4/java_PRO");
				File filesList[] = directoryPath.listFiles();
				String cnff= cnum[j] +".txt";
				String cusname= "";
				try (BufferedReader br = new BufferedReader(new FileReader(cnff))) 
				{
				
              				br.readLine();
       					br.readLine();
          				br.readLine();
					br.readLine();
					br.readLine();
					br.readLine();
					br.readLine();
					cusname = br.readLine();
					br.readLine();
					acc_bala = Float.parseFloat(br.readLine());
				}

				catch(IOException e){
        					System.out.println(e);
      				}
				System.out.println("\nAccount Number is : " + accnum);	
				System.out.println("Account Holder Name : " + cusname);	
				
				System.out.println("\nContinue your transaction Press 1 ");	
				System.out.println("Exit Press 2 ");	
				System.out.print("Enter Your Choice : ");		
				int uc = sc.nextInt();
				
				if(uc==1)
				{
					System.out.print("\nEnter Your Deposit Amount : ");
					Float amount = sc.nextFloat();

					System.out.println("\nDeposit Amount : " + amount);		
					
					System.out.println("\nIf Complete your transaction Press 1 ");	
					System.out.println("Exit Press 2 ");	
					System.out.print("Enter Your Choice : ");		
					int ucc = sc.nextInt();
					if(ucc==1)
					{
						float new_bala = acc_bala + amount;
						BalaceUpdate B = new BalaceUpdate(new_bala, acc_bala, cnff);
						String C="Credit";
						System.out.println("\nSuccesses Fully Completed your Transaction");	
						Atm.history(amount, acc_bala, C, cnff);
								 	
					}
					else if(ucc==2)
					{
						System.out.println("Your Last Transaction is Cancelled");
					}
					else
						System.out.println("\nInvalid Input"+"\nYour Last Transaction is Cancelled");
				}
				else if(uc==2)
				{
					System.out.println("Your Last Transaction is Cancelled");
				}
				else{
					System.out.println("\nInvalid Input"+"\nYour Last Transaction is Cancelled");
				}				
			}
		}
		if(cou==0)
		{
			System.out.println("\nInvalid Account Number");	
			System.out.println("Your Last Transaction is Cancelled");	
		}
	}
} 

class BalaceUpdate
{
	
	Scanner sc= new Scanner(System.in);
	BalaceUpdate(Float newbal, Float acc_balance, String cnf)
	{					
		String saccbal=String.valueOf(acc_balance);  
		String snewaccbal=String.valueOf(newbal);  
		//System.out.println(newbal);
							 	
		String originalFilePath = cnf;
		String originalFileContent = "";

		BufferedReader reader = null;
       		BufferedWriter writer = null;
							
		try {
			reader = new BufferedReader(new FileReader(originalFilePath));
			String currentReadingLine = reader.readLine();
			while (currentReadingLine != null) 
			{
              			originalFileContent += currentReadingLine + System.lineSeparator();
               			currentReadingLine = reader.readLine();
            		}
			String modifiedFileContent = originalFileContent.replaceAll(saccbal, snewaccbal);
			writer = new BufferedWriter(new FileWriter(originalFilePath));
			writer.write(modifiedFileContent);
		} 
		catch (IOException e){
		} 
		
		finally {
            	
           		try {
                			if (reader != null) {
                    			reader.close();
                			}

               			if (writer != null) {
                    			writer.close();
               			}

            		} 
			catch (IOException e) {
                
           		}
       		}
     	}
} 
