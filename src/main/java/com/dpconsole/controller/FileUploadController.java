package com.dpconsole.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dpconsole.model.order.Order;
import com.dpconsole.parsers.ZomatoCSVParser;
import com.dpconsole.service.OrderService;
import com.dpconsole.utils.Utils;

/**
 * @author SHABARINATH
 * 26-Dec-2018 9:27:45 AM 2018 
 */
@Controller
@SessionAttributes("FileUploadController")
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(ZomatoGMAILController.class);
	
	@Autowired
	OrderService orderService;
	
	public static final String UPLOAD_DIRECTORY="F://datastore//zomato";
	
	@RequestMapping(value = "/excelTally", method = RequestMethod.GET)
	public String excelTally(Model model) throws Exception{
		try {
			return "tally/CSVFileUpload";
		} catch(Exception e) {
			logger.error("Unable to load excelTally Page!! .", e);
			throw e;
		}
	}
	
	@RequestMapping(value="/processFile",method=RequestMethod.POST)  
	public String savefile(Model model, @RequestParam CommonsMultipartFile file, HttpSession session) throws Exception {
		try {
			Utils.saveFileToFilesDir(file, UPLOAD_DIRECTORY);
			ZomatoCSVParser zomatoCSVParser = new ZomatoCSVParser();
			Map<String, String> ordersVSAmountMap = zomatoCSVParser.parse(null, null, UPLOAD_DIRECTORY.concat("/").concat(file.getOriginalFilename()));
			for(Entry<String, String> entry : ordersVSAmountMap.entrySet()) {
				String orderId = "";
				try {
					orderId = entry.getKey().trim();
					Order order = orderService.getOrderByDPOrderID(orderId);
					if(null != order) {
						String[] amounts = entry.getValue().split("#");
						String creditAmount = amounts[0].trim();
						String debitAmount = amounts[1].trim();
						double dpAmountPaid = 0;
						if(StringUtils.isNotEmpty(creditAmount) && creditAmount.equalsIgnoreCase("--")) {
							dpAmountPaid = Double.parseDouble(debitAmount) * -1; //if its debited saving -ve value
						} else if(StringUtils.isNotEmpty(creditAmount)) {
							dpAmountPaid = Double.parseDouble(creditAmount);
						}
						order.setDpReceivedPrice(dpAmountPaid);
						orderService.saveOrUpdateOrder(order);
					} else {
						logger.error("Order missing in db: "+ orderId);
					}
				}catch(Exception e) {
					logger.error("Parse failed for orderId: "+ orderId, e);
				}
			}
			model.addAttribute("fileSuccess", "File successfully processed!");
			return "tally/CSVFileUpload";
		}catch(Exception e) {
			logger.error("Exception occured: ", e);
		}
		model.addAttribute("fileProcessingFailed", "File processing failed!");
		return "tally/CSVFileUpload";
	}
	
}

