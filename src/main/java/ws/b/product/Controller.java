/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.b.product;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ToRo
 */
@RestController
public class Controller {
    private static Map<String, Products> productRepo= new HashMap<>();
    static{
        Products honey = new Products();//deklarasi variabel
        honey.setId("1");//mengset id honey
        honey.setName("Honey");//mengset nama honey
        honey.setNumber(3);
        honey.setPrice(1000);
        productRepo.put(honey.getId(),honey);//mengisi nilai ke product repo
        
        Products almond = new Products();//deklarasi variabel
        almond.setId("2");//mengset id almond
        almond.setName("Almond");//mengset nama almond
        productRepo.put(almond.getId(),almond);//mengisi nilai ke product repo
    }
    
    //GET API
    @RequestMapping(value = "/products")//untuk merequest mapping
    public ResponseEntity<Object> getProduct(){//mendeklarasi variabel
        if (productRepo.isEmpty()){//kondisi jika productrepo kosong
            return new ResponseEntity<>("There are no products", HttpStatus.NOT_FOUND);//mengembalikan nilai
        }
        return new ResponseEntity<>(productRepo.values(),HttpStatus.OK);//mengembalikan nilai
    }
    
    //POST API
    @RequestMapping(value = "/products", method = RequestMethod.POST)//merequestmapping
    public ResponseEntity<Object> createproduct(@RequestBody Products product){//mendeklarasi variabel dan menangkap nilai dari product
        
        
        if (productRepo.containsKey(product.getId())){//kondisi jika id sudah ada
            return new ResponseEntity<>("Product already exist", HttpStatus.NOT_FOUND);//mengembalikan nilai
        }else{
             productRepo.put(product.getId(), product);//mengisi nilai id ke product repo
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);//mengembalikan nilai
        }
    }
    
    //PUT API
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)//merequestmapping
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Products product){ //deklarasi variable, request path, dan menangkap nilai dari product
        if(!productRepo.containsKey(id)){//kondisi jika id tidak ada
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);//mengembalikan nilai
        }else{
            productRepo.remove(id);//menghapus id
        product.setId(id);//mengatur id
        productRepo.put(id, product);//mengisi nilai id ke product repo
        return new ResponseEntity<>("Product is updated succesfully", HttpStatus.OK); //mengembalikan nilai
        }
}
    
    //DELETE API
    @RequestMapping(value ="/products/{id}", method = RequestMethod.DELETE)//merequestmapping
    public ResponseEntity<Object> delete(@PathVariable("id") String id){//deklarasi variable dan request path
        if(!productRepo.containsKey(id)){//kondisi jika tidak terdapat id
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);//mengembalikan nilai
        }else{
           productRepo.remove(id);//menghapus id
        return new ResponseEntity<>("Product is deleted succesfully", HttpStatus.OK); //mengembalikan nilai
        }
    }
}
    