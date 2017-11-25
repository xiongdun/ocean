/**
 * 
 */
package test;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.brucex.common.sercurity.MD5Utils;
import com.brucex.common.utils.StringUtils;
import com.brucex.modules.sys.entity.User;

/**
 * @description 
 * @author xiongdun
 * @datetime 2017年4月8日下午11:44:18
 */
public class CommonTest {

	//@Test
	public void test1() {
		System.out.println(MD5Utils.convertStrToMD5("admin"));
	}
	
	
	List<User> users = new ArrayList<User>();
	{
		users.add(new User("熊墩", "深圳市", "15717008024", "362226199407180912", "1274328268@qq.com"));
		users.add(new User("熊墩", "武汉市", "15717008024", "362226199407180912", "1274328268@qq.com"));
		users.add(new User("熊墩", "长沙市", "15717008024", "362226199407180912", "1274328268@qq.com"));
		users.add(new User("小泽", "深圳市", "17620355713", "362226199307181520", "756850212@qq.com"));
		users.add(new User("小泽", "南昌市", "17620355713", "362226199307181520", "756850212@qq.com"));
		users.add(new User("小泽", "成都市", "17620355713", "362226199307181520", "756850212@qq.com"));
		users.add(new User("甜甜", "西安市", "15717008024", "362226199407180912", "121212121@qq.com"));
		users.add(new User("甜甜", "上海市", "15717008024", "362226199407180912", "121212122@qq.com"));
		users.add(new User("甜甜", "北京市", "15717008024", "362226199407180912", "121212121@qq.com"));
	}
	//@Test
	public void test2() {
		String name = users.get(0).getName();
		for (User user : users) {
			if (StringUtils.equals(name, user.getName())) {
				System.out.println(user.getLoginName());
			} else {
				name = user.getName();
			}
			System.out.println(user);
		}
	}
	
	@Test
	public void test3() {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontName = environment.getAvailableFontFamilyNames();
		for (int i = 0; i < fontName.length; i++) {
			System.out.println(fontName[i]);
		}
				
	}
	
	public static void main(String[] args) {
		
	}
	
	
}
