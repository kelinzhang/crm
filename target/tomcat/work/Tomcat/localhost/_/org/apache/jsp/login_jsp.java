/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-04-27 11:46:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/default/easyui.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/icon.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"application/javascript\" src=\"/js/jquery-easyui/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("    <title>小码哥客户关系管理系统</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/style.css\">\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.min.js\"></script>\r\n");
      out.write("    <script type=\"application/javascript\">\r\n");
      out.write("        $(function () {\r\n");
      out.write("            $(document).keydown(function (event) {\r\n");
      out.write("                if(event.keyCode==13){\r\n");
      out.write("                    loginForm();\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("            $(\"#btn_login\").click(function () {\r\n");
      out.write("            \r\n");
      out.write("            \t\r\n");
      out.write("            \t\r\n");
      out.write("                loginForm();\r\n");
      out.write("            });\r\n");
      out.write("            \r\n");
      out.write("        });\r\n");
      out.write("        function loginForm() {\r\n");
      out.write("            $.post(\"/employee/login\", $(\"form\").serialize(), function (data) {\r\n");
      out.write("                if (data.success) {\r\n");
      out.write("                    window.location.href = \"/index\";\r\n");
      out.write("                } else {\r\n");
      out.write("                    alert(data.msg);\r\n");
      out.write("                }\r\n");
      out.write("            },\"json\");\r\n");
      out.write("        }\r\n");
      out.write("        function chageCode(){\r\n");
      out.write("            $('#codeImage').attr('src','/yanController/valicode.do?abc='+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。\r\n");
      out.write("            \r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<img  class=\"img\" src=\"../../images/login/bai.png\">\r\n");
      out.write("\t</div>\r\n");
      out.write("<section class=\"container\">\r\n");
      out.write("    <div class=\"login\">\r\n");
      out.write("        <h1>用户登录</h1>\r\n");
      out.write("        <form action=\"/employee/login\" method=\"post\">\r\n");
      out.write("            <p><input type=\"text\" name=\"username\" value=\"\" placeholder=\"账号\"></p>\r\n");
      out.write("            <p><input type=\"password\" name=\"password\" value=\"\" placeholder=\"密码\"></p>\r\n");
      out.write("           <p><input  id=\"authCode\" name=\"authCode\" type=\"text\" placeholder=\" 验证码\"></p>\r\n");
      out.write("        <!--这里img标签的src属性的值为后台实现图片验证码方法的请求地址-->\r\n");
      out.write("        <label><img type=\"image\" src=\"/yanController/valicode.do\" id=\"codeImage\" onclick=\"chageCode()\" title=\"图片看不清？点击重新得到验证码\" style=\"cursor:pointer;\"/></label>\r\n");
      out.write("        <label><a onclick=\"chageCode()\">换一张</a></label>\r\n");
      out.write("            \r\n");
      out.write("            \r\n");
      out.write("            \r\n");
      out.write("            <p class=\"submit\">\r\n");
      out.write("                <input id=\"btn_login\" type=\"button\" value=\"登录\">\r\n");
      out.write("                <input type=\"button\" value=\"重置\">\r\n");
      out.write("            </p>\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("</section>\r\n");
      out.write("<div style=\"text-align:center;\" class=\"login-help\">\r\n");
      out.write("    <p>Copyright ©2015 广州小码哥教育科技有限公司</p>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
