package adtec.adtec_message.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilGetMsg {

	/**
	 * 通过xml参数中的orgId获取机构的层级
	 * @param orgId
	 * @return
	 */
	public static Map<String,String> getLevel(String orgId){
		Map<String,String> ins = new HashMap<String,String>();
		//校验传递过来的orgId是否正确
		if(orgId.length() < 2){
			return ins;
		}
		else{
			String SpaceSeparator[] = orgId.split("");
			if(SpaceSeparator.length == 3){
				ins.put("level1", orgId);
				return ins;
			}else{
				
			
			String separator = SpaceSeparator[3];
			//校验xml中参数orgId中是否全部都是数字
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(orgId);
			if(isNum.matches()){
				int times = orgId.length()/2;
				for(int j=0;j<times;j++){
					String InterceptOrgId = orgId.substring(j*2,j*2+2);
					ins.put("level"+(j+1), InterceptOrgId);
					if(j==6){
						break;
					}
				}
				return ins;
			}else{
					if(separator.equals(".") || separator.equals("|")){
						separator = "\\"+separator;
						String levelAll[] = orgId.split(separator);
						for(int i=0;i<levelAll.length;i++){
							ins.put("level"+(i+1), levelAll[i]);
							if(i==6){
								break;
							}
						}
						return ins;
					}
				else{
					String levelAll[] = orgId.split(separator);
					for(int i=0;i<levelAll.length;i++){
						ins.put("level"+(i+1), levelAll[i]);
						if(i==6){
							break;
						}
					}
					return ins;
				}
			}
		}
		}
	}

}
