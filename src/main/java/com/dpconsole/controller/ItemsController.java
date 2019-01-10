package com.dpconsole.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.catalogue.SubCategory;
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
@SessionAttributes("itemsController")
public class ItemsController {

	private static final Logger logger = LoggerFactory.getLogger(ItemsController.class);
	
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
			return "menu/menuLayout";
		} catch(Exception e) {
			logger.error("Failed to load orders layout", e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/loadItems")
	public String dashboard(Model model,			
			@RequestParam("kitchenId") long kitchenId,
			@RequestParam("categoryId") long categoryId,
			@RequestParam("subCategoryId") long subCategoryId,
			HttpServletRequest request) throws Exception{
		try {
			String tableId="item";
			String sortName = DisplayTagUtils.getSortName(request, tableId, "");
			boolean isDecendingOrder = DisplayTagUtils.isDecendingSortOrder(request, tableId, true);
			int pageNo = DisplayTagUtils.getPage(request, tableId, 1);
			int pageSize = DisplayTagUtils.getPageSize(request, tableId, 20);
			PartialPage<KitchenItem> kitchenItems = kitchenService.getKitchenItemsByCategoryAndSubCategory(kitchenId, categoryId, subCategoryId, sortName, isDecendingOrder,  pageNo,  pageSize);
			model.addAttribute("kitchenItems", kitchenItems);
			return "menu/itemsPage";
		}catch(Exception e) {
			logger.error("Exception in processing dashbaord", e);
			throw e;
		}
	}
	
}

