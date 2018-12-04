/*
 * This computer program is the confidential information and proprietary trade
 * secret of OpsRamp, Inc. Possessions and use of this program must
 * conform strictly to the license agreement between the user and
 * OpsRamp, Inc., and receipt or possession does not convey any rights
 * to divulge, reproduce, or allow others to use this program without specific
 * written authorization of OpsRamp, Inc.
 *
 * Copyright 2018 OpsRamp, Inc. All Rights Reserved.
 */
package com.dpconsole.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.displaytag.tags.TableTagParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dpconsole.utils.DisplayTagUtils;
import com.dpconsole.utils.Utils;

/**
 * @author nanda.malve
 * created on 04-Dec-2018 4:18:08 PM
 */
public class DisplayTableParamTag extends SimpleTagSupport {

	private static Logger log = LoggerFactory.getLogger(DisplayTableParamTag.class);
	private String paramName;
	private String tableId;
	public DisplayTableParamTag() {

	}

	@Override
	public void doTag() throws JspException {
		PageContext context = (PageContext) getJspContext();
		JspWriter writer = context.getOut();
		try {
			if(!Utils.isEmpty(paramName)){
				if(paramName.equalsIgnoreCase("page")){
					writer.append(DisplayTagUtils.getPageParamName(tableId));
				}else if(paramName.equalsIgnoreCase("export")){
					writer.append(DisplayTagUtils.getExportParamName(tableId));
				}else if(paramName.equalsIgnoreCase("exporting")){
					writer.append(TableTagParameters.PARAMETER_EXPORTING);
				}else if(paramName.equalsIgnoreCase("sort")){
					writer.append(DisplayTagUtils.getSortParamName(tableId));
				}
			}
		} catch (IOException e) {
			log.debug("Failed to get table parameters");
			throw new JspException(e.getMessage(), e);
		}
	}

	public String getParamName() {
		return paramName;
	}

	public String getTableId() {
		return tableId;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
}
