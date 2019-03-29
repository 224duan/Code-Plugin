package utils;

import java.util.Locale;

public class StringUtils {
	
	/**
	 * 将string按需要格式化,前面加缩进符,后面加换行符
	 * @param tabNum 缩进量
	 * @param srcString
	 * @return
	 */
	public static String formatSingleLine(int tabNum, String srcString) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<tabNum; i++) {
			sb.append("\t");
		}
		sb.append(srcString);
		sb.append("\n");
		return sb.toString();
	}
	
	public static String firstToUpperCase(String key) {
		return key.substring(0, 1).toUpperCase(Locale.getDefault()) + key.substring(1);
	}

	/**
	 * 驼峰转下划线命名
     */
	public static String camel2underline(String src) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbWord = new StringBuilder();
		char[] chars = src.trim().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if(c >= 'A' && c <= 'Z') {
				// 一旦遇到大写单词，保存之前已有字符组成的单词
				if(sbWord.length() > 0) {
					if(sb.length() > 0) {
						sb.append("_");
					}
					sb.append(sbWord.toString());
				}
				sbWord = new StringBuilder();
			}
			sbWord.append(c);
		}

		if(sbWord.length() > 0) {
			if(sb.length() > 0) {
				sb.append("_");
			}
			sb.append(sbWord.toString());
		}

		return sb.toString();
	}

	/**
	 * 获取一个首字母大写的字符
	 * */
	public static String getCharacters(int length){

		if (length == 0){
			return "";
		}

		StringBuilder builder = new StringBuilder();
		while (builder.length() < length){
			int index = (int) (Math.random() * 26 + 97);
			builder.append(byteAsciiToChar(index));
		}
		return captureName(builder.toString());
	}


	// 首字母大写
	public static String captureName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
	}


	public static char byteAsciiToChar(int ascii) {
		char ch = (char) ascii;
		return ch;
	}


//	public static List<String> getListFromJson(Object object, String name){
//
//		try {
//			List<String> content = Files.readAllLines(Paths.get(StringUtils.class.getClassLoader().getResource(name).toURI()));
//			StringBuilder result = new StringBuilder();
//			content.forEach(result::append);
//			JsonElement jelement = new JsonParser().parse(result.toString());
//			JsonArray jsonArray =  jelement.getAsJsonArray();
//			List<String>  finalResult = new ArrayList<>(jsonArray.size());
//			for (JsonElement jsonElement : jsonArray) {
//				finalResult.add(jsonElement.getAsString());
//			}
//			System.out.println("解析成功");
//			return finalResult;
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//
//		return null;
//	}
}
