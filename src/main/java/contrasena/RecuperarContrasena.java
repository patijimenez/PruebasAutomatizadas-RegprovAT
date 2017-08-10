//Verificar que un usuario registrado pueda recuperar su contraseña y cancelar.
package contrasena;

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

public class RecuperarContrasena {
	public WebDriver driver;
	
	@Test(priority = 1)
	public void recuperar() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div[2]/div/div[4]/div[2]/div[1]/form[2]/div/div[1]/div/a[1]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("¿Olvidaste tu contraseña?"))).click();
	    driver.findElement(By.xpath("//input[@type='text']")).clear();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("mantogris@test.com");
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	}
	
	@Test(priority = 2)
	public void cancelar() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().refresh();
	    driver.findElement(By.linkText("Cancelar")).click();
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
		  driver.close();
	}
}