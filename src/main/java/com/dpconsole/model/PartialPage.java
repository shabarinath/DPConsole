/*
 * This computer program is the confidential information and proprietary trade
 * secret of DP Console Project. Possessions and use of this program must
 * conform strictly to the license agreement between the user and
 * DP Console Project, and receipt or possession does not convey any rights
 * to divulge, reproduce, or allow others to use this program without specific
 * written authorization of DP Console Project.
 *
 * Copyright 2018 DP Console Project. All Rights Reserved.
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