package admin;

import java.util.concurrent.TimeUnit; 

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TableroProcesos {
	
	public WebDriver driver;

	@Test(priority = 1)
	@Parameters({"usuario","contrasena"})
	public void iniciarSesion(String usuario, String contrasena){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  
		driver.findElement(By.id("email")).sendKeys(usuario);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasena);
		driver.findElement(By.name("rememberMe")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Tablero de procesos")).click();
		}
		
	//Verificar que un usuario con rol de administrador pueda agregar y limpiar un proceso.
	@Test(priority = 2)
	public void limpiarProceso() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Agregar proceso"))).click();
	    driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("PRUEBA AUTOMATIZADA");
	    driver.findElement(By.xpath("//textarea[@type='text']")).sendKeys("Proceso prueba");
	    driver.findElement(By.xpath("//input[@type='number']")).sendKeys("1");
	    //Activo
	    driver.findElement(By.cssSelector("div.col-md-3 > input.ng-pristine.ng-valid")).click();
	    driver.findElement(By.name("onDemand")).click();
	    //Script de tarea
	    driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[3]/div[3]/div/div[1]/div/div[6]")).click();
	    driver.findElement(By.xpath("//div[3]/div/div/div/div/textarea")).sendKeys("import mx.gob.inadem.*");
	    driver.findElement(By.xpath("//div[3]/div/div/div/div/textarea")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[3]/div/div/div/div/textarea")).sendKeys("def usuarios = ['patriciajicz@gmail.com']");
	    driver.findElement(By.xpath("//div[3]/div/div/div/div/textarea")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[3]/div/div/div/div/textarea")).sendKeys("		 usuarios.each{");
	    driver.findElement(By.xpath("//div[3]/div/div/div/div/textarea")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[3]/div/div/div/div/textarea")).sendKeys("		  paramsList.push([user: it])  }");

	    //Script de funcionalidad
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[3]/div[3]/div/div[2]/div/div[6]"))).click();
	    driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[3]/div[3]/div/div[2]/div/div[1]/textarea")).sendKeys("import mx.gob.inadem.*");
	    //Limpiar
	    driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[3]/div[4]/div/div/button[1]")).click();
	    driver.navigate().refresh();
		}
	
	//Verificar que  un usuario con rol de administrador pueda buscar un proceso.
	@Test(priority = 3)
	public void buscarProceso() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    //Buscar
		driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[2]/div[1]/div/div[1]/div/input")).click();
	    driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[2]/div[1]/div/div[1]/div/input")).sendKeys("PRUEBA");
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='button'])[2]"))).click();
		}
	
	//Verificar que  un usuario con rol de administrador pueda actualizar un proceso.
	@Test(priority = 4)
	public void actualizarProceso(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Actualizar
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div/div[2]/div/div[2]/div[1]/div/div[2]/div/div/button[2]/span"))).click();
	}
	
	//Verificar que un usuario con rol de administrador pueda ejecutar un proceso en el tablero de procesos.
	@Test(priority = 5)
	public void ejecutarProceso(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Ejecutar
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[8]/a[1]"))).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='confirmationDialog']/div/div/div[3]/button[2]"))).click();
	}
			
	@Test(priority = 6)
	public void cancelarProceso(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Cancelar
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[1]/div[1]/div[2]/div/div/div/div/ul/li[2]/a"))).click();
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[3]/div[4]/div/div/button[2]")).click();
	}
	
	//Verificar que un usuario con rol de administrardor pueda agregar y cancelar un proceso.
	@Test(priority = 7)
	public void eventosProceso(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Eventos
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[9]/a"))).click();
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[4]/div/div/table/tbody/tr[1]/td[1]/a")).click();
		//Buscar
		Select estatus = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div/div[2]/div/div[4]/div[1]/div/div/div/div/table/tbody/tr/td[2]/select"))));
		estatus.selectByValue("0");
		driver.findElement(By.cssSelector("button.btn")).click();
	    //Limpiar
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[2]"))).click();
	    driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[4]/div[2]/div/a[1]")).click();
	    //Console log
	    driver.findElement(By.linkText("95")).click();
	    driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[4]/div[1]/div/div/div/div/table/tbody/tr/td[2]/select")).click();
	    Select estatusConsole = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div/div[2]/div/div[4]/div[1]/div/div/div/div/table/tbody/tr/td[2]/select"))));
	    estatusConsole.selectByValue("0");
	    driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[4]/div[1]/div/div/div/div/table/tbody/tr/td[2]/button[1]")).click();			    
	    driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[4]/div[1]/div/div/div/div/table/tbody/tr/td[2]/button[2]")).click();
	    driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[4]/div[2]/div/a[2]")).click();
		}
		
	//Verificar que un usuario con rol de administrador pueda consultar un evento de la tabla de procesos.
	@Test(priority = 8)
	public void migasProceso(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Migas
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[1]/div[1]/div[1]/ol/li[1]/a"))).click();
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
	public void afterTest(){
		driver.quit();	
	}
}
