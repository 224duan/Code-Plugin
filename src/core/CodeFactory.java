package core;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import utils.MethodStorage;
import utils.MethodUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CodeFactory {


    /**
     * 文件中注入方法
     * percent : 占全部方法的百分比
     * */
    public static void addMethod(@NotNull Project project,@NotNull PsiClass fileClass, double percent){

        if (fileClass.getRBrace() == null){
            return;
        }


        // 需要区分java 和 kotlin
        int l = (int) (fileClass.getMethods().length * percent) + 1;
        List<PsiMethod> methods = getMethods(project, fileClass, l);
        callEachMethod(project, fileClass, methods);

        System.out.println("成功获取到了方法");

        if (methods.size() > 0){
            WriteCommandAction.runWriteCommandAction(project, () -> {
                for (PsiMethod m : methods){
                    fileClass.addBefore(m, fileClass.getRBrace());
                }
            });
        }
    }

    /**
     * 排序，按照是否为 pubilc 排序
     * 代码关联
     * 按照list 顺序关联
     * */
    private static void callEachMethod(@NotNull Project project, @NotNull PsiClass fileClass,@NotNull List<PsiMethod> methods){

        if (methods.size() < 1){
            return;
        }

        PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
        methods.stream().reduce((fm, sm) -> {
            try {
                fm.getBody().add(factory.createStatementFromText(sm.getName() + "();", fileClass));
            }catch (IncorrectOperationException e){
                System.out.println(e.getMessage());
            }
            return sm;
        });
    }


    /**
     * 获取一定数量的方法
     * */
    public static List<PsiMethod> getMethods(@NotNull Project project, @NotNull PsiClass fileClass, int num){

        PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();


        List<String> names = MethodStorage.getInstance().getMethodNames(3);

        final AtomicInteger index = new AtomicInteger(0);
        List<String> createTableCodes = names.stream().map(n -> {

            StringBuilder builder = new StringBuilder();
            if (index.getAndAdd(1) == 0){
                builder.append("public ");
            }else {
                builder.append("private ");
            }
            builder.append("void ");
            builder.append(n);
            builder.append("()");
            builder.append(MethodStorage.getInstance().getJavaImplement());
            return builder.toString();

        }).collect(Collectors.toList());

        ArrayList<PsiMethod> methods = new ArrayList<>();

        // 分两部分，创建名字，增加实现 返回值
        try {

            for (String code : createTableCodes){
                PsiMethod createTableMethod = factory.createMethodFromText(code, fileClass);
                MethodUtils.changeFormatOfMethodName(fileClass, createTableMethod);
                methods.add(createTableMethod);
            }

        }catch (IncorrectOperationException e){
            System.out.println(e.getMessage());
        }

        System.out.println("方法创建成功");

        return methods;
    }


    // 添加关联 invoke
    public static void invokeMethod(@NotNull Project project,@NotNull PsiClass psiClass){

        PsiMethod[] cMethods = psiClass.getMethods();
        PsiField[] fields = psiClass.getAllFields();

        if (cMethods.length > 0 && fields.length > 0){

            PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();

            for (PsiMethod cm : cMethods){

                System.out.println("cm -> " + cm.getName());

                if (cm.getModifierList().hasExplicitModifier(PsiModifier.PUBLIC)){
                    if (MethodUtils.isTargetMethod(psiClass, cm)){

                        for (PsiField field : fields) {

                            System.out.println("  field -> " + field.getName());

                            PsiType expressionType = field.getType();
                            if (expressionType instanceof PsiClassType) {
                                PsiClassType classType = (PsiClassType) expressionType;
                                PsiClass resolvedClass = classType.resolve();
                                if (resolvedClass != null){
                                    PsiMethod[] fMethods = resolvedClass.getMethods();
                                    for (PsiMethod fm : fMethods){
                                        if (MethodUtils.isTargetMethod(resolvedClass, fm)){
                                            // 添加方法
                                            WriteCommandAction.runWriteCommandAction(project, () -> {
                                                try {
                                                    cm.getBody().add(factory.createStatementFromText(field.getName() + "." + fm.getName() + "();", psiClass));
                                                }catch (IncorrectOperationException e){
                                                    System.out.println(" error : " + e.getMessage());
                                                }
                                            });
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
