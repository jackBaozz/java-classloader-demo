package test;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import com.alibaba.fastjson.JSONObject;

import uRLClassLoaderTest.ActionInterface;

public class ClassLoaderTest {

	
	public static final String URL1 = "http://127.0.0.1:8777/ping";
	
	public static void main(String args[]) {
		try {
            String s = new String();

            //idea放入resources文件夹下的rtest.txt文件使用一下方法获取
            InputStream in = ClassLoaderTest.class.getClassLoader().getResourceAsStream("rtest.txt");
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);


            //eclipse把文件放入跟pom.xml平级的路径,可以用以下获取
			//File file = new File("rtest.txt");
			//BufferedReader in = new BufferedReader(new FileReader(file));
			//while ((s = in.readLine()) != null) {
            while ((s = br.readLine()) != null) {
				URL url = new URL(s);
				s = null;
				
				//写法1,从txt文本读取
				/*URLClassLoader myClassLoader = new URLClassLoader(new URL[] { url },
						Thread.currentThread().getContextClassLoader());
				Class myClass = myClassLoader.loadClass("uRLClassLoaderTest.TestAction");
				ActionInterface action = (ActionInterface) myClass.newInstance();
				String str = action.action();
				System.out.println(str);*/




				//写法2.1,从URL获取jar包
                // url有两种写法. file:XXXXXXXXXX    File(str).toURI().toURL()

				//String str = "C:\\Users\\Administrator\\Documents\\test.jar";
                String str = "D:\\Users\\Asus\\WorkSpaces_IntelliJ_IDEA\\java-classloader-demo\\out\\artifacts\\classloader_2_jar\\test.jar";
				URL url1 = new File(str).toURI().toURL();

				//URL url2 = new URL("file:C:/Users/Administrator/Documents/test.jar");
				URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url1 },
						Thread.currentThread().getContextClassLoader());
				Class myClass1 = myClassLoader1.loadClass("uRLClassLoaderTest.TestAction");
				ActionInterface action1 = (ActionInterface) myClass1.newInstance();
				String str1 = action1.action();
				System.out.println(str1);
				myClass1 = null;
				action1 = null;
				//开始卸载
	            // 将加载这个类的URLClassLoader的引用置为null，以便让这个类释放  
				myClassLoader1 = null;  
	            // 启动垃圾回收（JVM虚拟机规范中明确说明，这个方法并不能保证垃圾回收一定执行，但是在此处的确有执行）
                // 必须直接run过才能看见卸载信息,如果调试一步一步,就看不到
	            System.gc();
                System.out.println("卸载完毕");






				//写法3,直接反射获取方法,方法调用对象+参数
				/*URLClassLoader myClassLoader = new URLClassLoader(new URL[] { url },
						Thread.currentThread().getContextClassLoader());
				Class<?> clazz = myClassLoader.loadClass("com.bzz.httpclient.httpclient_demo.App");
				Object obj = clazz.newInstance();//父类实例
	            Method method = clazz.getDeclaredMethod("httpGet",String.class);
	            JSONObject a = (JSONObject)method.invoke(obj,URL1);
	            System.out.println(a);
	            myClassLoader.close();*/
	            
	            
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
