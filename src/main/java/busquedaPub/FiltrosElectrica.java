//Verificar el funcionamiento de los filtros de busqueda publica de la Industria Electrica.
package busquedaPub;

import java.io.File;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FiltrosElectrica {
	
	public WebDriver driver;
	
	@Test (priority=1)
	public void nombreBienServicio() throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Buscar')])[2]"))).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("serviceName"))).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("serviceName"))).sendKeys("topografico");
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/busquedaPub/FiltrosElectrica-nombreBienServicior.jpg"));
		}
	    Thread.sleep(2000);
	}
	
	@Test (priority=2)
	public void nombreProveedor() throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.navigate().refresh();
		Thread.sleep(1000);
	    driver.findElement(By.id("providerName")).clear();
	    driver.findElement(By.id("providerName")).sendKeys("trisquel");
	    driver.findElement(By.linkText("Buscar")).click();
	    Thread.sleep(2000);
	    try {
	        String text=driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div/div[5]/div[2]/div/div[1]/div/div[1]/div[1]/dl/dt[1]")).getText();
	        if(text.equals("TRISQUEL CO. SA DE CV")){
	        }
	      } catch (Exception e){
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/busquedaPub/FiltrosElectrica-nombreProveedor.jpg"));
	    }
	    Thread.sleep(2000);
	 }
	
	@Test (priority=3)
	public void entidadFederativa() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		driver.navigate().refresh();
		Thread.sleep(1000);
		Select estado = new Select(driver.findElement(By.id("states")));
		estado.selectByVisibleText("Aguascalientes");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Buscar"))).click();
		Thread.sleep(2000);
	    try {
			List<WebElement> proveedores = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div/div[5]/div[2]/div/div"));
			Thread.sleep(3000);
			for(int entfed=0; entfed<proveedores.size()-2; entfed++){
				String xpath = "html/body/main/div/div[2]/div/div/div[5]/div[2]/div/div["+(entfed+1)+"]/div/div[1]/div[1]/dl/dt[5]/small";
	            String direccion = driver.findElement(By.xpath(xpath)).getText();
	            if(direccion=="Aguascalientes"){
	            	System.out.println(direccion);
	            }
			}
	      } catch (Exception e){
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/FiltrosElectrica-entidadFederativa.jpg"));
	    }
	}
	
	@Test (priority=4)
	public void cadenasProductivas()  throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		try{
			driver.navigate().refresh();
			Thread.sleep(1000);
		    driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[5]/div[1]/div/div[2]/form/div[7]/label/input")).click();
		    driver.findElement(By.linkText("Buscar")).click();	
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/FIltrosElectrica-cadenasProductivas"));	
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
