package admin;

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

public class UsuariosInternos {
	public WebDriver driver;
	
	@Test(priority = 1)
	@Parameters({"usuario","contrasena"})
	public void iniciarSesion(String usuario,String contrasena){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		driver.findElement(By.id("email")).sendKeys(usuario);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys(contrasena);
		driver.findElement(By.name("rememberMe")).click();
		driver.findElement(By.xpath("//*[@id='loginForm']/div[4]/div/button")).click();
	}
	
	//Administracion: Crear usuario interno
	@Test(priority = 2)
	public void crearUsuario(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.findElement(By.linkText("Usuarios internos")).click();
	    driver.findElement(By.linkText("Agregar nuevo usuario")).click();
	    driver.findElement(By.name("rfc")).sendKeys("CUPU800825570");
	    driver.findElement(By.name("username")).sendKeys("usuariointerno@test.com");
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("Gerardo");
	    driver.findElement(By.name("lastName")).clear();
	    driver.findElement(By.name("lastName")).sendKeys("Godinez");
	    driver.findElement(By.name("secondLastName")).clear();
	    driver.findElement(By.name("secondLastName")).sendKeys("Godinez");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("123456");
	    driver.findElement(By.name("confirmPassword")).clear();
	    driver.findElement(By.name("confirmPassword")).sendKeys("123456");
	    driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[2]/div[2]/div/form/div[5]/div/div/button"));
		driver.navigate().back();
	}
	
	//Verificar que funcionen las migas en la vista de usuario interno cuando se ingresa como administrador.
	@Test(priority = 2)
	public void migasUsuario(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[1]/div[1]/div[1]/ol/li[2]/a"))).click();
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
