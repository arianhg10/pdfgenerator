package com.lacantera.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lacantera.model.Producto;
import com.lacantera.service.ProductoService;


@RestController
public class PdfGeneratorController {
	
	@Autowired
	ProductoService productoService;
	
	@GetMapping("/productos")
	public List<Producto> getProductos(){
		return productoService.getProductos();
	}
	
	@GetMapping("/carta")
	public ResponseEntity<byte[]> descargarPdfProductos() throws IOException{
		return productoService.carta();
	}
}
