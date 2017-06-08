package controladoresServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioJDBC;
import modelo.Usuario;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = -750757728663175846L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servletUsuarioAccion = request.getParameter("servletUsuarioAccion");
		
		switch (servletUsuarioAccion) {
		case "login":
			login(request, response);
			break;
			
		case "logout":
			logout(request, response);
			break;
			
		case "registro":
			registro(request, response);
			break;			

		default:
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// Funciones lógicas
	private void registro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean usuarioRegistrado = false;
		
		String[] contrasenas = request.getParameterValues("contrasena");
		Usuario u = 
			UsuarioJDBC.crearUsuario(
				-1, 
				request.getParameter("usuario"), 
				request.getParameter("contrasena"), 
				request.getParameter("nombres"), 
				request.getParameter("apellidoPaterno"), 
				request.getParameter("apellidoMaterno"));
		
		if (
			contrasenas[0].equals(contrasenas[1]) &&
			!u.getUsuario().equals("") &&
			!u.getContrasena().equals("") &&
			!u.getNombres().equals("") &&
			!u.getApellidoPaterno().equals("") &&
			!u.getApellidoMaterno().equals("")
		) {
			
			usuarioRegistrado = UsuarioJDBC.insertarUsuario(u);
			
		}
		
		HttpSession session = request.getSession();
		
		if (usuarioRegistrado) {
			
			login(request, response);
			
		} else {
			
			session.setAttribute("usuarioRegistrado", new Boolean(usuarioRegistrado));
			session.setAttribute("usuarioRegistradoObj", u);
			
			response.sendRedirect(request.getContextPath() + "/Login/Registro.jsp");
			
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		
		Usuario u = UsuarioJDBC.seleccionarUsuario(usuario, contrasena);
		
		HttpSession session = request.getSession();
		
		if (u != null) {
			session.setAttribute("usuario", u);
			response.sendRedirect(request.getContextPath());
		} else {
			session.setAttribute("loginExitoso", new Boolean(false));
			session.setAttribute("loginUsuario", usuario);
			response.sendRedirect(request.getContextPath() + "/Login/Login.jsp");
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.setAttribute("usuario", null);
		response.sendRedirect(request.getContextPath());
	}
}
