package com.lcy.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.lcy.common.R;
import com.lcy.entity.User;
import com.lcy.entity.vo.UserVo;
import com.lcy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody User user){
        boolean flag = userService.insertUser(user);
        if (!flag){
            return R.error("注册失败！");
        }else {
            return R.success("注册成功！");
        }
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param captchaCode
     * @param request
     * @return
     */
    @PostMapping("/login")
    public R login(String username,String password,@RequestParam("captchaCode") String captchaCode, HttpServletRequest request){
        String sessionCode = String.valueOf(request.getSession().getAttribute("verifyCode")).toLowerCase();
        System.out.println("session里的验证码：" + sessionCode);
        String receivedCode = captchaCode.toLowerCase();
        System.out.println("用户的验证码：" + receivedCode);
        if(!"".equals(sessionCode) && !"".equals(receivedCode) && sessionCode.equals(receivedCode)){
            //1 获取 Subject 对象
            Subject subject = SecurityUtils.getSubject();
            //2 封装请求数据到 token 对象中
            AuthenticationToken token = new UsernamePasswordToken(username,password);
            //3 调用 login 方法进行登录认证
            try {
                subject.login(token);
                return R.success("登录成功！");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                System.out.println("登录失败！");
                return R.error("登录失败！");
            }
        }
        return R.error("验证码错误！");
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public R logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        return R.success("退出成功！");
    }

    /**
     * 分页查询 可以根据name和username模糊查询
     * @param page
     * @param pageSize
     * @param name
     * @param username
     * @return
     */
    //@RequiresRoles(value = {"系统管理员","超级管理员"},logical = Logical.OR)
    @GetMapping("/page")
    public R page(@RequestParam(value = "page",defaultValue = "1") Integer page,
                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, String name, String username){
        return userService.page(page,pageSize,name,username);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    //@RequiresPermissions("user:delete")
    @DeleteMapping()
    public R remove(Integer id){
        Boolean flag = userService.removeUser(id);
        if (flag){
            return R.success("删除成功！");
        }
        return R.error("删除失败！");
    }

    /**
     * 启用用户
     * @param id
     * @return
     */
    //@RequiresPermissions("user:enable")
    @PutMapping("/enable")
    public R enableUser(Integer id){
        Boolean flag = userService.enableUser(id);
        if (flag){
            return R.success("修改用户状态成功！");
        }
        return R.error("修改用户状态失败！");
    }

    /**
     * 登录时查询用户信息
     * @param id
     * @return
     */
    //@RequiresRoles(value = {"系统管理员","超级管理员"},logical = Logical.OR)
    @GetMapping("/userInfo")
    public R<UserVo> userInfo(Integer id){
        UserVo userInfo = userService.userInfo(id);
        if (userInfo != null){
            return R.success(userInfo);
        }
        return R.error("出错啦！");
    }

    /*
    后台管理员新增用户
     */
    //@RequiresPermissions("user:add")
    @PostMapping
    public R insertUser(@RequestBody User user){
        boolean flag = userService.insertUser(user);
        if (flag){
            return R.success("添加成功！");
        }
        return R.error("添加失败！");
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    //@RequiresPermissions("user:edit")
    @PutMapping("/edit")
    public R editUser(@RequestBody User user){
        boolean flag = userService.updateById(user);
        if (flag){
            return R.success("修改成功！");
        }
        return R.error("修改失败！");
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/verify")
    public void verify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        shearCaptcha.write(response.getOutputStream());
        //获取验证码中的文字内容
        System.out.println(shearCaptcha.getCode());
        request.getSession().setAttribute("verifyCode", shearCaptcha.getCode());
    }




}
