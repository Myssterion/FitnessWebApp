package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.MessageService;

public class FileUploadServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ServletContext context = getServletContext();
	    MessageService messageService = (MessageService) context.getAttribute("messageService");

	    // Check if the request is multipart (file upload)
	    if (ServletFileUpload.isMultipartContent(request)) {
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);

	        try {
	            
	            List<FileItem> items = upload.parseRequest(request);

	            
	            String messageContent = "";
	            InputStream fileContent = null;
	            String fileName = null;
	            String contentType = null;
	            int userId = 0;

	            // Process each item
	            for (FileItem item : items) {
	                if (item.isFormField()) {
	                	String fieldName = item.getFieldName();
	                    String fieldValue = item.getString();

	                    if (fieldName.equals("id")) {
	                        userId = Integer.parseInt(fieldValue); 
	                    } else if (fieldName.equals("message")) {
	                        messageContent = fieldValue; 
	                    }
	                } else {
	                    // Process file upload
	                    fileContent = item.getInputStream();
	                    fileName = item.getName();
	                    contentType = item.getContentType();
	                }
	            }

	            
	            messageService.sendMessage(userId, messageContent, fileContent, fileName, contentType);
	            messageService.loadMessages();
	           
	            response.sendRedirect("inbox.jsp");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendRedirect("error.jsp");
	        }
	    } else {
	   
	        String messageContent = request.getParameter("message");
	        int userId = Integer.parseInt(request.getParameter("userId")); 
	        messageService.sendMessage(userId, messageContent, null, null, null);
	        messageService.loadMessages();
	        
	        response.sendRedirect("inbox.jsp");
	    }
	}
}
