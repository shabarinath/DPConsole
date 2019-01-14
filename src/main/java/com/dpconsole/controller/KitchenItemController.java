package com.dpconsole.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.catalogue.Item;
import com.dpconsole.model.catalogue.SubCategory;
import com.dpconsole.model.catalogue.Type;
import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.service.CatalogueService;
import com.dpconsole.service.KitchenService;
import com.dpconsole.utils.DisplayTagUtils;

/**
 * @author SHABARINATH
 * 10-Jan-2019 8:45:22 AM 2019 
 */

@Controller
@SessionAttributes("kitchenItem")
public class KitchenItemController {

	private static final Logger logger = LoggerFactory.getLogger(KitchenItemController.class);
	
	@Autowired
	private KitchenService kitchenService;
	
	@Autowired
	private CatalogueService catelogueService;
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String loadMenuPage(Model model) throws Exception{
		try {
			List<Kitchen> kitchens = kitchenService.getAllKitchens();
			List<Category> categories = catelogueService.getAllCategories();
			List<SubCategory> subCategories = catelogueService.getAllSubCategories();
			model.addAttribute("kitchens", kitchens);
			model.addAttribute("categories", categories);
			model.addAttribute("subCategories", subCategories);
			return "item/menuLayout";
		} catch(Exception e) {
			logger.error("Failed to load orders layout", e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/loadKitchenItems")
	public String loadKitchenItems(Model model,			
			@RequestParam("kitchenId") long kitchenId,
			@RequestParam("categoryId") long categoryId,
			@RequestParam("subCategoryId") long subCategoryId,
			HttpServletRequest request) throws Exception{
		try {
			String tableId="kitchenItem";
			String sortName = DisplayTagUtils.getSortName(request, tableId, "");
			boolean isDecendingOrder = DisplayTagUtils.isDecendingSortOrder(request, tableId, true);
			int pageNo = DisplayTagUtils.getPage(request, tableId, 1);
			int pageSize = DisplayTagUtils.getPageSize(request, tableId, 20);
			PartialPage<KitchenItem> kitchenItems = kitchenService.getKitchenItemsByCategoryAndSubCategory(kitchenId, categoryId, subCategoryId, sortName, isDecendingOrder,  pageNo,  pageSize);
			model.addAttribute("kitchenItems", kitchenItems);
			return "item/itemsPage";
		}catch(Exception e) {
			logger.error("Exception in processing dashbaord", e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/kitchenItem/{id}")
	public String displayKitchenItem(Model model,			
			@PathVariable("id") long kitchenItemId) throws Exception{
		try {
			KitchenItem ki = (kitchenItemId >0) ? kitchenService.getKitchenItemById(kitchenItemId) : new KitchenItem();
			model.addAttribute("kitchenItem", ki);
			List<Category> categories = catelogueService.getAllCategories();
			List<SubCategory> subCategories = catelogueService.getAllSubCategories();
			List<Kitchen> kitchens = kitchenService.getAllKitchens();
			model.addAttribute("categories", categories);
			model.addAttribute("subCategories", subCategories);
			model.addAttribute("kitchens", kitchens);
			model.addAttribute("types", Type.values());
			return "item/itemForm";
		}catch(Exception e) {
			logger.error("Exception in processing dashbaord", e);
			throw e;
		}	
	}
	
	@RequestMapping(value = "/saveKitchenItem/{id}", method = RequestMethod.POST)
	public String saveOrUpdateKitchenItem(@ModelAttribute KitchenItem kitchenItem,BindingResult result,Model model,			
			@PathVariable("id") long kitchenItemId) throws Exception{
		try {
			KitchenItem dbKitchenItem = null;
			if(kitchenItemId > 0) {
				dbKitchenItem = kitchenService.getKitchenItemById(kitchenItemId);
				dbKitchenItem.setActive(kitchenItem.isActive());
				dbKitchenItem.getItem().setManufacturingPrice(kitchenItem.getItem().getManufacturingPrice());
				dbKitchenItem.getItem().setPackingPrice(kitchenItem.getItem().getPackingPrice());
				dbKitchenItem.setMarketPrice(kitchenItem.getMarketPrice());
				kitchenService.saveOrUpdateKitchenItem(dbKitchenItem);
			} /*else {
				List<Kitchen> kitchens = kitchenService.getAllKitchens(); 
				for(Kitchen kitchen : kitchens) {
					SubCategory subCategory = catelogueService.getSubCategoryById(kitchenItem.getItem().getSubCategory().getId());
					dbKitchenItem = new KitchenItem();
					Item item = new Item();
					item.setActive(true);
					item.setManufacturingPrice(kitchenItem.getItem().getManufacturingPrice());
					item.setName(kitchenItem.getItem().getName());
					item.setPackingPrice(kitchenItem.getItem().getPackingPrice());
					item.setType(kitchenItem.getItem().getType());
					SubCategory subCategory = new SubCategory();
					subCategory.setActive(true);
					subCategory.setName(kitchenItem.getItem().getSubCategory().getName());
					Category category = new Category();
					category.setName(kitchenItem.getItem().getSubCategory().getCategory().getName());
					subCategory.setCategory(category);
					item.setSubCategory(subCategory);
					dbKitchenItem.setKitchen(kitchen);
					dbKitchenItem.setMarketPrice(kitchenItem.getMarketPrice());
					kitchenService.saveOrUpdateKitchenItem(dbKitchenItem);
				}
			}*/
			return "redirect:/menu";
		}catch(Exception e) {
			logger.error("Exception in processing dashbaord", e);
			throw e;
		}
	}
}


