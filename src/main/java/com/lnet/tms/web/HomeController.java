package com.lnet.tms.web;

import com.lnet.tms.model.sys.SysFunction;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.SystemUtils;
import com.lnet.tms.service.sys.SysFunctionService;
import com.lnet.tms.service.sys.SysRoleService;
import com.lnet.tms.service.sys.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.*;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysFunctionService functionService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SystemUtils systemUtils;

    @Autowired
    private FileManager fileManager;

    @RequestMapping(method = RequestMethod.GET)
    public String layout(ModelMap model) {
        model.addAttribute("currentUser", SecurityUtils.getSubject());
        return "layout";
    }

    @RequestMapping("/home")
    public String home(ModelMap model, HttpServletRequest request) {
        return "home";
    }

    @RequestMapping("/comeSoon")
    public String comeSoon() {
        return "comeSoon";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        model.addAttribute("isAjaxRequest", isAjaxRequest(request));
        return "login";
    }

    private boolean isAjaxRequest(HttpServletRequest request)
    {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(String username, String password, boolean rememberMe, ModelMap model) {

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(rememberMe);
            SecurityUtils.getSubject().login(token);
            return "redirect:/";

        } catch (UnknownAccountException uae) {
            model.addAttribute("message", "用户名不存在");
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("message", "密码不正确！");
        } catch (LockedAccountException lae) {
            model.addAttribute("message", "该用户被禁用！");
        } catch (AuthenticationException ae) {
            model.addAttribute("message", "登录失败！");
        }

        model.addAttribute("username", username);

        return "login";
    }

    @RequestMapping("/initAdmin")
    public String initAdmin() throws InvalidKeySpecException, NoSuchAlgorithmException {

        SysUser admin = userService.getByUsername("admin");
        if (admin == null) {
            admin = new SysUser();
            admin.setUsername("admin");
            admin.setEmail("noreply@56-net.com");
            admin.setPassword("admin123456");
            admin.setIsActive(true);
            admin.setIsAllowLogin(true);
            userService.create(admin);
        } else {
            admin.setPassword("admin123456");
            userService.update(admin);
        }
        return "redirect:/login";
    }

    @RequestMapping("/init")
    public String init() throws InvalidKeySpecException, NoSuchAlgorithmException {

        // clear all user
        userService.deleteAll();

        // create admin account
        SysUser admin = admin = new SysUser();
        admin.setUsername("admin");
        admin.setFullName("admin system");
        admin.setEmail("noreply@56-net.com");
        admin.setPassword("admin123456");
        admin.setIsActive(true);
        admin.setIsAllowLogin(true);
        userService.create(admin);

        // create 10000 users for test
        List<SysUser> users = new LinkedList<>();

        for (int i = 1; i <= 10000; i++) {
            SysUser user = new SysUser();
            String username = "user" + i;
            user.setUsername(username);
            user.setFullName(username + " system");
            user.setPassword("123456");
            user.setIsActive(true);
            admin.setIsAllowLogin(true);
            user.setRemark("random user " + i);

            users.add(user);
        }

        userService.create(users);

        return "redirect:/login";
    }

    @RequestMapping("/initFunctionAndRole")
    public String initFunctionAndRole(){

        // clear all user
//        userService.deleteAll();
        // clear all roles
        roleService.deleteAll();
        // clear all function
        functionService.deleteAll();
        systemUtils.initFunctionAndRole();
        return "redirect:/login";
    }

    @RequestMapping(value = "/picUpload/{pic}", method = RequestMethod.PUT)
    public String save(@PathVariable(value = "pic")String pic,HttpServletRequest request,HttpServletResponse response) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = request.getInputStream();
            File file=fileManager.PicUpload(pic);
            os = new FileOutputStream(file);
            int length = 0;
            byte[] bytes = new byte[4096];
            while((length = is.read(bytes,0,4096))!=-1){
                    os.write(bytes,0,length);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    @RequestMapping(value = "/addFunction", method = RequestMethod.POST)
    public String addFunction(){
        //add
        SysFunction f = new SysFunction();
        functionService.saveOrUpdate(f);

        return "redirect:/login";
    }


}
