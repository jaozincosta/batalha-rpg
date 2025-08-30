package br.senac.sp.batalha;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BatalhaController {

    // declarar um atributo jogador
    private Jogador jogador;
    // declarar um atributo inimigo
    private Jogador inimigo;

    @GetMapping
    public String index() {
        // apagar a instancia do jogador
        jogador = null;
        // apagar a instancia do inimigo
        inimigo = null;
        return "index";
    }

    @PostMapping("jogador")
    public String criarJogador(String nome, int vida, int ataque, int defesa, Model model) {
        // instanciar o jogador com os parâmetros do método
        jogador = new Jogador(nome, vida, ataque, defesa);
        return "redirect:batalha";
    }

    @GetMapping("batalha")
    public String batalha(Model model) {
        if (jogador == null) return "redirect:/";
        if (inimigo == null) inimigo = randomJogador();

        model.addAttribute("jogador", jogador);
        model.addAttribute("inimigo", inimigo);

        return "batalha";
    }

    @PostMapping("turno")
    public String turno(Model model) {
        // model.addAttribute("jogador", jogador);
        model.addAttribute("jogador", jogador);
        return "batalha";
    }

    @PostMapping("batalha")
    public String batalha(RedirectAttributes redirect){
        String msg = "";
        String jogador1 = jogador.atacar(inimigo);
        String inimigo1 = inimigo.atacar(jogador);
        msg = jogador1 + " | " + inimigo1;

        redirect.addFlashAttribute("msg", msg);
        return "redirect:batalha";
    }

    private Jogador randomJogador() {
        String[] nomes = {"Amanda", "Antonio", "Eunice", "Pieter"};
        int vida = (int) (Math.random() * 50) + 1;
        int ataque = (int) (Math.random() * 20) + 1;
        int defesa = (int) (Math.random() * 10) + 1;
        int i = (int) (Math.random() * nomes.length);
        return new Jogador(nomes[i], vida, ataque, defesa);
    }
}
