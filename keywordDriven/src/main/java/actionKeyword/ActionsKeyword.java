package actionKeyword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import basePackage.BrowserManager;
import basePackage.Helper;
import page_Objects.LoginPage;
import page_Objects.MyRequestsPage;
import utils.DataReader;

public class ActionsKeyword extends BrowserManager {
	// using excel
	@Test
	public static void login() throws IOException, InterruptedException {
		DataReader.getExcelSheet("Sheet1");
		// System.out.println(DataReader.sh.getLastRowNum());
		// System.out.println(DataReader.sh.getRow(0).getLastCellNum());
		for (int irow = 0; irow <= DataReader.sh.getLastRowNum(); irow++) {
			String isAction = DataReader.getCellValue(irow, DataReader.sh.getRow(0).getLastCellNum() - 1);
			switch (isAction) {
			case "MYS_Login":
				LoginPage.mys_Login();
				log.info("User Successfully logged into application");
				break;
			case "MYS_Leave_Apply":
				MyRequestsPage.click_My_Requests_Menu();
				MyRequestsPage.click_My_Requests_Leave();
				MyRequestsPage.click_My_Requests_Apply_Leave();
				Thread.sleep(2000);
				Helper.selectbyValue(MyRequestsPage.select_My_Requests_Leave(), "cl");
				MyRequestsPage.my_Requests_EL_Error();
				MyRequestsPage.click_From_Date_Field();
				MyRequestsPage.click_To_Date_Field();
				MyRequestsPage.enter_My_Leave_AltNum();
				MyRequestsPage.enter_My_Leave_Comments();
				MyRequestsPage.click_My_Leave_Apply_Button();
				if (MyRequestsPage.get_My_Leave_Success_Msg()
						.equalsIgnoreCase("Leave Request Successfully Submitted")) {
					log.info(MyRequestsPage.get_My_Leave_Success_Msg());
				} else {
					log.info(MyRequestsPage.get_My_Leave_Error_Msg());
				}
				break;
			case "MYS_Logout":
				LoginPage.mys_Logout();
				log.info("User logged out from Appn");
				break;
			default:
				log.info("Invalid details");
				break;
			}
		}

	}

	// using text file
	// @Test
	public static void testMethod() throws IOException, InterruptedException {
		BufferedReader breader = new BufferedReader(
				new FileReader(new File(CURRENTDIR) + "\\DataEngine\\mys_loginDetails.txt"));
		String currentLine;
		while ((currentLine = breader.readLine()) != null) {
			// System.out.println(currentLine);
			String[] currentLine1 = currentLine.split(Pattern.quote("|"));
			for (int i = 0; i <= currentLine1.length - 1; i++) {
				switch (currentLine1[i]) {
				case "LOGIN":
					LoginPage.mys_Login();
					log.info("User Successfully logged into application");
					break;
				case "MYSLeaveApply":
					MyRequestsPage.click_My_Requests_Menu();
					MyRequestsPage.click_My_Requests_Leave();
					MyRequestsPage.click_My_Requests_Apply_Leave();
					Thread.sleep(1000);
					Select sel = new Select(MyRequestsPage.select_My_Requests_Leave());
					sel.selectByValue("el");
					if (sel.getFirstSelectedOption().getText().equalsIgnoreCase("Earned Leave")) {
						MyRequestsPage.my_Requests_EL_Error();
					}
					MyRequestsPage.click_From_Date_Field();
					MyRequestsPage.click_To_Date_Field();
					MyRequestsPage.enter_My_Leave_AltNum();
					MyRequestsPage.enter_My_Leave_Comments();
					MyRequestsPage.click_My_Leave_Apply_Button();
					try {
						if (MyRequestsPage.get_My_Leave_Success_Msg()
								.equalsIgnoreCase("Leave Request Successfully Submitted")) {
							log.info(MyRequestsPage.get_My_Leave_Success_Msg());
						}
					} catch (Exception e) {
						log.info(MyRequestsPage.get_My_Leave_Error_Msg());
					}

					break;
				case "LOGOUT":
					LoginPage.mys_Logout();
					log.info("User logged out from Appn");
					break;
				default:
					log.info("Invalid details");
					break;
				}
			}
			// for (String w : currentLine1) {
			// System.out.println(w);
			// }
		}

	}

}
