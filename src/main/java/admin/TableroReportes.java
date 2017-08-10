package admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TableroReportes {
	public WebDriver driver;
	
  @Test(priority = 1)
  @Parameters({"usuario","contrasena"})
  public void iniciarSesion(String usuario, String contrasena) {
	WebDriverWait wait = new WebDriverWait(driver, 10);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	driver.findElement(By.id("email")).sendKeys(usuario);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasena);
	driver.findElement(By.name("rememberMe")).click();
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
  
  @Test(priority = 2)
  public void editarReporte(){
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[2]/div/div/div/div[3]/div[1]/div[2]/div/div[2]/a[5]")).click();
	  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/form/div[1]/div/div[1]/input[1]")).sendKeys("Reporte prueba");
	  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/form/div[1]/div/div[1]/input[2]")).sendKeys("0 0 8,18 * * ?");
	  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/form/div[1]/div/div[1]/input[3]")).sendKeys("Reporte para pruebas");
	  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/form/div[1]/div/div[2]/select")).click();
	  Select lenguaje = new Select(driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/form/div[1]/div/div[2]/select")));
	  lenguaje.selectByVisibleText("Sql");
	  driver.findElement(By.name("email")).sendKeys("patriciajicz@gmail.com");
	  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/form/div[2]/div/div/button")).click();
	  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/form/div[4]/div/div/div[6]")).click();
	  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/form/div[4]/div/div/div[6]")).sendKeys("SELECT * FROM USUARIO");
	  //Guardar
	  WebElement element = driver.findElement(By.cssSelector("body > main > div > div:nth-child(2) > div > div > form > div:nth-child(4) > div > button.btn.btn-primary"));
	  JavascriptExecutor executor = (JavascriptExecutor)driver;
	  executor.executeScript("arguments[0].click()", element);
	  //Limpiar
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div/div[2]/div/div/form/div[4]/div/button[2]"))).click();
	  //Buscar
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div/div[2]/div/div/div[4]/div/form/div/input"))).sendKeys("prueba");
	  driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div/div[4]/div/form/button")).click();
	  //Enviar ahora
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div/div[2]/div/div/div[5]/div/div[1]/div/table/tbody/tr/td[7]/button[1]"))).click();
	  //Pausar
	  driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div/div[5]/div/div[1]/div/table/tbody/tr/td[7]/button[2]")).click();
	  //Reiniciar
	  WebElement element1 = driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[5]/div/div[1]/div/table/tbody/tr/td[7]/button[3]"));
	  JavascriptExecutor executor1 = (JavascriptExecutor)driver;
	  executor1.executeScript("arguments[0].click()", element1);
	  //Eliminar
	  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > main > div > div:nth-child(2) > div > div > div:nth-child(5) > div > div.panel.panel-default > div > table > tbody > tr > td:nth-child(7) > button.btn.btn-danger.btn-sm"))).click();
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
	public void afterTest(){
		driver.quit();	
	}

}
