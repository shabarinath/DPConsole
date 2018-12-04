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
package com.dpconsole.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ObjectUtils;
import org.displaytag.properties.MediaTypeEnum;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 * @author nanda.malve
 * created on 04-Dec-2018 3:24:15 PM
 */
public final class DisplayTagUtils {

	private DisplayTagUtils() {

	}

	public static String getSortNameParamName(String tableId) {
		return new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_SORT);
	}

	public static String getExportingTypeNameParamName(String tableId) {
		return new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_EXPORTTYPE);
	}

	public static String getExportingTypeParamName(HttpServletRequest request,String tableId) {
		String key = getExportingTypeNameParamName(tableId);
		return request.getParameter(key);
	}

	public static String getExportMediaType(HttpServletRequest request,String tableId) {
		String exportParam = getExportType(request, tableId);
		MediaTypeEnum currentMediaType = (MediaTypeEnum) ObjectUtils.defaultIfNull(MediaTypeEnum.fromCode(Integer.parseInt(exportParam)),MediaTypeEnum.HTML);
		if(currentMediaType != null){
			return currentMediaType.getName();
		}
		return null;
	}

	public static String getSortName(HttpServletRequest request,String tableId) {
		String key = getSortNameParamName(tableId);
		return request.getParameter(key);
	}

	public static String getSortName(HttpServletRequest request,String tableId,String defaultValue) {
		String val = getSortName(request,tableId);
		return val != null && !"".equals(val) ? val : defaultValue;
	}

	public static String getExportType(HttpServletRequest request,String tableId) {
		String val = getExportingTypeParamName(request,tableId);
		return val != null && !"".equals(val) ? val : null;
	}


	public static String getPageParamName(String tableId) {
		return new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE);
	}

	public static String getExportParamName(String tableId) {
		return new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_EXPORTTYPE);
	}

	public static String getExportingParamName(String tableId) {
		return new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_EXPORTING);
	}

	public static String getSortParamName(String tableId) {
		return new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_SORT);
	}

	public static String getPage(HttpServletRequest request,String tableId) {
		String key = getPageParamName(tableId);
		return request.getParameter(key);
	}

	public static int getPageSize(HttpServletRequest request, String tableId,int defaultValue) {
		try {
			if(request.getParameter("pageSize") != null) {
				return	Integer.parseInt(request.getParameter("pageSize"));
			}
		}catch(NumberFormatException e) {
			return defaultValue;
		}
		return defaultValue;
	}


	public static int getPage(HttpServletRequest request,String tableId,int defaultValue) {
		try {
			return Integer.parseInt(getPage(request,tableId));
		} catch(Throwable e){
			return defaultValue;
		}
	}

	public static String getSortOrderParamName(String tableId) {
		return new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_ORDER);
	}

	public static String getSortUsingNameParamName(String tableId) {
		return new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_SORTUSINGNAME);
	}

	public static String getSortOrder(HttpServletRequest request,String tableId) {
		String key = getSortOrderParamName(tableId);
		return request.getParameter(key);
	}

	public static String getSortOrder(HttpServletRequest request,String tableId,String defaultValue) {
		String val = getSortOrder(request,tableId);
		return val != null && !"".equals(val) ? val : defaultValue;
	}

	public static boolean isDecendingSortOrder(HttpServletRequest request,String tableId,boolean defaultValue) {
		String order = getSortOrder(request,tableId);
		return order != null ? "2".equals(order) : defaultValue;
	}

	public static String getCustomURLAppender(String tableId, int pageNo,String sortName,boolean isDecending){
		return getSortNameParamName(tableId) + "=" + sortName + "&"  + getSortUsingNameParamName(tableId) + "= " + 1 + "&"+ getPageParamName(tableId) + "=" + pageNo + "&" + getSortOrderParamName(tableId) + "=" + (isDecending ? "2" : "1" );
	}

}