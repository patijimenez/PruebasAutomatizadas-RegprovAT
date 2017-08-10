//Verificar que una persona moral pueda registrarse.
package registrar;

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

public class RegistroPersonaMoral {
	public WebDriver driver;	
	String estadoProv;
	String municipioProv;
	  
	  
	@Test(priority = 1)
	@Parameters ({"rfcPM","correoPM"})
	public void registro(String rfcPM, String correoPM) throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.findElement(By.id("email")).sendKeys("vangogh4@test.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.name("rememberMe")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.findElement(By.xpath("//a[2]/font")).click();
		Select tipoPersona = new Select(driver.findElement(By.name("personType")));
		tipoPersona.selectByVisibleText("Persona Moral");
		Select giroEmpresa = new Select(driver.findElement(By.name("profile")));
		giroEmpresa.selectByVisibleText("Proveedor de servicios");
		driver.findElement(By.name("companyRfc")).sendKeys(rfcPM);
		driver.findElement(By.name("companyName")).sendKeys("Art Project SA");
		driver.findElement(By.name("name")).sendKeys("");
		driver.findElement(By.name("lastName")).sendKeys("");
		driver.findElement(By.name("secondLastName")).sendKeys("");
		driver.findElement(By.name("email")).sendKeys(correoPM);
		driver.findElement(By.name("institutionalEmail")).sendKeys(correoPM);
		driver.findElement(By.name("webPage")).sendKeys("www.dominio.com");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("confirmationPassword")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		Thread.sleep(3000);
		try{
			driver.findElement(By.xpath(".//*[@id='stratification']"));
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroPM.png"));
	  	}		
	}  

	@Test(priority = 2)
	@Parameters({"logo"})
	public void registroPasoUno(String logo) throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		Select estratificacion = new Select(driver.findElement(By.id("stratification")));
		estratificacion.selectByVisibleText("Mediana");
		driver.findElement(By.id("logo")).sendKeys(logo);
		driver.findElement(By.xpath("//button[@type='submit']")).click();	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[4]/div[2]/div[2]/div/select"))).click();
		Select industria = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/main/div/div[2]/div/div[4]/div[2]/div[2]/div/select"))));
		industria.selectByVisibleText("Industria de Hidrocarburos");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='postalCode']"))).sendKeys("01030");
		Select colonia = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("locality"))));
		Thread.sleep(3000);
		colonia.selectByValue("0");
		driver.findElement(By.id("street")).sendKeys("Alyscamps");
		driver.findElement(By.id("externalNumber")).sendKeys("2");
		driver.findElement(By.id("internalNumber")).sendKeys("9");
		Select telefono = new Select(driver.findElement(By.xpath("//div[6]/div/div/div/div/div/select")));
		telefono.selectByValue("0");
		driver.findElement(By.name("lada")).sendKeys("044");
		driver.findElement(By.name("phone")).sendKeys("5544660349");
		driver.findElement(By.name("extension")).sendKeys("12");
		driver.findElement(By.cssSelector("div.pull-right > button.btn.btn-default")).click();
		driver.findElement(By.id("stepOneNext")).click();
		Thread.sleep(3000);
		try{
			driver.findElement(By.xpath(".//*[@id='productiveChains']/div/div/div/input"));
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroPMPasoUno.png"));
		}
	}

	@Test(priority = 3)
	public void registroPasoDos() throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 50);	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='allExpanded']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='221']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='53']"))).click();
		driver.findElement(By.id("stepTwoNext")).click();
		Thread.sleep(3000);
		try{
			driver.findElements(By.xpath("html/body/main/div/div[2]/div/div[6]/div[5]/div/div/div"));
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroPMPasoDos.png"));
		}
	}
	
	@Test(priority = 4)
	public void registroPasoTres() throws IOException{
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='iso9000']"))).click();
		driver.findElement(By.xpath("(//input[@id='certTecnicas'])[3]")).click();		
		driver.findElement(By.xpath("(//input[@type='text'])[14]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[14]")).sendKeys("Capacitacion");
		driver.findElement(By.xpath("//div[3]/div/div[2]/multiple-descriptions/div[2]/button")).click();
		driver.findElement(By.id("toEndRegister")).click();
		try{
			driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[2]/div[4]/div/a")).isDisplayed();
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroPMPasoTres.png"));
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
		 driver.close();
	 }
}

