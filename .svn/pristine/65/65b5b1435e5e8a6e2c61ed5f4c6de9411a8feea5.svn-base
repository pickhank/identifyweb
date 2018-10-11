package com.woodare.core.web.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.constant.CommonConstant;
import com.woodare.core.jpa.data.systemroleuser.SearchSystemRoleUserData;
import com.woodare.core.jpa.model.SystemRole;
import com.woodare.core.jpa.model.SystemRoleUser;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.service.ISystemRoleDAO;
import com.woodare.core.jpa.service.ISystemRoleUserDAO;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.core.web.common.viewdata.CodeAndName;
import com.woodare.core.web.system.viewdata.systemroleuser.SearchSystemRoleUserViewData;
import com.woodare.core.web.system.viewdata.systemroleuser.SystemRoleUserViewData;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.JavaMD5Hash;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.SysProperties;

/**
 * 
 * @author lu_feng
 * 
 */
@Controller
@RequestMapping("/system/roleUser")
public class SystemRoleUserController extends BaseController {
    private static Logger log = Logger.getLogger(SystemRoleUserController.class);

    @Autowired
    private ISystemRoleUserDAO systemRoleUserDAO;

    @Autowired
    private ISystemRoleDAO systemRoleDAO;

    @Autowired
    private ISystemUserDAO systemUserDAO;

    /**
     * 
     * @param searchData
     * @return
     */
    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(SearchSystemRoleUserViewData searchData) {
        log.debug("init role user list");
        ModelAndView mav = new ModelAndView("/system/roleUser/index");
        mav.addObject("searchData", searchData);
        mav.addObject("items", this.getUserRoleList(searchData));

        List<CodeAndName> roles = new ArrayList<CodeAndName>();
        List<SystemRole> roleModels = this.systemRoleDAO.findAll();

        if (roleModels != null && roleModels.size() > 0) {
            for (SystemRole roleModel : roleModels) {
                roles.add(new CodeAndName(roleModel.getRoleName(), roleModel.getId()));
            }
        }
        mav.addObject("roles", roles);

        return mav;
    }

    /**
     * 
     * @param searchData
     * @return
     */
    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView search(SearchSystemRoleUserViewData searchData) {
        log.debug("search role user list");

        ModelAndView mav = new ModelAndView("/system/roleUser/list");

        mav.addObject("searchData", searchData);
        mav.addObject("items", this.getUserRoleList(searchData));

        List<CodeAndName> roles = new ArrayList<CodeAndName>();
        List<SystemRole> roleModels = this.systemRoleDAO.findAll();

        if (roleModels != null && roleModels.size() > 0) {
            for (SystemRole roleModel : roleModels) {
                roles.add(new CodeAndName(roleModel.getRoleName(), roleModel.getId()));
            }
        }
        mav.addObject("roles", roles);

        return mav;
    }

    /**
     * 
     * @param searchData
     * @return
     */
    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(SystemRoleUserViewData roleUserData) {
        log.debug("init detail with ");
        return getUserRoleDetail(roleUserData.getId());
    }

