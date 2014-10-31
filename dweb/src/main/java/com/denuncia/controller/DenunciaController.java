package com.denuncia.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.denuncia.model.Categoria;
import com.denuncia.model.Denuncia;
import com.denuncia.model.LocalizacaoDenuncia;
import com.denuncia.model.vo.DenunciaVo;
import com.denuncia.service.DenunciaService;


@Controller
@RequestMapping("/denuncia")

public class DenunciaController {

	@Autowired
	private DenunciaService denunciaService;

	private Logger logger = org.slf4j.LoggerFactory.getLogger(CategoriaController.class);

	private static final String BASE_DIRECTORY = "/Users/augusto/Pictures";

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	//	@RequestMapping(method = RequestMethod.GET)
	//	public @ResponseBody List<DenunciaVo> getAllByCategoria(@RequestParam( value = "type", required = false ) String type) {   
	//		
	//		List<DenunciaVo> denuncias = null;   
	////		logger.info(categorias.toString());
	//		return denuncias; 
	//	}




	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK )
	@ResponseBody
	public Long save(@RequestBody DenunciaVo denunciaVo) {

		Denuncia denuncia = new Denuncia();
		denuncia.setDescricao(denunciaVo.getDescricao());
		if (denunciaVo.getIdcategoria()!=null){
			denuncia.setCategoria(denunciaService.load(Categoria.class, denunciaVo.getIdcategoria()));
		}

		if(denunciaVo.getLocalizacao()!=null){
			LocalizacaoDenuncia localizacao = new LocalizacaoDenuncia(denunciaVo.getLocalizacao());
			denunciaService.save(localizacao);
			denuncia.setLocalizacao(localizacao);
		}
		denunciaService.save(denuncia);

		//		Categoria categoria = new Categoria();
		//		categoria.setId(categoriaVo.getId());
		//		categoria.setNome(categoriaVo.getNome());
		//		categoria.setDescricao(categoriaVo.getDescricao());
		//		if (categoriaVo.getIdpai()!=null){
		//			categoria.setPai(categoriaService.load(Categoria.class, categoriaVo.getIdpai()));
		//		}
		//		categoriaService.save(categoria);

		return denuncia.getId();
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public String saveImage(HttpServletRequest request, HttpServletResponse response) {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		// check if the http request is a multipart request
		// with other words check that the http request can have uploaded files
		if (isMultipart) {

			//  Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			//  Create a new file upload handler
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

			// Set upload parameters
			// See Apache Commons FileUpload for more information
			// http://jakarta.apache.org/commons/fileupload/using.html
			servletFileUpload.setSizeMax(-1);

			try {

				String directory = "";

				// Parse the request
				List items = servletFileUpload.parseRequest(request);

				// Process the uploaded items
				Iterator iter = items.iterator();

				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					// the param tag directory is sent as a request parameter to
					// the server
					// check if the upload directory is available
					if (item.isFormField()) {

						String name = item.getFieldName();

						if (name.equalsIgnoreCase("directory")) {

							directory = item.getString();
						}

						// retrieve the files
					} else {

						// the fileNames are urlencoded
						String fileName = URLDecoder.decode(item.getName());

						File file = new File(directory, fileName+".jpeg");
						file = new File(BASE_DIRECTORY, file.getPath());

						// retrieve the parent file for creating the directories
						File parentFile = file.getParentFile();

						if (parentFile != null) {
							parentFile.mkdirs();
						}

						// writes the file to the filesystem
						item.write(file);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

			response.setStatus(HttpServletResponse.SC_OK);

		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return "success";
	}




	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {

			System.out.println("File name:"+ file.getOriginalFilename());
			//byte[] bytes = file.getBytes();
			System.out.println("Content type:"+ file.getContentType());
			String [] contentType = file.getContentType().split("/");
			String fileType = contentType[contentType.length-1];
			System.out.println("File type:"+ fileType);
			System.out.println("File size:"+ (file.getSize()/1024) + "KB");

			String path = "/resources/images";// .getRealPath( "/resources/images/");
			System.out.println("Path: " + path);



			InputStream inputStream = null;
			OutputStream outputStream = null;
			try {
				inputStream = file.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outputStream = new FileOutputStream(path + file.getOriginalFilename());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int readBytes = 0;
			byte[] buffer = new byte[8192];
			try {
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			// store the bytes somewhere
			return "theme";
		}
		else
			return "uploadError";
	}

	//	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	//	public @ResponseBody DenunciaVo getById(@PathVariable Long id ) {      
	//		DenunciaVo denunciaVo = null;
	////		denunciaService.load(Denuncia.class, id);
	//		return denunciaVo;     
	//	}

	//	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	//	@ResponseStatus( value = HttpStatus.NO_CONTENT )
	//	public void deleteMessage(@PathVariable Integer id ) throws NotFoundException {     
	//		denunciaService.delete(id);      
	//	}

}
