package com.toj.jr;

import com.toj.pojo.Record;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WorkRecordDataSource implements JRDataSource {
    private Iterator iter;

    //创建一个，map对象用与数据对应
    Map map = new HashMap();

    //无参的构造函数
    public WorkRecordDataSource(){

    }

    public WorkRecordDataSource(List<Map> list,List hList,String s_date,String pname){
        //通过性别获取相应用户的数据
        List<WorkRecordBean> datas=WorkRecordFactory.getBeanCollection(list,hList,s_date,pname);
        //要将List中的数据迭代，需要使用Iterator迭代对象
        iter=datas.iterator();
    }
    public WorkRecordDataSource(List<Map> list,List hList,String pname){
        //通过性别获取相应用户的数据
        List<WorkRecordBean> datas=WorkRecordFactory.getBeanCollection(list,hList,pname);
        //要将List中的数据迭代，需要使用Iterator迭代对象
        iter=datas.iterator();
    }

    //通过key获取value值
    @Override
    public Object getFieldValue(JRField arg0) throws JRException {
        return map.get(arg0.getName());
    }

    //接口JRDataSource的方法，判断是否有下一个数据
    @Override
    public boolean next() throws JRException {
        if(iter.hasNext()){
            map=(Map)iter.next();
            return true;
        }
        return false;
    }
}
