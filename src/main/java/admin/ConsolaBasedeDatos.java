//Verificar que un usuario con rol de administrador pueda ejecutar una consulta en la consola de base de datos.
package admin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;  

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ConsolaBasedeDatos {
	public WebDriver driver;
	
	@Test(priority = 1)
	@Parameters ({"usuario", "contrasena"})
	public void iniciarSesion(String usuario, String contrasena){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
		driver.findElement(By.id("email")).sendKeys(usuario);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasena);
		driver.findElement(By.name("rememberMe")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[2]/div/div/div/div[3]/div[1]/div[2]/div/div[2]/a[3]")).click();	
	}
	
	@Test(priority = 2)
	public void ejecutar() throws InterruptedException, IOException{
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div/div/div/textarea")).sendKeys("select * from usuario");
	    driver.findElement(By.cssSelector("button.pull-right.btn")).click();
	    Thread.sleep(3000);
		try{
			driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[4]/table")).isDisplayed();
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/Admin/ConsolaBasedeDatos.jpg"));
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
	public void afterTest(){
		driver.quit();	
	}
}
