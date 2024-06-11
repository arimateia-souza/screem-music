package com.alura.screenmusic;

import com.alura.screenmusic.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ScreenMusicApplication implements CommandLineRunner {
    @Autowired
    private ArtistaRepository artistaRepository;

    public static void main(String[] args) {
        SpringApplication.run(ScreenMusicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(artistaRepository);
        principal.exibeMenu();

    }
}
