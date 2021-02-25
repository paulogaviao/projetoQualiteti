package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import bean.Arquivo;
import dao.DaoArquivo;



@WebServlet("/salvarArquivo")
public class SalvarArquivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
  /*instacia do dao na servlet para recuperar a lista de arquivos */     
  private DaoArquivo daoArquivo = new DaoArquivo();
   
    public SalvarArquivo() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			/*injeta a lista do dao na nova lista e manda a resposta depois da condição*/
			
			try {
				List<Arquivo> lista = daoArquivo.listaArquivo();

				if (!lista.isEmpty()) {
					
					String listaA = "";
					int totalUser=lista.size() ;
					int index=1;
					for (Arquivo arquivo : lista) {
						
					
						
						listaA+="[" + "\""+arquivo.getId()+"\", \""+arquivo.getTitulo1()+"\", "+"\""+arquivo.getTitulo2()+"\""+ "]";
						
						if(index < totalUser) {
							listaA+=",";
						}
						index++;
					}

					String json = "{" + "\"draw\": 1," + "\"recordsTotal\": "+lista.size()+"," + "\"recordsFiltered\": "+lista.size()+"," + "\"data\":[" +listaA+ "]" + "}";
					response.setStatus(200);
					response.getWriter().write(json);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Arquivo arquivo = new Arquivo();

		String titulo1 = request.getParameter("titulo1");
		String titulo2 = request.getParameter("titulo2");

		System.out.println(titulo1);
		System.out.println(titulo2);
		
		arquivo.setTitulo1(titulo1);
		arquivo.setTitulo2(titulo2);
		
		RequestDispatcher view = request.getRequestDispatcher("index.html");
		view.forward(request, response);
		
	}

}
