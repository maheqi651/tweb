package com.easymap.ticket.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClearRepeat {
	/*@SuppressWarnings("rawtypes")
	public static List<Map> clearRepeat(List<Map> list,String proCol){
		List<Map> alist = new ArrayList<Map>();
		Map map = list.get(0);
		String sName = map.get(proCol).toString().trim();
		alist.add(map);
		for(int i = 1 ; i < list.size();i++){
			Map m = list.get(i);
			String tName = m.get(proCol).toString().trim();
			if(sName.equals(tName)){
				continue;
			}else{
				alist.add(m);
			}
		}
		clearRepeat(alist,proCol);
		return alist;
	}
	*/
	
	
	public static  List<Map>   clearRepeats(List<Map> list,String proCol)
	{
		list.size();
		List<Map> result = new ArrayList<Map>();
		Map<String,String> flagmap=new HashMap<String,String>();
		for(Map map:list)
		{   
			 
			if(flagmap.containsKey(map.get(proCol)))
			{//包含
				//list.remove(map);
			}else{
				result.add(map);
				flagmap.put((String)map.get(proCol), "1");
			}
		}
		return result;
	}
	public static void main(String[] args) {
		/*String s = "QYMC,BGRQ";
		int index = s.indexOf(",");
		s = s.substring(0,index);
		System.out.println(s);*/
		List<Map> list = new ArrayList<Map>();
		Map map = new HashMap();
		Map map1 = new HashMap();
		Map map2 = new HashMap();
		Map map3 = new HashMap();
		Map map4 = new HashMap();
		map.put("QYMC", "广州市垒才贸易有限公司");
		map.put("BGRQ", "2010-08-18 16:27:32.0");
		map.put("CLRQ", "2010-08-18 00:00:00.0");
		map.put("SFZJHM", "412829197703202423");
		
		map1.put("QYMC", "广州市垒才贸易有限公司");
		map1.put("BGRQ", "2010-08-18 16:27:32.0");
		map1.put("CLRQ", "2010-08-18 00:00:00.0");
		map1.put("SFZJHM", "412829197703202423");
		
		map2.put("QYMC", "服务顾问有限公司");
		map2.put("BGRQ", "2010-08-18 16:27:32.0");
		map2.put("CLRQ", "2010-08-18 00:00:00.0");
		map2.put("SFZJHM", "412829197703202423");
		map3.put("QYMC", "服务顾问有限公司");
		map3.put("BGRQ", "2010-08-18 16:27:32.0");
		map3.put("CLRQ", "2010-08-18 00:00:00.0");
		map3.put("SFZJHM", "412829197703202423");
		map4.put("QYMC", "菲儿有限公司");
		map4.put("BGRQ", "2010-08-18 16:27:32.0");
		map4.put("CLRQ", "2010-08-18 00:00:00.0");
		map4.put("SFZJHM", "412829197703202423");
		list.add(map);
		list.add(map1);
		list.add(map3);
		list.add(map4);
		List<Map> result=clearRepeats(list,"QYMC");
		for(Map<Object,Object> maps:result)
		{
			System.out.println(maps.get("QYMC"));
		}
		
		 
	}
}
