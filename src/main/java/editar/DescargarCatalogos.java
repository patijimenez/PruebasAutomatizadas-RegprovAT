//Verificar que un usuario con rol de proveedor pueda descargar los catalogos de electricidad e hidrocarburos.
package editar;

import java.util.concurrent.TimeUnit; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DescargarCatalogos {

	public WebDriver driver;
	
	@Test (priority = 1)
	@Parameters({"correo","contrasena"})
	public void iniciarSesion(String correo,String contrasena) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys(correo);
		driver.findElement(By.id("password")).sendKeys(contrasena);
		driver.findElement(By.name("rememberMe")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	  }
	
	 @Test (priority = 2)
	 public void descargar() {
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 WebDriverWait wait = new WebDriverWait(driver, 30);
			
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='mainMenu']/ul/li[2]/a"))).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='mainMenu']/ul/li[3]/a"))).click();
		 driver.findElement(By.xpath(".//*[@id='mainMenu']/ul/li[3]/ul/li[1]/a")).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='mainMenu']/ul/li[3]/a"))).click();
		 driver.findElement(By.xpath(".//*[@id='mainMenu']/ul/li[3]/ul/li[2]/a")).click();
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
