package org.example.pimob;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class PimobApplication {

  @Value("${serverUrl}")
  private String serverUrl;

  @Value("${server.port:8080}")
  private String serverPort;

  public static void main(String[] args) {
    SpringApplication.run(PimobApplication.class, args);

    System.out.println("\n Aplicação rodando na porta 8080\n");
  }

  @EventListener(ApplicationReadyEvent.class)
  public void openSwaggerUI() {
    String swaggerUrl = serverUrl + ":" + serverPort + "/swagger-ui/index.html";

    System.out.println("\n✅ Aplicação rodando na porta " + serverPort);
    System.out.println("📚 Acesse a documentação Swagger em: " + swaggerUrl + "\n");

    try {
      if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(new URI(swaggerUrl));
      } else {
        System.out.println("⚠️ Navegador automático não suportado neste ambiente.");
      }
    } catch (Exception e) {
      System.out.println("❌ Erro ao abrir o navegador: " + e.getMessage());
    }
  }
}
