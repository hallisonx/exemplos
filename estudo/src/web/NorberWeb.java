/** @author Raimundo Vasconcelos de Oliveira */
package web;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NorberWeb {

	public static void main(String[] args) {
		//registrar("656351", "123456", "http://52.71.95.168/webpontoindra");
		
		if(args.length >= 2) {
			if(args[2] != null) {
				System.out.println(args.length+" params: "+args[0] +" - "+ args[1] +" - "+ args[2]);
				//registrar(args[0], args[1], args[2]);
			}else {
				System.out.println(args.length+" params: "+args[0] +" - "+ args[1]);
				//registrar(args[0], args[1], "http://52.71.95.168/webpontoindra");
			}
		}else {
			System.out.println("O sistema requer ao menos 2 parametros ([matricula] [senha] [host<opcional>])");
		}
		
	}

	public static void registrar(String matricula, String senha, String host) {
		
		Document doc;
		boolean valendo = true; 

		String urlLogin = host + "/default.asp";
		String urlPonto = host + "/just_user/IncluirMarcacaoOnLine.asp";
		String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0";

		try {
			
			/** Fazendo o login */
			Response res = Jsoup.connect(urlLogin)
					.method(Method.POST)
					.requestBody("Default__=&CodEmpresa=1&requiredempresa=1&requiredusuario="+matricula.trim()+"&requiredsenha="+senha.trim()+"&Submit=++OK++&senhacrip=%3A%2Cs%2Co%2Ck%2Cr%2C6%2Cs%2Cn%2C4%2Ce%2Ci%2Cd%2Cy%2Cc%2CW%2C1%2C9%2Cb%2C%3D%2CI%2CR%2Cf%2C%3F%2C%3F%2C%5C%2Cg%2Cr%2Cy%2Ct%2CC%2CV%2Cn%2Cj%2Ci%2CH%2CQ%2C%60%2CW%2C2%2CK%2Cg%2Cy%2CJ%2Cp%2Cu%2Cs%2Ce%2C%3C%2Cs%2C%40%2CJ%2C%5D%2Ca%2Cu%2C%40%2Cc%2CV%2CE%2CU%2C%40%2CY%2C1%2Ch%2C2%2CW%2C%3B%2CY%2C%5C%2Ca%2C%5E%2C%40%2C%5C%2CJ%2CV%2CM%2Cx%2C3%2CV%2C4%2C7%2CK%2Cf%2CK%2C%3C%2CC%2Cs%2C_%2CY%2CU%2Cj%2Cg%2CS%2Cf%2CF%2CF%2Cz%2Cn%2Cv%2Ci%2C7%2Cn%2Cu%2CE%2CV%2Ct%2C1%2C%3E%2C1%2CK%2CR%2C7%2Cz%2Cd%2CP%2CF%2Co%2Cv%2Cq%2Cu%2Cx%2C5%2CR%2Cu%2Cn%2CA%2CB%2Cs%2CE%2C6%2C%3B%2Cq%2Cu%2CG%2Cx%2Cd%2CR%2C%5C%2Cl%2CT%2CV%2C5%2CZ%2Cp%2Cc%2CL%2C%5E%2Cx%2Cb%2C7%2Ci%2C%5C%2C%60%2C8%2C%5C%2Ca%2Cv%2Cp%2Ck%2Ck%2Ce%2Cf%2Cl%2C8%2Ch%2CC%2CN%2CV%2CT%2C8%2C%3F%2CT%2Cm%2CH%2Cn%2CQ%2Ci%2C_%2Ct%2Cp%2Cc%2C6%2Cm%2Cy%2CZ%2C%5B%2Cg%2CC%2C_%2Cr%2C%5C%2Ce%2Cx%2Cd%2C%5E%2Cx%2CF%2Cm%2Cn%2CJ%2CY%2C%3D%2CX%2Cl%2CB%2CS%2CP%2C1%2Cr%2Ck%2Cj%2CT%2CL%2CW%2CZ%2Cg%2Ct%2Cg%2Cq%2CY%2Cw%2Ca%2Cf%2Cx%2Cy%2Cw%2CF%2Cm%2CL%2Cv%2CP%2Ca%2Ct%2CR%2CR%2Cm%2CT%2C7%2Cg%2CB%2CK%2CX%2CS%2Cn%2Cd%2CZ%2CZ%2C%3B%2CI%2Cs%2CU%2C%5C%2CN%2Cv%2Cl%2C%60%2Cv%2CP%2CG%2CX%2Cs%2Cq%2C%3D%2CS%2C%40%2C_%2CW%2CV%2Cr%2CW%2CW%2CP%2Cu%2Ct%2CS%2CZ%2Cs%2CN%2Ca%2Cb%2Cn%2CW%2CW%2CB%2CP%2Cx%2Cl%2CX%2CW%2Cq%2Cb%2Cs%2Ca%2Cx%2CM%2C1%2Cs%2C4%2CA%2Cw%2C%3C%2CS%2C%3C%2C5%2CR%2CZ%2C%3B%2CE%2Cl%2Ct%2Cu%2C8%2C%5C%2C4%2Cv%2Ci%2Cg%2Cr%2Ck%2CL%2Ct%2Cw%2C%3F%2CN%2CP%2Cu%2C%3F%2CM%2C%5D%2CK%2CX%2C%3C%2Cq%2Cv%2CC%2C4%2Cf%2Cd%2C1%2Cu%2C_%2CF%2C4%2CQ%2Ca%2Ch%2CR%2Ce%2CK%2Cr%2CT%2Co")
					.userAgent(userAgent)
					.execute();

			System.out.println("HTTP CODE: " + res.statusCode());
			
			if(res.statusCode() >= 200 && res.statusCode() < 300 ) {
				
				Map<String, String> loginCookies = res.cookies();
				
				/** Acessando a pagina de marcação do ponto */
				doc = acessarTelaMarcacao(urlPonto, userAgent, loginCookies);
				
				String codSeguranca = doc.getElementById("txt_CodSeguranca").attr("value").toString();
				String nome = doc.getElementsByAttribute("name").get(1).attr("value");
				String data = doc.getElementsByAttribute("name").get(4).attr("value");
				String hora = doc.getElementsByAttribute("name").get(5).attr("value");
				
				if(codSeguranca!=null && nome!=null && data!=null && hora!=null && valendo) {
					
					registrarPonto(urlPonto, userAgent, loginCookies, codSeguranca, nome, data, hora);
					
					
				}else {
					System.out.println("Alguma informação está nula ou o parâmetro de testes está ativado.");
				}
				
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void registrarPonto(String urlPonto, String userAgent, Map<String, String> loginCookies,
			String codSeguranca, String nome, String data, String hora) throws IOException {
		Document doc = Jsoup.connect(urlPonto)
				.ignoreContentType(true) 
				.userAgent(userAgent)
				.requestBody(
						 "txtNome="+nome.replace(" ", "+").trim()
						+"&txt_OpcAltera=F"
						+"&txt_CodSeguranca="+codSeguranca.trim()
						+"&data="+data.replace("/", "%2F")
						+"&hora="+hora.replace(":", "%3A")
						+"&Gravado=1")
				.cookies(loginCookies)
				.post();
		//Marcação Incluida com Sucesso
		System.out.println(doc.toString());
	}

	private static Document acessarTelaMarcacao(String urlPonto, String userAgent, Map<String, String> loginCookies)
			throws IOException {
		Document doc;
		doc = Jsoup.connect(urlPonto)
				.ignoreContentType(true)
				.cookies(loginCookies)
				.userAgent(userAgent)
				.get();
		return doc;
	}
}

/*
 * POST http://52.71.95.168/webpontoindra/just_user/IncluirMarcacaoOnLine.asp
 * 
 * Host: 52.71.95.168 
 * User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0 
 * //Accept-Language:
 * pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3 //Accept-Encoding: gzip, deflate
 * Content-Type: application/x-www-form-urlencoded Content-Length: 153 DNT: 1
 * Connection: keep-alive Referer:
 * http://52.71.95.168/webpontoindra/just_user/IncluirMarcacaoOnLine.asp Cookie:
 * cCodEmp=CodEmp=1; cUsuario=Usuario=656351;
 * ASPSESSIONIDAATBCRCQ=AKBEFMNDGLPFKDHEONINFHLK;
 * ASPSESSIONIDAAQAASCR=OLPLAAECNIBIGHNIDLAMIELK Upgrade-Insecure-Requests: 1
 * 
 * Request Body:
 * txtNome=HALLISON+DE+CARVALHO+FONSECA&txt_OpcAltera=F&txt_CodSeguranca=
 * B4E011D38201A3B0BBF2AE047879DD69BCD9358D&data=16%2F07%2F2019&hora=14%3A53&
 * Gravado=1
 * 
 */

/*
 * 
 * <input type="text" name="txtNome" size="75" maxlength="30"
 * onfocus="this.blur()" readonly class="Campo"
 * value="HALLISON DE CARVALHO FONSECA"> <input type="hidden"
 * name="txt_OpcAltera" value="F" id="txt_OpcAltera"> <input type="hidden"
 * name="txt_CodSeguranca" value="A0DBF74F7D9668003AA3ECC69F41BECA8F1DE648"
 * id="txt_CodSeguranca"> <input id="data" type="text" maxlength="10" size="13"
 * name="data" class="CampoCentro" onfocus="this.blur()" readonly
 * value="16/07/2019"> <input type="text" name="hora" size="8"
 * onfocus="this.blur()" readonly maxlength="5" class="CampoCentro"
 * value="15:15"> <input id="Button1" class="BotaoAchatado"
 * onclick="javascript: gravaMarcacao();" type="button" value="       OK       "
 * name="Submit"> <input id="Button2" class="BotaoAchatado"
 * onclick="javascript: window.close()" type="button" value="   Fechar   "
 * name="Submit2"> <input type="hidden" name="Gravado" value="0">
 * 
 */
