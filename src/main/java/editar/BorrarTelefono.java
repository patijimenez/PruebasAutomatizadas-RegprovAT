//Verificar que un correo con rol de proveedor pueda borrar el o los telefonos que capturo en su registro.
package editar;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BorrarTelefono {
	 public WebDriver driver;
	 
  @Test (priority = 1)
  @Parameters({"correo","contrasena"})
  public void iniciarSesion(String correo,String contrasena) {
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  driver.findElement(By.id("email")).sendKeys(correo);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasena);
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  }
  
  @Test (priority = 2) 
  public void eliminaTelefono() throws InterruptedException{
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/button"))).click();
	  List<WebElement> telefonos = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div/div[10]/div[2]/table/tbody/tr"));
	  int telTotalIni= telefonos.size();
	  driver.findElement(By.xpath("//button[contains(text(),'Borrar')]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//button[contains(text(),'Guardar')]")).click();
	  Thread.sleep(2000);
	  int telTotalFin = telTotalIni-1;
	  try{
		  if(telTotalIni==telTotalFin){
			  System.out.println(telTotalFin);
			  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/editarBorrarTelefono.png"));
		 }
	  }catch(Exception e){
		  
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
  public void afterTest() {
	  driver.quit();
  }
}
