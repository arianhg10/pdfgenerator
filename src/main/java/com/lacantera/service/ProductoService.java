package com.lacantera.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.lacantera.feignclient.ProductoFeignClient;
import com.lacantera.mapper.DataMapper;
import com.lacantera.model.Producto;

@Service
public class ProductoService {
	
	@Autowired
	ProductoFeignClient productoFeignClient;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	SpringTemplateEngine springTemplateEngine;
	
	public List<Producto> getProductos(){
		return productoFeignClient.getProductos();
	}
	
	
	public ResponseEntity<byte[]> carta() throws IOException {
		Context dataContext = dataMapper.setData(getProductos());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter pdfWriter = new PdfWriter(baos);
		ConverterProperties converterProperties = new ConverterProperties();
		String finalHtml = springTemplateEngine.process("template", dataContext);
		HtmlConverter.convertToPdf(finalHtml, pdfWriter, converterProperties);
		byte[] pdfBytes = baos.toByteArray();
		baos.close();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=ReporteProductos.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
	}
}
