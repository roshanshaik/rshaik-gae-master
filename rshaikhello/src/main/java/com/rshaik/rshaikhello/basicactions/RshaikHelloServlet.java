
package com.rshaik.rshaikhello.basicactions;

import com.rshaik.rshaikhello.daos.AuditDao;
import com.rshaik.rshaikhello.daos.DatastoreDao;
import com.rshaik.rshaikhello.objects.Audit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 
 * 
 * 
 * @author rshaik
 *
 */

@SuppressWarnings("serial")
public class RshaikHelloServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(RshaikHelloServlet.class.getName());
	// [START setup]
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {

		logger.log(Level.INFO, "doGet Request is", req);

		req.getRequestDispatcher("/index.jsp").forward(req, resp);


	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {


		logger.log(Level.INFO, "doPost Request is"+ req);


		String output = null;
		String nameFromService = null;
		// do POST
		try{
			Client client = Client.create();

			String name = req.getParameter("name");
			logger.log(Level.INFO, "name is ...." + name);
			String postUrl = "http://rshaik-hello.appspot.com/api/hello?name="+java.net.URLEncoder.encode(name, "UTF-8");

			WebResource webResource = client.resource(postUrl);

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}


			output = response.getEntity(String.class);
			logger.log(Level.INFO, "Output from Service ...." + output);
			
			JSONObject jsonObj = new JSONObject(output);
			nameFromService = (String) jsonObj.get("name");

		} catch (Exception e) {

			logger.log(Level.SEVERE, "Output from Service ...." + getStackTrace(e));

		}

		req.setAttribute("action", "Add");          // Part of the Header in form.jsp
		req.setAttribute("destination", "create");  // The urlPattern to invoke (this Servlet)
		req.setAttribute("page", "form");           // Tells base.jsp to include form.jsp
		req.setAttribute("output", output); 
		req.setAttribute("name", nameFromService); 

		logger.log(Level.INFO, "doPost Request is" + req);

		String remoteHost = req.getRemoteHost();
		String remoteAddr = req.getRemoteAddr();
		int remotePort = req.getRemotePort();
		String clientAddr = remoteHost + " (" + remoteAddr + ":" + remotePort + ")";

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateStr = dateFormat.format(cal.getTime());

		Audit audit = new Audit.Builder()
		.json(output)   
		.createdDate(dateStr)
		.ipAddress(clientAddr)
		.build();


		AuditDao dao = new DatastoreDao();
		try {
			Long id = dao.createAudit(audit);
			logger.log(Level.INFO, "Created audit " + audit);

		} catch (Exception e) {
			throw new ServletException("Error creating audit", e);
		}

		req.getRequestDispatcher("/index.jsp").forward(req, resp);

	}


	private static String getStackTrace(Exception ex) {
		StringBuffer sb = new StringBuffer(500);
		StackTraceElement[] st = ex.getStackTrace();
		sb.append(ex.getClass().getName() + ": " + ex.getMessage() + "\n");
		for (int i = 0; i < st.length; i++) {
			sb.append("\t at " + st[i].toString() + "\n");
		}
		return sb.toString();
	}
}

