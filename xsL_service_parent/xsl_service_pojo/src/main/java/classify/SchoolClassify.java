package classify;

public class SchoolClassify {
//    //Form实体类，对应tb_form表
//    private Form form;
//    //FormModule实体类，对应tb_form_module表
//    private FormModule fmodule;
//    //页面输出内容变量
//    private StringBuffer sb=new StringBuffer();
//    //数据访问类,定义全局变量
//    private HibernateDB hdb;
//
//
//    public Form getForm() {
//        return form;
//    }
//    public void setForm(Form form) {
//        this.form = form;
//    }
//    public FormModule getFmodule() {
//        return fmodule;
//    }
//    public void setFmodule(FormModule fmodule) {
//        this.fmodule = fmodule;
//    }
//
//
//    public StringBuffer getSb() {
//        return sb;
//    }
//    public void setSb(StringBuffer sb) {
//        this.sb = sb;
//    }
//
//    /**
//     * 自动生存表单
//     * @return string
//     */
//    public String createForm(){
//        hdb=new HibernateDB();
//        //查询Form表单信息
//        List<Object> list=hdb.querySql("from Form");
//        //生存From表单
//        for(int i=0;i<list.size();i++){
//            form=(Form)list.get(i);
//            sb.append("<table><form ");
//            sb.append("action=\""+form.getAction()+"\" ");
//            sb.append("method=\""+form.getMethod()+"\">");
//            sb.append("<caption>"+form.getFormtitle()+"</caption>");
//            //添加表单内容
//            List<Object> listl=hdb.querySql("from FormModule where formid="+form.getId());
//            //使用迭代器
//            Iterator<Object> it=listl.iterator();
//            while(it.hasNext()){
//                fmodule=(FormModule)it.next();
//                //判断是否为非空
//                if(fmodule.getNotnull()>0){
//                    //添加必填标识
//                    sb.append("<tr><td>"+fmodule.getInputtitle()+"<font style=\"color: red;\">*</font></td>");
//                }else{
//                    sb.append("<tr><td>"+fmodule.getInputtitle()+"</td>");
//                }
//                sb.append("<td><input name=\""+fmodule.getInputname()+"\" type=\""+fmodule.getInputtyoe()+"\"/>");
//                sb.append("</td></tr>");
//            }
//            sb.append("<tr><td><input type=\"submit\" value=\"提交\"/></tr></td></form></table>");
//        }
//        return "cform";
//    }
}

