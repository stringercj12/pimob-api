package org.example.pimob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PimobApplication {

  public static void main(String[] args) {
    SpringApplication.run(PimobApplication.class, args);

    System.out.println("\n Aplicação rodando na porta 8080\n");
  }

}
