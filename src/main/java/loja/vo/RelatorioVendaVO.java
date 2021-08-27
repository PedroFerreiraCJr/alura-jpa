package loja.vo;

import java.time.LocalDate;

public class RelatorioVendaVO {
	private final String nomeProduto;
	private final Long quantidadeVendida;
	private final LocalDate dataUltimaVenda;

	public RelatorioVendaVO(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RelatorioVendaVO [nomeProduto=").append(nomeProduto).append(", quantidadeVendida=")
				.append(quantidadeVendida).append(", dataUltimaVenda=").append(dataUltimaVenda).append("]");
		return builder.toString();
	}
}