    /**
     * 
     * @param searchData
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public ModelAndView saveDetail(SystemRoleUserViewData roleUserData) {
        log.debug("save role user detail");

        List<String> roleIds = roleUserData.getRoleIds();
        List<SystemRoleUser> userRoles = null;
        if (StringUtils.isNotEmpty(roleUserData.getId())) {
            SearchSystemRoleUserData searchRoleUserData = new SearchSystemRoleUserData();
            searchRoleUserData.setPageSize(Integer.MAX_VALUE);
            searchRoleUserData.setUserId(roleUserData.getId());
            userRoles = systemRoleUserDAO.searchSystemRoleUsers(searchRoleUserData);
            if (userRoles != null && userRoles.size() > 0) {
                for (SystemRoleUser userRole : userRoles) {
                    if (roleIds != null && !roleIds.contains(userRole.getId())) {
                        systemRoleUserDAO.delete(userRole);
                    }
                }
            }
        }

        if (roleIds != null) {
            for (String roleId : roleIds) {
                boolean newFlag = true;
                if (userRoles != null) {
                    for (SystemRoleUser userRole : userRoles) {
                        if (userRole.getId().equals(roleId)) {
                            newFlag = false;
                            break;
                        }
                    }

                }
                if (newFlag) {
                    SystemRoleUser roleUser = new SystemRoleUser();
                    roleUser.setRoleId(roleId);
                    if (StringUtils.isNotEmpty(roleUserData.getId())) {
                        SystemUser user = systemUserDAO.findOne(roleUserData.getId());
                        if (null != user) {
                            user.setEmail(roleUserData.getEmail());
                            user.setIsAdminFlag(roleId.equals(CommonConstant.SYSTEM_ROLE.MANGER_USER.toString()) || roleId.equals(CommonConstant.SYSTEM_ROLE.SUPER_USER.toString()));
                            systemUserDAO.update(user);
                            
                            roleUser.setUserId(roleUserData.getId());
                            roleUser.setUser(user);
                            systemRoleUserDAO.save(roleUser);
                        } else {
                            return this.alertMessage("操作失败，用户数据非法！", false);
                        }
                    } else {
                        if(StringUtils.isNotEmpty(roleUserData.getEmail())) {
                            SystemUser u = systemUserDAO.findByEmail(roleUserData.getEmail());
                            if(u != null) {

                                ModelAndView mav = new ModelAndView("/system/roleUser/detail");
                                mav.addObject("item", roleUserData);
                                mav.addObject("errorMsg", "添加失败,该用户已经存在，请检查用户名的唯一性");
                                List<CodeAndName> roles = new ArrayList<CodeAndName>();
                                List<SystemRole> roleModels = this.systemRoleDAO.findAll();

                                if (roleModels != null && roleModels.size() > 0) {
                                    for (SystemRole roleModel : roleModels) {
                                        roles.add(new CodeAndName(roleModel.getRoleName(), roleModel.getId()));
                                    }
                                }
                                mav.addObject("roles", roles);
                                
                                return mav;
                            }
                        }
                        
                        SystemUser newUser = new SystemUser();
                        SaftyBeanUtils.copyProperties(roleUserData, newUser, new String[] { "id" });
                        String newUserName = newUser.getUsername();
                        SystemUser user = systemUserDAO.findByUsername(newUserName);
                        if (null != user) {

                            ModelAndView mav = new ModelAndView("/system/roleUser/detail");
                            mav.addObject("item", roleUserData);
                            mav.addObject("errorMsg", "添加失败,该用户已经存在，请检查用户名的唯一性");
                            List<CodeAndName> roles = new ArrayList<CodeAndName>();
                            List<SystemRole> roleModels = this.systemRoleDAO.findAll();

                            if (roleModels != null && roleModels.size() > 0) {
                                for (SystemRole roleModel : roleModels) {
                                    roles.add(new CodeAndName(roleModel.getRoleName(), roleModel.getId()));
                                }
                            }
                            mav.addObject("roles", roles);
                            
                            return mav;
                        } else {
                            String encryptPassword = JavaMD5Hash.md5(roleUserData.getPassword());
                            newUser.setPassword(encryptPassword);
                            newUser.setIsAdminFlag(roleId.equals(CommonConstant.SYSTEM_ROLE.MANGER_USER.toString()) || roleId.equals(CommonConstant.SYSTEM_ROLE.SUPER_USER.toString()));
                            systemUserDAO.save(newUser);
                            roleUser.setUserId(newUser.getId());
                            roleUser.setUser(newUser);
                            systemRoleUserDAO.save(roleUser);
                        }
                    }
                }
            }
            return this.alertMessage("操作成功！", true);
        } else {
            return this.alertMessage("该用户释放掉！", true);
        }

    }

    /**
     * 
     * @param searchData
     * @return
     */
    private IPagedList<SystemRoleUserViewData> getUserRoleList(SearchSystemRoleUserViewData searchData) {
        searchData.setAutoRedirect(false);
        IPagedList<SystemUser> modelLst = systemRoleUserDAO.searchUsers(searchData);
        IPagedList<SystemRoleUserViewData> items = SaftyBeanUtils.cloneToList(modelLst, SystemRoleUserViewData.class);
        if (items != null && items.size() > 0) {
            List<String> userIds = new ArrayList<String>();
            for (SystemRoleUserViewData item : items) {
                userIds.add(item.getId());
            }

            SearchSystemRoleUserData searchRoleUserData = new SearchSystemRoleUserData();
            searchRoleUserData.setPageSize(Integer.MAX_VALUE);
            searchRoleUserData.setUserIds(userIds);
            
            String superUsername = SysProperties.getInstance().getProperty("super.account.name", "");
            
            List<SystemRoleUser> userRoles = systemRoleUserDAO.searchSystemRoleUsers(searchRoleUserData);
            if (userRoles != null && userRoles.size() > 0) {
                for (SystemRoleUserViewData item : items) {
                    if (item.getRoleIds() == null) {
                        item.setRoleIds(new ArrayList<String>());
                    }
                    if (item.getRoleNames() == null) {
                        item.setRoleNames(new ArrayList<String>());
                    }
                    
                    if(superUsername.equals(item.getUsername())) {
                        item.setIsSuperFlag(true);
                    }
                    else {
                        item.setIsSuperFlag(false);
                    }
                    
                    List<String> roleIds = item.getRoleIds();
                    List<String> roleNames = item.getRoleNames();
                    for (SystemRoleUser userRole : userRoles) {
                        if (item.getId().equals(userRole.getUserId())) {
                            roleIds.add(userRole.getRoleId());
                            roleNames.add(userRole.getRole().getRoleName());
                        }
                    }
                }
            }
        }

        return items;
    }

