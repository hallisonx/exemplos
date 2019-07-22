package web;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetRequest {

	public static void main(String[] args) {
		try {
			
			search("07110058425");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void search(String cpf){
		Document doc;
		try {
			doc = Jsoup.connect("http://200.164.109.51:8080/BBDT_HABILITACAO_CLIENTE/Habilitacao?opcao=17&&action=C&cpf="+cpf).get();
			Elements newsHeadlines = doc.select("font");
			if(newsHeadlines.size() > 4) {
				Element elementoSelecionado = newsHeadlines.get(4);
				if(elementoSelecionado.toString().length() > 29) {
					String nome = elementoSelecionado.toString().substring(29, elementoSelecionado.toString().length());
					System.out.println(nome.replace(" </font>", ""));
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
