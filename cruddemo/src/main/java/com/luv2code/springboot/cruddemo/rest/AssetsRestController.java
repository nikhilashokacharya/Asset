package com.luv2code.springboot.cruddemo.rest;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Assets;
import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.exceptions.AssetNotFoundException;
import com.luv2code.springboot.cruddemo.response.Response;
import com.luv2code.springboot.cruddemo.service.AssetsService;
import com.luv2code.springboot.cruddemo.service.RequestsService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class AssetsRestController {
	
	private AssetsService assetsService;
	@Autowired
	public AssetsRestController(AssetsService theAssetsService) {
		this.assetsService = theAssetsService;
	}
	
	//expose "/Assets" and return the list
	@GetMapping("/assets")
	public Response<List<Assets>> findAll() throws AssetNotFoundException {
		List<Assets> assets = assetsService.findAll();
		if (assets != null) {
			return new Response<>(false, "records found", assets);
		} else {
			throw new AssetNotFoundException();
		}
	}
	
	// add mapping for GET /Assets/{assetId}
	@GetMapping("/assets/{assetId}")
	public Response<Assets> getAsset(@PathVariable int assetId) throws AssetNotFoundException {
		Assets theAsset = assetsService.findById(assetId);
		
		if (theAsset != null) {
			return new Response<>(false, "records found", theAsset);
		} else {
			throw new AssetNotFoundException();
		}
		
		
	}
	
	// add for POST /Assets
	
	@PostMapping("/assets")
	public Response<Assets> addAsset(@RequestBody Assets theAsset) {
//		theAsset.setAssetID(0);
//		theAsset.setRequestInfoBeanList();
		 assetsService.save(theAsset);
		 if (theAsset != null) {

				return new Response<>(false, "successfully saved", theAsset);

			} else {
				return new Response<>(true, "save failed", null);
			}
		}
	
	// add mapping for PUT /Assets - update
	
	@PutMapping("/assets")
	public Response<Assets> updateAsset(@RequestBody Assets theAsset) {
		assetsService.save(theAsset);
		if (theAsset != null) {

			return new Response<>(false, "successfully saved", theAsset);

		} else {
			return new Response<>(true, "save failed", null);
		}
	}
	
	// add mapping for DELETE /Assets/{assetId}
	@DeleteMapping("/assets/{assetId}")
	public Response<String> deleteAsset(@PathVariable int assetId) throws AssetNotFoundException {
		Assets tempAsset = assetsService.findById(assetId);
		
		if(tempAsset==null) {
			throw new AssetNotFoundException();
		}
		assetsService.deleteById(assetId);
		return new Response<>(false, "successfully deleted", null);
		
		
	}
	
	@GetMapping("/assets/{pageNumber}/{itemsPerPage}")
	public Page<Assets> getAssets(@PathVariable int pageNumber,@PathVariable int itemsPerPage){
		return assetsService.getAssets(pageNumber, itemsPerPage);
	}
	
	@GetMapping("/assets/{pageNumber}/{itemsPerPage}/{fieldName}")
	public Page<Assets> getSortAssets(@PathVariable int pageNumber,@PathVariable int itemsPerPage,@PathVariable String fieldName){
	return assetsService.getSortAssets(pageNumber, itemsPerPage, fieldName);	
	}
	
	
	
}
