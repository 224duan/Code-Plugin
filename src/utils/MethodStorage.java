package utils;
import groovy.json.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class MethodStorage {

    private String[] allJavaImplements;
    private String[] allKtImplements;


    private static MethodStorage instance;

    private MethodStorage (){

        allJavaImplements = new String[]{

                "int xx = 10;      while( xx < 13 ) {     xx++;     }",
                "byte x = 2;        x += 3;        int result = 0;        for (byte i =0; i < x; x++){            result += i;        }",
                "int a,b,c,d;        int s[]=new int[4];        boolean flag ;        for(int i=1000;i<10000;i++){        s[0]=i/1000;        s[1]=i%1000/100;        s[2]=i%100/10;        s[3]=i%10;        flag = false;   if(s[2]==0&&s[3]==0){            continue;        } }",
                "for(int x4 = 10; x4 < 20; x4 = x4+1) {         System.out.print(x4 );         }",
                "int [] numbers = {10, 20, 30, 40, 50};       for(int gg : numbers ) { if( gg == 30 ) {            break;         }         System.out.print( gg );           }",
                "int ii = 5;      switch(ii){         case 0:            System.out.println(\"0\");         case 1:            System.out.println(\"1\");         case 2:            System.out.println(\"2\");         default:            System.out.println(\"default\");      }",
                "char[] helloArray = { 'r', 'u', 'n', 'o', 'o', 'b'};      String helloString = new String(helloArray);        System.out.println( helloString );",
                "StringBuffer sBuffer = new StringBuffer(\"菜鸟教程官网：\");    sBuffer.append(\"www\");    sBuffer.append(\".runoob\");    sBuffer.append(\".com\");    System.out.println(sBuffer);",
                "int size = 10; double[] myList = new double[size];      myList[0] = 5.6;      myList[1] = 4.5;      myList[2] = 3.3;      myList[3] = 13.2;      myList[4] = 4.0;      myList[5] = 34.33;      myList[6] = 34.0;      myList[7] = 45.45;      myList[8] = 99.993;      myList[9] = 11123;   double total = 0;      for (int ix = 0; ix < size; ix++) {         total += myList[ix];      }      System.out.println(\\\"总和为： \\\" + total);",
                "try{         int aaa[] = new int[2];         System.out.println(\"Access element three :\" + aaa[3]);      }catch(ArrayIndexOutOfBoundsException e){         System.out.println(\"Exception thrown  :\" + e);      }      System.out.println(\"Out of the block\");"
        };



        allKtImplements = new String[]{

                "val a = 9"+
                        "val b = 4"+
                        "var max = a \n" +
                        "if (a < b) max = b\n" +
                        "if (a > b) {\n" +
                        "    max = a\n" +
                        "} else {\n" +
                        "    max = b\n" +
                        "}\n",

                "var x = 2 \n" +
                        "when (x) {\n" +
                        "    1 -> print(\"x == 1\")\n" +
                        "    2 -> print(\"x == 2\")\n" +
                        "    else -> { \n" +
                        "        print(\"x is neither 1 nor 2\")\n" +
                        "    }\n" +
                        "}\n",

                "var separator: String = \", \"\n" +
                        "    var prefix: String = \"\"\n" +
                        "    var postfix: String = \"\" \n",

                "val dividend = 25\n" +
                        "    val divisor = 4\n" +
                        "\n" +
                        "    val quotient = dividend / divisor\n" +
                        "    val remainder = dividend % divisor\n" +
                        "\n" +
                        "    println(\"Quotient = $quotient\")\n" +
                        "    println(\"Remainder = $remainder\")\n",

                "val n1 = -4.5\n" +
                        "    val n2 = 3.9\n" +
                        "    val n3 = 2.5\n" +
                        "\n" +
                        "    if (n1 >= n2 && n1 >= n3)\n" +
                        "        println(\"$n1 is the largest number.\")\n" +
                        "    else if (n2 >= n1 && n2 >= n3)\n" +
                        "        println(\"$n2 is the largest number.\")\n" +
                        "    else\n" +
                        "        println(\"$n3 is the largest number.\")\n",

                "val year = 1900\n" +
                        "    var leap = false\n" +
                        "\n" +
                        "    if (year % 4 == 0) {\n" +
                        "        if (year % 100 == 0) {\n" +
                        "            // year is divisible by 400, hence the year is a leap year\n" +
                        "            leap = year % 400 == 0\n" +
                        "        } else\n" +
                        "            leap = true\n" +
                        "    } else\n" +
                        "        leap = false\n",

                "val num = 10\n" +
                        "    var factorial: Long = 1\n" +
                        "    for (i in 1..num) {\n" +
                        "        // factorial = factorial * i;\n" +
                        "        factorial *= i.toLong()\n" +
                        "    }\n",

                "val rows = 5\n" +
                        "\n" +
                        "    for (i in 1..rows) {\n" +
                        "        for (j in 1..i) {\n" +
                        "            print(\"$j \")\n" +
                        "        }\n" +
                        "        println()\n" +
                        "    }\n"
        };
    }

    public static MethodStorage getInstance() {
        if (instance == null) {
            instance = new MethodStorage();
        }
        return instance;
    }

    public List<String> getMethodNames(int count){


        ArrayList<String> names = new ArrayList<>();

        while (names.size() < count){

            String n = getAMethodName((int) (Math.random() * 3 + 3));
            if (!names.contains(n)){
                names.add(n);
            }
        }
        return names;
    }

    /**
     * 获取方法名
     * count : 单词数量
     * */
    public String getAMethodName(int count){

        StringBuilder builder = new StringBuilder();
        while (count > 0){

            int length = (int) (Math.random() * 4 + 3);
            builder.append(StringUtils.getCharacters(length));
            count--;
        }
        return builder.toString();
    }


    public String getJavaImplement(){

        return getImplement(allJavaImplements);
    }

    public String getKtImplement(){
        return getImplement(allKtImplements);
    }

    private String getImplement(String[] allImplements){

        int count = (int) (Math.random() * 2 + 1);
        ArrayList<String> container = new  ArrayList<>();

        int index = 0;
        StringBuilder builder = new StringBuilder("{");
        while (count > 0){
            index = (int) (Math.random() * allImplements.length);
            String str = allImplements[index];
            if (!container.contains(str)){
                builder.append(str);
                container.add(str);
                count--;
            }
        }

        builder.append("\n\n }");

        return StringEscapeUtils.unescapeJava(builder.toString());
    }





}
