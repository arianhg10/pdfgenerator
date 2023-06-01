package com.lacantera.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.lacantera.model.Producto;

@FeignClient(name = "producto-servicio", url = "https://lacantera.somee.com")
public interface ProductoFeignClient {
	
	@GetMapping("/productos")
	public List<Producto> getProductos();
}
