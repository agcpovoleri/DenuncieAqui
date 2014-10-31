package com.denuncia.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javassist.NotFoundException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.denuncia.model.Categoria;
import com.denuncia.model.Denuncia;
import com.denuncia.model.vo.CategoriaVo;
import com.denuncia.model.vo.DenunciaVo;
import com.denuncia.service.CategoriaService;
import com.denuncia.service.DenunciaService;


@Controller
@RequestMapping("/categoria")

public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private DenunciaService denunciaService;

	private Logger logger = org.slf4j.LoggerFactory.getLogger(CategoriaController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<CategoriaVo> getAll(@RequestParam( value = "type", required = false ) String type) {   
		List<CategoriaVo> categorias = categoriaService.getAllCategorias();   
		logger.info(categorias.toString());
		return categorias; 
	}
	
	@RequestMapping( value = "/all", method = RequestMethod.GET )
	public @ResponseBody List<CategoriaVo> getCategorias(@RequestParam( value = "type", required = false ) String type) {   
		List<CategoriaVo> categorias = categoriaService.getAll();   
		logger.info(categorias.toString());
		return categorias; 
	}
	
	@RequestMapping( value = "/filter", method = RequestMethod.GET )
	public @ResponseBody List<CategoriaVo> filtrarCategorias(@RequestParam Long idCategoria ) {   
		List<CategoriaVo> categorias = categoriaService.getSubcategoriasByIdcategoria(idCategoria);   
		logger.info(categorias.toString());
		return categorias; 
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK )
	@ResponseBody
	public Long save(@RequestBody CategoriaVo categoriaVo) {
		
		Categoria categoria = new Categoria();
		categoria.setId(categoriaVo.getId());
		categoria.setNome(categoriaVo.getNome());
		categoria.setDescricao(categoriaVo.getDescricao());
		if (categoriaVo.getIdpai()!=null){
			categoria.setPai(categoriaService.load(Categoria.class, categoriaVo.getIdpai()));
		}
		categoriaService.save(categoria);
		
		return categoria.getId();
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public @ResponseBody CategoriaVo getById(@PathVariable Long id ) {      
		CategoriaVo categoriaVo = categoriaService.getById(id);
		return categoriaVo;     
	}
	
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	@ResponseStatus( value = HttpStatus.NO_CONTENT )
	public void deleteMessage(@PathVariable Integer id ) throws NotFoundException {     
		categoriaService.delete(id);      
	}

	private HttpHeaders addAccessControllAllowOrigin() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		return headers;
	}

}
