//Verificar la funcionalidad del paginador de la busqueda publica de la Industria de hidrocarburos.
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


public class PaginadorBusquedaHidro {
	public WebDriver driver;
	
	@Test
	public void pobrarPaginador() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Buscar"))).click();
			List<WebElement> paginador = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div/div[5]/div[2]/div/div[21]/div/ul/li"));
			if(paginador.size()>0){
				for(int pagina=2; pagina<paginador.size()-2; pagina++){
					String xpath = "html/body/main/div/div[2]/div/div/div[5]/div[2]/div/div[21]/div/ul/li["+(pagina+1)+"]/a";
					driver.findElement(By.xpath(xpath)).click();
					Thread.sleep(2000);
				}
			}	
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/busquedaPub/PaginadorBusquedaHidro.jpg"));		
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