    /**
     * 
     * @param userId
     * @return
     */
    private ModelAndView getUserRoleDetail(String userId) {
        ModelAndView mav = new ModelAndView("/system/roleUser/detail");

        SystemRoleUserViewData item = null;
        if (StringUtils.isNotEmpty(userId)) {
            SystemUser user = systemUserDAO.findOne(userId);
            item = SaftyBeanUtils.cloneTo(user, SystemRoleUserViewData.class);
            if (item != null) {
                SearchSystemRoleUserData searchRoleUserData = new SearchSystemRoleUserData();
                searchRoleUserData.setPageSize(Integer.MAX_VALUE);
                searchRoleUserData.setUserId(user.getId());

                List<SystemRoleUser> userRoles = systemRoleUserDAO.searchSystemRoleUsers(searchRoleUserData);
                if (userRoles != null && userRoles.size() > 0) {
                    List<String> roleIds = new ArrayList<String>();
                    List<String> roleNames = new ArrayList<String>();
                    for (SystemRoleUser userRole : userRoles) {
                        roleIds.add(userRole.getRoleId());
                        roleNames.add(userRole.getRole().getRoleName());
                    }
                    item.setRoleIds(roleIds);
                    item.setRoleNames(roleNames);
                }
            }
        } else {
            item = new SystemRoleUserViewData();
            item.setRoleIds(new ArrayList<String>());
            item.setRoleNames(new ArrayList<String>());
        }

        mav.addObject("item", item);

        List<CodeAndName> roles = new ArrayList<CodeAndName>();
        List<SystemRole> roleModels = this.systemRoleDAO.findAll();

        if (roleModels != null && roleModels.size() > 0) {
            for (SystemRole roleModel : roleModels) {
                roles.add(new CodeAndName(roleModel.getRoleName(), roleModel.getId()));
            }
        }
        mav.addObject("roles", roles);

        return mav;
    }

}
