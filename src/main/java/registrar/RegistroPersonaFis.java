//Verificar que una persona física pueda registrarse. 
package registrar;

import org.testng.annotations.Parameters;   
import org.testng.annotations.Test; 
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistroPersonaFis {
	  public WebDriver driver;
	  String estadoProv;
	  String municipioProv;
	  
	  @Test (priority = 1)
	  @Parameters ({"rfcPF","correoPF","logo"})
	  public void registroPasoUno(String rfcPF, String correoPF,String logo) throws IOException, InterruptedException {
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  WebDriverWait wait = new WebDriverWait(driver, 40);
		  
		  driver.findElement(By.xpath("//a[2]/font")).click();
		  Select tipoSolicitante = new Select(driver.findElement(By.name("personType")));
		  tipoSolicitante.selectByValue("0");
		  Select giroEmpresa = new Select(driver.findElement(By.name("profile")));
		  giroEmpresa.selectByValue("3");
		  driver.findElement(By.name("rfc")).sendKeys(rfcPF);
		  Thread.sleep(2000);
		  driver.findElement(By.name("name")).sendKeys("Roberto");
		  driver.findElement(By.name("lastName")).sendKeys("Acosta");
		  driver.findElement(By.name("secondLastName")).sendKeys("Nava");
		  driver.findElement(By.name("email")).sendKeys(correoPF);
		  driver.findElement(By.name("institutionalEmail")).sendKeys(correoPF);
		  Thread.sleep(2000);
		  driver.findElement(By.name("webPage")).sendKeys("www.paginaprueba.com");
		  driver.findElement(By.name("password")).sendKeys("123456");
		  driver.findElement(By.name("confirmationPassword")).sendKeys("123456");
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox']"))).click();
		  Thread.sleep(2000);
		  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='logo']")));
		  driver.findElement(By.xpath("//*[@id='logo']")).sendKeys(logo);
		  WebElement cargarLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		  cargarLogo.click();
		  Thread.sleep(4000);
		  Thread.sleep(3000);
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[4]/div[2]/div[2]/div/select"))).click();
		  Select industria = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/main/div/div[2]/div/div[4]/div[2]/div[2]/div/select"))));
		  industria.selectByValue("0");
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='postalCode']"))).sendKeys("01030");
		  estadoProv = driver.findElement(By.xpath("//div/div[2]/div/div[4]/div[3]/div/form/div[2]/div[2]/div/span")).getText();
		  System.out.println(estadoProv);
		  municipioProv = driver.findElement(By.xpath("//div/div[2]/div/div[4]/div[3]/div/form/div[2]/div[3]/div/span")).getText();
		  System.out.println(municipioProv);
		  Select colonia = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("locality"))));
		  colonia.selectByValue("0");
		  driver.findElement(By.id("street")).sendKeys("Hortensia");
		  driver.findElement(By.id("externalNumber")).sendKeys("13");
		  driver.findElement(By.id("internalNumber")).sendKeys("9");
		  Select tipoTelefono = new Select(driver.findElement(By.xpath("//div/div[2]/div/div[4]/div[6]/div[1]/div[1]/div/div/div/select")));
		  tipoTelefono.selectByVisibleText("Móvil");
		  driver.findElement(By.name("lada")).sendKeys("0155");
		  driver.findElement(By.name("phone")).sendKeys("51000000");
		  driver.findElement(By.xpath("//div/div[2]/div/div[4]/div[6]/div[1]/div[3]/div[2]/div/div/button")).click();
		  Thread.sleep(2000);
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("stepOneNext"))).click();
		  Thread.sleep(3000);
		  try{
			  driver.findElement(By.xpath(".//*[@id='productiveChains']/div/div/div/input"));
		  }catch(Exception e){
			  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroPFPasoUno.png"));
		  }
	  }
	  @Test (priority = 2) 
	  public void registroPasoDos() throws IOException, InterruptedException{ 
		  driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		  WebDriverWait wait = new WebDriverWait(driver, 40); 

		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='industria1']/span[1]"))).click();
		  driver.findElement(By.xpath(".//*[@id='industria1-3']/span[1]")).click();
		  driver.findElement(By.xpath(".//*[@id='industria1-3-2630']/span[1]")).click();
		  driver.findElement(By.xpath(".//*[@id='industria1-3-2630-2631']/span[1]")).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='2692']"))).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='industria1-2']/span[1]"))).click();
		  driver.findElement(By.xpath(".//*[@id='industria1-2-2088']/span[1]")).click();
		  driver.findElement(By.xpath(".//*[@id='industria1-2-2088-2089']/span[1]")).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='2090']"))).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='industria1-3035']/span[1]"))).click();
		  driver.findElement(By.xpath(".//*[@id='industria1-3035-3106']/span[1]")).click();
		  driver.findElement(By.xpath(".//*[@id='industria1-3035-3106-3110']/span[1]")).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='3111']"))).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='industria1-4']/span[1]"))).click();
		  driver.findElement(By.xpath(".//*[@id='industria1-4-2603']/span[1]")).click();
		  driver.findElement(By.xpath(".//*[@id='industria1-4-2603-2620']/span[1]")).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='2621']"))).click();
		  Thread.sleep(3000);
		  driver.findElement(By.xpath(".//*[@id='stepTwoNext']")).click();
		  Thread.sleep(3000);
		  try{
			  driver.findElements(By.xpath("html/body/main/div/div[2]/div/div[6]/div[3]/div/form/div/div/div"));
		  }catch(Exception e){
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroPFPasoDos.png"));
		  }
	  }

	  @Test (priority = 3)
	  public void registroPasoTres() throws InterruptedException, IOException{
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		  WebDriverWait wait = new WebDriverWait(driver, 40);  
		  
		  Thread.sleep(2000);
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='can3z299']"))).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='iso9000']"))).click();
		  WebElement certificado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("certTecnicas")));
		  certificado.click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div[2]/div/div[6]/div[5]/div/div/div[1]/div/div[2]/multiple-descriptions/div[1]/div/input")));
		  driver.findElement(By.xpath("(//input[@type='text'])[10]")).sendKeys("prueba");
		  driver.findElement(By.cssSelector("multiple-descriptions.ng-isolate-scope.ng-scope > div.col-md-2 > button.btn.btn-default")).click();
		  driver.findElement(By.xpath(".//*[@id='toEndRegister']")).click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div[2]/div/div[2]/div[4]/div/strong")));
		  System.out.println(driver.findElement(By.xpath("//div/div[2]/div/div[2]/div[4]/div/span/h4")).getText());
		  Thread.sleep(3000);
		  try{
			  driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[2]/div[4]/div")).isDisplayed();
		  }catch(Exception e){
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("/home/paty/CapturasPantallaSelenium/registroPFPasoTres.png"));
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
