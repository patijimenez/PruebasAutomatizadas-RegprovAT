//Verificar que un usuario con rol de administrador pueda guardar y ejecutar un script  en la consola de scripting.
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
import org.openqa.selenium.Keys;

public class ConsolaScripting {
	public WebDriver driver;
	
	@Test(priority = 1)
	@Parameters ({"usuario", "contrasena"})
	public void iniciarSesion(String usuario, String contrasena){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  
		driver.findElement(By.id("email")).sendKeys(usuario);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasena);
		driver.findElement(By.name("rememberMe")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.linkText("Consola de scripting")).click();
	}
	
	@Test(priority = 2)
	public void ejecutar() throws InterruptedException, IOException{
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys("import ");
	    driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys("mx.gob");
	    driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys(".economia");
	    driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys(".regprov.*");
	    driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys("Role.list().each{");
	    driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys("println it.name");
	    driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[5]/div/div/div/textarea")).sendKeys("}");
	    //driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/form/button[2]")).click();
	    Thread.sleep(3000);
		try{
			driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[2]/div[6]/div/div[3]")).isDisplayed();
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/Admin/ConsolaScripting.jpg"));
		}
	}
	
	@Test(priority = 3)
	public void guardar(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.findElement(By.id("tempScriptKey")).sendKeys("Roles");
	    driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/form/button[1]")).click();
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

