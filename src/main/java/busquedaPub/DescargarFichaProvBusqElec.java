//Verificar que se pueda descargar la ficha de un proveedor en la busqueda por industria electrica.
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

public class DescargarFichaProvBusqElec {
	public WebDriver driver;
	
	@Test
	public void descargar() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//Industria Electrica
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Buscar')])[2]"))).click();
	    driver.findElement(By.linkText("Detalle")).click();
	    driver.findElement(By.linkText("Descargar ficha")).click();
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
