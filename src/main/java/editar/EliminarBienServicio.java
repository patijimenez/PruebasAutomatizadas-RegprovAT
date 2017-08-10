//Verificar que un usuario registrado pueda eliminar el bien o servicio que eligio en su registro.
package editar;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;  
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EliminarBienServicio {
	public WebDriver driver;
	
  @Test (priority=1)
  @Parameters({"correo","contrasena"})
  public void iniciarSesion(String correo,String contrasena){
	  driver.findElement(By.id("email")).sendKeys(correo);
	  driver.findElement(By.id("password")).sendKeys(contrasena);
	  driver.findElement(By.xpath("//button[@type='submit']")).click();  
  }
  
  @Test (priority=2)
  public void eliminar() throws InterruptedException {
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  WebDriverWait wait = new WebDriverWait(driver, 50);
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div/div[2]/div/div/div[3]/div/div/div[1]/div/div[2]/a"))).click();
	  List<WebElement> bienServicio = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div[2]/div[1]/div/table/tbody/tr"));
	  int bienServicioTamIni = bienServicio.size();
	  System.out.println(bienServicioTamIni);
	  Thread.sleep(3000);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='deleteProducts']")));
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteProducts"))).click();
	  driver.findElement(By.id("updateProductiveChains")).click();	 
	  driver.navigate().refresh();
	  Thread.sleep(5000);
	  List<WebElement> bienServicioFin = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div[2]/div[1]/div/table/tbody/tr"));
	  int bienServicioTamFin = bienServicioFin.size();
	  System.out.println(bienServicioTamFin);
	  try{
		  if(bienServicioTamIni == bienServicioTamFin){
			  System.out.println(bienServicioTamFin);
			  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/editarEliminarBienServicio.png"));
		  }
	  }catch (Exception e){
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
