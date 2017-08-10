//Verificar que funcione el paginador en la vista de detalle del contacto.
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DetalleElectricaPaginador {
	public WebDriver driver;
	
	@Test(priority = 1)
	public void probarPagindor() throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		try{
			driver.findElement(By.xpath("(//a[contains(text(),'Buscar')])[2]")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Detalle"))).click();
			List<WebElement> paginador = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div/div[4]/div[1]/div[5]/div/ul/li"));
			if(paginador.size()>0){
				for(int pagina=0; pagina<4; pagina++){
					String xpath = "html/body/main/div/div[2]/div/div/div[4]/div[1]/div[5]/div/ul/li["+(pagina+1)+"]/a";
					driver.findElement(By.xpath(xpath)).click();
					Thread.sleep(2000);
				}
			}	
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/busquedaPub/DetalleElectricaPaginador-pobrarPaginador.jpg"));
		}
	}
	//Verificar que funcionen la migas de la vista de detalle del contacto de la busqueda de la industria electrica.
	@Test(priority = 2)
	public void probarMigas() throws IOException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		try{
			driver.navigate().back();
			driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div/div[5]/div[2]/div/div[1]/div/div[2]/div/div/a")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div/div[2]/div/div/div[1]/div/ol/li[2]/a"))).click();
			driver.findElement(By.linkText("Inicio")).click();	
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/busquedaPub/DetalleElectricaPaginador-pobrarMigas.jpg"));	
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
