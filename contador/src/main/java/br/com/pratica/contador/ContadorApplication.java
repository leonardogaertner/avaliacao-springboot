package br.com.pratica.contador;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.Tarefa;

@SpringBootApplication
public class ContadorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ContadorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Obtém o recurso tarefa.json do classloader
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("tarefa.json");
        if (inputStream == null) {
            throw new FileNotFoundException("Arquivo tarefa.json não encontrado em src/main/resources");
        }

        // Lê o arquivo e faz o mapeamento
        Tarefa[] tarefas = mapper.readValue(inputStream, Tarefa[].class);

        // Exemplo de saída das tarefas lidas
        for (Tarefa tarefa : tarefas) {
            System.out.println("Título: " + tarefa.getTitulo());
            System.out.println("Descrição: " + tarefa.getDescricao());
        }
    }
}
