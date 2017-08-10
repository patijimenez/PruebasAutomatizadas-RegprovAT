//Verificar que un usuario con rol proveedor pueda agregar el detalle al bien o servicio registrado.
package editar;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test; 
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AgregarDetalle {
	public WebDriver driver;
	 
  @Test (priority=1)
  @Parameters({"correo","contrasena"})
  public void iniciarSesion(String correo,String contrasena){
	WebDriverWait wait = new WebDriverWait(driver, 30);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	driver.findElement(By.id("email")).sendKeys(correo);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasena);
	driver.findElement(By.xpath("//button[@type='submit']")).click();
  }
	 
  @Test (priority=2)
  @Parameters({"docPDF","logo"})
  public void agregarDetalleBienServicio(String docPDF,String logo) throws InterruptedException {
	  WebDriverWait wait = new WebDriverWait(driver, 50); 
	  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	  
	  wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cadenas elegidas"))).click();
	  Thread.sleep(3000);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='addDetails']"))).click();
	  driver.findElement(By.id("newDescriptionProduct")).sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sed orci tincidunt, aliquet est at, facilisis arcu. Vestibulum tincidunt ultrices libero sed vestibulum.");
	  driver.findElement(By.id("temporalDocumentImg")).sendKeys(logo);
	  driver.findElement(By.id("temporalDocumentPdf")).sendKeys(docPDF);
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("saveDetail"))).click();
	  Thread.sleep(3000);
	  try {
		  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[2]/div[1]/div/table/tbody/tr[1]/td[8]/button[4]")).click();
	      if(!driver.findElement(By.xpath(".//*[@id='productsInChain']/div/div/div[3]/button[1]")).isDisplayed()){
	    	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	  FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/editarAgregarBienServicio.png"));	
	      }
	  } catch (Exception e){
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
