package br.com.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TaskTest {

	public WebDriver acessaAplicacao() throws MalformedURLException {
		ChromeOptions option = new ChromeOptions();

		/*
		 * option.addArguments("--test-type");
		 * option.addArguments("--disable-popup-bloacking");
		 * option.addArguments("--incognito");
		 */


		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(ChromeOptions.CAPABILITY, option);
		//WebDriver driver = new ChromeDriver(option);

		//System.setProperty('WebDriver.chrome.driver', 'D:\\SeleniumSoftwaresReq\\chromedriver.exe');
		WebDriver drive = new RemoteWebDriver(new URL(" http://192.168.0.58:4500/wd/hub"), cap);
		return drive;

	}








	@Test
	public void gravarTaskSeCorreta() throws MalformedURLException {
		WebDriver drive = acessaAplicacao();


		//navegar at� a pagina desejada
		drive.navigate().to("http://localhost:8001/tasks");

		//clicar no botao ADD TODO
		drive.findElement(By.id("addTodo")).click();
		//teclar de/scricao da tarefa TASK
		drive.findElement(By.id("task")).sendKeys("Teste de Automacao 2405");
		//teclar data devida DUE DATE
		drive.findElement(By.id("dueDate")).sendKeys("24/05/2023");
		//clicar save
		drive.findElement(By.id("saveButton")).click();
		//validar mensagem de SUCESSO
		WebElement mensagem = drive.findElement(By.id("message"));

		//System.out.println(mensagem.getText());
		Assert.assertEquals("Sucess!", mensagem.getText());

		drive.quit();

	}
	@Test
	public void naoGravarTaskSeDataPassada() throws MalformedURLException {

		WebDriver drive = acessaAplicacao();


		//navegar at� a pagina desejada
		drive.navigate().to("http://localhost:8001/tasks");

		//clicar no botao ADD TODO
		drive.findElement(By.id("addTodo")).click();
		//teclar de/scricao da tarefa TASK
		drive.findElement(By.id("task")).sendKeys("Teste de Automacao 2405");
		//teclar data devida DUE DATE
		drive.findElement(By.id("dueDate")).sendKeys("24/05/2021");
		//clicar save
		drive.findElement(By.id("saveButton")).click();
		//validar mensagem de SUCESSO
		WebElement mensagem = drive.findElement(By.id("message"));

		//System.out.println(mensagem.getText());
		Assert.assertEquals("Due date must not be in past", mensagem.getText());

		drive.quit();

	}
	@Test
	public void naoGravarTaskSemDescricao() throws MalformedURLException {

		WebDriver drive = acessaAplicacao();


		//navegar at� a pagina desejada
		drive.navigate().to("http://localhost:8001/tasks");

		//clicar no botao ADD TODO
		drive.findElement(By.id("addTodo")).click();
		//teclar de/scricao da tarefa TASK
		drive.findElement(By.id("task")).sendKeys("");
		//teclar data devida DUE DATE
		drive.findElement(By.id("dueDate")).sendKeys("24/05/2021");
		//clicar save
		drive.findElement(By.id("saveButton")).click();
		//validar mensagem de SUCESSO
		WebElement mensagem = drive.findElement(By.id("message"));

		//System.out.println(mensagem.getText());
		Assert.assertEquals("Fill the task description", mensagem.getText());

		drive.quit();

	}
	@Test
	public void naoGravarTaskSemData() throws MalformedURLException {

		WebDriver drive = acessaAplicacao();


		//navegar at� a pagina desejada
		drive.navigate().to("http://localhost:8001/tasks");

		try {
		//clicar no botao ADD TODO
		drive.findElement(By.id("addTodo")).click();
		//teclar de/scricao da tarefa TASK
		drive.findElement(By.id("task")).sendKeys("tasks sem data ");
		//teclar data devida DUE DATE
		drive.findElement(By.id("dueDate")).sendKeys("");
		//clicar save
		drive.findElement(By.id("saveButton")).click();
		//validar mensagem de SUCESSO
		WebElement mensagem = drive.findElement(By.id("message"));

		//System.out.println(mensagem.getText());
		Assert.assertEquals("Fill the due date", mensagem.getText());
		} finally {
		drive.quit();
		}
	}

	
}
