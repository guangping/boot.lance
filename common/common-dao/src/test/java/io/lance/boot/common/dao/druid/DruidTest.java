package io.lance.boot.common.dao.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;

/**
 * @desc:
 * @author: lance
 * @time: 2017-10-16 15:17
 */
public class DruidTest {

    private String sql = "select * from ebs_bp_main t where t.item_code='ebs_bp_main'\n" +
            " and t.id in( select id from a) order by t.create_time";

    String dbType = JdbcConstants.MYSQL;

    @Test
    public void runMain(){
        //格式sql
        String result = SQLUtils.format(sql, dbType);
        System.out.printf(result);

        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        //解析出的独立语句的个数
        System.out.println("size is:" + stmtList.size());


        stmtList.forEach(data->{
            MySqlSchemaStatVisitor visitor=new MySqlSchemaStatVisitor();
            data.accept(visitor);

            System.out.println(JSONObject.toJSONString(visitor));

          /*  System.out.println("table name is :"+visitor.getCurrentTable());
            System.out.println("alias name is :"+visitor.getTables());
            System.out.println("column is :"+visitor.getColumns());*/

            List<TableStat.Condition> conditions=visitor.getConditions();
            conditions.forEach(condition -> { });


        });
    }

}
