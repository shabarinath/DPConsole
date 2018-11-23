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
package com.dpconsole.model;

import java.util.List;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:14:35 PM
 */
public interface PartialPage<E> {

	List<E> getList();

	int getTotalResults();

	int getFirstResultNumber();

	int getLastResultNumber();

	int getLastPageNumber();

	int getNextPageNumber();

	int getPreviousPageNumber();

	int getTotalPages();

	int getPageNo();

	int getPageSize();

	boolean isNextPage();

	boolean hasNextPage();

}