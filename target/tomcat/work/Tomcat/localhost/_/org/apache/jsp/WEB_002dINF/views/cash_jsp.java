/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-04-30 12:48:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class cash_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("    <title>收银管理</title>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/icon.css\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"application/javascript\" src=\"/js/jquery-easyui/locale/easyui-lang-zh_CN.js\"></script>");
      out.write("\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/views/cash.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<form id=\"cash_form\" method=\"post\">\r\n");
      out.write("<div data-options=\"region:'north',split:true\" style=\"padding:8px;\">\r\n");
      out.write("        收银编号:<input type=\"text\" name=\"proSn\">\r\n");
      out.write("    <a class=\"easyui-linkbutton btn_cash\" id=\"cash_btn\" style=\"float: right;\">收银记录</a>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</form>\r\n");
      out.write("<div id=\"item_dialog\">\r\n");
      out.write("    <table id=\"pro_data\" style=\"margin-top: 15px;\"></table>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 数据表格CRUD按钮 -->\r\n");
      out.write("<div id=\"cash_datagrid_tb\">\r\n");
      out.write("    <div>\r\n");
      out.write("        <a class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" data-cmd=\"addItem\">添加明细</a>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 对话框保存取消按钮 -->\r\n");
      out.write("<div id=\"pro_data_bt\">\r\n");
      out.write("    <a class=\"easyui-linkbutton\" iconCls=\"icon-save\" plain=\"true\" data-cmd=\"choose\">选择商品</a>\r\n");
      out.write("</div>\r\n");
      out.write("<div data-options=\"region:'south',split:true\" style=\"height: 200px\">\r\n");
      out.write("    <div data-options=\"region:'center'\" style=\"height:330px;\">\r\n");
      out.write("        <!-- 数据表格 -->\r\n");
      out.write("        <table id=\"cash_datagrid\">\r\n");
      out.write("        </table>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div>\r\n");
      out.write("        <table style=\"width: 90%; height: 50px; margin-left: 60px\">\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>会员卡号：\r\n");
      out.write("                    <input id=\"number\" name=\"number\" style=\"height:30px;width:150px;\" type=\"text\"/>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>会员姓名：\r\n");
      out.write("                    <input name=\"name\" style=\"width:50px;border:0px \"/>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>现有金额：\r\n");
      out.write("                    <input name=\"balance\" style=\"width:50px;border:0px \"/>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>会员等级：\r\n");
      out.write("                    <input name=\"levelName\" style=\"width:50px;border:0px \"/>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>商品折扣：\r\n");
      out.write("                    <input name=\"levelProducteDisCount\" style=\"width:50px;border:0px \"/>%\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>服务折扣：\r\n");
      out.write("                    <input name=\"levelServiceDisCount\" style=\"width:50px;border:0px \"/>%\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div style=\"width: 100%;  margin-left: 60px;\">\r\n");
      out.write("        <span style=\"width: 80px\">应收金额：</span>\r\n");
      out.write("        <input id=\"price\" name=\"Price\" style=\"height:30px;width:150px;font-size:20px\" type=\"text\" value=\"0\"/>\r\n");
      out.write("        <span>元</span>\r\n");
      out.write("        <span style=\"margin-left: 15px\">实收金额：</span>\r\n");
      out.write("        <input data-val=\"true\"\r\n");
      out.write("               id=\"discountPrice\" name=\"discountPrice\" style=\"height:30px;width:150px;font-size:20px\" type=\"text\"\r\n");
      out.write("               value=\"\"/>\r\n");
      out.write("        <span>元</span>\r\n");
      out.write("    </div>\r\n");
      out.write("    <br/>\r\n");
      out.write("    <div class=\"botbtbx pdb0\" style=\"width: 100%;  margin-left: 60px\">\r\n");
      out.write("        <a class=\"easyui-linkbutton\" id=\"payMoney\" style=\"float: left; \">会员结账</a>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
