package com.solvd.laba.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class SqlDateAdapter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date unmarshal(String v) throws Exception {
        return new Date(dateFormat.parse(v).getTime());
    }

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }
}
