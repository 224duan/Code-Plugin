package utils;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.psi.KtClass;
import org.jetbrains.kotlin.psi.KtNamedFunction;

public class MethodUtils {



    /**
     * 获取方法名前缀
     * */
    public static String getPrefixOfMethod(@NotNull String clazzName){
        return "useless" + clazzName + "_";
    }

    // java

    /**
     * 判读是不是目标名称
     **/
    public static Boolean isTargetMethod (PsiClass clazz, PsiMethod method){

        if (method != null){
            String prefix = getPrefixOfMethod(clazz.getName());
            if (prefix != null){
                return method.getName().startsWith(prefix);
            }
        }
        return false;
    }

    /**
     * 将方法名转成目标格式
     * */
    public static void changeFormatOfMethodName( PsiClass clazz, PsiMethod method){

        if (!isTargetMethod(clazz, method)){
            if (clazz != null && method != null){
                String name = getPrefixOfMethod(clazz.getName()) + method.getName();
                method.setName(name);
            }
        }
    }


    // kotlin

    public static Boolean isTargetFunction (KtClass clazz, KtNamedFunction function){

        if (function != null){
            String prefix = getPrefixOfMethod(clazz.getName());
            if (prefix != null){
                return function.getName().startsWith(prefix);
            }
        }
        return false;
    }

    public static void changeFormatOfFunctionName(KtClass clazz, KtNamedFunction function){

        if (!isTargetFunction(clazz, function)){
            if (clazz != null && function != null){
                String name = getPrefixOfMethod(clazz.getName()) + function.getName();
                function.setName(name);
            }
        }
    }
}
