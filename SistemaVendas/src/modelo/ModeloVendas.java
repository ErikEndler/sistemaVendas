package modelo;

public class ModeloVendas {
	private int id;
	private double valor;
	private double desconto;
	private double valorLiquido;
	private String clienteVenda;
	private String produtoVenda;
	private String tipoPagamento;
	private String obs;
	private String data;
	private int venda_fk_cliente;
	private int venda_fk_produto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public void calculoValorLiquido() {
		this.valorLiquido = valor-desconto;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	public String getClienteVenda() {
		return clienteVenda;
	}
	public void setClienteVenda(String clienteVenda) {
		this.clienteVenda = clienteVenda;
	}
	public String getProdutoVenda() {
		return produtoVenda;
	}
	public void setProdutoVenda(String produtoVenda) {
		this.produtoVenda = produtoVenda;
	}
	public int getVenda_fk_cliente() {
		return venda_fk_cliente;
	}
	public void setVenda_fk_cliente(int venda_fk_cliente) {
		this.venda_fk_cliente = venda_fk_cliente;
	}
	public int getVenda_fk_produto() {
		return venda_fk_produto;
	}
	public void setVenda_fk_produto(int venda_fk_produto) {
		this.venda_fk_produto = venda_fk_produto;
	}
	

}
