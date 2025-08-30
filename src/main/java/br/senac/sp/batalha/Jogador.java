package br.senac.sp.batalha;

public class Jogador {
    private String nome;
    private int vidas;
    private int ataque;
    private int defesa;

    public Jogador(String nome, int vida, int ataque, int defesa){
        this.nome = nome;
        this.vidas = vidas;
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
        int ataqueCritico = (int) (Math.random() * 100);
        int danoBase = this.ataque - inimigo.getDefesa();
        if (danoBase < 0){
            danoBase = 0;
        }

        if(ataqueCritico < 10){
            danoBase *= 2;
            inimigo.receberDano(danoBase);
            return "ATAQUE CRITICO! " + this.nome + " atacou" + inimigo.getNome() + " e causou " + danoBase + " de dano";
        } else {
            inimigo.receberDano(danoBase);
            return this.nome + " atacou " + inimigo.getNome() + " e causou" + danoBase + " de dano";
        }
    }

    public void receberDano(int dano) {
        this.vidas -= dano;
        if (this.vidas < 0){
            this.vidas = 0;
        }
    }

}
