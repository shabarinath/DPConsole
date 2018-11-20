package com.dpconsole.service;

import com.dpconsole.dao.AdminDao;
import com.dpconsole.dao.DefaultDao;
import com.dpconsole.domain.HomePage;
import com.dpconsole.domain.User;
import com.dpconsole.utils.SecurityUtils;

public class AdminServiceImpl implements AdminService {
	
	private AdminDao adminDao;
	
	private DefaultDao defaultDao;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) throws Exception {
		this.adminDao = adminDao;
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	@Override
	public HomePage saveHomePageConfig(HomePage homePage) throws Exception {
		if(!SecurityUtils.isAdminUser()) {
			throw new Exception("Only admin can update home page");
		}
		adminDao.saveOrUpdate(homePage);
		return (HomePage) adminDao.get(HomePage.class, homePage.getId());
	}
	
	/*@Override
	public Resource saveResource(Resource resource) throws Exception {
		if(!SecurityUtils.isAdminUser()) {
			throw new Exception("Only admin can add a resource");
		}
		resource.setCreatedTime(Utils.getSystemTimeInGMT());
		adminDao.saveOrUpdate(resource);
		return (Resource) adminDao.get(Resource.class, resource.getId());
	}
	
	@Override
	public OtherRelatedLink getOtherRelatedLinkByName(String displayName) throws Exception {
		return adminDao.getOtherRelatedLinkByName(displayName);
	}
	
	@Override
	public OtherRelatedLink getOtherRelatedLinkByHyperLink(String hyperlink) throws Exception {
		return adminDao.getOtherRelatedLinkByHyperLink(hyperlink);
	}
	
	@Override
	public NoticeBoardLink getNoticeBoardLinkByName(String displayName) throws Exception {
		return adminDao.getNoticeBoardLinkByName(displayName);
	}
	
	@Override
	public NoticeBoardLink getNoticeBoardLinkByHyperLink(String hyperlink) throws Exception {
		return adminDao.getNoticeBoardLinkByHyperLink(hyperlink);
	}
	
	@Override
	public OtherRelatedLink saveOrUpdateOtherRelatedLink(OtherRelatedLink otherRelatedLink) throws Exception {
		if(!SecurityUtils.isAdminUser()) {
			throw new Exception("Only admin can add other related link");
		}
		adminDao.saveOrUpdate(otherRelatedLink);
		return (OtherRelatedLink) adminDao.get(OtherRelatedLink.class, otherRelatedLink.getId());
	}
	
	@Override
	public NoticeBoardLink saveOrUpdateNoticeBoardLink(NoticeBoardLink noticeBoardLink) throws Exception {
		if(!SecurityUtils.isAdminUser()) {
			throw new Exception("Only admin can add notice board link");
		}
		adminDao.saveOrUpdate(noticeBoardLink);
		return (NoticeBoardLink) adminDao.get(NoticeBoardLink.class, noticeBoardLink.getId());
	}

	@Override
	public void deleteOtherRelatedLink(OtherRelatedLink orl) throws Exception {
		if(!SecurityUtils.isAdminUser()) {
			throw new Exception("Only admin can delete other related link");
		}
		adminDao.delete(orl);
	}
	
	@Override
	public void deleteNoticeBoardLink(NoticeBoardLink nrl) throws Exception {
		if(!SecurityUtils.isAdminUser()) {
			throw new Exception("Only admin can delete other related link");
		}
		adminDao.delete(nrl);
	}*/
	
	/*@Override
	public void deleteResource(Resource resource) throws Exception {
		if(!SecurityUtils.isAdminUser()) {
			throw new Exception("Only admin can delete a resource");
		}
		adminDao.delete(resource);
	}*/
	
	@Override
	public User getUser(long id) throws Exception {
	   return adminDao.getUser(id); 
	}

	@Override
	public void saveOrUpdateUser(User user) throws Exception {
		 adminDao.saveOrUpdateUser(user);
	}
}
