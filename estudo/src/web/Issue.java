package web;

public class Issue {
	
	private int id;
	
	private String projeto; // project/name
	
	private String descricao; //subject
	
	private String versao; // fixed_version

	public Issue(int id, String projeto, String descricao, String versao) {
		this.id = id;
		this.projeto = projeto;
		this.descricao = descricao;
		this.versao = versao;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", projeto=" + projeto + ", descricao=" + descricao + ", versao=" + versao + "]";
	}
	
	

}
