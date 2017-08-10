//Verificar que un usuario que no ha finalizado su registro pueda borrar el telefono que registro
package registrar;

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

public class BorrarTelefono {
	public WebDriver driver;
	String estadoProv;
	String municipioProv;

	@Test (priority = 1)
	@Parameters ({"rfcBorrarT","correoBorrarT","logo"})
	public void registro(String rfcBorrarT, String correoBorrarT,String logo) throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		driver.findElement(By.linkText("Regístrate Aquí")).click();
		Select tipoSolicitante = new Select(driver.findElement(By.name("personType")));
		tipoSolicitante.selectByValue("0");
		Select giroEmpresa = new Select(driver.findElement(By.name("profile")));
		giroEmpresa.selectByVisibleText("Proveedor de servicios"); 
		driver.findElement(By.name("rfc")).sendKeys(rfcBorrarT);
		Thread.sleep(2000);
		driver.findElement(By.name("name")).sendKeys("Jorge");
		driver.findElement(By.name("lastName")).sendKeys("Martinez");
		driver.findElement(By.name("secondLastName")).sendKeys("Martinez");
		driver.findElement(By.name("email")).sendKeys(correoBorrarT);
		driver.findElement(By.name("institutionalEmail")).sendKeys(correoBorrarT);
		Thread.sleep(2000);
		driver.findElement(By.name("webPage")).sendKeys("www.paginaprueba.com");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("confirmationPassword")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo"))).sendKeys(logo);
		WebElement cargarLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		cargarLogo.click();			
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[4]/div[2]/div[2]/div/select"))).click();
		Select industria = new Select(driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[4]/div[2]/div[2]/div/select")));
		industria.selectByValue("0");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='postalCode']"))).sendKeys("01030");
		Select colonia = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("locality"))));
		colonia.selectByValue("0");
		estadoProv = driver.findElement(By.xpath("//div/div[2]/div/div[4]/div[3]/div/form/div[2]/div[2]/div/span")).getText();
		System.out.println(estadoProv);
		municipioProv = driver.findElement(By.xpath("//div/div[2]/div/div[4]/div[3]/div/form/div[2]/div[3]/div/span")).getText();
		System.out.println(municipioProv);
		driver.findElement(By.id("street")).clear();
		driver.findElement(By.id("street")).sendKeys("Sin nombre");
		driver.findElement(By.id("externalNumber")).clear();
		driver.findElement(By.id("externalNumber")).sendKeys("9");
		driver.findElement(By.id("internalNumber")).clear();			driver.findElement(By.id("internalNumber")).sendKeys("3");
		Thread.sleep(3000);
		Select tipoTelefono = new Select(driver.findElement(By.xpath("//div/div[2]/div/div[4]/div[6]/div[1]/div[1]/div/div/div/select")));
		tipoTelefono.selectByValue("2");
		driver.findElement(By.name("lada")).sendKeys("52");
		driver.findElement(By.name("phone")).sendKeys("58065126");
		//Boton agregar telefono
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[4]/div[6]/div[1]/div[3]/div[2]/div/div/button"))).click();
		List<WebElement> telefonosRegistro = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div[4]/div[6]/div[2]/table/tbody/tr"));
		int telRegIni= telefonosRegistro.size();
		Thread.sleep(2000);
		//Boton borrar
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[4]/div[6]/div[2]/table/tbody/tr/td[2]/button"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#stepOneSave"))).click();
		Thread.sleep(3000);
		driver.navigate().refresh();
		int telRegFin = telRegIni-1;
		try{
			if(telRegIni == telRegFin){
				System.out.println(telRegFin);
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroBorrarTelefono.png"));
			}
		}catch(Exception e){
			  
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
