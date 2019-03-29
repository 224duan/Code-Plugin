package core;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.psi.KtClass;
import org.jetbrains.kotlin.psi.KtNamedFunction;
import org.jetbrains.kotlin.psi.KtPsiFactory;
import utils.MethodStorage;
import utils.MethodUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CodeKtFactory {

    public static void addMethod(@NotNull Project project, @NotNull KtClass ktClass, double percent) {


        int l = (int) (ktClass.getDeclarations().size() * percent) + 1;
        List<KtNamedFunction> methods = getMethods(project, ktClass, l);
        callEachMethod(project, methods);

        if (methods.size() > 0){
            WriteCommandAction.runWriteCommandAction(project, () -> {

                try {

                    for (KtNamedFunction f : methods){
                        ktClass.getBody().addBefore(f, ktClass.getBody().getRBrace());
                    }

                }catch (IncorrectOperationException e){

                    System.out.println(e.getMessage());
                }
            });
        }
    }

    /**
     * 排序，按照是否为 pubilc 排序
     * 代码关联
     * 按照list 顺序关联
     * */
    public static void callEachMethod(@NotNull Project project, @NotNull List<KtNamedFunction> functions){

        if (functions.size() < 1){
            return;
        }

        KtPsiFactory factory = new KtPsiFactory(project);
        functions.stream().reduce((fm, sm) -> {
            try {
                fm.getBodyExpression().addBefore(factory.createExpression(sm.getName() + "()"),fm.getBodyExpression().getLastChild());
            }catch (IncorrectOperationException e){
                System.out.println(e.getMessage());
            }
            return sm;
        });
    }


    /**
     * 获取一定数量的方法
     * */
    public static List<KtNamedFunction> getMethods(@NotNull Project project,@NotNull KtClass ktClass , int num){

        KtPsiFactory factory = new KtPsiFactory(project);

        List<String> names = MethodStorage.getInstance().getMethodNames(num);

        final AtomicInteger index = new AtomicInteger(0);
        List<String> createTableCodes = names.stream().map(n -> {

            StringBuilder builder = new StringBuilder();
            if (index.getAndAdd(1) == 0){
                builder.append("public ");
            }else {
                builder.append("private ");
            }
            builder.append("fun " + n + "()");
            builder.append(MethodStorage.getInstance().getKtImplement());
            return builder.toString();

        }).collect(Collectors.toList());

        ArrayList<KtNamedFunction> methods = new ArrayList<>();

        // 分两部分，创建名字，增加实现 返回值
        try {

            for (String code : createTableCodes){
                KtNamedFunction function = factory.createFunction(code);
                MethodUtils.changeFormatOfFunctionName(ktClass, function);
                methods.add(function);
            }

        }catch (IncorrectOperationException e){
            System.out.println(e.getMessage());
        }

        System.out.println("方法创建成功");

        return methods;
    }

    public static void invokeMethod(@NotNull Project project,@NotNull KtClass ktClass){}
}


/*
*
//        KtPsiFactory factory = new KtPsiFactory(project);
//        String code = "fun testfun(){ val a = 3 \n\n }";
//        KtNamedFunction function = factory.createFunction(code);
//
//        try {
//
//            function.getBodyExpression().addBefore(factory.createExpression("myfun()"),function.getBodyExpression().getLastChild());
//        }catch (IncorrectOperationException e){
//            System.out.println(e.getMessage());
//        }
//
//
//
//        WriteCommandAction.runWriteCommandAction(project, () -> {
//            try {
//                ktClass.getBody().addBefore(function, ktClass.getBody().getRBrace());
//            }catch (IncorrectOperationException e){
//
//                System.out.println(e.getMessage());
//            }
//        });
* */