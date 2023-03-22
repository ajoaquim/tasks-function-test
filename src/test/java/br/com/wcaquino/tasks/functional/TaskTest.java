package br.com.wcaquino.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TaskTest {
	
	@Test
	public void testAmbiente() {
		ChromeOptions chromeOptions = new ChromeOptions();

		chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");

		WebDriver drive = new ChromeDriver(chromeOptions);
		
		//navegar até a pagina desejada
		drive.navigate().to("http://localhost:8001/tasks");
		//clicar no botao ADD TODO
		drive.findElement(By.id("addTodo")).click();
		//teclar descricao da tarefa TASK
		drive.findElement(By.id("task")).sendKeys("Teste de Automacao 2405");
		//teclar data devida DUE DATE
		drive.findElement(By.id("dueDate")).sendKeys("24/05/2023");
		//clicar save
		drive.findElement(By.id("saveButton")).click();
		//validar mensagem de SUCESSO
		WebElement mensagem = drive.findElement(By.id("message"));
		
		//System.out.println(mensagem.getText());
		Assert.assertEquals("Success!", mensagem.getText());
				
		
	}

}
