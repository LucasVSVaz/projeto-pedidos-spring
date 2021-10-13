package br.com.alura.mudi.dto;

import br.com.alura.mudi.model.Pedido;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class PedidoDto {
    @NotBlank
    private String nomeProduto;
    @NotBlank
    private String urlProduto;
    private BigDecimal valorProduto;
    @NotBlank
    private String urlImagem;
    private String descricao;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getUrlProduto() {
        return urlProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(BigDecimal valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setNomeProduto(this.nomeProduto);
        pedido.setDescricao(this.descricao);
        pedido.setUrlImagem(this.urlImagem);
        pedido.setUrlProduto(this.urlProduto);
        pedido.setValorProduto(this.valorProduto);
        return pedido;
    }
}
