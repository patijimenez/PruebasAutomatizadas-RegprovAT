//Verificar que un usuario con rol de proveedor pueda agregar cadenas productivas mediante la b�squeda por palabras claves.

package editar;

import org.testng.annotations.Test; 
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BusquedaEdicion {
	public WebDriver driver;
	
  @Test (priority = 1)
  @Parameters ({"correo","contrasena"})
  public void iniciarSesion(String correo, String contrasena) {
  driver.findElement(By.id("email")).sendKeys(correo);
  driver.findElement(By.id("password")).sendKeys(contrasena);
  driver.findElement(By.xpath("//button[@type='submit']")).click();
  }
  
  @Test (priority = 2)
  public void buscarCadena() throws InterruptedException {
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  
	  wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cadenas elegidas"))).click();
	  List<WebElement> servicios = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div[2]/div[1]/div/table/tbody/tr"));
	  int serviciosTamIni = servicios.size();
	  System.out.println(serviciosTamIni);
	  wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("palabras claves"))).click();
	  Thread.sleep(2000);
	  driver.findElement(By.name("criteria")).sendKeys("valvulas");
	  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary.pull-right"))).click();
	  Thread.sleep(1000);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[1]/td[1]/input"))).click();
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[3]/td[1]/input"))).click();
	  Thread.sleep(1000);
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("updateProductiveChains"))).click();
	  Thread.sleep(3000);
	  driver.navigate().refresh();
	  Thread.sleep(5000);
	  List<WebElement> serviciosFin = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div[2]/div[1]/div/table/tbody/tr"));
	  int serviciosTamFin = serviciosFin.size();
	  System.out.println(serviciosTamFin);
	  try{
		  if(serviciosTamIni == serviciosTamFin ){
			  System.out.println(serviciosFin);
			  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/editarBusquedaEdicion.png"));
		  }
	  }catch (Exception e){
		  
	  }
	  driver.findElement(By.xpath("//*[@id='mainMenu']/ul/li[4]/a")).click();
	  driver.findElement(By.linkText("Salir")).click();
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
        driver.close();
  }


}
