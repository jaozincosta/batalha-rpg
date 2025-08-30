package br.senac.sp.batalha;

public class Jogador {
    private String nome;
    private int vidas;
    private int ataque;
    private int defesa;

    public Jogador(String nome, int vida, int ataque, int defesa){
        this.nome = nome;
        this.vidas = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vidas;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public String atacar(Jogador inimigo){
        int chance = (int) (Math.random() * 100);
        int danoBase = this.ataque - inimigo.getDefesa();
        if (chance < 10) {
            danoBase = (this.ataque * 2) - inimigo.getDefesa();
        }
        danoBase = Math.max(1, danoBase);

        inimigo.receberDano(danoBase);
        return (chance < 10 ? "ATAQUE CRITICO! " : "") +
                this.nome + " atacou " + inimigo.getNome() + " e causou " + danoBase + " de dano";
    }

    public void receberDano(int dano) {
        this.vidas -= dano;
        if (this.vidas < 0){
            this.vidas = 0;
        }
    }
}