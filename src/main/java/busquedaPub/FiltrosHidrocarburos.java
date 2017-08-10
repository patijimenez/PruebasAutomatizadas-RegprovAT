//Verificar el funcionamiento de los filtros de busqueda publica de Hidrocarburos.
package busquedaPub;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FiltrosHidrocarburos {
	
	public WebDriver driver;
	
	@Test (priority=1)
	public void nombreBienServicio() throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Buscar"))).click();
			Thread.sleep(1000);
		    driver.findElement(By.id("serviceName")).clear();
		    driver.findElement(By.id("serviceName")).sendKeys("Acabados en madera");
		    driver.findElement(By.linkText("Buscar")).click();
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/busquedaPub/FiltrosHidrocarburos-nombreBienServicio.jpg"));	
		}
	    Thread.sleep(2000);
	}

	@Test (priority=2)
	public void nombreProveedor() throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		try{
			driver.navigate().refresh();
			Thread.sleep(1000);
		    driver.findElement(By.id("providerName")).clear();
		    driver.findElement(By.id("providerName")).sendKeys("TRISQUEL");
		    driver.findElement(By.linkText("Buscar")).click();	
		}
		catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/busquedaPub/FiltrosHidrocarburos-nombreProveedor.jpg"));	
		}
	    Thread.sleep(2000);
	}
	
	@Test (priority=3)
	public void entidadFederativa() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		try{
			driver.navigate().refresh();
			Thread.sleep(1000);
		    Select estado = new Select(driver.findElement(By.id("states")));
		    Thread.sleep(2000);
		    estado.selectByVisibleText("Sonora");
		    driver.findElement(By.linkText("Buscar")).click();	
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/busquedaPub/FiltrosHidrocarburos-entidadFederativa.jpg"));	
		}
	}
	
	@Test (priority=4)
	public void cadenasProductivas()  throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		try{
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		    driver.findElement(By.linkText("Buscar")).click();	
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/busquedaPub/FiltrosHidrocarburos-cadenasProductivas.jpg"));	
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
	public void cerrarChrome() {
		driver.quit();
	}
}

