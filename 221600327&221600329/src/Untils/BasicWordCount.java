package Untils;


import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.regex.*;
import Bean.Word;
public class BasicWordCount {
	
	//比较器对象
	  private Comparator<Word> comp =
	      (o1, o2) -> {
	        if (o1.getCountNum() > o2.getCountNum()) {
	          return 1;
	        } else if (o1.getCountNum() < o2.getCountNum()) {
	          return -1;
	        }else if(o1.getCountNum() == o2.getCountNum()) {
	        	if(o1.getKey().compareTo(o2.getKey())<0) {
	        		return 1;
	        	}else {
	        		return -1;
	        	}
	        }
	        return 0;
	      };

	/**
	 * 计算文件字符数
	 * 
	 * @param fileName 文件名
	 * @return countOfCharacter 字符数
	 */
	public long characterCount(String fileName) {
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = new StringBuffer();
		//读入文件数据
		bufferedReader = readFile(fileName);
		int byteCode = -1;
		//转换为字符串 
		try {
			while((byteCode = bufferedReader.read()) != -1) {
				//将十进制ASCII码值，转化为字符，添加到data
				stringBuffer.append((char)byteCode);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//在windows中，换行符为/r/n,统计时需要替换为/n
		return stringBuffer.toString().replaceAll("\r\n", "\n").length();	
	}
	
	public long wordCount(String fileName) {
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = new StringBuffer();
		//读入文件数据
		bufferedReader = readFile(fileName);
		int byteCode = -1;
		//转换为字符串 
		try {
			while((byteCode = bufferedReader.read()) != -1) {
				//将十进制ASCII码值，转化为字符，添加到data
				stringBuffer.append((char)byteCode);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//用分隔符"|"替换字符串中非字母数字的字符
		String noLetterOrDigitalRegex = "[^a-zA-Z0-9]";
		String updateString = stringBuffer.toString().toLowerCase().replaceAll(noLetterOrDigitalRegex, "|");
		//按分隔符"|"，分割字符串
		String splitStrings[] =  updateString.split("\\|");
		
		String regex = "[a-z]{4}.*";
//		Map<String, Integer> countMap = new HashMap<>();
		long test = 0;
		for(int i = 0; i < splitStrings.length; i++) {
			if(Pattern.matches(regex, splitStrings[i])) {
				test++;
//				Integer outValue = countMap.get(splitStrings[i]);
//				if (null == outValue) {
//				      outValue = 0;
//				}
//				outValue++;
//				countMap.put(splitStrings[i], outValue);
			}
		}
		
		return test;
	}
	
	public Word[] topTenWord(String fileName) {
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = new StringBuffer();
		//读入文件数据
		bufferedReader = readFile(fileName);
		int byteCode = -1;
		//转换为字符串 
		try {
			while((byteCode = bufferedReader.read()) != -1) {
				//将十进制ASCII码值，转化为字符，添加到data
				stringBuffer.append((char)byteCode);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//用分隔符"|"替换字符串中非字母数字的字符
		String noLetterOrDigitalRegex = "[^a-zA-Z0-9]";
		String updateString = stringBuffer.toString().toLowerCase().replaceAll(noLetterOrDigitalRegex, "|");
		//按分隔符"|"，分割字符串
		String splitStrings[] =  updateString.split("\\|");
		
		String regex = "[a-z]{4}.*";
		//进行统计的散列表
		Map<String, Integer> countMap = new HashMap<>();
		for(int i = 0; i < splitStrings.length; i++) {
			if(Pattern.matches(regex, splitStrings[i])) {
				Integer outValue = countMap.get(splitStrings[i]);
				if (null == outValue) {
				      outValue = 0;
				}
				outValue++;
				countMap.put(splitStrings[i], outValue);
			}
		}
		//求top10
		PriorityQueue<Word> topN = new PriorityQueue<>(10, comp);
	    Iterator<Map.Entry<String, Integer>> iter = countMap.entrySet().iterator();
	    Map.Entry<String, Integer> entry;
	    while (iter.hasNext()) {
	      entry = iter.next();
	      if (topN.size() < 10) {
	        topN.offer(new Word(entry.getKey(), entry.getValue()));
	      } else {
	        // 如果当前数据比小顶堆的队头大，则加入，否则丢弃
	        if (topN.peek().getCountNum() < entry.getValue()) {
	          topN.poll();
	          topN.offer(new Word(entry.getKey(), entry.getValue()));
	        }
	      }
	    }
	    //结果集
	    Word[] result = new Word[10];
	    topN.toArray(result);
		
	    //对top10单词排序
	    Arrays.sort(result, comp);
		return result;
	}
	
	/**
	 * 读入指定文件名的文件数据
	 * 
	 * @param fileName  文件名
	 */
	public BufferedReader readFile(String fileName) {
		InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
       
        //字节流方式读入文件
        try {
        	inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
        	if(inputStreamReader != null) {
        		bufferedReader= new BufferedReader(inputStreamReader);
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return bufferedReader;
	}
}
