//Verificar que un usuario que no ha finalizado su registro pueda realizar búsquedas por palabras claves de bien o servicio  y limpiar su búsqueda.
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

public class BuscarBienServicio {
	public WebDriver driver;
	String estadoProv;
	String municipioProv;
	
	//Utilizar anitabarbosa@test.com --> para probar iniciando sesión
	
	@Test(priority = 1)
	@Parameters ({"rfcBuscarBS","correoBuscarBS","logo"})
	public void registro(String rfcBuscarBS, String correoBuscarBS,String logo) throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		
		driver.findElement(By.xpath("//a[2]/font")).click();
		Select tipoSolicitante = new Select(driver.findElement(By.name("personType")));
		tipoSolicitante.selectByValue("0");
		Select giroEmpresa = new Select(driver.findElement(By.name("profile")));
		giroEmpresa.selectByValue("3");
		driver.findElement(By.name("rfc")).sendKeys(rfcBuscarBS);
		Thread.sleep(2000);
		driver.findElement(By.name("name")).sendKeys("JESUS");
		driver.findElement(By.name("lastName")).sendKeys("LICONA");
		driver.findElement(By.name("secondLastName")).sendKeys("MARTINEZ");												
		driver.findElement(By.name("email")).sendKeys(correoBuscarBS);
		driver.findElement(By.name("institutionalEmail")).sendKeys(correoBuscarBS);
		Thread.sleep(2000);
		driver.findElement(By.name("webPage")).sendKeys("www.paginaprueba.com");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("confirmationPassword")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='logo']")));
		driver.findElement(By.xpath("//*[@id='logo']")).sendKeys(logo);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[4]/div[2]/div[2]/div/select"))).click();
		Select industria = new Select(driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[4]/div[2]/div[2]/div/select")));
		industria.selectByValue("0");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='postalCode']"))).sendKeys("01030");
		Select colonia = new Select(driver.findElement(By.id("locality")));
		estadoProv = driver.findElement(By.xpath("//div/div[2]/div/div[4]/div[3]/div/form/div[2]/div[2]/div/span")).getText();
		System.out.println(estadoProv);
		municipioProv = driver.findElement(By.xpath("//div/div[2]/div/div[4]/div[3]/div/form/div[2]/div[3]/div/span")).getText();
		System.out.println(municipioProv);
		colonia.selectByVisibleText("Florida");
		driver.findElement(By.id("street")).sendKeys("Primavera");
		driver.findElement(By.id("externalNumber")).sendKeys("3");
		driver.findElement(By.id("internalNumber")).sendKeys("1");
		Select telefono = new Select(driver.findElement(By.xpath("//div[6]/div/div/div/div/div/select")));
		telefono.selectByValue("0");
		driver.findElement(By.name("lada")).sendKeys("044");
		driver.findElement(By.name("phone")).sendKeys("5533333333");
		driver.findElement(By.cssSelector("div.pull-right > button.btn.btn-default")).click(); 
		wait.until(ExpectedConditions.elementToBeClickable(By.id("stepOneNext"))).click();
	}
	
	@Test(priority = 2)
	public void limpiarBienServicio() throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		Thread.sleep(3000);
    	driver.findElement(By.name("criteria")).sendKeys("cuchillas");
    	driver.findElement(By.xpath("//*[@id=\"productiveChains\"]/div/div/div/button[1]")).click();
    	Thread.sleep(2000);
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"productiveChains\"]/div/div/div/button[2]"))).click();
    	Thread.sleep(3000);
    	driver.navigate().refresh();
    	Thread.sleep(3000);
		try{
			int servicios = driver.findElements(By.xpath("html/body/main/div/div[2]/div/div[5]/div[6]/div/table/tbody/tr")).size();
			if(servicios<=0){
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroBorrarTelefono.png"));
			}
		}catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroBorrarTelefono.png"));  
		}
	} 
	
	@Test(priority = 3)
	public void buscarBienServicio() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='productiveChains']/div/div/div/button[2]"))).click();
    	driver.findElement(By.name("criteria")).sendKeys("interruptores");
    	driver.findElement(By.xpath(".//*[@id='productiveChains']/div/div/div/button[1]")).click();
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
