//Verificar que se pueda descargar la ficha de un proveedor en la busqueda por hidrocarburos
package busquedaPub;

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

public class DescargarFichaProvBusqHidro {
	public WebDriver driver;
	
	@Test
	public void descargar(){
		WebDriverWait wait = new WebDriverWait(driver, 50);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Buscar"))).click();
	    driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[5]/div[2]/div/div[15]/div/div[2]/div/div/a")).click(); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Descargar ficha"))).click();   
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

