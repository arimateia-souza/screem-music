package com.alura.screenmusic;

import com.alura.screenmusic.model.Artista;
import com.alura.screenmusic.model.Carreira;
import com.alura.screenmusic.model.Musica;
import com.alura.screenmusic.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository artistaRepository;
    private Scanner teclado = new Scanner(System.in);

    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }


    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = teclado.nextInt();
            teclado.nextLine();//limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
//                case 5:
//                    pesquisarDadosDoArtista();
//                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

//    private void pesquisarDadosDoArtista() {
//    }

    private void buscarMusicasPorArtista() {
        System.out.println("BUSCAR MUSICA POR POR ARTISTA");
        System.out.println("Digite o nome do artista");
        var nome = teclado.nextLine();
        List<Musica> musicas = artistaRepository.buscaMusicaPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void listarMusicas() {
        System.out.println("MUSICAS CADASTRADAS");
        List<Artista> artistas = artistaRepository.findAll();
        artistas.forEach(System.out::println);
    }

    private void cadastrarMusicas() {
        System.out.println("CADASTRO DE MUSICAS");
        System.out.print("Informe o nome do artista que deseja cadastrar uma música: ");
        var nomeArtista = teclado.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if (artista.isPresent()){
            System.out.println("Qual nome da Música que deseja cadastrar para o artista " + artista.get().getNome() + "?");
            var musicaNome = teclado.nextLine();
            Musica musica = new Musica(musicaNome);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            artistaRepository.save(artista.get());
            System.out.println("Musica cadastrada com sucesso!");
        }else {
            System.out.println("Artista não encontrado");
        }

    }

    private void cadastrarArtistas() {
        var novocadastro = "s";
        while(novocadastro.equalsIgnoreCase("s")){
            System.out.println("CADASTRO DE ARTISTA");
            try{
                System.out.print("Informe o nome do artista: ");
                var nome = teclado.nextLine();
                System.out.print("Qual a carreira do artista? (solo, dupla, banda): ");
                var carreira = teclado.nextLine();
                Carreira carreiraArtista = Carreira.valueOf(carreira.toUpperCase());
                Artista artista = new Artista(nome, carreiraArtista);
                artistaRepository.save(artista);
                System.out.print("Deseja cadastrar um(a) novo artista? (S/N) ");
                novocadastro = teclado.nextLine();

            }catch (DataIntegrityViolationException e){
                System.out.println("Erro: Já existe um cantor cadastrado com este nome");
            }
        }

    }
}
