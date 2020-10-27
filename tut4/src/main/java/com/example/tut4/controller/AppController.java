package com.example.tut4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.tut4.model.ProductModel;
import com.example.tut4.service.ProductService;

@Controller //sınıfın controller olduğunu belirttik 
public class AppController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")  //GET http://127.0.0.1:8080/
	public String viewHomePage(Model model) {
		List<ProductModel> listProducts = productService.listAll();
		model.addAttribute("listProducts", listProducts);
		return "index"; //cevap olarak index.html renderlenecek
	}
	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		ProductModel productModel = new ProductModel();
		model.addAttribute("productObj", productModel);
		return "new_product"; //new_product.html view'ı  cliente renderle
	}
	
	@RequestMapping(value= "/save", method= RequestMethod.POST)// POST http://127.0.0.1:8080/save
	public String saveProduct(@ModelAttribute("productObjGettingFromFormData") ProductModel productModel) { //view da form ile oluşturan productObj datasına, controllerdeki @ModelAttribute sayesinde ulaşılır
		productService.save(productModel);
		return "redirect:/"; //cliente cevap olarak http://127.0.0.1:8080/ anasayfaya yönlendirlir
	}
	
	@RequestMapping("/edit/{pageId}")// GET  http://127.0.0.1:8080/edit/4
	public ModelAndView showEditProductForm(@PathVariable(name="pageId") Long id) { //@PathVariable ve @RequestMapping("/edit/{pageId}") sayesinde pageId yerine geçen değer alınır. bu örnekte 4
		ModelAndView mav = new ModelAndView("edit_product"); //bu sayede contollerdaki veriye hangi view(html) erişeceğini söylemiş oldjuk  
		
		ProductModel productModel = productService.get(id);
		mav.addObject("productObj", productModel);//bu sayede view(html'den), Controllerdaki productModel verisine,  productObj adını kullanarak ulaşabilir
		return mav;
	} 
	
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id) { //@PathVariable ve @RequestMapping("/delete/{id}") sayesinde urldeki id= karşısındaki değer alınır
		productService.delete(id);
		return "redirect:/"; //cliente cevap olarak http://127.0.0.1:8080/ anasayfaya yönlendirlir
	}
	
	
	
}
