//Verificar que se puedan agregar y eliminar las necesidades de certificaciones tecnicas, certificaciones de seguridad, capacitacion y otros.
package editar;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class NecesidadesDesarrollo {
	public WebDriver driver;
	
	@Test (priority=1)
	@Parameters({"correo","contrasena"})
	public void iniciarSesion(String correo,String contrasena){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.id("email")).sendKeys(correo);
		driver.findElement(By.id("password")).sendKeys(contrasena);
		driver.findElement(By.xpath("//button[@type='submit']")).click();	  
		driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div/div[3]/div/div/div[2]/div/div[2]/a")).click();
	}
  
	@Test (priority=2)
	public void agregarCertificacionesTecnicas() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
	  
		WebElement certificacionTecnica = driver.findElement(By.id("1"));
		String valorCT = certificacionTecnica.getAttribute("value");
		String activo = "true";
		if(valorCT.contentEquals(activo)){
			driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("Certificacion tecnica N");
			driver.findElement(By.xpath("//div[1]/div/div[2]/multiple-descriptions/div[2]/button")).click();
								   
		} else {

			driver.findElement(By.id("1")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[2]"))).sendKeys("Certificación técnica M");
			driver.findElement(By.xpath("//div[1]/div/div[2]/multiple-descriptions/div[2]/button")).click();	
			}
	
	}
 
	@Test (priority=3)
	public void agregarCertificacionesSeguridad() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
	  
		WebElement certificacionSeguridad = driver.findElement(By.id("2"));
		String valorCS = certificacionSeguridad.getAttribute("value");
		String activo = "true";
		if(valorCS.contentEquals(activo)){
			driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("Certificacion de seguridad N");
			driver.findElement(By.xpath("//div[2]/div/div[2]/multiple-descriptions/div[2]/button")).click();
		} else {
			driver.findElement(By.id("2")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[4]"))).sendKeys("Certificación de seguridad M");
			driver.findElement(By.xpath("//div[2]/div/div[2]/multiple-descriptions/div[2]/button")).click();	
			}
		}
 
	@Test (priority=4)
	public void agregarCapacitacion() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
	  
		WebElement capacitacion = driver.findElement(By.id("4"));
		String cap = capacitacion.getAttribute("value");
		String activo = "true";
		if(cap.contentEquals(activo)){
			driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys("Capacitacion N");
			driver.findElement(By.xpath("//div[3]/div/div[2]/multiple-descriptions/div[2]/button")).click();
		} else {
			driver.findElement(By.id("4")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[6]"))).sendKeys("Capacitación M");
			driver.findElement(By.xpath("//div[3]/div/div[2]/multiple-descriptions/div[2]/button")).click();	
			}
	}
   
	@Test (priority=5)
	public void agregarOtros() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
	  
		WebElement otrasNecesidades = driver.findElement(By.id("8"));
		String otros = otrasNecesidades.getAttribute("value");
		String activo = "true";
		if(otros.contentEquals(activo)){
			driver.findElement(By.xpath("(//input[@type='text'])[14]")).sendKeys("Otras necesidades N");
			driver.findElement(By.xpath("//div[7]/div/div[2]/multiple-descriptions/div[2]/button")).click();
		} else {
			driver.findElement(By.id("8")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[14]"))).sendKeys("Otras necesidades M");
			driver.findElement(By.xpath("//div[7]/div/div[2]/multiple-descriptions/div[2]/button")).click();	
			}
	}
 
	@Test (priority=6)
	public void guardarNecesidades()  throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Guardar']")).click();
		Thread.sleep(3000);
	}
  
	@Test (priority=7)
	public void eliminarCertificacionesTecnicas(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	    WebElement certificacionTecnica = driver.findElement(By.id("1"));
		String valorCT = certificacionTecnica.getAttribute("value");
		String activo = "true";
		if(valorCT.contentEquals(activo)){
			try{
			driver.findElement(By.xpath("(//div[1]/div/div[2]/multiple-descriptions/div[3]/table/tbody/tr[1]/td[2]/button)")).click();			
			} catch(Exception e) {
				System.out.println("No existen registros para eliminar para necesidades de certificacion tecnicas.");
			}
		}
  }
		
	@Test (priority=8)
	public void eliminarCertificacionesSeguridad(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
		WebElement certificacionSeguridad = driver.findElement(By.id("2"));
		String valorCS = certificacionSeguridad.getAttribute("value");
		String activo = "true";
		if(valorCS.contentEquals(activo)){
		  try{
			  driver.findElement(By.xpath("(//div[2]/div/div[2]/multiple-descriptions/div[3]/table/tbody/tr[1]/td[2]/button)")).click();			
		  	  } catch (Exception e){
		  		  System.out.println("No existen registros para eliminar para necesidades de certificacion de seguridad.");
		  	  }
		}
	}
  
	@Test (priority=9)
	public void eliminarCapacitacion(){
   	  	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
   	  	WebElement capacitacion = driver.findElement(By.id("4"));
   	  	String cap = capacitacion.getAttribute("value");
   	  	String activo = "true";
   	  	if(cap.contentEquals(activo)){
		  try{
			driver.findElement(By.xpath("(//div[3]/div/div[2]/multiple-descriptions/div[3]/table/tbody/tr[1]/td[2]/button)")).click();			
			} catch (Exception e) {
				System.out.println("No existen registros a eliminar para necesidades de capacitacion.");
			}
   	  	}  
	}
  
	@Test (priority=10)
	public void eliminarOtros(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
		WebElement otrasNecesidades = driver.findElement(By.id("8"));
		String otros = otrasNecesidades.getAttribute("value");
		String activo = "true";
		if(otros.contentEquals(activo)){
		  try{
		  driver.findElement(By.xpath("//div[7]/div/div[2]/multiple-descriptions/div[3]/table/tbody/tr[1]/td[2]/button")).click();			
		} 
		  catch (Exception e){
			  System.out.println("No existen registros a eliminar de otras necesidades.");			  
		  }	
		}
		driver.findElement(By.xpath("//button[text()='Guardar']")).click();
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
