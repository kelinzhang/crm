/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-04-30 07:52:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class baobiao_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/views/common.jsp", Long.valueOf(1493039729433L));
  }

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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\">\r\n");
      out.write("    <meta http-equiv=\"x-ua-compatible\" content=\"IE=edge,chrome=1\">\r\n");
      out.write("    <style type=\"text/css\">\r\n");
      out.write("        .imageButton {\r\n");
      out.write("            float: left;\r\n");
      out.write("            margin-right: 27px;\r\n");
      out.write("            background: #fafbfb;\r\n");
      out.write("            box-shadow: 1px 2px 5px 0 rgba(111, 111, 111, 0.7);\r\n");
      out.write("            width: 70px;\r\n");
      out.write("            height: 70px;\r\n");
      out.write("            padding-top: 10px;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/icon.css\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"application/javascript\" src=\"/js/jquery-easyui/locale/easyui-lang-zh_CN.js\"></script>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/views/baobiao.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"../../js/views/index.js\"></script>\r\n");
      out.write("    <link media=\"all\" href=\"../../css/index.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div  fit=\"true\">\r\n");
      out.write("    <div data-options=\"region:'north'\" style=\"padding-left: 260px;height: 90px\">\r\n");
      out.write("        <div class=\"imageButton\" data-cmd=\"cashMethod\">\r\n");
      out.write("            <img src=\"/images/systemSetting/index2.png\" width=\"30\" style=\"padding-left: 15px\">\r\n");
      out.write("            <p>收银会员报表</p>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"imageButton\" data-cmd=\"pitServiceMethod\">\r\n");
      out.write("            <img src=\"/images/systemSetting/index3.png\" width=\"30\" style=\"padding-left: 15px\">\r\n");
      out.write("            <p>宠物服务报表</p>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"imageButton\" data-cmd=\"stockMethod\">\r\n");
      out.write("            <img src=\"/images/systemSetting/index4.png\" width=\"30\" style=\"padding-left: 15px\">\r\n");
      out.write("            <p>库存报表</p>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\t\r\n");
      out.write("\r\n");
      out.write("\t<div>\r\n");
      out.write("        <iframe style=\"width: 100%;height: 90%\" id=\"showgrid\" name=\"showgrid\" frameborder=\"1\" src=\"../petChart\"></iframe>           \r\n");
      out.write("    </div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
