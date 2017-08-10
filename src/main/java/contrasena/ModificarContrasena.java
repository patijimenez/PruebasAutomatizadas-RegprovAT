//Verificar que un usuario con rol de proveedor pueda modificar su contraseña.
package contrasena;

import java.util.concurrent.TimeUnit; 

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ModificarContrasena {
	 public WebDriver driver;
	 
	 @Test(priority = 1)
	 @Parameters({"correoMC","contrasenaMC"})
	 public void iniciarSesion(String correoMC,String contrasenaMC) {	
		 WebDriverWait wait = new WebDriverWait(driver, 40);
		 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 
		 driver.findElement(By.id("email")).sendKeys(correoMC);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasenaMC);
		 driver.findElement(By.name("rememberMe")).click();
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		 WebElement element = driver.findElement(By.xpath("//*[@id='mainMenu']/ul/li[4]/a"));
		 JavascriptExecutor executor = (JavascriptExecutor)driver;
		 executor.executeScript("arguments[0].click()", element);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mainMenu']/ul/li[4]/ul/li[1]/a"))).click();
		 driver.findElement(By.name("password")).sendKeys("12345678");
		 driver.findElement(By.name("confirmPassword")).sendKeys("12345678");
		 driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[2]/div[1]/form/fieldset/button")).click();
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
		} 
}
