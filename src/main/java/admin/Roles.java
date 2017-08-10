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

public class Roles {
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
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[1]/div[1]/div[2]/div/div/div/div/ul/li[2]/a[2]")).click();
	}
	
	//Verificar que un usuario con el rol de administrador pueda crear un rol.
	@Test(priority = 2)
	public void crearRol(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/main/div/div[2]/div/div[2]/div/div/div/div[1]/div[1]/div[1]/div/div[2]/a[3]"))).click();
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[2]/div/div[2]/a")).click();
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[2]/div/form/div[1]/input")).sendKeys("Prueba");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[2]/div/form/div[2]/input")).sendKeys("prueba");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div/div[2]/div/form/a")).click();
		driver.navigate().back();
		driver.navigate().back();
	}
	
	//Verificar que un usuario con rol de administrador pueda editar un rol de la lista de roles.
	@Test(priority = 3)
	public void editarRol(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[3]/div/div/table/tbody/tr[1]/td[4]/a")).click();
		//Actualizar
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[1]/div[2]/div/form/div[1]/div/input")).sendKeys("Test");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[1]/div[2]/div/form/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[1]/div[2]/div/form/div[2]/div/input")).sendKeys("test");
		//Permiso
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[1]/div[2]/div/form/div[4]/div/input")).sendKeys("apiProvider:saveQuestionary");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[1]/div[2]/div/form/div[5]/div/button")).click();
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[1]/div[2]/div/form/div[4]/div/input")).sendKeys("apiCatalog:getStratifications");
		driver.findElement(By.xpath("html/body/main/div/div[2]/div/div[1]/div[2]/div/form/div[5]/div/button")).click();
		//Eliminar
		driver.findElement(By.cssSelector("body > main > div > div:nth-child(2) > div > div:nth-child(2) > div > div > table > tbody > tr > td:nth-child(2) > button")).click();
		driver.navigate().back();
	}
	
	//Verificar que un usuario con rol de administrador pueda eliminar un rol de la lista de roles.
	@Test(priority = 4)
	public void eliminarRol(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("/html/body/main/div/div[2]/div/div[3]/div/div/table/tbody/tr[1]/td[4]/button")).click();
		driver.navigate().back();
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


