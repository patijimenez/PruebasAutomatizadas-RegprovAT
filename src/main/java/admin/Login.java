//Verificar que un usuario con rol administrador pueda iniciar sesion marcando la casilla "Recordarme"  y que pueda salir del sistema.
package admin;

import static org.testng.Assert.fail; 

import org.testng.annotations.Parameters;
import org.testng.annotations.Test; 
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest; 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Login {
	public WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
  @Test
  @Parameters ({"correo", "contrasena"})
  public void iniciarSesion(String correo, String contrasena) throws IOException {
	WebDriverWait wait = new WebDriverWait(driver, 10);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	driver.findElement(By.id("email")).sendKeys(correo);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasena);
	driver.findElement(By.name("rememberMe")).click();
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	driver.findElement(By.linkText("Salir")).click();
    try {
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div/div/div[2]/div[1]/a[2]"));
      } catch (Exception e){
  		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  		FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/Admin/login.jpg"));
      }
	}
  
	@BeforeTest
	@Parameters ({"url", "driverPath"})
	public void abrirChrome(String url, String driverPath)  throws InterruptedException{
		   System.setProperty("webdriver.chrome.driver", driverPath);
		   driver = new ChromeDriver();
		   driver.manage().window().maximize();
		   driver.get(url);
	}

	@AfterTest
	public void cerrarChrome() {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {	      
			fail(verificationErrorString);
		}
	} 
}
