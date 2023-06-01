package com.lacantera.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.lacantera.model.Producto;

@Service
public class DataMapper {
	public Context setData(List<Producto> productoList) {
		Context context = new Context();
		Map<String, Object> data = new HashMap<>();
		data.put("productos", productoList);
		context.setVariables(data);
		return context;
	}
}
