//Verificar que un proveedor registrado pueda agregar bienes y/o servicios mediante la exploracion de cadenas.
package editar;

import org.testng.annotations.Parameters;  
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.awt.AWTException;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

public class AgregarBienServicio {
	
	public WebDriver driver;
	
  @Test (priority=1)
  @Parameters({"correo","contrasena"})
  public void iniciarSesion(String correo,String contrasena){
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  
	  driver.findElement(By.id("email")).sendKeys(correo);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasena);
	  driver.findElement(By.xpath("//button[@type='submit']")).click();	  
  }
  
  @Test (priority=2)
  public void agregar() throws InterruptedException, AWTException{
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  WebDriverWait wait = new WebDriverWait(driver, 50);
	  
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Cadenas elegidas"))).click();
	  Thread.sleep(3000);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='headingTwo']/a"))).click();
	  driver.findElement(By.xpath("//*[@id='collapseTwo']/div[1]/div/div/div/input")).sendKeys("lutitas");
	  driver.findElement(By.xpath(".//*[@id='collapseTwo']/div[1]/div/div/div/button[1]")).click();
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='collapseTwo']/div[2]/div/table/tbody/tr[1]/td[1]/input"))).click();
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='updateProductiveChains']"))).click();
	  Thread.sleep(2000);
	  driver.navigate().refresh();
	  Thread.sleep(3000);
	  try {
		  String text=driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[2]/div[1]/div/table")).getText();
	      if(!text.contains("lutitas bituminosas")){
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
