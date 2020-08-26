package com.bhavin.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bhavin.dto.ProductRepositry;
import com.bhavin.entity.Product;
import com.bhavin.service.ProductService;

@Controller
public class HomeController {
	@Autowired 
	private ProductRepositry productRepo;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/addProduct",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnAddProduct()
	{
	  ModelAndView mv = new ModelAndView();
	  mv.setViewName("addProduct");
	  return mv;
	  
	}
	
	@RequestMapping(value = "/listProduct",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnListProduct()
	{
	  ModelAndView mv = new ModelAndView();
	  List<Product> products = productRepo.findAll();
	  mv.setViewName("listProducts");
	  mv.addObject("products", products);
	  return mv;
	  
	}
	
	@GetMapping("/listProducts.html")
	public String showExampleView(Model model)
	{
		List<Product> products = productService.getAllProduct();
		model.addAttribute("products", products);
		return "/listProducts.html";
	}
	
    @GetMapping("/")
    public String showAddProduct()
    {
    	
    	return "/index.html";
    }
    
    @GetMapping("/index")
    public String showHomePage()
    {
    	
    	return "/index.html";
    }

    @GetMapping("/editProductForm")
    public String edit()
    {
    	
    	return "/editProductForm.html";
    }
    
    @PostMapping("/addP")
    public String saveProduct(@RequestParam("file") MultipartFile file,
    		@RequestParam("pname") String name,
    		@RequestParam("price") int price,
    		@RequestParam("discount") int discount,
    		@RequestParam("desc") String desc)
    		
    {
    	productService.saveProductToDB(file, name, desc, price,discount);
    	return "listProducts.html";
    }
 
    
    @GetMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model)
    {
    	
    	Product p = productService.getProductById(id);
    	System.out.println(id+"\n\n\n\n\n\n\n"+p.getDescription());
    	model.addAttribute("product", p);
    	return "redirect:/editProductForm.html";
    }
    
    
    
    @GetMapping("/deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") Long id)
    {
    	
    	productService.deleteProductById(id);
    	return "redirect:/listProducts.html";
    }
    
    @PostMapping("/changeName")
    public String changePname(@RequestParam("id") Long id,
    		@RequestParam("newPname") String name)
    {
    	productService.chageProductName(id, name);
    	return "redirect:/listProducts.html";
    }
    @PostMapping("/changeDescription")
    public String changeDescription(@RequestParam("id") Long id ,
    		@RequestParam("newDescription") String description)
    {
    	productService.changeProductDescription(id, description);
    	return "redirect:/listProducts.html";
    }
    
    @PostMapping("/changePrice")
    public String changePrice(@RequestParam("id") Long id ,
    		@RequestParam("newPrice") int price)
    {
    	productService.changeProductPrice(id, price);
    	return "redirect:/listProducts.html";
    }

}
