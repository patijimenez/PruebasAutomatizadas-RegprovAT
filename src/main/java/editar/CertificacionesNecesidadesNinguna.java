//Verificar que un usuario con rol de proveedor pueda eliminar una necesidad que capturo al seleccionar la casilla "Ninguna".
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

public class CertificacionesNecesidadesNinguna {
	public WebDriver driver;
	
	@Test(priority = 1)
	@Parameters({"correo","contrasena"})
	public void iniciarSesion(String correo,String contrasena){
		
		driver.findElement(By.id("email")).sendKeys(correo);
		driver.findElement(By.id("password")).sendKeys(contrasena);
		driver.findElement(By.name("rememberMe")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();		
	}
	
	@Test(priority = 2)
	@Parameters({"capturapantalla"})
	public void certificacionesNinguna(String capturapantalla) throws InterruptedException, IOException{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	    driver.findElement(By.linkText("Cuestionario de certificaciones y necesidades")).click();
	    
	    Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("otherCerts"))).click();
		if(driver.findElement(By.id("otherCerts")).isSelected()){
    		System.out.println("Otros seleccionado");
    	}
		else{
			System.out.println("Otros no seleccionado");
		}
		
	    Thread.sleep(3000);
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("noneCertifications"))).click();
	    if (driver.findElement(By.id("noneCertifications")).isSelected() )
	    {
	    	System.out.println("--> Certificaciones ninguna seleccionada");
	    	if(driver.findElement(By.id("can3z299")).isSelected()){
	    		System.out.println("CAN3Z299 seleccionada");
	    	}
	    	else{
	    		System.out.println("CAN3Z299 deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("api")).isSelected()){
	    		System.out.println("API seleccionada");
	    	}
	    	else{
	    		System.out.println("API deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("rp2eqiso")).isSelected()){
	    		System.out.println("RP 2EQ/ISO seleccionada");
	    	}
	    	else{
	    		System.out.println("RP 2EQ/ISO deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("spec")).isSelected()){
	    		System.out.println("SPEC seleccionada");
	    	}
	    	else{
	    		System.out.println("SPEC deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("iso9000")).isSelected()){
	    		System.out.println("ISO9000 seleccionada");
	    	}
	    	else{
	    		System.out.println("ISO9000 deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("ansiasqc")).isSelected()){
	    		System.out.println("ANSI/ASQC seleccionada");
	    	}
	    	else{
	    		System.out.println("ANSI/ASQC deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("dnd")).isSelected()){
	    		System.out.println("DND seleccionada");
	    	}
	    	else{
	    		System.out.println("DND deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("ance")).isSelected()){
	    		System.out.println("ANCE seleccionada");
	    	}
	    	else{
	    		System.out.println("ANCE deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.xpath("//*[@id='api']")).isSelected()){
	    		System.out.println("IAEA SC-QA3 seleccionada");
	    	}
	    	else{
	    		System.out.println("IAEA SC-QA3 deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("asme")).isSelected()){
	    		System.out.println("ASME seleccionada");
	    	}
	    	else{
	    		System.out.println("ASME deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("mil")).isSelected()){
	    		System.out.println("MIL seleccionada");
	    	}
	    	else{
	    		System.out.println("MIL deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("ema")).isSelected()){
	    		System.out.println("EMA seleccionada");
	    	}
	    	else{
	    		System.out.println("EMA deshabilitada");
	    	}
	    	
	    	if(driver.findElement(By.id("otherCerts")).isSelected()){
	    		System.out.println("Otros seleccionada");
	    	}
	    	else{
	    		System.out.println("Otros deshabilitada");
	    	}
	    }
	    else{
	    	System.out.println("Certificaciones ninguna deshabilitada");
	    }
	    Thread.sleep(3000);
		if( driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div/div[2]/div[5]/div/form/div/div/div[6]/div[2]/button")).isDisplayed()){
			System.out.println("Boton agregar presente");
		}else{
			System.out.println("Boton agregar ausente");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(capturapantalla));
		}
	}
	
	@Test(priority = 3)
	@Parameters({"capturapantalla"})
	public void necesidadesNinguna(String capturapantalla) throws InterruptedException, IOException{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		 Thread.sleep(3000);
		 wait.until(ExpectedConditions.elementToBeClickable(By.id("8"))).click();
		 if(driver.findElement(By.id("8")).isSelected()){
			 System.out.println("Otros seleccionada");
		 }
		 else{
			 System.out.println("Otros no seleccionada");
		 }
		Thread.sleep(3000);	
		wait.until(ExpectedConditions.elementToBeClickable(By.id("9"))).click();
		if (driver.findElement(By.id("9")).isSelected()){
			System.out.println("--> Necesidades ninguna seleccionada");
			
			if (driver.findElement(By.id("1")).isSelected()){
				System.out.println("Certificaciones tecnicas seleccionada");
			}
			else{
				System.out.println("Certificaciones tecnicas deshabilitada");
			}
			
			if(driver.findElement(By.id("2")).isSelected()){
				System.out.println("Certificaciones de seguirdad seleccionada");
			}
			else{
				System.out.println("Certificaciones de seguirdad deshabilitada");
			}
			
			if(driver.findElement(By.id("4")).isSelected()){
				System.out.println("Capacitacion seleccionada");
			}
			else{
				System.out.println("Capacitacion deshabilitada");
			}
			
			if(driver.findElement(By.id("5")).isSelected()){
				System.out.println("Adopcion de nuevas tecnologias seleccionada");
			}
			else{
				System.out.println("Adopcion de nuevas tecnologias deshabilitada");
			}
			
			if(driver.findElement(By.id("6")).isSelected()){
				System.out.println("Renovacion de maquinaria y equipo seleccionada");
			}
			else{
				System.out.println("Renovacion de maquinaria y equipo deshabilitada");
			}
			if(driver.findElement(By.id("7")).isSelected()){
				System.out.println("Financiamiento seleccionada");
			}
			else{
				System.out.println("Financiamiento deshabilitada");
			}
			if(driver.findElement(By.id("8")).isSelected()){
				System.out.println("Otros seleccionada");
			}
			else{
				System.out.println("Otros deshabilitada");
			}
		}
		else{
			System.out.println("Necesidades ninguna no seleccionada");
		}
		
		Thread.sleep(3000);
		if( driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div/div[2]/div[7]/div/div/div[7]/div/div[2]/multiple-descriptions/div[2]/button")).isDisplayed()){
			System.out.println("Boton agregar presente");
		}else{
			System.out.println("Boton agregar ausente");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(capturapantalla));
		}
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
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
		driver.quit();
	}

}








