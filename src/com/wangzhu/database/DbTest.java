package com.wangzhu.database;

import java.util.List;
import java.util.Map;

import com.wangzhu.OtherUtils;

public class DbTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
	List<Map<String, Object>> list = null;
	// System.out.println(DbUtils.INSTANCE.query(
	// "SELECT TABLE_NAME FROM USER_TABLES WHERE NUM_ROWS > ?",
	// new Object[] { "10000" }));

	// IDbHelpers helpers = new DbHelpers();
	// list = helpers
	// .query("select * from fbk_ydzd_zbgl_source where fbzd_fjd = ? and fhcc = ?",
	// Arrays.asList(new Object[] { "0300", 0 }));
	//

	list = DbUtils.INSTANCE.callProcedure("call P_FBZD(?,?,?)");
	for (Map<String, Object> map : list) {
	    System.out.println(map);
	    map = null;
	}
	list = null;
	OtherUtils.logMemory();

    }

}
