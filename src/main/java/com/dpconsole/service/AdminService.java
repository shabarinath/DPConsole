package com.dpconsole.service;

import com.dpconsole.model.user.User;

public interface AdminService {

	/*public HomePage saveHomePageConfig(HomePage homePage) throws Exception;

	/*public Resource saveResource(Resource resource) throws Exception;

	public OtherRelatedLink getOtherRelatedLinkByName(String displayName) throws Exception;

	public OtherRelatedLink getOtherRelatedLinkByHyperLink(String hyperlink) throws Exception;

	public NoticeBoardLink getNoticeBoardLinkByName(String displayName) throws Exception;

	public NoticeBoardLink getNoticeBoardLinkByHyperLink(String hyperlink) throws Exception;

	public OtherRelatedLink saveOrUpdateOtherRelatedLink(OtherRelatedLink otherRelatedLink) throws Exception;

	public NoticeBoardLink saveOrUpdateNoticeBoardLink(NoticeBoardLink noticeBoardLink) throws Exception;

	public void deleteOtherRelatedLink(OtherRelatedLink orl) throws Exception;

	public void deleteNoticeBoardLink(NoticeBoardLink nrl) throws Exception;

	public void deleteResource(Resource resource) throws Exception;*/

	User getUser(long id) throws Exception;

	void saveOrUpdateUser(User user) throws Exception;

}