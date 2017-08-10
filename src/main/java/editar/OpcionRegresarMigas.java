//Verificar la funcionalidad de las migas (inicio) y el boton Regresar de Edicion, Cadenas elegidas y Certificaciones y necesidades de desarrollo.

package editar;

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

public class OpcionRegresarMigas {
	public WebDriver driver;
	
	@Test(priority = 1)
	@Parameters ({"correo","contrasena"})
	public void iniciarSesion(String correo, String contrasena) {		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys(correo);
		driver.findElement(By.id("password")).sendKeys(contrasena);
		driver.findElement(By.name("rememberMe")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	@Test (priority = 2)
	public void opcionRegresar()throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/button"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='backHome']"))).click();
			driver.findElement(By.xpath("//div[2]/button"));
			Thread.sleep(200);
		    driver.findElement(By.linkText("Cadenas elegidas")).click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Regresar"))).click();
		    driver.findElement(By.linkText("Cadenas elegidas"));
		    Thread.sleep(200);
		    driver.findElement(By.linkText("Cuestionario de certificaciones y necesidades")).click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Regresar"))).click();
		    driver.findElement(By.linkText("Cuestionario de certificaciones y necesidades"));	
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/editarOpcionRegresar.png"));
		}

	}
	
	@Test(priority = 3)
	public void probarMigas() throws InterruptedException, IOException{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/button"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Inicio"))).click();
			driver.findElement(By.xpath("//div[2]/button"));
			Thread.sleep(200);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cuestionario de certificaciones y necesidades"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Inicio"))).click();
			driver.findElement(By.linkText("Cuestionario de certificaciones y necesidades"));
			Thread.sleep(200);
		    driver.findElement(By.linkText("Cadenas elegidas")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Inicio"))).click();
			driver.findElement(By.linkText("Cadenas elegidas"));
			Thread.sleep(200);
			driver.findElement(By.linkText("Men\u00fa")).click();
			driver.findElement(By.linkText("Salir")).click();	
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/editarProbarMigas.png"));
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
	public void cerrarChrome(){
		driver.close();
	}
	

}