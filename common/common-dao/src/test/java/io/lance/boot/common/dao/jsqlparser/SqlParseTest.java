package io.lance.boot.common.dao.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.junit.Test;

/**
 * @desc: jsqlparser test
 * @author: lance
 * @time: 2017-10-16 11:37
 */
public class SqlParseTest {


    private String sql = "select * from ebs_bp_main t where t.name='aa' and t.item_code='ebs_bp_main' and t.id='1' order by t.create_time";


    @Test
    public void romveOrderBy() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        if (!(statement instanceof Select)) {
            return;
        }
        Select select = (Select) statement;
        SelectBody selectBody = select.getSelectBody();
        System.out.println(selectBody);
        if (!(selectBody instanceof PlainSelect)) {
            return;
        }
        PlainSelect plainSelect = (PlainSelect) selectBody;
        //删除order by
        plainSelect.setOrderByElements(null);

        Expression where = plainSelect.getWhere();


        if (!(where instanceof EqualsTo)) {
            return;
        }
        EqualsTo equalsTo = (EqualsTo) where;
        System.out.println(equalsTo);


        System.out.println(select.toString());


    }


}
