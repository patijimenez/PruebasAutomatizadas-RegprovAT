//Verificar que una persona moral pueda editar la informacion que ingreso en su registro.
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ModificarDatosPersMor {
	public WebDriver driver;	
	String estadoProv;
	String municipioProv;

	@Test(priority = 1)
	@Parameters({"correoMoral","contrasena"})
	public void iniciarSesion(String correoMoral,String contrasena){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
		driver.findElement(By.id("email")).sendKeys(correoMoral);
		driver.findElement(By.id("password")).sendKeys(contrasena);	
		driver.findElement(By.name("rememberMe")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	@Test(priority = 2)
	@Parameters({"logo"})
	public void editarInformacion(String logo) throws IOException{
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div/div[2]/div/general-data/div/div[2]/div/div[2]/button"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='modify']"))).click();
		driver.findElement(By.id("logo")).sendKeys(logo);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='stratification']"))).click();
		Select estratificacion = new Select(driver.findElement(By.id("stratification")));
		estratificacion.selectByVisibleText("Grande");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[6]/div/form/div/input")).clear();
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[6]/div/form/div/input")).sendKeys("www.artproject.com.mx");
		driver.findElement(By.xpath(".//*[@id='postalCode']")).clear();
		driver.findElement(By.xpath(".//*[@id='postalCode']")).sendKeys("06040");
		Select colonia = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("locality"))));
		colonia.selectByValue("0");
		driver.findElement(By.xpath(".//*[@id='street']")).clear();
		driver.findElement(By.xpath(".//*[@id='street']")).sendKeys("Antonio Caso");
		driver.findElement(By.xpath(".//*[@id='externalNumber']")).clear();
		driver.findElement(By.xpath(".//*[@id='externalNumber']")).sendKeys("13");
		driver.findElement(By.xpath(".//*[@id='internalNumber']")).clear();
		driver.findElement(By.xpath(".//*[@id='internalNumber']")).sendKeys("9");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[10]/div[1]/div[1]/div/div/div/select")).click();
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[10]/div[1]/div[1]/div/div/div/select/option[2]")).click();
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[10]/div[1]/div[2]/div[1]/div/div/input")).sendKeys("055");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[10]/div[1]/div[2]/div[2]/div/input")).sendKeys("58065126");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[10]/div[1]/div[2]/div[3]/div/div/input")).sendKeys("2");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[10]/div[1]/div[3]/div[2]/div/div/button")).click();
		try{
			driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[11]/div/div/button")).click();	
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/modificarDatosPersFis.png"));
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
	public void afterTest() {
		driver.quit();
	}
}

